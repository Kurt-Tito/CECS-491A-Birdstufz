package CECS491B;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class EggHuntWalls {
	
	private BufferedImage[] walls = new BufferedImage[1];
	int x, y = 0;
	
	public EggHuntWalls () 
	{
		try
		{
			walls[0] = ImageIO.read(new File("images/sprites/walltile.png"));

		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public EggHuntWalls (int inx, int iny)
	{
		x = inx;
		y = iny;
	}
	
	public void setLocation (int inx, int iny)
	{
		x = inx;
		y = iny;
	}
	
	public void draw(Graphics2D g2)
	{		
		g2.drawImage(walls[0], x, y, 64, 64, null);
	}

}
