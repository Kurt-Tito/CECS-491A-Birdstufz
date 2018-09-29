package AStarTest;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
public class Skeleton {
	private Point2D location;
	private int width, height;
	private double rotation;
	private double speed;
	private Point2D target;
	private LinkedList<Point> waypoints;
	public Skeleton(double x, double y, int width, int height, double rotationDegrees, double speed)
	{
		location = new Point2D.Double(x,y);
		this.width = width;
		this.height = height;
		this.rotation = rotationDegrees * (double)Math.PI / 180; //degrees to radians;
		this.speed = speed;
		target = (Point2D) location.clone();
		waypoints = new LinkedList<Point>();
	}
	
	public Point2D getLocation()
	{
		return location;
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
	
	public void move(double rotation)
	{
		this.rotation = rotation;
		
		double dx, dy;
		dx = Math.cos(rotation) * speed;
		dy = Math.sin(rotation) * speed * -1;
		moveX(dx);
		moveY(dy);
	}
	
	public void moveTo(double x, double y)
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
	
	public void moveTo(Point2D p)
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
		g2.setColor(Color.red);
		g2.fillOval((int)location.getX() - (width/2), (int)location.getY() - (height/2), width, height);
		g2.setColor(Color.black);
		g2.fillArc((int)location.getX() - (width/2), (int)location.getY() - (height/2), width, height, (int)(rotation * 180 / Math.PI) - 45, 90);
		
		g2.setColor(Color.RED);
		g2.fillOval((int)target.getX() - 5, (int)target.getY() - 5, 10, 10);
		for(Point i: waypoints)
		{
			g2.fillOval((int)i.getX() - 5, (int)i.getY() - 5, 10, 10);
		}
	}
	
	public void updatePath(LinkedList<Point> waypoints)
	{
		if(!waypoints.isEmpty())
		{
			this.waypoints = waypoints;
			//target = waypoints.poll();
		}
	}
	
	public void update()
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
