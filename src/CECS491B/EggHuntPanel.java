package CECS491B;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import CECS491A.GamePanel;

public class EggHuntPanel extends GamePanel{
	
	EggHuntArena arena = new EggHuntArena();
	
	public EggHuntPanel()
	{	
		setPreferredSize(new Dimension(1600, 900));
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
		arena.draw(g2);
	}

}
