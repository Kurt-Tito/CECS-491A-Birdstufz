package CECS491A;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazeCell {
	private static BufferedImage[] images = new BufferedImage[16];
	private BufferedImage image;
	private boolean north, south, east, west;
	private boolean hidden;
	static
	{
		for(int i = 0; i < images.length; i++)
		{
			try
			{
				String path = "images/maze_tile_" + i + ".png";
				images[i] = ImageIO.read(new File(path));
				System.out.println(path);
			}catch(IOException e) {
				
			}
		}
	}
	public MazeCell(boolean n, boolean s, boolean e, boolean w)
	{
		north = n;
		south = s;
		east = e;
		west = w;
		hidden = true;
		setImage();
	}
	public MazeCell()
	{
		north = south = east = west = false;
		hidden = true;
	}

	public boolean hasWall(Direction d)
	{
		if(d == Direction.NORTH)
		{
			return north;
		}
		if(d == Direction.SOUTH)
		{
			return south;
		}
		if(d == Direction.EAST)
		{
			return east;
		}
		if(d == Direction.WEST)
		{
			return west;
		}
		return true;
	}

	public void setWall(Direction d, boolean state)
	{
		if(d == Direction.NORTH)
		{
			north = state;
		}
		else if(d == Direction.SOUTH)
		{
			south = state;
		}
		else if(d == Direction.EAST)
		{
			east = state;
		}
		else if(d == Direction.WEST)
		{
			west = state;
		}
		setImage();
	}
	public boolean isHidden()
	{
		return hidden;
	}
	public void reveal()
	{
		if(hidden)
			hidden = false;
	}
	private void setImage()
	{
		if(!north && !east && !south && !west)
		{
			image = images[0];
		}
		else if(!north && !east && !south && west)
		{
			image = images[1];
		}
		else if(!north && !east && south && !west)
		{
			image = images[2];
		}
		else if(!north && !east && south && west)
		{
			image = images[3];
		}
		else if(!north && east && !south && !west)
		{
			image = images[4];
		}
		else if(!north && east && !south && west)
		{
			image = images[5];
		}
		else if(!north && east && south && !west)
		{
			image = images[6];
		}
		else if(!north && east && south && west)
		{
			image = images[7];
		}
		else if(north && !east && !south && !west)
		{
			image = images[8];
		}
		else if(north && !east && !south && west)
		{
			image = images[9];
		}
		else if(north && !east && south && !west)
		{
			image = images[10];
		}
		else if(north && !east && south && west)
		{
			image = images[11];
		}
		else if(north && east && !south && !west)
		{
			image = images[12];
		}
		else if(north && east && !south && west)
		{
			image = images[13];
		}
		else if(north && east && south && !west)
		{
			image = images[14];
		}
		else if(north && east && south && west)
		{
			image = images[15];
		}
	}
	public void draw(Graphics2D g2, int x, int y, int cellSize)
	{
		g2.drawImage(image, x, y, cellSize, cellSize, null);
	}
	
	@Override
	public String toString()
	{
		String s = "";
		if(north)
			s += "N ";
		if(south)
			s += "S ";
		if(east)
			s += "E ";
		if(west)
			s += "W ";
		return s;
	}
}
