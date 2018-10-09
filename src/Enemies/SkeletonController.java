package Enemies;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import CECS491B.EggHuntArena;
import entities.creatures.Player;

public class SkeletonController {
	private List<Skeleton> skeletons;
	private Player[] players = new Player[2];
	private ObstacleMap grid;
	
	public SkeletonController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		skeletons = new ArrayList<Skeleton>();
		grid = getMap(arena);
		
		boolean x = false;
		for(int i = 0; i < grid.getObstacleGrid().length - 1; i++)
		{
			Point2D loc = grid.getTileCenter(3, i);
			if(!grid.getObstacleGrid()[i][3] && !x)
			{
				skeletons.add(new Skeleton(loc.getX(), loc.getY(), 64, 64, 0, 3));
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
		for(Skeleton i: skeletons)
		{
			doAction(i);
		}
	}
	
	public void doAction(Skeleton skel)
	{
		if(skel.isWaiting())
		{
			Point2D location1 = new Point((int)players[0].getX() + 32, (int)players[0].getY() + 32);
			Point2D location2 = new Point((int)players[1].getX(), (int)players[1].getY());
			Point2D target = location1;
			Point2D targetTile = grid.getTileFromPoint(target);
			System.out.println(location1);
			System.out.println(targetTile);
			if(skel.isWaiting())
			{
				if(!grid.isBlocked(targetTile))
				{
					LinkedList<Point> path = null;
					path = PathFinder.findPath(grid.getObstacleGrid(), (Point)grid.getTileFromPoint(skel.getLocation()), (Point) grid.getTileFromPoint(target));
					
					if(path != null && !path.isEmpty())
					{
						for(Point i: path)
						{
							i.setLocation(grid.getTileCenter(i));
						}
						skel.updatePath(path);
					}
				}
			}
		}
		skel.update();
	}
	
	public void addSkeleton(Skeleton skel)
	{
		skeletons.add(skel);
	}
	
	public void draw(Graphics2D g2)
	{
		for(Skeleton i: skeletons)
		{
			i.draw(g2);
		}
	}
}
