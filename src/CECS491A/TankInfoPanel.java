package CECS491A;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TankInfoPanel extends JPanel{
	private final int HEIGHT = 100, WIDTH = 200;
	private final Rectangle rect1 = new Rectangle(25, 100, 150, 50);
	private final Rectangle rect2 = new Rectangle(25, 600, 150, 50);
	TankMazeGame game;
	public TankInfoPanel(TankMazeGame game)
	{
		this.game = game;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	public void paintComponent(Graphics g)
	{
		TankMazePlayer player = game.getPlayer();
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("Courier", Font.BOLD, 30);
		g2.setFont(font);
		g2.drawString("Player 1", 25, 75);
		g2.drawString("Player 2", 25, 575);
		
		font = new Font("Courier", Font.BOLD, 20);
		g2.drawString("Ammo: " + player.ammo1, 25, 175);
		g2.drawString("Ammo: " + player.ammo2, 25, 675);
		if(player != null)
		{
		g2.setColor(Color.BLACK);
		g2.fillRect(rect1.x, rect1.y, rect1.width, rect1.height);
		g2.fillRect(rect2.x, rect2.y, rect2.width, rect2.height);
		
		g2.setColor(Color.RED);
		g2.fillRect(rect1.x, rect1.y, (int) ((double)rect1.width * (player.health/100.0)), rect1.height);
		g2.fillRect(rect2.x, rect2.y, (int) ((double)rect2.width * (player.health2/100.0)), rect2.height);
		}
		
	}
}
