package CECS491B;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gfx.Assets;


public class EggHuntArenaCell {
	
	private static BufferedImage[] images = new BufferedImage[8];
	private BufferedImage image;
	//private boolean FrontWall, SideWall, Pumpkin, Floor, Tree1, Tree2, Tree3, Tree4;
	private TileType tile;
	
	int x, y = 0;
	
	static {
		try
		{
			images[0] = ImageIO.read(new File("images/sprites/grasstile.png"));
			images[1] = ImageIO.read(new File("images/sprites/tombstone_front.png"));
			images[2] = ImageIO.read(new File("images/sprites/tombstone_side.png"));
			images[3] = ImageIO.read(new File("images/sprites/Pumpkin-sprite.png"));
			images[4] = Assets.trees[0];
			images[5] = Assets.trees[1];
			images[6] = Assets.trees[2];
			images[7] = Assets.trees[3];
			

		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public EggHuntArenaCell () 
	{
		tile = TileType.CLEAR;
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
	
	public void setTile(TileType intile)
	{
		tile = intile;
		
		setImage(tile);
	}
	
	public TileType getTile()
	{
		return tile;
	}
	
	private void setImage(TileType intile)
	{
//		if(Floor)
//			image = images[0];
//		if(FrontWall)
//			image = images[1];
//		if(SideWall)
//			image= images[2];
//		if(Pumpkin)
//			image = images[3];
		
		tile = intile;
		
		if(tile == TileType.CLEAR)
		{
			image = null;
		}
		if(tile == TileType.FRONTWALL)
		{
			image = images[1];
		}
		if(tile == TileType.SIDEWALL)
		{
			image = images[2];
		}
		if(tile == TileType.PUMPKIN)
		{
			image = images[3];
		}
		if(tile == TileType.TREE1)
		{
			image = images[4];
		}
		if(tile == TileType.TREE2)
		{
			image = images[5];
		}
		if(tile == TileType.TREE3)
		{
			image = images[6];
		}
		if(tile == TileType.TREE4)
		{
			image = images[7];
		}
		
	}
	
	public boolean isBlocked()
	{
		if (tile == TileType.CLEAR)
			return false;
		
		return true;
	}
	
	public void draw(Graphics2D g2)
	{		
		g2.drawImage(image, x, y, 64, 64, null);
	}

}
