package AStarTest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Enemies.Map;
import Enemies.PathFinder;
import Enemies.Skeleton;


public class AnimationPanel2 extends JPanel implements Runnable, KeyListener, MouseListener{
	private final int FRAME_RATE = 60;
	private final double X_COORDINATE = 120;
	private final double Y_COORDINATE = 64;
	private final int WIDTH = 50;
	private final int HEIGHT = 50;
	private final double INITIAL_ROTATION = 0;
	private final double LINEAR_SPEED = 2;
	private final int[][] grid = {
			{2,2,2,2,2,2,2,2,2,2},
			{2,0,1,0,0,0,0,0,0,2},
			{2,0,1,0,0,0,0,0,0,2},
			{2,0,1,0,0,0,0,0,0,2},
			{2,0,0,0,0,1,0,0,0,2},
			{2,0,0,0,0,1,1,0,0,2},
			{2,0,1,1,0,0,0,0,0,2},
			{2,0,0,1,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,2},
			{2,2,2,2,2,2,2,2,2,2}};
	
	Skeleton skel;
	Map map;
	
	private boolean[] keys = {false, false, false, false};

	public AnimationPanel2()
	{
		Tile[][] tiles = new Tile[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == 0)
				{
					tiles[i][j] = Tile.CLEAR;
				}
				else if(grid[i][j] == 1)
				{
					tiles[i][j] = Tile.ROCK;
				}
				else if(grid[i][j] == 2)
				{
					tiles[i][j] = Tile.TREE;
				}
			}
		}
		map = new Map(tiles);
		Point2D location = map.getTileCenter(map.getTileFromPoint(X_COORDINATE, Y_COORDINATE));
		skel = new Skeleton(location.getX(), location.getY(), WIDTH, HEIGHT, INITIAL_ROTATION, LINEAR_SPEED);
		
		setPreferredSize(new Dimension(800, 800));
		Thread thread = new Thread(this);
		thread.start();
		setBackground(Color.black);
		addKeyListener(this);
		addMouseListener(this);
	}
	
	public void update()
	{
		skel.update();
	}
	
	public void updateGUI()
	{
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.red);
		map.draw(g2);
		skel.draw(g2);
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		try
		{
			while(true)
			{
				update();
				updateGUI();
				Thread.sleep(1000/FRAME_RATE);
			}
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		AnimationPanel2 panel = new AnimationPanel2();
		frame.add(panel);
		panel.requestFocusInWindow();
		frame.pack();
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
		Point2D endTile = map.getTileFromPoint(arg0.getX(), arg0.getY());
		
		boolean[][] gridMap = new boolean[grid[0].length][grid.length];
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] > 0)
				{
					gridMap[i][j] = true;
				}
			}
		}
		Point2D dest = map.getTileFromPoint(arg0.getX(), arg0.getY());
		LinkedList<Point> path = PathFinder.findPath(gridMap, (Point)map.getTileFromPoint(skel.getLocation()), (Point) dest);
		for(Point i: path)
		{
			System.out.println(i);
			i.setLocation(map.getTileCenter(i));
		}
		skel.updatePath(path);
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
