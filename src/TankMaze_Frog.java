import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class TankMaze_Frog {
	
	private BufferedImage[] img = new BufferedImage[1];
	private int row, col;
	private int cellSize;
	
	public TankMaze_Frog(int incellSize)
	{	
		try 
		{
			String path = "images/tankmaze/sprite_frog1.png";
			System.out.println(path);
			img[0] = ImageIO.read(new File(path));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		cellSize = incellSize;
	}
	
	public void RandomizeFrog()
	{
		Random rand = new Random();
		row = rand.nextInt(9);
		col = rand.nextInt(11);
	}
	
	public boolean checkConsumed(TankMazePlayer player)
	{		
		int a = Arrays.binarySearch(player.getXLocation(), this.getX());
		int b = Arrays.binarySearch(player.getYLocation(), this.getY());
		
		int a2 = Arrays.binarySearch(player.getX2Location(), this.getX());
		int b2 = Arrays.binarySearch(player.getY2Location(), this.getY());
		
		if(a > 0 && b > 0) 
		{	
			player.regainHealthP1();
			return true;
		}
		if(a2 > 0 && b2 > 0)
		{
			player.regainHealthP2();
			return true;
		}
		else
			return false;
	}
	
	public BufferedImage currentImage()
	{
		return img[0];
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public double getX()
	{
		return col*cellSize + cellSize/5;
	}
	
	public double getY()
	{
		return row*cellSize + cellSize/5;
	}
	
	public void concealFrog()
	{
		row = -100;
		col = -100;
	}
	
	public boolean isConcealed()
	{
		if(row > 0 && col >0)
			return false;
		else
			return true;
	}
	
	public void draw(Graphics2D g2, int x, int y)
	{
		g2.drawImage(currentImage(), col*cellSize + cellSize/5, row*cellSize + cellSize/5, null);
	}
	
	
}
