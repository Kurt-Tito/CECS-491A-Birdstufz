package billiardbunnies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import billiard.gfx.Assets;

public class Fireball extends GameEntity {
	private static final int MOVESPEED = 6;
	private static final int RADIUS = 8;
	private static final int MAX_COLLISIONS = 10;
	private double rotation;
	private boolean active;
	private int collisionCount;
	private Color color;
	public Fireball(int x, int y)
	{
		super(x,y);
		color = Color.black;
		this.speed = MOVESPEED;
		active = true;
		//setRotation(Math.random() * 2 * Math.PI);
		//setRotation(Math.random() * 2 * Math.PI);
		collisionCount = 0;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void setRotation(double rotation)
	{
		this.rotation = rotation;
		dx = speed * Math.cos(rotation);
		dy = -speed * Math.sin(rotation);
	}
	
	private void incrementCollisionCounter()
	{
		collisionCount++;
	}
	public int getCollisionCount()
	{
		return collisionCount;
	}
	
	private void reflectX()
	{
		dx = -dx;
	}
	
	private void reflectY()
	{
		dy = -dy;
	}
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		for(LineSegment i: LineSegment.getWalls())
		{
			if(Collisions.CircleLine(i, x,y, RADIUS))
			{
				//setRotation(rotation + Math.PI/4);
				i.setColor(Color.red);
				//System.out.println(i);
				
				if(i.getX1() < i.getX2())
				{
					if(i.getY1() == i.getY2())
					{
						//horizontal
						reflectY();
					}
					else
					{
						//fwd diagonal
						double temp = dx;
						dx = dy;
						dy = temp;
					}
				}
				else
				{
					if(i.getX1() == i.getX2())
					{
						//vertical
						reflectX();
					}
					else
					{
						//horizontal
						double temp = dx;
						dx = -dy;
						dy = -temp;
					}
				}
				incrementCollisionCounter();
				if(getCollisionCount() >= MAX_COLLISIONS)
				{
					setActive(false);
				}
			}
		}
		move();
		
	}
	
	
	public void setActive(boolean f)
	{
		if(f == true)
			active = true;
		if(f == false)
			active = false;
	}
	
	public boolean isActive()
	{
		return active;
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		//g2.setColor(color);
		g2.drawImage(Assets.fireball, (int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2, null);
		//g2.fillOval((int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2);
	}
}
