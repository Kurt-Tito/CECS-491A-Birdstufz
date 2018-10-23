 package Enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


import entities.Orientation;
import gfx.Assets;

public class Zombie extends Monster{
	private final int MAX_HEALTH = 5;
	private final int MOVE_SPEED = 3;
	
	private double rotation;
	private double speed;
	private Point2D target;
	private LinkedList<Point> waypoints;
	private long waitTime;
	
	public Zombie(double x, double y, int width, int height)
	{
		location = new Point2D.Double(x,y);
		this.width = width;
		this.height = height;
		healthRemaining = MAX_HEALTH;
		setRotation(0); //degrees to radians;
		this.speed = MOVE_SPEED;
		target = (Point2D) location.clone();
		waypoints = new LinkedList<Point>();
		waitTime = 0;
	}
	
	public boolean hasMoves()
	{
		if(location.equals(target) && waypoints.isEmpty())
			return false;
		return true;
	}
	public boolean isPaused()
	{
		return waitTime > 0;
	}
	public void pause(long waitTime)
	{
		if(waitTime == 0)
			this.waitTime = waitTime;
	}
	public void setDestination(double x, double y)
	{
		if(location.equals(target))
		{
			target.setLocation(x, y);
		}
	}
	public void setDestination(Point2D p)
	{
		setDestination(p.getX(), p.getY());
	}
	
	private void setRotation(double rotation)
	{
		this.rotation = rotation;
		updateOrientation();
	}
	
	private void move(double rotation)
	{
		setRotation(rotation);
		double dx, dy;
		dx = Math.cos(rotation) * speed;
		dy = Math.sin(rotation) * speed * -1;
		moveX(dx);
		moveY(dy);
	}
	
	private void moveTo(double x, double y)
	{
		if(Point2D.distance(location.getX(), location.getY(), x, y) <= speed)
		{
			location.setLocation(x, y);
		}
		else
		{
			move(Math.atan2(location.getY() - y, x - location.getX()));
		}
	}
	
	private void moveTo(Point2D p)
	{
		moveTo(p.getX(), p.getY());
	}
	
	private void moveX(double dx)
	{
		location.setLocation(location.getX() + dx, location.getY());
	}
	
	private void moveY(double dy)
	{
		location.setLocation(location.getX(), location.getY() + dy);
	}
	
	public void draw(Graphics2D g2)
	{
		g2.drawImage(getImage(), (int) (location.getX() - width/2), (int) (location.getY() - height/2), width, height, null);
	}
	
	public void updatePath(LinkedList<Point> waypoints)
	{
		this.waypoints = waypoints;
		//target = waypoints.poll();
	}
	
	private void updateOrientation()
	{
		double b1, b2, b3, b4;
		b1 = Math.PI / 3;
		b2 = 2 * Math.PI / 3;
		b3 = -2 * Math.PI / 3;
		b4 = -Math.PI / 3;
		if(rotation >= b1 && rotation <= b2)
			orientation = Orientation.NORTH;
		else if(rotation >= b2 && rotation <= Math.PI)
			orientation = Orientation.WEST;
		else if(rotation >= -Math.PI && rotation <= b3)
			orientation = Orientation.WEST;
		else if(rotation >= b3 && rotation <= -Math.PI / 3)
			orientation = Orientation.SOUTH;
		else orientation = Orientation.EAST;
	}
	
	private BufferedImage getImage()
	{
		if(orientation == Orientation.EAST || orientation == Orientation.NORTHEAST || orientation == Orientation.SOUTHEAST)
		{
			return Assets.zombies[0];
		}
		if(orientation == Orientation.NORTH)
		{
			return Assets.zombies[1];
		}
		if(orientation == Orientation.WEST || orientation == Orientation.NORTHWEST || orientation == Orientation.SOUTHWEST)
		{
			return Assets.zombies[2];
		}
		if(orientation == Orientation.SOUTH)
		{
			return Assets.zombies[3];
		}
		return null;
	}
	
	public void update()
	{
		if(waitTime > 0)
		{
			waitTime--;
		}
		else
		{
			moveTo(target);
			if(location.equals(target))
			{
				if(!waypoints.isEmpty())
				{
					target = waypoints.poll();
				}
			}
		}
	}
}
