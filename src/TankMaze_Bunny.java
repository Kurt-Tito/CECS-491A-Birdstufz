import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//---
import java.util.Arrays;
import java.util.Random;

public class TankMaze_Bunny {
	
	private BufferedImage[] bunny_imgs = new BufferedImage[12];
	
	BufferedImage[] walkSouth = new BufferedImage[3];
	BufferedImage[] walkWest = new BufferedImage[3];
	BufferedImage[] walkEast = new BufferedImage[3];
	BufferedImage[] walkNorth = new BufferedImage[3];
	
	
	public int walk = 0;

	private int row, col;
	private int cellSize;
	private int frame;
	private String direction;
	private int delay = 0;

	public TankMaze_Bunny(int cellSize, int x, int y)
	{
		try
		{
			for(int i = 0; i < 12; i++)
			{
				bunny_imgs[i] = ImageIO.read(new File("images/tankmaze/bunny" +String.format("%02d", i) +".png"));
				System.out.println("images/tankmaze/bunny" +String.format("%02d", i) +".png");
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.cellSize = cellSize;
		this.col = x;
		this.row = y;
		//this.direction = dir;
		
		randomize();
		
	}
	
	public BufferedImage[] walkSouth()
	{
		//BufferedImage[] walkSouth = new BufferedImage[3];
		for(int i = 0; i < 3; i++)
			walkSouth[i] = bunny_imgs[i];
		
		return walkSouth;
	}
	
	public BufferedImage[] walkWest()
	{
		//BufferedImage[] walkWest = new BufferedImage[3];
		for(int i = 3; i < 6; i++)
			walkWest[i-3] = bunny_imgs[i];
		
		return walkWest;
	}
	
	public BufferedImage[] walkEast()
	{
		//BufferedImage[] walkEast = new BufferedImage[3];
		for(int i = 6; i < 9; i++)
			walkEast[i-6] = bunny_imgs[i];
		
		return walkEast;
	}
	
	public BufferedImage[] walkNorth()
	{
		//BufferedImage[] walkNorth = new BufferedImage[3];
		for(int i = 9; i < 12; i++)
			walkNorth[i-9] = bunny_imgs[i];
		
		return walkNorth;
	}
	
	
	public void direction(String direction)
	{
		this.direction = direction;
	}
	
	public void draw(Graphics2D g2, int x, int y)
	{	
		if(direction == "South")
		{	
			walkSouth(); //load images
			
			if(frame == walkSouth.length - 1)
				frame = 0;
			else
			{	// Animation Components
				delay++;
				
				if(delay == 25)
				{
					frame++;
					walk += cellSize/8;
					delay = 0;
				}
			}
			
			g2.drawImage(walkSouth[frame], col*cellSize +cellSize/8, row*cellSize + walk, null);
		}
		if(direction == "West")
		{
			walkWest();
			
			if(frame == walkWest.length - 1)
				frame = 0;
			else
			{
				delay++;
				
				if(delay == 25)
				{
					frame++;
					walk -= cellSize/8;
					delay = 0;
				}
			}
			
			g2.drawImage(walkWest[frame], col*cellSize + walk, row*cellSize +cellSize/8, null);
		}
		if(direction == "East")
		{
			walkEast();
			
			if(frame == walkEast.length - 1)
				frame = 0;
			else
			{
				delay++;
				
				if(delay == 25)
				{
					frame++;
					walk += 10;
					delay = 0;
				}
			}
			
			g2.drawImage(walkEast[frame], col*cellSize + walk, row*cellSize +cellSize/8, null);
		}
		if(direction == "North")
		{
			walkNorth();
			
			if(frame == walkNorth.length - 1)
				frame = 0;
			else
			{
				delay++;
				
				if(delay == 25)
				{
					frame++;
					walk -= 10;
					delay = 0;
				}
			}
			
			g2.drawImage(walkNorth[frame], col*cellSize +cellSize/8, row*cellSize + walk, null);
		}
	}
	
	public void randomize()
	{
		Random rand = new Random();
		int dir = rand.nextInt(4 + 1 - 1) + 1;
		
		if(dir == 1)
		{
			direction = "North";
		}
		else if(dir == 2)
		{
			direction = "South";
		}
		else if(dir == 3)
		{
			direction = "West";
		}
		else if(dir == 4)
		{
			direction = "East";
		}
	}
	
	public Double getX()
	{	
		if(direction == "South")
			return (double) col*cellSize+cellSize/8;
		else if(direction == "West")
			return (double) col*cellSize+cellSize/8 +walk;
		else if(direction == "East")
			return (double) col*cellSize+cellSize/8 +walk;
		else if(direction == "North")
			return (double) col*cellSize+cellSize/8;
		return (double) 0;
	}
	
	public Double getY()
	{
		if(direction == "South")
			return (double) row*cellSize+cellSize/8 +walk;
		else if(direction == "West")
			return (double) row*cellSize+cellSize/8;
		else if(direction == "East")
			return (double) row*cellSize+cellSize/8;
		else if(direction == "North")
			return (double) row*cellSize+cellSize/8 +walk;
		return (double) 0;
	}
}
