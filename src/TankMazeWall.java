import java.awt.Rectangle;

public class TankMazeWall extends Rectangle{
	public TankMazeWall(Edge edge, int cellSize, int thickness) 
	{
		if(edge.getNode1().compareTo(edge.getNode2()) < 0)
		{
			if(edge.getNode1().getX() < edge.getNode2().getX())
			{
				setBounds((edge.from.getX()+1) * cellSize - thickness/2, edge.from.getY() * cellSize - thickness/2,
						thickness, cellSize + thickness);
			}
			else
			{
				setBounds(edge.from.getX() * cellSize - thickness/2, (edge.from.getY()+1) * cellSize - thickness/2,
						cellSize + thickness, thickness);
			}
		}
	}
	
	public TankMazeWall(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
}
