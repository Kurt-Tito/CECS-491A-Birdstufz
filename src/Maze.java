import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
public class Maze {
	final Random random = new Random(System.currentTimeMillis());
	int size;
	MazeCell[][] grid;
	public Maze(int size)
	{
		this.size = size;
		grid = new MazeCell[size][size];
		initMaze();
		printMaze();
		
	}
	
	public void initMaze()
	{
		for(int i = 0; i < grid[0].length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				grid[j][i] = new MazeCell();
				if(j == 0)
				{
					grid[j][i].setWall(Direction.NORTH, true);
				}
				if(i == 0)
				{
					grid[j][i].setWall(Direction.WEST, true);
				}
				if(j == grid[0].length - 1)
				{
					grid[j][i].setWall(Direction.SOUTH, true);
				}
				if(i == grid.length - 1)
				{
					grid[j][i].setWall(Direction.EAST, true);
				}
			}
		}
		
		buildMaze();
	}
	
	public void buildMaze()
	{
		buildMaze(0, 0, grid[0].length-1, grid.length-1);
	}
	
	private void buildMaze(int x1, int y1, int x2, int y2)
	{
		System.out.println("(" + x1 + ","+y1 + ") --- (" + x2 + "," + y2 + ")");
		System.out.println((x2-x1) + " " + (y2-y1));
		if(((x2-x1) > 0) && ((y2-y1) > 0))
		{
			int a = weightedRoll(x2-x1+1, y2-y1+1);//random.nextInt(2);
			if(a == 0)
			{
				//Horizontal split
				System.out.print("Horizontal\t");
				int splitRow = random.nextInt(y2-y1) + y1;
				System.out.print("Row: " + splitRow);
				int portalCol = random.nextInt(x2-x1+1) + x1;
				System.out.println(" PCol: " + portalCol);
				for(int k = x1; k <= x2; k++)
				{
					setWallNorthSouth(grid[splitRow][k], grid[splitRow+1][k], true);
				}
				setWallNorthSouth(grid[splitRow][portalCol], grid[splitRow+1][portalCol], false);
				buildMaze(x1, y1, x2, splitRow);
				buildMaze(x1, splitRow+1, x2, y2);
			}
			else
			{
				//Vertical split
				System.out.print("Vertical\t");
				int splitCol = random.nextInt(x2-x1) + x1;
				System.out.print("Row: " + splitCol);
				int portalRow = random.nextInt(y2-y1+1) + y1;
				System.out.println(" PCol: " + portalRow);
				for(int k = y1; k <= y2; k++)
				{
					setWallWestEast(grid[k][splitCol], grid[k][splitCol + 1], true);
				}
				setWallWestEast(grid[portalRow][splitCol], grid[portalRow][splitCol + 1], false);
				buildMaze(x1, y1, splitCol, y2);
				buildMaze(splitCol+1, y1, x2, y2);
			}
			
		}
	}
	
	//Rig roll to make maze more "maze-like"
	public int weightedRoll(int width, int height)
	{
		double val = (double)width / (width + height);
		if(random.nextDouble() < val)
		{
			return 1;
		}
		
		return 0;
	}
	
	public void setWallNorthSouth(MazeCell north, MazeCell south, boolean s)
	{
		north.setWall(Direction.SOUTH, s);
		south.setWall(Direction.NORTH, s);
	}
	
	public void setWallWestEast(MazeCell west, MazeCell east, boolean s)
	{
		west.setWall(Direction.EAST, s);
		east.setWall(Direction.WEST, s);
	}
	
	public void printMaze()
	{
		for(int i = 0; i < grid[0].length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				System.out.println("(" + i + "," + j + "): " + grid[j][i]);
			}
		}
	}
	
	public void drawMaze(Graphics2D g2, int x, int y, int cellSize)
	{
		
		//g2.drawLine(x, y, x+(cellSize*grid[0].length), y); //north maze border
		//g2.drawLine(x, y, x, y+(cellSize*grid.length));	//west border
		//g2.drawLine(x, y+(cellSize*grid.length), x+(cellSize*grid[0].length), y+(cellSize*grid.length));	//south border
		//g2.drawLine(x+(cellSize*grid[0].length), y, x+(cellSize*grid[0].length), y+(cellSize*grid.length)); //east border
		for(int i = 0; i < grid[0].length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				if(!grid[j][i].isHidden())
				{
					g2.setColor(Color.green);
					g2.fillRect(x + (cellSize*i), (cellSize*j), cellSize, cellSize);
					g2.setColor(Color.red);
					g2.setStroke(new BasicStroke(5));
					if(grid[j][i].hasWall(Direction.NORTH))
					{
						//g2.fillRect(x + (cellSize*i + cellSize), (cellSize*j + cellSize), cellSize, cellSize);
						g2.drawLine(cellSize*i+x, cellSize*j+y, cellSize*i+x + cellSize, cellSize*j+y);
					}
					if(grid[j][i].hasWall(Direction.WEST))
					{
						//g2.fillRect(x + (cellSize*i + cellSize), (cellSize*j + cellSize), cellSize, cellSize);
						g2.drawLine(cellSize*i+x, cellSize*j+y, cellSize*i+x, cellSize*j+y + cellSize);
	
					}
					if(grid[j][i].hasWall(Direction.EAST))
					{
						//g2.fillRect(x + (cellSize*i + cellSize), (cellSize*j + cellSize), cellSize, cellSize);
						g2.drawLine(cellSize*i+x + cellSize, cellSize*j+y, cellSize*i+x + cellSize, cellSize*j+y + cellSize);
	
					}
					if(grid[j][i].hasWall(Direction.SOUTH))
					{
						//g2.fillRect(x + (cellSize*i + cellSize), (cellSize*j + cellSize), cellSize, cellSize);
						g2.drawLine(cellSize*i+x, cellSize*j+y+cellSize, cellSize*i+x+cellSize, cellSize*j+y + cellSize);
					}
				}
			}
		}
	}
	
	public void reveal(int x, int y)
	{
		grid[y][x].reveal();
		if(!grid[y][x].hasWall(Direction.NORTH))
		{
			grid[y-1][x].reveal();
		}
		if(!grid[y][x].hasWall(Direction.WEST))
		{
			grid[y][x-1].reveal();
		}
		if(!grid[y][x].hasWall(Direction.EAST))
		{
			grid[y][x+1].reveal();
		}
		if(!grid[y][x].hasWall(Direction.SOUTH))
		{
			grid[y+1][x].reveal();
		}
	}
	
	public boolean blocked(int x, int y, Direction d)
	{
		return grid[y][x].hasWall(d);
	}
}
