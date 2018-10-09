package Enemies;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import AStarTest.Tile;

public class Map {
	private Tile[][] grid;
	private final int TILE_SIZE = 64;
	public Map(Tile[][] grid)
	{
		this.grid = grid;
	}
	
	public void draw(Graphics2D g2)
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == Tile.CLEAR)
				{
					g2.setColor(Color.WHITE);
					
				}
				else if(grid[i][j] == Tile.ROCK)
				{
					g2.setColor(Color.GRAY);
				}
				else if(grid[i][j] == Tile.TREE)
				{
					g2.setColor(Color.GREEN);
				}
				g2.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				g2.setColor(Color.black);
				g2.drawRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
	}
	
	public Point2D getTileCenter(Point2D p)
	{
		return getTileCenter((int)p.getX(), (int)p.getY());
	}
	public Point2D getTileCenter(int x, int y)
	{
		Point2D point = (x >= 0 && y>=0 && x < grid.length && y < grid[0].length) ? 
				new Point(x*TILE_SIZE + TILE_SIZE/2, y*TILE_SIZE + TILE_SIZE/2) : new Point(TILE_SIZE,TILE_SIZE);
		return point;
	}
	
	public Point2D getTileFromPoint(double x, double y)
	{
		Point2D point = (x >= 0 && y>=0 && x < grid.length * TILE_SIZE && y < grid[0].length * TILE_SIZE) ? 
				new Point((int) (x/TILE_SIZE), (int) (y/TILE_SIZE)) : new Point(0,0);
		return point;
	}
	
	public Point2D getTileFromPoint(Point2D p)
	{
		return getTileFromPoint(p.getX(), p.getY());
	}
	
	public int getTileSize()
	{
		return TILE_SIZE;
	}
	
}
