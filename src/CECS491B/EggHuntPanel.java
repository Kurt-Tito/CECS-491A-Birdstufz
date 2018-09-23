package CECS491B;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import CECS491A.GamePanel;

public class EggHuntPanel extends GamePanel{
	
	private EggHuntFloor floor = new EggHuntFloor();
	private EggHuntWalls topborder[] = new EggHuntWalls[25];
	private EggHuntWalls bottomborder[] = new EggHuntWalls[25];
	private EggHuntWalls leftborder[] = new EggHuntWalls[13];
	private EggHuntWalls rightborder[] = new EggHuntWalls[13];
	
	int cellSize = 64;
	int horizontalCells = 25;
	int verticalCells = 13;
	
	public EggHuntPanel()
	{	
		setPreferredSize(new Dimension(1600, 900));
		
		for(int i = 0; i < horizontalCells; i++)
		{
			topborder[i] = new EggHuntWalls();
			topborder[i].setLocation(i*cellSize, 0);
		}
		
		/////////////////////////////////////////
		
		for(int i = 0; i < horizontalCells; i++)
		{
			bottomborder[i] = new EggHuntWalls();
			bottomborder[i].setLocation(i*cellSize, cellSize*verticalCells);
		}
		/////////////////////////////////////////
		
		for(int j = 0; j < verticalCells; j++)
		{
			leftborder[j] = new EggHuntWalls();
			leftborder[j].setLocation(0, j*cellSize);
		}
		
		/////////////////////////////////////////
		
		for(int j = 0; j < verticalCells; j++)
		{
			rightborder[j] = new EggHuntWalls();
			rightborder[j].setLocation(cellSize*(horizontalCells-1), j*cellSize);
		}
		
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw Floor
		floor.draw(g2);
		
		//Draw Borders
		for(int i = 0; i < horizontalCells; i++)
		{
			topborder[i].draw(g2);
		}
		
		for(int i = 0; i < horizontalCells; i++)
		{
			bottomborder[i].draw(g2);
		}
		
		for(int i = 0; i < verticalCells; i++)
		{
			leftborder[i].draw(g2);
		}
		
		for(int i = 0; i < verticalCells; i++)
		{
			rightborder[i].draw(g2);
		}
		
	}

}
