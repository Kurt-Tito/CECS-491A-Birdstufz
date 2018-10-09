package Enemies;

import java.awt.Point;
import java.awt.geom.Point2D;

import CECS491B.EggHuntArena;
import CECS491B.TileType;

public class ObstacleMap {
	private boolean[][] grid;
	private int tileSize;
	public ObstacleMap(EggHuntArena arena)
	{
		grid = new boolean[14][25];
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				grid[i][j] = (arena.getTile(j, i) == TileType.CLEAR) ? false : true;
			}
		}
		tileSize = 64;
	}
	
	public boolean[][] getObstacleGrid()
	{
		return grid;
	}
	
	public boolean isBlocked(int column, int row)
	{
		if(column < 0 || row < 0 || column >= grid[0].length || row >= grid.length)
			return true;
		return grid[row][column];
	}
	
	public boolean isBlocked(Point2D p)
	{
		return isBlocked((int)p.getX(), (int)p.getY());
	}
	
	public ObstacleMap(boolean[][] grid, int tileSize)
	{
		this.grid = grid;
		this.tileSize = tileSize;
	}
	
	public Point2D getTileCenter(Point2D p)
	{
		return getTileCenter((int)p.getX(), (int)p.getY());
	}
	public Point2D getTileCenter(int x, int y)
	{
		Point2D point = (x >= 0 && y>=0 && x < grid[0].length && y < grid.length) ? 
				new Point(x*tileSize + tileSize/2, y*tileSize + tileSize/2) : new Point(tileSize,tileSize);
		return point;
	}
	
	public Point2D getTileFromPoint(double x, double y)
	{
		Point2D point = (x >= 0 && y>=0 && x < grid[0].length * tileSize && y < grid.length * tileSize) ? 
				new Point((int) (x/tileSize), (int) (y/tileSize)) : new Point(0,0);
		return point;
	}
	
	public Point2D getTileFromPoint(Point2D p)
	{
		return getTileFromPoint(p.getX(), p.getY());
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
}
