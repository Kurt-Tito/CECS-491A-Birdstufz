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
	private final int MAX_SKELETONS = 5;
	private final int SPAWN_DELAY = 300;
	private List<Skeleton> skeletons;
	private List<SkeletonProjectile> projectiles;
	private List<Rectangle> shootObstacles;
	private Player[] players = new Player[2];
	private ObstacleMap moveGrid;
	private ObstacleMap shootGrid;
	private int spawnTimer;
	
	public SkeletonController(Player p1, Player p2, EggHuntArena arena)
	{
		players[0] = p1;
		players[1] = p2;
		skeletons = new ArrayList<Skeleton>();
		projectiles = new ArrayList<SkeletonProjectile>();
		
		moveGrid = new ObstacleMap(arena, false);
		shootGrid = new ObstacleMap(arena, true);
		shootObstacles = getObstacles();
		
		spawnTimer = SPAWN_DELAY;
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
		Rectangle[] playerRectangles = new Rectangle[players.length];
		for(int i = 0; i < playerRectangles.length; i++)
		{
			playerRectangles[i] = players[i].getBoundingBox();
		}
		int i = 0;
		while(i < skeletons.size())
		{
			if(skeletons.get(i).getHealth() == 0)
			{
				skeletons.remove(i);
			}
			else
			{
				doAction(skeletons.get(i));
				for(int p = 0; p < playerRectangles.length;p++)
				{
					if(playerRectangles[p].intersects(skeletons.get(i).getBoundingBox()))
					{
						//Player p takes damage
						System.out.println("Skeleton hit");
						players[p].takeSetDamage(5);
						players[p].setInvincible(true);
					}
				}
				i++;
			}
		}
		
		i = 0;
		while(i < projectiles.size())
		{
			projectiles.get(i).tick();
			
			int j = 0;
			boolean collided = false;
			while(!collided && j < playerRectangles.length)
			{
				if(projectiles.get(i).getBoundingBox().intersects(playerRectangles[j]))
				{
					//Player takes damage
					System.out.println("Skeleton attacks Player " + (j+1));
					players[j].takeSetDamage(15);
					players[j].setInvincible(true);
					collided = true;
					projectiles.remove(i);
				}
				else
				{
					j++;
				}
			}
			j = 0;
			while(!collided && j < shootObstacles.size())
			{
				if(projectiles.get(i).getBoundingBox().intersects(shootObstacles.get(j)))
				{
					projectiles.remove(i);
					collided = true;
				}
				j++;
			}
			if(!collided)
			{
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
		if(skeletons.size() < MAX_SKELETONS)
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
			spawnTimer += SPAWN_DELAY;
		}
	}
	
	public void doAction(Skeleton skel)
	{
		skel.update();
		Point2D location1 = new Point((int)players[0].getX() + 32, (int)players[0].getY() + 32);
		Point2D location2 = new Point((int)players[1].getX() + 32, (int)players[1].getY() + 32);
		
		double dist1 = Math.max(Math.abs(location1.getX() - skel.getLocation().getX()), location1.getY() - skel.getLocation().getY());
		double dist2 = Math.max(Math.abs(location2.getX() - skel.getLocation().getX()), location2.getY() - skel.getLocation().getY());
		
		
		Point2D target = (dist1 > dist2) ? location2 : location1;
		SkeletonProjectile projectile = skel.fire(target);
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
