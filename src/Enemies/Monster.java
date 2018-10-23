package Enemies;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import entities.Orientation;

public abstract class Monster {
	protected Point2D location;
	protected int width, height;
	protected Orientation orientation;
	protected int healthRemaining;
	
	public int getHealth()
	{
		return healthRemaining;
	}
	
	public void takeDamage()
	{
		if(healthRemaining > 0)
		{
			healthRemaining--;
		}
	}
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle((int)location.getX() - (width/2), (int) location.getY() - (height/2), width, height);
	}
	
	public Point2D getLocation()
	{
		return location;
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics2D g2);
	
	
}
