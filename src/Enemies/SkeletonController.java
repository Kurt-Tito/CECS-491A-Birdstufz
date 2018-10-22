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

public class SkeletonController {
	private List<Skeleton> skeletons;
	private List<SkeletonProjectile> projectiles;
	private List<Rectangle> shootObstacles;
	private Player[] players = new Player[2];
	private ObstacleMap moveGrid;
	private ObstacleMap shootGrid;
	
	public SkeletonController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		skeletons = new ArrayList<Skeleton>();
		projectiles = new ArrayList<SkeletonProjectile>();
		
		moveGrid = new ObstacleMap(arena, false);
		shootGrid = new ObstacleMap(arena, true);
		shootObstacles = getObstacles();
		
		boolean x = false;
		int sCount = 2;
		while(sCount > 0)
		{
			boolean valid = false;
			int col = 0, row = 0;
			while(!valid)
			{
				Random random = new Random(System.nanoTime());
				col = random.nextInt(moveGrid.getObstacleGrid()[0].length);
				row = random.nextInt(moveGrid.getObstacleGrid().length);
				if(!moveGrid.isBlocked(col, row))
				{
					valid = true;
				}
			}
			Point2D loc = moveGrid.getTileCenter(col, row);
			skeletons.add(new Skeleton(loc.getX(), loc.getY(), 64, 64));
			sCount--;
		}
	}
	
	private List<Rectangle> getObstacles()
	{
		List<Rectangle> obstacles = new ArrayList<Rectangle>();
		boolean[][] grid = shootGrid.getObstacleGrid();
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] == true)
				{
					obstacles.add(new Rectangle(j * shootGrid.getTileSize(), i * shootGrid.getTileSize(), shootGrid.getTileSize(), shootGrid.getTileSize()));
				}
			}
		}
		return obstacles;
	}
	
	public void tick()
	{
		Rectangle playerRectangle = players[0].getBoundingBox();
		for(Skeleton i: skeletons)
		{
			doAction(i);
		}
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).tick();
			if(projectiles.get(i).getBoundingBox().intersects(playerRectangle))
			{
				System.out.println("Player hit");
				projectiles.remove(i);
				i--;
			}
			else
			{
				int j = 0;
				boolean collided = false;
				while(!collided && j < shootObstacles.size())
				{
					if(projectiles.get(i).getBoundingBox().intersects(shootObstacles.get(j)))
					{
						System.out.println("hit");
						projectiles.remove(i);
						i--;
						collided = true;
					}
					j++;
				}
			}
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
