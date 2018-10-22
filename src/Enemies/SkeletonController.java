package Enemies;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import CECS491B.EggHuntArena;
import entities.creatures.Player;

public class SkeletonController {
	private List<Skeleton> skeletons;
	private List<SkeletonProjectile> projectiles;
	private Player[] players = new Player[2];
	private ObstacleMap grid;
	
	public SkeletonController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		skeletons = new ArrayList<Skeleton>();
		projectiles = new ArrayList<SkeletonProjectile>();
		grid = getMap(arena);
		
		boolean x = false;
		int sCount = 2;
		while(sCount > 0)
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
			skeletons.add(new Skeleton(loc.getX(), loc.getY(), 64, 64));
			sCount--;
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
		for(SkeletonProjectile i: projectiles)
		{
			i.tick();
		}
	}
	
	public void doAction(Skeleton skel)
	{
		skel.update();
		Point2D location = new Point((int)players[0].getX() + 32, (int)players[0].getY() + 32);
		SkeletonProjectile projectile = skel.fire(location);
		if(projectile != null)
		{
			projectiles.add(projectile);
		}
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
		for(SkeletonProjectile i: projectiles)
		{
			i.draw(g2);
		}
	}
}
