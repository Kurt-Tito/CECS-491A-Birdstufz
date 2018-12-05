package billiardbunnies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LineSegment {
	public static ArrayList<LineSegment> walls = new ArrayList<LineSegment>();
	private Point2D point1, point2;
	private Color color;
	public LineSegment(Point2D p1, Point2D p2)
	{
		point1 = p1;
		point2 = p2;
		walls.add(this);
		color = Color.black;
	}
	public void setColor(Color c)
	{
		color = c;
	}
	
	public LineSegment(double x1, double y1, double x2, double y2)
	{
		point1 = new Point2D.Double(x1, y1);
		point2 = new Point2D.Double(x2, y2);
		walls.add(this);
		color = Color.black;
	}
	
	public static ArrayList<LineSegment> getWalls()
	{
		return walls;
	}
	public Point2D getP1()
	{
		return point1;
	}
	
	public Point2D getP2()
	{
		return point2;
	}
	
	public double getX1()
	{
		return point1.getX();
	}
	
	public double getX2()
	{
		return point2.getX();
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.black);
		g2.drawLine((int)getX1(), (int)getY1(), (int)getX2(), (int) getY2());
	}
	
	public double getY1()
	{
		return point1.getY();
	}
	
	public double getY2()
	{
		return point2.getY();
	}
	
	public double length()
	{
		return Math.sqrt(Math.pow((point2.getX()-point1.getX()), 2) + Math.pow(point2.getY()-point1.getY(), 2));
	}
	
	public static double length(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2-y1, 2));
	}
}
