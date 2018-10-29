package entities.creatures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Random;

import CECS491B.EggHuntArena;
import Enemies.ObstacleMap;

public class BirdController {
	private final int SPAWN_TIME = 600;
	final int EGG_POINT_VALUE = 1000;
	private ObstacleMap map;
	private Player[] players = new Player[2];
	private Bird bird;
	private BirdEgg egg;
	private Point2D eggDropLocation;
	private int delay;
	private int arenaWidth;
	private int score;
	private boolean hasDropped;
	public BirdController(Player p1, Player p2, EggHuntArena arena)
	{
		map = new ObstacleMap(arena, false);
		delay = SPAWN_TIME;
		players[0] = p1;
		players[1] = p2;
		score = 0;
		hasDropped = false;
		
		arenaWidth = map.getTileSize() * map.getObstacleGrid()[0].length;
	}
	
	public void tick()
	{
		if(egg != null)
		{
			Rectangle[] playerRectangles = new Rectangle[players.length];
			
			for(int i = 0; i < playerRectangles.length; i++)
			{
				playerRectangles[i] = players[i].getBoundingBox();
			}
			int p = 0;
			boolean collided = false;
			while(p < players.length && !collided)
			{
				if(playerRectangles[p].intersects(egg.getBoundingBox()))
				{
					players[p].heal(25);
					egg = null;
					delay += SPAWN_TIME;
					score += EGG_POINT_VALUE;
					collided = true;
				}
				p++;
			}
		}
		if(bird != null)
		{
			if(bird.getX() > arenaWidth)
			{
				bird = null;
			}
			else
			{
				bird.tick();
				if(egg == null && !hasDropped)
				{
					if(map.getTileFromPoint(bird.getX(), bird.getY()).equals(eggDropLocation) )
					{
						Point p = (Point) map.getTileCenter(eggDropLocation);
						egg = new BirdEgg(p.x, p.y);
						hasDropped = true;
					}
				}
			}
		}
		if(bird == null && egg == null)
		{
			if(delay > 0)
			{
				delay--;
			}
			else
			{
				spawnBird();
			}
		}
	}
	
	public void draw(Graphics g)
	{
		if(bird != null)
		{
			bird.render(g);
		}
		if(egg != null)
		{
			egg.draw(g);
		}
	}
	
	public void spawnBird()
	{
		boolean[][] grid = map.getObstacleGrid();
		Random r = new Random(System.nanoTime());
		int attempts = 0;
		boolean valid = false;
		while(attempts < 20 && !valid)
		{
			int x = r.nextInt(grid[0].length - 2) + 1;
			int y = r.nextInt(grid.length - 2) + 1;
			if(!map.isBlocked(x, y))
			{
				eggDropLocation = new Point(x,y);
			}
			
			attempts++;
		}
		Point2D p = map.getTileCenter(0, (int)eggDropLocation.getY());
		bird = new Bird((int)p.getX(), (int)p.getY(), 64, 64);
		hasDropped = false;
	}
	
	public int getScore()
	{
		return score;
	}
}
