package billiardbunnies;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Vector;

public class Collisions {
	public static boolean CircleLine(LineSegment line, double x, double y, double radius)
	{
		double distance;
		
		Point2D n = new Point2D.Double(line.getP2().getX() - line.getP1().getX(), line.getP2().getY() - line.getP1().getY());
		Point2D pa = new Point2D.Double(line.getP1().getX() - x, line.getP1().getY() - y);
		
		double c = dot(n, pa);
		
		if(c > 0)
		{
			distance = Math.sqrt(dot(pa, pa));
			return distance <= radius;
		}
		Point2D bp = new Point2D.Double(x - line.getX2(), y - line.getY2());
		double d = dot(n, bp);
		if(d > 0)
		{
			distance = Math.sqrt(dot(bp, bp));
			return distance <= radius;
		}
		distance = distanceLinePoint(line,x,y);
		return distance <= radius;
	}
	
	private static double dot(Point2D a, Point2D b)
	{
		return (a.getX()*b.getX()) + (a.getY() * b.getY());
	}
	
	private static double distanceLinePoint(LineSegment line, double x, double y)
	{
		double ans = 0;
		double dx = line.getP2().getX() - line.getP1().getX();
		double dy = line.getP2().getY() - line.getP1().getY();
		double det = (line.getP2().getX()*line.getP1().getY()) - (line.getP2().getY() * line.getP1().getX());
		ans = Math.abs((dy * x) - (dx * y) + det) / line.length();
		return ans;
	}
}
