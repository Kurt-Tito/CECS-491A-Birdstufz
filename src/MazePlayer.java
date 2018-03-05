import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazePlayer {
	private BufferedImage[] img = new BufferedImage[4];
	private int row, col;
	private int cellSize;
	private Direction dir;
	public MazePlayer(int cellSize)
	{
		try
		{
			for(int i = 0; i < 4; i++)
			{
				String path = "images/maze_player" + i + ".png";
				System.out.println(path);
				img[i] = ImageIO.read(new File(path));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		row = col = 2;
		this.cellSize = cellSize;
		dir = Direction.NORTH;
	}
	
	public void reset()
	{
		row = col = 2;
		dir = Direction.NORTH;
	}
	
	public void turn(Direction d)
	{
		dir = d;
	}
	
	public BufferedImage currentImage()
	{
		if(dir == Direction.NORTH)
		{
			return img[0];
		}
		if(dir == Direction.SOUTH)
		{
			return img[2];
		}
		if(dir == Direction.EAST)
		{
			return img[1];
		}
		if(dir == Direction.WEST)
		{
			return img[3];
		}
		return null;
	}
	
	public void move(Direction d)
	{
		if(d == Direction.NORTH)
		{
			row--;
		}
		else if(d == Direction.SOUTH)
		{
			row++;
		}
		else if(d == Direction.EAST)
		{
			col++;
		}
		else if(d == Direction.WEST)
		{
			col--;
		}
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void draw(Graphics2D g2, int x, int y)
	{
		//g2.setColor(Color.green);
		//g2.fillOval(col*cellSize + 5, row*cellSize + 5, cellSize-10, cellSize -10);
		g2.drawImage(currentImage(), col*cellSize,row*cellSize,cellSize,cellSize, null);
	}
}
