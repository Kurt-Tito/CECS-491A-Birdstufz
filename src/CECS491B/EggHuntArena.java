package CECS491B;

import java.awt.Graphics2D;

import entities.creatures.Player;

public class EggHuntArena {
	
	private EggHuntFloor floor = new EggHuntFloor();
	private EggHuntWalls topborder[] = new EggHuntWalls[25];
	private EggHuntWalls bottomborder[] = new EggHuntWalls[25];
	private EggHuntWalls leftborder[] = new EggHuntWalls[13];
	private EggHuntWalls rightborder[] = new EggHuntWalls[13];
	
	private Player p1;
	
	int cellSize = 64;
	int horizontalCells = 25;
	int verticalCells = 13;
	
	public EggHuntArena()
	{
		createBorder();
	}
	
	public void createBorder()
	{
		for(int i = 0; i < horizontalCells; i++)
		{
			topborder[i] = new EggHuntWalls();
			topborder[i].setLocation(i*cellSize, 0);
			
			bottomborder[i] = new EggHuntWalls();
			bottomborder[i].setLocation(i*cellSize, cellSize*verticalCells);
		}
		
		for(int j = 0; j < verticalCells; j++)
		{
			leftborder[j] = new EggHuntWalls();
			leftborder[j].setLocation(0, j*cellSize);
			
			rightborder[j] = new EggHuntWalls();
			rightborder[j].setLocation(cellSize*(horizontalCells-1), j*cellSize);
		}
	}
	
	public void draw(Graphics2D g2)
	{		
		//Draw Floor
		floor.draw(g2);
		
		//Draw Borders
		for(int i = 0; i < horizontalCells; i++)
		{
			topborder[i].draw(g2);
			bottomborder[i].draw(g2);
		}
		
		for(int i = 0; i < verticalCells; i++)
		{
			leftborder[i].draw(g2);
			rightborder[i].draw(g2);
		}
		
		p1.render(g2);
		
	}
	
}
