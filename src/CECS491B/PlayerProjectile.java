package CECS491B;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import entities.creatures.Player;


public class PlayerProjectile {
	private static final String IMG_PATH = "images/Bulletr.png";
	private static final String IMG_PATH2 = "images/Bulletb.png";
	private static BufferedImage img, img2;
	private final double SPEED = 14;
	private final double WIDTH = 20;
	private final double HEIGHT = 15;
	private boolean active;
	public double px, py;
	private double rotation;
	private double dx, dy;
	private double[] angles = new double[4];
	private Line2D[] lines = new Line2D[4];
	private Player player;
	
	private LinkedList<Point> collisionpoints = new LinkedList<Point>();
	
	static
	{
		try
		{
			img = ImageIO.read(new File(IMG_PATH));
			img2 = ImageIO.read(new File(IMG_PATH2));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	

	private BufferedImage getTransformedImage()
	{
		AffineTransform transform = new AffineTransform();
		transform.rotate(rotation + (Math.PI/2), img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}
	private BufferedImage getTransformedImage2()
	{
		AffineTransform transform = new AffineTransform();
		transform.rotate(rotation + (Math.PI/2), img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img2, null);
	}
	public PlayerProjectile(double px, double py, double rotation)
	{
		this.px = px;
		this.py = py;
		this.rotation = rotation;
		active = true;
		
		dx = SPEED * Math.cos(rotation);
		dy = SPEED * Math.sin(rotation);
		
		double theta = Math.atan(HEIGHT / WIDTH);
		angles[0] = theta;
		angles[1] = Math.PI - theta;
		angles[2] = Math.PI + theta;
		angles[3] = Math.PI * 2 -theta;
		for(int i = 0; i < angles.length; i++)
		{
			angles[i] += rotation;
		}
		
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
	
	private void move()
	{
		px += dx;
		py += dy;
	}
	
	
	public void update()
	{
		if(active)
		{
			move();
		}
	
	}
	public double getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}
	
	public double getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}
	
	public Rectangle getBoundingBox()
	{
		Rectangle bounds = new Rectangle();
		bounds.setBounds((int)px, (int)py, 5, 5);
		return bounds;
	}
	
	public void draw(Graphics g)
	{
		if(active)
		{
			BufferedImage drawImage = getTransformedImage();
			g.drawImage(drawImage, (int)px - (drawImage.getWidth()/2), (int)py - (drawImage.getHeight()/2), null);
		}
		
	}
	public void draw2(Graphics g)
	{
		if(active)
		{
			BufferedImage drawImage = getTransformedImage2();
			g.drawImage(drawImage, (int)px - (drawImage.getWidth()/2), (int)py - (drawImage.getHeight()/2), null);
		}
		
	}	

}


