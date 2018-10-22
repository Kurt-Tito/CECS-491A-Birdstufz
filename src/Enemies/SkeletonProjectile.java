package Enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import gfx.Assets;

public class SkeletonProjectile {
	private Point2D location;
	private int width, height;
	private double dx, dy;
	private double speed;
	public SkeletonProjectile(double x, double y, int width, int height, double rotation, double speed)
	{
		location = new Point2D.Double(x,y);
		this.width = width;
		this.height = height;
		this.speed = speed;
		dx = Math.cos(rotation) * speed;
		dy = Math.sin(rotation) * speed * -1;
	}
	
	public SkeletonProjectile(double x, double y, double rotation)
	{
		location = new Point2D.Double(x,y);
		this.width = 32;
		this.height = 32;
		this.speed = 8;
		dx = Math.cos(rotation) * speed;
		dy = Math.sin(rotation) * speed * -1;
		
	}
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle((int)location.getX() - (width/2), (int)location.getY() - (height/2), width, height);
	}
	
	private void moveX(double dx)
	{
		location.setLocation(location.getX() + dx, location.getY());
	}
	
	private void moveY(double dy)
	{
		location.setLocation(location.getX(), location.getY() + dy);
	}
	
	public void move()
	{
		moveX(dx);
		moveY(dy);
	}
	
	public void draw(Graphics2D g2)
	{
		//g2.setColor(Color.RED);
		//g2.fillRect((int) (location.getX() - (width/2)), (int) (location.getY() - (height/2)), width, height);
		g2.drawImage(Assets.skeletonProjectile, (int) (location.getX() - width/2), (int) (location.getY() - height/2), width, height, null);
	}
	
	public void tick()
	{
		move();
	}
}
