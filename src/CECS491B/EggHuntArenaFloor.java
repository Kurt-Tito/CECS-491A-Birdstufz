package CECS491B;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import CECS491A.StatusBar;
import gfx.Assets;

public class EggHuntArenaFloor {
	
	BufferedImage floor;
	BufferedImage tombstone, tombstone2;
	private StatusBar statusbar = new StatusBar();
	private HighScore score = new HighScore();
	public EggHuntArenaFloor()
	{
		//floor[0] = ImageLoader.loadImage("/texture/Floor/grasstile_darker.png");
		floor = Assets.floor;
		tombstone = Assets.frontWallb;
		tombstone2 = Assets.sideWallb;
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
	
	public void drawGameOver(Graphics2D g2, int inscore, String highscore) {
		for (int i = 0; i < 1600; i = i + 64)
		{
			for (int j = 0; j < 900; j = j + 64)
		    {
		    	g2.drawImage(floor, i, j, null);
		    }
		}
		g2.drawImage(tombstone, 450, 115, null);
		g2.drawImage(tombstone2, 900, 108, null);
		g2.setFont(new Font("Helvetica", Font.BOLD, 80)); 
		g2.setColor(Color.BLACK);
		g2.drawString("GAME OVER", 545, 455);
		g2.setFont(new Font("Helvetica", Font.PLAIN, 35)); 
		g2.setColor(Color.white);
		if(Integer.parseInt(score.ReadHighScore()) > inscore){
		g2.drawString("Your Score: " + inscore, 665, 525);
		g2.drawString("Highscore: " + highscore, 670, 600);
		}
		else{
		g2.drawString("New High Score!", 670, 525);	
		g2.drawString("Your Score: " + inscore, 665, 600);
		g2.drawString("Highscore: " + highscore, 670, 675);
		}
	}
}
