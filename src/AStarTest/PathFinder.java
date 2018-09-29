package AStarTest;
import java.awt.Point;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {
	public static LinkedList<Point> findPath(boolean[][] grid, Point start, Point end)
	{
		LinkedList<Point> solution = new LinkedList<Point>();
		PriorityQueue<ASNode> frontier = new PriorityQueue<ASNode>();
		boolean[][] explored = new boolean[grid.length][grid[0].length];
		
		frontier.add(new ASNode(0, getEDistance(start, end), start, null));
		while(!frontier.isEmpty())
		{
			ASNode current = frontier.remove();
			if(current.getLocation().equals(end))
			{
				//return solution
				while(current.getPrev() != null)
				{
					solution.add(0, current.getLocation());
					current = current.getPrev();
				}
				return solution;
			}
			explored[current.getY()][current.getX()] = true;
			LinkedList<Point> adjacentNodes = getAdjacentNodes(grid, current.getLocation());
			while(!adjacentNodes.isEmpty())
			{
				Point cLocation = adjacentNodes.remove(0);
				double addCost = (getMDistance(cLocation,current.getLocation()) == 2) ? 1.4 : 1;
				ASNode child = new ASNode(current.getCurrentCost() + addCost, getEDistance(cLocation, end), cLocation, current);
				
				if(!explored[cLocation.y][cLocation.x] && !frontier.contains(child))
				{
					frontier.add(child);
				}
				else if(frontier.contains(child))
				{
					frontier.remove(child);
					frontier.add(child);
				}
			
			}
			
		}
		return null;
	}
	
	private static LinkedList<Point> getAdjacentNodes(boolean[][] grid, Point parent)
	{
		LinkedList<Point> nodes = new LinkedList<Point>();
		
		
		
		//check for sides
		try
		{
			if(!grid[parent.y-1][parent.x])
			{
				nodes.add(new Point(parent.x, parent.y-1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y+1][parent.x])
			{
				nodes.add(new Point(parent.x, parent.y+1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y][parent.x-1])
			{
				nodes.add(new Point(parent.x-1, parent.y));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y][parent.x+1])
			{
				nodes.add(new Point(parent.x+1, parent.y));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		/**
		if(parent.x > 0)
		{
			nodes.add(new Point(parent.x-1, parent.y));
		}
		if(parent.y > 0)
		{
			nodes.add(new Point(parent.x, parent.y-1));
		}
		if(parent.x < grid[0].length - 1)
		{
			nodes.add(new Point(parent.x, parent.y));
		}
		if(parent.y < grid.length - 1)
		{
			nodes.add(new Point(parent.x, parent.y));
		}
		*/
		
		//check for corners
		try
		{
			if(!grid[parent.y][parent.x-1] && !grid[parent.y-1][parent.x] && !grid[parent.y-1][parent.x-1])
			{
				nodes.add(new Point(parent.x-1, parent.y-1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y][parent.x+1] && !grid[parent.y-1][parent.x] && !grid[parent.y-1][parent.x+1])
			{
				nodes.add(new Point(parent.x+1, parent.y-1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y][parent.x-1] && !grid[parent.y+1][parent.x] && !grid[parent.y+1][parent.x-1])
			{
				nodes.add(new Point(parent.x-1, parent.y+1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		try
		{
			if(!grid[parent.y][parent.x+1] && !grid[parent.y+1][parent.x] && !grid[parent.y+1][parent.x+1])
			{
				nodes.add(new Point(parent.x+1, parent.y+1));
			}
		}catch(ArrayIndexOutOfBoundsException e) {}
		
		return nodes;
	}
	private static int getEDistance(Point start, Point end)
	{
		return Math.max(Math.abs(end.x - start.x), Math.abs(end.y - start.y));
	}
	
	private static int getMDistance(Point start, Point end)
	{
		return Math.abs(end.x - start.x)+ Math.abs(end.y - start.y);
		
	}
}
