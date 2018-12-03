package billiardbunnies;
import java.awt.Graphics2D;

public class BunniesGrid {
	private final int WIDTH = 10, HEIGHT = 10;
	private BunniesCell[][] grid = new BunniesCell[HEIGHT][WIDTH];
	
	public BunniesGrid()
	{
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[y][x] = new BunniesCell(x, y);
				if(x > 0 && y > 0 && x < WIDTH-1 && y < HEIGHT-1)
				{
					grid[y][x].addRandomWall();
				}
			}
		}
	}
	
	public void draw(Graphics2D g2)
	{
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[y][x].draw(g2);
			}
		}
	}
}
