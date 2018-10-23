package CECS491B;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import gfx.Assets;

public class EggHuntArenaCell extends Rectangle {
	
	private static BufferedImage[] images = new BufferedImage[8];
	private BufferedImage image;
	private TileType tile;
	
	int x, y = 0;
	
	static {
		images[0] = Assets.floor;
		images[1] = Assets.frontWall;
		images[2] = Assets.sideWall;
		images[3] = Assets.pumpkin;
		images[4] = Assets.trees[0];
		images[5] = Assets.trees[1];
		images[6] = Assets.trees[2];
		images[7] = Assets.trees[3];	
	}
	
	public EggHuntArenaCell () 
	{
		tile = TileType.CLEAR;
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
		
		setBounds(x, y, 64, 64);
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
		
//		if(this.isBlocked())
//		{
//			g2.setColor(Color.WHITE);
//			g2.drawRect(x, y, 64, 64);
//		}
		
	}

}
