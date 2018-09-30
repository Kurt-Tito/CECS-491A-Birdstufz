package CECS491B;

import java.awt.Graphics2D;
import java.util.Random;

public class EggHuntArena {
	
	private EggHuntFloor floor = new EggHuntFloor();

//	int cellSize = 64;
	int horizontalCells = 25;
	int verticalCells = 14;
	
	EggHuntArenaCell[][] grid;
	
	public EggHuntArena()
	{
		initTiles();
		createBorder();
		createObstacles();
	}
	
	public void initTiles()
	{		
		grid = new EggHuntArenaCell[horizontalCells][verticalCells];
		
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 0; j < grid.length; j++)
			{
				grid[j][i] = new EggHuntArenaCell();
				grid[j][i].setLocation(j*64, i*64);
				
				if (j == 0)
				{
					//grid[j][i].setLocation(0, i*64);
				}
				if (i == 0)
				{
					//grid[j][i].setLocation(j*64, 0);
				}
				if (j == grid[0].length)
				{
					//grid[j][i].setLocation(grid[0].length*64, i*64);
				}
				if (i == grid.length)
				{
					//grid[j][i].setLocation(j*64, grid.length*64);
				}
				
			}
		}
	}
	
	public void createBorder()
	{
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 0; j < grid.length; j++)
			{
				grid[j][0].setTile("FrontWall"); //top wall
				grid[0][i].setTile("SideWall"); //left wall
				grid[j][verticalCells-1].setTile("FrontWall"); //bottom wall
				grid[horizontalCells-1][i].setTile("SideWall"); //right wall
			}
		}
	}
	
	public void createObstacles()
	{
		for (int i = 0; i < 25; i++)
		{
			Random rand = new Random();
			int rng_x = rand.nextInt((23-1) + 1) + 1;
			int rng_y = rand.nextInt((12-1) + 1) + 1;
			
			grid[rng_x][rng_y].setTile("Pumpkin");
		}
	}
	
	public void draw(Graphics2D g2)
	{		
		//Draw Floor
		floor.draw(g2);
		
		for(int i = 0; i < grid[0].length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{	
				grid[j][i].draw(g2);
			}
		}
	}
	
}
