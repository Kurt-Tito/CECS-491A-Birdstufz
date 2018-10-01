package CECS491B;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EggHuntFloor {
	
	BufferedImage[] floor = new BufferedImage[1];
	
	public EggHuntFloor()
	{
		try {
			floor[0] = ImageIO.read(new File("images/sprites/dirt.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2)
	{		
		for (int i = 0; i < 1600; i = i + 64)
		{
			for (int j = 0; j < 900; j = j + 64)
		    {
		    	g2.drawImage(floor[0], i, j, null);
		    }
		}
	}
}
