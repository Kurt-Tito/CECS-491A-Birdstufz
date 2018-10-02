package CECS491B;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import gfx.Assets;

public class EggHuntArenaFloor {
	
	BufferedImage floor;
	
	public EggHuntArenaFloor()
	{
		//floor[0] = ImageLoader.loadImage("/texture/Floor/grasstile_darker.png");
		floor = Assets.floor;
	}
	
	public void draw(Graphics2D g2)
	{		
		for (int i = 0; i < 1600; i = i + 64)
		{
			for (int j = 0; j < 900; j = j + 64)
		    {
		    	g2.drawImage(floor, i, j, null);
		    }
		}
	}
}
