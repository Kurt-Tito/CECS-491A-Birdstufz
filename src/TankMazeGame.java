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
	
	private TankMaze_Bunny bunny;
	
	private int FrogTimer = 0;
	private int FrogSpawnTime;
	
	public TankMazeGame()
	{
		t = new Timer(10, this);
	}
	
	public void reset()
	{
		maze = new TankMaze();
		TankProjectile.addWallColliders(maze.getMazeWalls());
		t.restart();
		try
		{
			player = new TankMazePlayer(this);
			frog = new TankMaze_Frog(90);
			bunny = new TankMaze_Bunny(90, 5, 4, "South"); //(cellSize, col, row, direction)
			
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
		
		if(bunny.walk <= 900)
			bunny.draw(g2, 0, 0);
	}

	public TankMazePlayer getPlayer()
	{
		return player;
	}
	
	public TankMaze getMaze()
	{
		return maze;
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
		if(player.endGame() == true){
		System.out.println("Black tank wins");
		FrogTimer = 0;
		t.stop();
		}
		if(player.endGame2() == true){
		System.out.println("White tank wins");	
		FrogTimer = 0;
		t.stop();
		}
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
		
		//System.out.println(bunny.walk);
	}
}
