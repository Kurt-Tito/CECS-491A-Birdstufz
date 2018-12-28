package billiardbunnies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import billiard.gfx.Assets;
import billiard.player.billiardPlayer;

public class Bunny extends GameEntity{
	private static final int MOVESPEED = 4;
	private static final int RADIUS = 20;
	private static final int SHOOT_DELAY = 10;
	private static final double p = .75;
	private BunniesGrid map;
	private billiardPlayer player;
	private Random rand = new Random(System.nanoTime());
	private Point2D dest;
	private int shootDelay;
	private Direction lastDirection;
	private ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
	public Bunny(int x, int y, BunniesGrid map, billiardPlayer player) {
		super(x, y);
		dest = new Point2D.Double();
		shootDelay = SHOOT_DELAY;
		this.map = map;
		this.player = player;
		Point2D p2 = map.getCellCenter(x,y);
		this.x = p2.getX();
		this.y = p2.getY();
		getRandomDestination();
	}
	
	public void move()
	{
		if(LineSegment.length(x, y, dest.getX(), dest.getY()) >= MOVESPEED)
		{
			super.move();
		}
		else
		{
			x = dest.getX();
			y = dest.getY();
		}
	}
	
	private boolean atDestination()
	{
		return x == dest.getX() && y == dest.getY();
	}
	
	private void updateMovement()
	{
		double rotation = Math.atan2(dest.getY() - y, dest.getX() - x);
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
				fb.setColor(Color.red);
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
				
				for(int l = 0; l < 48; l++)
				{
					for (int k = 0; k < 4; k++)
					{
						if(fireballs.get(i).getX() == player.getX()+l)
						{
							if(fireballs.get(i).getY() == player.getY()+k)
							{
								System.out.println("hit");
								player.takeDamage();
								fireballs.get(i).setActive(false);
							}
						}
					}
				}
					
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
		Point location = map.getCell(x, y);
		Direction d = lastDirection;
		boolean valid = false;
		if(Math.random() < .8)
		{
			if(d != null && map.isTraversable(location, d))
			{
				valid = true;
			}
		}
		while(!valid)
		{
			d = Direction.values()[rand.nextInt(Direction.values().length)];
			
			if(map.isTraversable(location, d))
			{
				valid = true;
			}
		}
		Point c = map.getAdjacentCell(location.x,location.y, d);
		Point2D p = map.getCellCenter(c.x, c.y);
		dest.setLocation(p);
		updateMovement();
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.green);
		//g2.fillOval((int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2);
		g2.drawImage(Assets.playerBunny, (int) x-RADIUS, (int) y-RADIUS, RADIUS*2, RADIUS*2, null);
		for(Fireball fb: fireballs)
		{
			fb.draw(g2);
		}
	}

}
