package billiardbunnies;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

import billiardbunnies.BunniesCell.CellWall;

public class BunniesGrid {
	private final int WIDTH = 10, HEIGHT = 10;
	private BunniesCell[][] grid = new BunniesCell[HEIGHT][WIDTH];
	
	public BunniesGrid()
	{
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[y][x] = new BunniesCell(x, y);
				if(x > 0 && y > 0 && x < WIDTH-1 && y < HEIGHT-1)
				{
					grid[y][x].addRandomWall();
				}
			}
		}
	}
	
	public boolean isTraversable(int x, int y, Direction d)
	{
		BunniesCell current = grid[y][x];
		BunniesCell target = current;
		if(current.hasWall(CellWall.FORWARD_DIAGONAL) || current.hasWall(CellWall.BACK_DIAGONAL))
		{
			return false;
		}
		switch(d)
		{
			case UP:
				if(y == 0)
				{
					return false;
				}
				target = grid[y-1][x];
				break;
			case LEFT:
				if(x == 0)
				{
					return false;
				}
				target = grid[y][x-1];
				break;
			case DOWN:
				if(y == grid.length-1)
				{
					return false;
				}
				target = grid[y+1][x];
				break;
			case RIGHT:
				if(x == grid.length-1)
				{
					return false;
				}
				target = grid[y][x+1];
				break;
		}
		if(target.hasWall(CellWall.FORWARD_DIAGONAL) || target.hasWall(CellWall.BACK_DIAGONAL))
		{
			return false;
		}
		switch(d)
		{
			case UP:
				return !current.hasWall(CellWall.TOP);
			case LEFT:
				return !current.hasWall(CellWall.LEFT);
			case DOWN:
				return !target.hasWall(CellWall.TOP);
			case RIGHT:
				return !target.hasWall(CellWall.LEFT);
		}
		return false;
	}
	
	public boolean isTraversable(Point p, Direction d)
	{
		return isTraversable(p.x, p.y, d);
	}
	
	public Point2D getCellCenter(int x, int y)
	{
		return new Point2D.Double(x * BunniesCell.SIZE + BunniesCell.SIZE/2, y * BunniesCell.SIZE + BunniesCell.SIZE/2);
	}
	
	public Point getCell(double x, double y)
	{
		return new Point((int) x / BunniesCell.SIZE, (int) y / BunniesCell.SIZE);
	}
	
	public Point getAdjacentCell(int x, int y, Direction d)
	{
		switch(d)
		{
			case UP:
				if(y == 0)
				{
					return new Point(x, HEIGHT - 1);
				}
				return new Point(x, y-1);
			case LEFT:
				if(x == 0)
				{
					return new Point(WIDTH - 1, y);
				}
				return new Point(x-1, y);
			case DOWN:
				if(y == HEIGHT-1)
				{
					return new Point(x, 0);
				}
				return new Point(x, y+1);
			case RIGHT:
				if(x == WIDTH-1)
				{
					return new Point(0, y);
				}
				return new Point(x+1, y);
		}
		return new Point(x,y);
	}
	
	public void draw(Graphics2D g2)
	{
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[y][x].draw(g2);
			}
		}
	}
}
