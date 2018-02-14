import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MazePanel extends JPanel implements MouseListener, KeyListener{
	Maze maze;
	MazePlayer player;
	public MazePanel()
	{
		maze = new Maze(10);
		player = new MazePlayer(64);
		maze.reveal(0, 0);
		setBackground(Color.black);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, 0, getWidth(), getHeight());
		maze.drawMaze(g2, 0, 0, 64);
		player.draw(g2, 0, 0);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		Direction d;
		if(arg0.getKeyCode() == 37)
		{
			d = Direction.WEST;
			player.turn(d);
			if(!maze.blocked(player.getCol(), player.getRow(), Direction.WEST))
			{
				player.move(Direction.WEST);
			}
		}
		else if(arg0.getKeyCode() == 38)
		{
			d = Direction.NORTH;
			player.turn(d);
			if(!maze.blocked(player.getCol(), player.getRow(), Direction.NORTH))
			{
				player.move(Direction.NORTH);
			}
		}
		else if(arg0.getKeyCode() == 39)
		{
			d = Direction.EAST;
			player.turn(d);
			if(!maze.blocked(player.getCol(), player.getRow(), Direction.EAST))
			{
				player.move(Direction.EAST);
			}
		}
		else if(arg0.getKeyCode() == 40)
		{
			d = Direction.SOUTH;
			player.turn(d);
			if(!maze.blocked(player.getCol(), player.getRow(), Direction.SOUTH))
			{
				player.move(Direction.SOUTH);
			}
		}
		maze.reveal(player.getCol(), player.getRow());
		System.out.println(arg0.getKeyCode());
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

