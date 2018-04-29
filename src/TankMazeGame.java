import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.Timer;

public class TankMazeGame implements ActionListener {
	private Timer t;
	private TankMaze maze;
	private TankMazePlayer player;
	private TankMaze_Frog frog;
	
	private int FrogTimer = 0;
	private int FrogSpawnTime;
	
	public TankMazeGame()
	{
		t = new Timer(10, this);
	}
	
	public void reset()
	{
		maze = new TankMaze();
		t.restart();
		try
		{
			player = new TankMazePlayer();
			frog = new TankMaze_Frog(90);
			
			frog.concealFrog();
			FrogTimer = 0;
			FrogSpawnTime = generateFrogSpawnTime();
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
	
	public int generateFrogSpawnTime()
	{	//generates a random time for Frog to Spawn bounded between 100 and 2000 
		Random rand = new Random();
		int msec = rand.nextInt(2000 - 100 + 1) + 100;
		
		return msec;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.actionPerformed(arg0);
		
		if(frog.checkConsumed(player) == true)
		{	
			System.out.println("Frog EATEN");
			frog.concealFrog();
			FrogSpawnTime = generateFrogSpawnTime();
			FrogTimer = 0;
		}
		
		if(frog.isConcealed())
			FrogTimer++;
		
		if(FrogTimer == FrogSpawnTime) 
		{
			FrogTimer = 0;
			frog.RandomizeFrog();
		}
		
	}
}
