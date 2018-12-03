package billiardbunnies;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Circle {
	private Point2D center;
	private double radius;
	
	public Circle(Point2D c, double r)
	{
		center = c;
		radius = r;
	}
	
	public Point2D getLocation()
	{
		return center;
	}
	
	public double getRadius()
	{
		return radius;
	}
}
