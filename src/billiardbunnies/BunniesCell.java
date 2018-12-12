package billiardbunnies;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class BunniesCell {
	public static final int SIZE = 80;
	private static Random rnd = new Random(System.nanoTime());
	private int x, y;
	private LineSegment[] walls;
	public BunniesCell(int x, int y)
	{
		this.x = x * SIZE + SIZE/2;
		this.y = y * SIZE + SIZE/2;
		
		walls = new LineSegment[CellWall.values().length];
		
	}
	
	public boolean hasWall(CellWall w)
	{
		return walls[w.ordinal()] != null;
	}
	
	public void addRandomWall()
	{
		double p = .6;
		double r = rnd.nextDouble();
		if(r < p)
		{
			addWall(CellWall.values()[rnd.nextInt(CellWall.values().length)]);
		}
	}
	
	public void addWall(CellWall w)
	{
		if(walls[w.ordinal()] == null)
		{
			switch(w)
			{
			case TOP:
				walls[w.ordinal()] = new LineSegment(x - (SIZE/2), y - (SIZE/2), x + (SIZE/2), y - (SIZE/2));
				break;
			case LEFT:
				walls[w.ordinal()] = new LineSegment(x - (SIZE/2), y - (SIZE/2), x - (SIZE/2), y + (SIZE/2));
				break;
			case FORWARD_DIAGONAL:
				walls[w.ordinal()] = new LineSegment(x - (SIZE/2), y - (SIZE/2), x + (SIZE/2), y + (SIZE/2));
				break;
			case BACK_DIAGONAL:
				walls[w.ordinal()] = new LineSegment(x + (SIZE/2), y - (SIZE/2), x - (SIZE/2), y + (SIZE/2));
				break;
			}
		}
	}
	
	public void removeWall(CellWall w)
	{
		walls[w.ordinal()] = null;
	}
	
	public enum CellWall
	{
		TOP, LEFT, FORWARD_DIAGONAL, BACK_DIAGONAL
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.lightGray);
		//g2.drawRect(x - SIZE/2, y - SIZE/2, SIZE, SIZE);
		for(LineSegment line: walls)
		{
			if(line != null)
			{
				line.draw(g2);
			}
		}
	}
}
