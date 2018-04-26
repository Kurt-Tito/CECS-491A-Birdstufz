import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class TankInfoPanel extends JPanel{
	private final int HEIGHT = 100, WIDTH = 200;
	TankMazeGame game;
	public TankInfoPanel(TankMazeGame game)
	{
		this.game = game;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
	}
}
