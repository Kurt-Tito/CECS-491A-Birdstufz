import java.awt.Color;
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

import javax.imageio.ImageIO;

public class TankProjectile {
	private static final String IMG_PATH = "images/tankmaze/sprite_carrot1.png";
	private static BufferedImage img;
	private final double SPEED = 5;
	private final double WIDTH = 20;
	private final double HEIGHT = 15;
	private boolean active;
	private double x, y;
	private double rotation;
	private double dx, dy;
	private Point2D[] points = new Point2D[4];
	private double[] angles = new double[4];
	private Line2D[] lines = new Line2D[4];
	
	private int collisionPoint_x;
	private int collisionPoint_y;
	
	private boolean spawnBunny = false;
	
	static
	{
		try
		{
			img = ImageIO.read(new File(IMG_PATH));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static ArrayList<TankMazeWall> walls = new ArrayList<TankMazeWall>();
	
	private BufferedImage getTransformedImage()
	{
		AffineTransform transform = new AffineTransform();
		transform.rotate(rotation + (Math.PI/2), img.getWidth() / 2, img.getHeight() / 2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}
	public TankProjectile(double x, double y, double rotation)
	{
		this.x = x;
		this.y = y;
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
		f();
	}
	public static void addWallColliders(ArrayList<TankMazeWall> maze)
	{
		walls = maze;
		System.out.println(walls.size() + "WALLS ASDFSDSAFDSAF");
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	private void move()
	{
		x += dx;
		y += dy;
	}
	private void f()
	{
		double mgn = Math.sqrt(Math.pow(.5 *HEIGHT, 2) + Math.pow(.5 *WIDTH, 2));
		for(int i = 0; i < points.length; i++)
		{
			points[i] = new Point((int)(Math.cos(angles[i]) * mgn + x), (int) (Math.sin(angles[i]) * mgn + y));
		}
		
		lines[0] = new Line2D.Double(points[0], points[1]);
		lines[1] = new Line2D.Double(points[1], points[2]);
		lines[2] = new Line2D.Double(points[2], points[3]);
		lines[3] = new Line2D.Double(points[3], points[0]);
	}
	
	public void update()
	{
		if(active)
		{
			move();
			checkCollisions();
			f();
		}
		System.out.println("Hello");
		
	}
	
	public void draw(Graphics2D g2)
	{
		if(active)
		{
			g2.setColor(Color.red);
			BufferedImage drawImage = getTransformedImage();
			g2.drawImage(drawImage, (int)x - (drawImage.getWidth()/2), (int)y - (drawImage.getHeight()/2), null);
			/**
			for(int i = 0; i < lines.length; i++)
			{
				drawLine(g2, lines[i]);
			}
			*/
		}
		
	}
	
	private void drawLine(Graphics2D g2, Line2D l)
	{
		g2.drawLine((int)l.getX1(), (int)l.getY1(), (int)l.getX2(), (int)l.getY2());
	}
	
	public boolean isColliding(Rectangle r)
	{
		if(r.contains(x, y))
		{
			return true;
		}
		for(int i = 0; i < lines.length; i++)
		{
			if(r.intersectsLine(lines[i])) {
				return true;
			}
		}
		return false;
	}
	
	public void checkCollisions()
	{
		boolean isColliding = false;
		for(int i = 0; i < walls.size(); i++)
		{
			if(isColliding(walls.get(i)))
			{
				isColliding = true;
			}
		}
		if(isColliding)
		{
			active = false;
			
			collisionPoint_x = (int) x;
			collisionPoint_y = (int) y;
			
			spawnBunny = true;
		}
			
	}
	
	public Line2D getProjectile(int i)
	{
		return lines[i];
	}
	
	
	public int getProjectileLength()
	{
		return lines.length;
	}
	
	public void setActive(boolean f)
	{
		if(f == true)
			active = true;
		if(f == false)
			active = false;
	}
	
	public int getCollisionPointX()
	{
		return collisionPoint_x;
	}
	
	public int getCollisionPointY()
	{
		return collisionPoint_y;
	}
}
