package entities.creatures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import gfx.Assets;

public class BirdEgg {
	final int WIDTH = 32, HEIGHT = 32;
	private Point2D location;
	private int width,height;
	
	public BirdEgg(int x, int y)
	{
		location = new Point(x, y);
		width = WIDTH;
		height = HEIGHT;
	}
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle((int)location.getX()- width/2, (int)location.getY()- height/2, width, height);
	}
	
	public Point2D getLocation()
	{
		return location;
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(Assets.egg, (int) (location.getX() - width/2), (int) (location.getY() - height/2), width, height, null);
	}
	
}
