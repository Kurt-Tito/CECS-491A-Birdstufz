import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class MazeExit {
	private BufferedImage[] img = new BufferedImage[1];
	private int row, col;
	private int cellSize;
	
	public MazeExit(int cellSize)
	{
		try
		{
			String path = "images/maze_exit.png";
			System.out.println(path);
			img[0] = ImageIO.read(new File(path));

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//row = col = 3;
		RandomizeExit();
		this.cellSize = cellSize;
	}
	
	public void RandomizeExit()
	{
		Random rand = new Random();
		row = rand.nextInt(9);
		col = rand.nextInt(9);
	}
	
	//boolean check if player and maze_exit have the same coordinates
	public boolean checkFinish(MazePlayer player)
	{
		if((this.getCol() == player.getCol()) && (this.getRow() == player.getRow()))
			return true;
		else
			return false;
	}
	
	public BufferedImage currentImage()
	{
		return img[0];
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
