package billiardbunnies;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

public class BunniesCell {
	private static int size = 80;
	private static Random rnd = new Random(System.nanoTime());
	private int x, y;
	private boolean top, left, fwdDiagonal, backDiagonal;
	private ArrayList<LineSegment> walls;
	public BunniesCell(int x, int y)
	{
		this.x = x * size + size/2;
		this.y = y * size + size/2;
		
		walls = new ArrayList<LineSegment>();
		
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
		switch(w)
		{
		case TOP:
			top = true;
			break;
		case LEFT:
			left = true;
			break;
		case FORWARD_DIAGONAL:
			fwdDiagonal = true;
			break;
		case BACK_DIAGONAL:
			backDiagonal = true;
			break;
		default:
			break;
		}
		updateWalls();
	}
	
	private void updateWalls()
	{
		
		walls.clear();
		if(top)
		{
			walls.add(new LineSegment(x - (size/2), y - (size/2), x + (size/2), y - (size/2)));
		}
		if(left)
		{
			walls.add(new LineSegment(x - (size/2), y - (size/2), x - (size/2), y + (size/2)));
		}
		if(fwdDiagonal)
		{
			walls.add(new LineSegment(x - (size/2), y - (size/2), x + (size/2), y + (size/2)));
		}
		if(backDiagonal)
		{
			walls.add(new LineSegment(x + (size/2), y - (size/2), x - (size/2), y + (size/2)));
		}
	}
	
	public void removeWall(CellWall w)
	{
		switch(w)
		{
		case TOP:
			top = false;
			break;
		case LEFT:
			left = false;
			break;
		case FORWARD_DIAGONAL:
			fwdDiagonal = false;
			break;
		case BACK_DIAGONAL:
			backDiagonal = false;
			break;
		default:
			break;
		}
	}
	
	public enum CellWall
	{
		TOP, LEFT, FORWARD_DIAGONAL, BACK_DIAGONAL
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.lightGray);
		//g2.drawRect(x - size/2, y - size/2, size, size);
		for(LineSegment line: walls)
		{
			line.draw(g2);
		}
	}
}
