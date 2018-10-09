package Enemies;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import CECS491B.EggHuntArena;
import entities.creatures.Player;

public class ZombieController {
	private List<Zombie> zombies;
	private Player[] players = new Player[2];
	private ObstacleMap grid;
	
	public ZombieController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		zombies = new ArrayList<Zombie>();
		grid = getMap(arena);
		
		boolean x = false;
		for(int i = 0; i < grid.getObstacleGrid().length - 1; i++)
		{
			Point2D loc = grid.getTileCenter(3, i);
			if(!grid.getObstacleGrid()[i][3] && !x)
			{
				zombies.add(new Zombie(loc.getX(), loc.getY(), 64, 64, 0, 3));
				x = true;
			}
			
		}
	}
	
	private ObstacleMap getMap(EggHuntArena arena)
	{
		ObstacleMap map = new ObstacleMap(arena);
		return map;
	}
	
	public void tick()
	{
		for(Zombie i: zombies)
		{
			doAction(i);
		}
	}
	
	public void doAction(Zombie zomb)
	{
		if(zomb.isWaiting())
		{
			Point2D location1 = new Point((int)players[0].getX() + 32, (int)players[0].getY() + 32);
			Point2D location2 = new Point((int)players[1].getX(), (int)players[1].getY());
			Point2D target = location1;
			Point2D targetTile = grid.getTileFromPoint(target);
			if(zomb.isWaiting())
			{
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
