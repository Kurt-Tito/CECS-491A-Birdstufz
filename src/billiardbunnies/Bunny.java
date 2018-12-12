package billiardbunnies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import billiard.player.billiardPlayer;

public class Bunny extends GameEntity{
	private static final int MOVESPEED = 4;
	private static final int RADIUS = 20;
	private static final int SHOOT_DELAY = 5;
	private static final double p = .75;
	private BunniesGrid map;
	private billiardPlayer player;
	private Random rand = new Random(System.nanoTime());
	private Point2D d;
	private int shootDelay;
	private ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
	public Bunny(int x, int y, BunniesGrid map, billiardPlayer player) {
		super(x, y);
		d = new Point2D.Double();
		shootDelay = SHOOT_DELAY;
		this.map = map;
		this.player = player;
		getRandomDestination();
	}
	
	public void move()
	{
		System.out.println(d);
		if(LineSegment.length(x, y, d.getX(), d.getY()) >= MOVESPEED)
		{
			super.move();
		}
		else
		{
			x = d.getX();
			y = d.getY();
		}
	}
	
	private boolean atDestination()
	{
		return x == d.getX() && y == d.getY();
	}
	
	private void updateMovement()
	{
		double rotation = Math.atan2(d.getY() - y, d.getX() - x);
		dx = MOVESPEED * Math.cos(rotation);
		dy = MOVESPEED * Math.sin(rotation);
	}

	@Override
	public void tick() {
		if(atDestination())
		{
			if(shootDelay == 0)
			{
				Fireball fb = new Fireball((int) x, (int)y);
				fb.setRotation(Math.atan2(-(player.getCenterY() - y), player.getCenterX() - x));
				fireballs.add(fb);
				
				shootDelay += SHOOT_DELAY;
			}
			else
			{
				getRandomDestination();
				shootDelay--;
			}
		}
		else
		{
			move();
		}
		for(int i = 0; i < fireballs.size(); i++)
		{
			if(fireballs.get(i).isActive())
			{
				fireballs.get(i).tick();
			}
			else
			{
				fireballs.remove(i);
				i--;
			}
		}
		
	}
	
	private void getRandomDestination()
	{
		boolean valid = false;
		while(!valid)
		{
			Direction d = Direction.values()[rand.nextInt(Direction.values().length)];
			Point location = map.getCell(x, y);
			if(map.isTraversable(location, d))
			{
				Point c = map.getAdjacentCell(location.x,location.y, d);
				Point2D p = map.getCellCenter(c.x, c.y);
				this.d.setLocation(p);
				updateMovement();
				valid = true;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillOval((int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2);
		for(Fireball fb: fireballs)
		{
			fb.draw(g2);
		}
	}

}
