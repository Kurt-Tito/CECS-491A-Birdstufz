package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import entities.Orientation;
import gfx.Assets;
public class Skeleton {
	private Point2D location;
	private int width, height;
	private long reloadTimer;
	private Orientation orientation;
	public Skeleton(double x, double y, int width, int height)
	{
		location = new Point2D.Double(x,y);
		this.width = width;
		this.height = height;
		reloadTimer = 0;
		orientation = Orientation.SOUTH;
	}
	
	public boolean canShoot()
	{
		return reloadTimer == 0;
	}
	public Point2D getLocation()
	{
		return location;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.drawImage(getImage(), (int) (location.getX() - width/2), (int) (location.getY() - height/2), width, height, null);
		/**
		g2.setColor(Color.GREEN);
		g2.fillOval((int)location.getX() - (width/2), (int)location.getY() - (height/2), width, height);
		g2.setColor(Color.black);
		g2.fillArc((int)location.getX() - (width/2), (int)location.getY() - (height/2), width, height, (int)(rotation * 180 / Math.PI) - 45, 90);
		*/
		/**
		g2.setColor(Color.RED);
		g2.fillOval((int)target.getX() - 5, (int)target.getY() - 5, 10, 10);
		for(Point i: waypoints)
		{
			g2.fillOval((int)i.getX() - 5, (int)i.getY() - 5, 10, 10);
		}
		*/
	}

	public void update()
	{
		if(reloadTimer > 0)
		{
			reloadTimer--;
		}
	}
	
	public SkeletonProjectile fire(Point2D p)
	{
		double rotation = Math.atan2(location.getY() - p.getY(), p.getX() - location.getX());
		updateOrientation(rotation);
		if(canShoot())
		{
			SkeletonProjectile projectile = new SkeletonProjectile(location.getX(), location.getY(), rotation);
			reloadTimer += System.nanoTime() % 120 + 120;
			return projectile;
		}
		return null;
	}
	
	private void updateOrientation(double rotation)
	{
		double b1, b2, b3, b4;
		b1 = Math.PI / 4;
		b2 = 3 * Math.PI / 4;
		b3 = -3 * Math.PI / 4;
		b4 = -Math.PI / 4;
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
			return Assets.skeletons[0];
		}
		if(orientation == Orientation.NORTH)
		{
			return Assets.skeletons[1];
		}
		if(orientation == Orientation.WEST || orientation == Orientation.NORTHWEST || orientation == Orientation.SOUTHWEST)
		{
			return Assets.skeletons[2];
		}
		if(orientation == Orientation.SOUTH)
		{
			return Assets.skeletons[3];
		}
		return null;
	}
}
