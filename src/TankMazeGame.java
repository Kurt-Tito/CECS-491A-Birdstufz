import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

public class TankMazeGame implements ActionListener {
	private Timer t = new Timer(10, this);
	private TankMaze maze;
	private TankMazePlayer player;
	public TankMazeGame()
	{
		maze = new TankMaze();
		try
		{
			player = new TankMazePlayer();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		t.start();
	}
	
	public void draw(Graphics2D g2)
	{
		maze.draw(g2);
		player.draw(g2);
	}

	public TankMazePlayer getPlayer()
	{
		return player;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
