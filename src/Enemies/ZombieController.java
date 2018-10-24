package Enemies;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import CECS491B.EggHuntArena;
import entities.creatures.Player;

public class ZombieController {
	private final int MAX_ZOMBIES = 10;
	private final int SPAWN_DELAY = 180;
	private List<Zombie> zombies;
	private Player[] players = new Player[2];
	private ObstacleMap grid;
	private int spawnTimer;
	
	public ZombieController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		zombies = new ArrayList<Zombie>();
		grid = getMap(arena);
		
		spawnTimer = SPAWN_DELAY;
	}
	
	private ObstacleMap getMap(EggHuntArena arena)
	{
		ObstacleMap map = new ObstacleMap(arena, false);
		return map;
	}
	
	public void tick()
	{
		Rectangle[] playerRectangles = new Rectangle[players.length];
		for(int i = 0; i < playerRectangles.length; i++)
		{
			playerRectangles[i] = players[i].getBoundingBox();
		}
		int i = 0;
		while(i < zombies.size())
		{
			if(zombies.get(i).getHealth() == 0)
			{
				zombies.remove(i);
			}
			else
			{
				doAction(zombies.get(i));
				for(int p = 0; p < playerRectangles.length;p++)
				{
					if(playerRectangles[p].intersects(zombies.get(i).getBoundingBox()))
					{
						//Player p takes damage
						System.out.println("Zombie attacks Player " + (p+1));
						
						players[p].takeSetDamage(5);
						players[p].setInvincible(true);
					}
				}
				i++;
			}
		}
		if(spawnTimer > 0)
		{
			spawnTimer--;
		}
		else
		{
			spawn();
		}
	}
	
	public void spawn()
	{
		if(zombies.size() < MAX_ZOMBIES)
		{
			boolean valid = false;
			int col = 0, row = 0;
			while(!valid)
			{
				Random random = new Random(System.nanoTime());
				col = random.nextInt(grid.getObstacleGrid()[0].length);
				row = random.nextInt(grid.getObstacleGrid().length);
				if(!grid.isBlocked(col, row))
				{
					valid = true;
				}
			}
			Point2D loc = grid.getTileCenter(col, row);
			zombies.add(new Zombie(loc.getX(), loc.getY(), 64, 64));
			spawnTimer += SPAWN_DELAY;
		}
	}
	
	public void doAction(Zombie zomb)
	{
		if(!zomb.isPaused())
		{
			if(!zomb.hasMoves())
			{
				Point2D location1 = new Point((int)players[0].getX() + 32, (int)players[0].getY() + 32);
				Point2D location2 = new Point((int)players[1].getX() + 32, (int)players[1].getY() + 32);
				
				double dist1 = Math.max(Math.abs(location1.getX() - zomb.getLocation().getX()), location1.getY() - zomb.getLocation().getY());
				double dist2 = Math.max(Math.abs(location2.getX() - zomb.getLocation().getX()), location2.getY() - zomb.getLocation().getY());
				
				
				Point2D target = (dist1 > dist2) ? location2 : location1;
				Point2D targetTile = grid.getTileFromPoint(target);
				if(!grid.isBlocked(targetTile))
				{
					LinkedList<Point> path = null;
					path = PathFinder.findPath(grid.getObstacleGrid(), (Point)grid.getTileFromPoint(zomb.getLocation()), (Point) grid.getTileFromPoint(target));
					
					if(path != null && !path.isEmpty())
					{
						for(Point i: path)
						{
							i.setLocation(grid.getTileCenter(i));
						}
						zomb.updatePath(path);
					}
					else
					{
						zomb.pause(60);
					}
				}
			}
		}
		zomb.update();
	}
	
	public void addZombie(Zombie zomb)
	{
		zombies.add(zomb);
	}
	
	public void draw(Graphics2D g2)
	{
		for(Zombie i: zombies)
		{
			i.draw(g2);
		}
	}
}
