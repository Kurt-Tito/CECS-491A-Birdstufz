package CECS491B;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import CECS491A.GamePanel;
import game.Game;

public class EggHuntArenaPanel extends GamePanel{
	
	Game EggHuntGame;
	
	public EggHuntArenaPanel()
	{	
		setPreferredSize(new Dimension(1600, 900));
		EggHuntGame = new Game("EggHuntGame", 1600, 900);
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		EggHuntGame.start();
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
	}

}
