package Enemies;
import java.awt.Point;

public class ASNode implements Comparable<ASNode>{
	private double currentCost; //cost to reach this node from start
	private double remainingCost; //distance from destination according to remainingCosteuristic
	private Point loc;
	private ASNode prev;
	public ASNode(double currentCost, double remainingCost, Point loc, ASNode prev)
	{
		this.currentCost = currentCost;
		this.remainingCost = remainingCost;
		this.loc = loc;
		this.prev = prev;
	}
	
	public ASNode getPrev()
	{
		return prev;
	}
	
	public double getCurrentCost()
	{
		return currentCost;
	}
	
	public double getRemainingCost()
	{
		return remainingCost;
	}
	
	public double getTotalCost()
	{
		return currentCost + remainingCost;
	}
	
	public Point getLocation()
	{
		return loc;
	}
	
	public int getX()
	{
		return loc.x;
	}
	public int getY()
	{
		return loc.y;
	}

	@Override
	public int compareTo(ASNode o) {
		if(this.getTotalCost() > o.getTotalCost())
			return 1;
		if(this.getTotalCost() < o.getTotalCost())
			return -1;
		return 0;
	}
	
	public boolean equals(ASNode o)
	{
		return this.loc.equals(o.loc);
	}
}
