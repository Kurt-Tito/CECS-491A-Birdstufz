import java.awt.Graphics2D;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

public class TankMazeGame implements ActionListener {
	private Timer t;
	private TankMaze maze;
	private TankMazePlayer player;
	private TankMaze_Frog frog;
	private int FrogTimer = 0;
	private boolean concealed = false;
	
	public TankMazeGame()
	{
		t = new Timer(10, this);
		frog = new TankMaze_Frog(90);
	}
	
	public void reset()
	{
		maze = new TankMaze();
		t.restart();
		try
		{
			player = new TankMazePlayer();
			frog.concealFrog();
			
			FrogTimer = 0;
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
		
		
		frog.draw(g2, 0, 0);
	}

	public TankMazePlayer getPlayer()
	{
		return player;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.actionPerformed(arg0);
		
		if(frog.checkConsumed(player) == true)
		{	
			System.out.println("Frog EATEN");
			frog.concealFrog();
		}
		
		if(frog.isConcealed())
			FrogTimer++;
		
		if(FrogTimer == 1000) {
			FrogTimer = 0;
			frog.RandomizeFrog();
		}
		
		//System.out.println("Frog Timer = " +FrogTimer);
	}
}
