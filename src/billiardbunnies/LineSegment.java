package billiardbunnies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class LineSegment {
	private Point point1, point2;
	public LineSegment(Point p1, Point p2)
	{
		point1 = p1;
		point2 = p2;
	}
	
	public LineSegment(int x1, int y1, int x2, int y2)
	{
		point1 = new Point(x1, y1);
		point2 = new Point(x2, y2);
	}
	public Point getP1()
	{
		return point1;
	}
	
	public Point getP2()
	{
		return point2;
	}
	
	public int getX1()
	{
		return point1.x;
	}
	
	public int getX2()
	{
		return point2.x;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.black);
		g2.drawLine(getX1(), getY1(), getX2(), getY2());
	}
	
	public int getY1()
	{
		return point1.y;
	}
	
	public int getY2()
	{
		return point2.y;
	}
	
	public double length()
	{
		return Math.sqrt(Math.pow((point2.getX()-point1.getX()), 2) + Math.pow(point2.getY()-point1.getY(), 2));
	}
}
