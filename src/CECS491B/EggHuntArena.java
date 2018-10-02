package CECS491B;

import java.awt.Graphics2D;
import java.util.Random;

import AStarTest.Tile;

public class EggHuntArena {
	
	int horizontalCells = 25;
	int verticalCells = 14;
	
	int TreeWeightedAmount = 25;
	int PumpkinWeightedAmount = 35;
	
	int rng_x, rng_y;
	
	EggHuntArenaCell[][] grid;
	
	public EggHuntArena()
	{
		initTiles();
		createBorder();
		createObstacles();
		
		System.out.println(getTile(10,6));
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
			}
		}
	}
	
	public void createBorder()
	{
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 1; j < grid.length-1; j++)
			{	
				if (i == 6)
					i = 8;
				
				grid[j][0].setTile(TileType.FRONTWALL); //top wall
				grid[0][i].setTile(TileType.SIDEWALL); //left wall
				grid[j][verticalCells-1].setTile(TileType.FRONTWALL); //bottom wall
				grid[horizontalCells-1][i].setTile(TileType.SIDEWALL); //right wall
			}
		}
	}
	
	public void createObstacles()
	{	
		for (int i = 0; i < TreeWeightedAmount; i++)
		{
			ObstacleRandomizer();
			
			if (!(grid[rng_x][rng_y].isBlocked() 
					|| grid[rng_x+1][rng_y].isBlocked() 
					|| grid[rng_x][rng_y+1].isBlocked() 
					|| grid[rng_x+1][rng_y+1].isBlocked()))
			{	
				if((rng_x != 1 && rng_y != 6) || (rng_x != 1 && rng_y != 7)) //for entrance grid(1,6) && grid(1,7)
					grid[rng_x][rng_y].setTile(TileType.TREE1);
			}
			else
			{
				//System.out.println("Tile Blocked");
			}
			
			if (grid[rng_x][rng_y].getTile() == TileType.TREE1)
			{
				grid[rng_x+1][rng_y].setTile(TileType.TREE2);
				grid[rng_x][rng_y+1].setTile(TileType.TREE3);
				grid[rng_x+1][rng_y+1].setTile(TileType.TREE4);
			}
		}
		
		for (int i = 0; i < PumpkinWeightedAmount; i++)
		{			
			ObstacleRandomizer();
			
			if (!grid[rng_x][rng_y].isBlocked())
			{
				if((rng_x != 1 && rng_y != 6) || (rng_x != 1 && rng_y != 7)) //for entrance grid(1,6) && grid(1,7)
					grid[rng_x][rng_y].setTile(TileType.PUMPKIN);
			}
			else
			{
				//System.out.println("Tile Blocked");
			}
		}
	}
	
	public void ObstacleRandomizer()
	{
		int hmax = horizontalCells - 2;
		int vmax = verticalCells - 2;
		
		Random rand = new Random();
		rng_x = rand.nextInt((hmax-2) + 1) + 1;
		rng_y = rand.nextInt((vmax-2) + 1) + 1;
	}
	
	public TileType getTile(int j, int i)
	{
		return grid[j][i].getTile();
	}
	
	public void draw(Graphics2D g2)
	{				
		for(int i = 0; i < grid[0].length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{	
				grid[j][i].draw(g2);
			}
		}
	}
	
}
