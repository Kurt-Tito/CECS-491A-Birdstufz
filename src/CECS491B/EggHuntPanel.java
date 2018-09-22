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
	
	public EggHuntPanel()
	{	
		setPreferredSize(new Dimension(1600, 900));
		
		for(int i = 0; i < 25; i++)
		{
			topborder[i] = new EggHuntWalls();
		}
		
		for(int i = 0; i < 25; i++)
		{
			topborder[i].setLocation(0 +i*64, 0);
		}
		
		/////////////////////////////////////////
		
		for(int i = 0; i < 25; i++)
		{
			bottomborder[i] = new EggHuntWalls();
		}
		
		for(int i = 0; i < 25; i++)
		{
			bottomborder[i].setLocation(0 +i*64, 64*13);
		}
		
		/////////////////////////////////////////
		
		for(int j = 0; j < 13; j++)
		{
			leftborder[j] = new EggHuntWalls();
		}
		
		for(int j = 0; j < 13; j++)
		{
			leftborder[j].setLocation(0, j*64);
		}
		
		/////////////////////////////////////////
		
		for(int j = 0; j < 13; j++)
		{
			rightborder[j] = new EggHuntWalls();
		}
		
		for(int j = 0; j < 13; j++)
		{
			rightborder[j].setLocation(64*24, j*64);
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
		floor.draw(g2);
		
		for(int i = 0; i < 25; i++)
		{
			topborder[i].draw(g2);
		}
		
		for(int i = 0; i < 25; i++)
		{
			bottomborder[i].draw(g2);
		}
		
		for(int i = 0; i < 13; i++)
		{
			leftborder[i].draw(g2);
		}
		
		for(int i = 0; i < 13; i++)
		{
			rightborder[i].draw(g2);
		}
		
	}

}
