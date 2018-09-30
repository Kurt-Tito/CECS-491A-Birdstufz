package CECS491B;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class EggHuntArenaCell {
	
	private static BufferedImage[] images = new BufferedImage[4];
	private BufferedImage image;
	private boolean FrontWall, SideWall, Pumpkin, Floor;
	
	int x, y = 0;
	
	static {
		try
		{
			images[0] = ImageIO.read(new File("images/sprites/grasstile.png"));
			images[1] = ImageIO.read(new File("images/sprites/tombstone_front.png"));
			images[2] = ImageIO.read(new File("images/sprites/tombstone_side.png"));
			images[3] = ImageIO.read(new File("images/sprites/Pumpkin-sprite.png"));
			

		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public EggHuntArenaCell () 
	{
		FrontWall = SideWall = Pumpkin = Floor = false;
		//setImage();
	}
	
	public EggHuntArenaCell (int inx, int iny)
	{
		x = inx;
		y = iny;
	}
	
	public void setLocation (int inx, int iny)
	{
		x = inx;
		y = iny;
	}
	
	public int getXLoc ()
	{
		return x;
	}
	
	public int getYLoc ()
	{
		return y;
	}
	
	public void setTile(String tile)
	{
		if(tile == "Floor")
		{
			Floor = true;
			Pumpkin = false;
			FrontWall = false;
			SideWall = false;
		}
		if(tile == "FrontWall")
		{
			Floor = false;
			Pumpkin = false;
			FrontWall = true;
			SideWall = false;
		}
		if(tile == "SideWall")
		{
			Floor = false;
			Pumpkin = false;
			FrontWall = false;
			SideWall = true;
		}
		if(tile == "Pumpkin")
		{
			Floor = false;
			Pumpkin = true;
			FrontWall = false;
			SideWall = false;
		}
		
		setImage();
	}
	
	private void setImage()
	{
		if(Floor)
			image = images[0];
		if(FrontWall)
			image = images[1];
		if(SideWall)
			image= images[2];
		if(Pumpkin)
			image = images[3];
	}
	
	public void draw(Graphics2D g2)
	{		
		g2.drawImage(image, x, y, 64, 64, null);
	}

}
