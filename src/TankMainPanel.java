import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TankMainPanel extends JPanel{
	private final int TILE_SIZE = 90;
	private final int WALL_THICKNESS = 10;
	private int size;
	private TankMaze maze;
	public TankMainPanel()
	{
		setPreferredSize(new Dimension(990, 990));
		maze = new TankMaze();
		size = maze.size;
	}
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		
		for(int i = 0; i < size; i++)
		{
			//g2.drawLine(i* TILE_SIZE, 0, i* TILE_SIZE + 75, 0);
			//g2.drawLine(i* TILE_SIZE, TILE_SIZE * size, i* TILE_SIZE + 75, TILE_SIZE * size);
			//g2.drawLine(0, i* TILE_SIZE, 0, i* TILE_SIZE + TILE_SIZE);
			//g2.drawLine(TILE_SIZE * size, i* TILE_SIZE, TILE_SIZE * size, i* TILE_SIZE + TILE_SIZE);
			
			g2.fillRect(i*TILE_SIZE - WALL_THICKNESS/2, 0 - WALL_THICKNESS/2, TILE_SIZE + WALL_THICKNESS/2, WALL_THICKNESS);
			g2.fillRect(i*TILE_SIZE - WALL_THICKNESS/2, TILE_SIZE * size - WALL_THICKNESS/2, TILE_SIZE + WALL_THICKNESS, WALL_THICKNESS);
			
			g2.fillRect(0 - WALL_THICKNESS/2, i*TILE_SIZE - WALL_THICKNESS/2, WALL_THICKNESS, TILE_SIZE + WALL_THICKNESS/2);
			g2.fillRect(TILE_SIZE * size - WALL_THICKNESS/2, i*TILE_SIZE - WALL_THICKNESS/2, WALL_THICKNESS, TILE_SIZE + WALL_THICKNESS);
		}
		
		for(int i = 0; i < maze.walls.size(); i++)
		{
			Edge wall = maze.walls.get(i);
			if(wall.from.getX() == wall.to.getX())
			{
				//South
				System.out.println("Hello");
				g2.setColor(Color.black);
				g2.fillRect(wall.from.getX() * TILE_SIZE - WALL_THICKNESS/2, (wall.from.getY()+1) * TILE_SIZE - WALL_THICKNESS/2, TILE_SIZE + WALL_THICKNESS, WALL_THICKNESS);
				//g2.setColor(Color.green);
				//g2.drawLine(wall.from.getX() * TILE_SIZE, (wall.from.getY() + 1)* TILE_SIZE, wall.to.getX()* TILE_SIZE + TILE_SIZE, (wall.from.getY() + 1) * TILE_SIZE);
			}
			else
			{
				//West
				System.out.println("Hi");
				g2.setColor(Color.black);
				g2.fillRect((wall.from.getX()+1) * TILE_SIZE - WALL_THICKNESS/2, wall.from.getY() * TILE_SIZE - WALL_THICKNESS/2,  WALL_THICKNESS, TILE_SIZE + WALL_THICKNESS);
				//g2.setColor(Color.green);
				//g2.drawLine((wall.from.getX()+1) * TILE_SIZE, wall.from.getY()* TILE_SIZE, (wall.from.getX()+1)* TILE_SIZE, wall.to.getY()* TILE_SIZE + TILE_SIZE);
			}
			
		}
		
	}
	
	
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new TankMainPanel());
		frame.pack();
	}
}

