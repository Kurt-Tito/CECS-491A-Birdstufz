package entities.creatures;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import CECS491B.EggHuntArena;
import CECS491B.EggHuntArenaCell;
import CECS491B.HighScore;
import CECS491B.PlayerHealthBar;
import CECS491B.PlayerProjectile;
import game.Game;
import gfx.Animation;
import gfx.Assets;
import input.KeyManager;

public class Player extends Creature {

	private Game game;
	private PlayerProjectile projectile;
	private ArrayList<PlayerProjectile> projectiles;
	private PlayerHealthBar health;
	private Animation idle, meleeAttack, rifleMove, rifleShoot;
	
	private Rectangle nextMoveUP = new Rectangle();
	private Rectangle nextMoveDWN = new Rectangle();
	private Rectangle nextMoveLFT = new Rectangle();
	private Rectangle nextMoveRT = new Rectangle();
	
	private Rectangle UP = new Rectangle();
	private Rectangle DOWN = new Rectangle();
	private Rectangle LEFT = new Rectangle();
	private Rectangle RIGHT = new Rectangle();

    private int counter = 0, active = 0;
	private int direction;
	private int id; //1 for player1, 2 for player2
	private EggHuntArenaCell[][] grid;
	private boolean invincible;
	private boolean bulletActive;
	
	private long firingTimer;
	private long firingDelay;
	
	public Player(Game game, int x, int y, EggHuntArena arena, int id) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
		this.grid = arena.getGrid();
		this.id = id;
		
		direction = 7;
		
		idle = new Animation(30, Assets.idle);
		meleeAttack = new Animation(50, Assets.meleeAttack);
		rifleMove = new Animation(20, Assets.rifleMove);
		rifleShoot = new Animation(100, Assets.rifleShoot);
		
		health = new PlayerHealthBar(id);
		
		projectiles = new ArrayList<PlayerProjectile>();
		firingTimer = System.nanoTime();
		firingDelay = 200; //change firing delay in ms
	}
	

	public void tick() {
		//animations
		idle.tick();
		meleeAttack.tick();
		rifleMove.tick();
		rifleShoot.tick();
	
		getInput();
		move();
		for (PlayerProjectile p: projectiles) {
			if(p.isActive()) {
				p.update();
			}
		}
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		nextMoveUP.setBounds(x+3, y-3-(int)speed, 48, 48);
		nextMoveDWN.setBounds(x+3, y+3+(int)speed, 48, 48);
		nextMoveLFT.setBounds(x-3-(int)speed, y+3, 48, 48);
		nextMoveRT.setBounds(x+3+(int)speed, y+3, 48, 48);
		

       
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 0; j < grid.length; j++)
			{
				
				if (id == 1)
				{
					if (game.getKeyManager().up && (!nextMoveUP.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						yMove = -speed;
					if (game.getKeyManager().up && (nextMoveUP.intersects(grid[j][i])) && grid[j][i].isBlocked())
						y += speed;
					
					if (game.getKeyManager().down  && (!nextMoveDWN.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						yMove = speed;
					if (game.getKeyManager().down  && (nextMoveDWN.intersects(grid[j][i])) && grid[j][i].isBlocked())
						y -= speed;
					
					if (game.getKeyManager().left && (!nextMoveLFT.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						xMove = -speed;
					if (game.getKeyManager().left && (nextMoveLFT.intersects(grid[j][i])) && grid[j][i].isBlocked())
						x += speed;
					
					if (game.getKeyManager().right && (!nextMoveRT.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						xMove = speed;
					if (game.getKeyManager().right && (nextMoveRT.intersects(grid[j][i])) && grid[j][i].isBlocked())
						x -= speed;
				}
				else if (id == 2)
				{
					if (game.getKeyManager().d_up && (!nextMoveUP.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						yMove = -speed;
					if (game.getKeyManager().d_up && (nextMoveUP.intersects(grid[j][i])) && grid[j][i].isBlocked())
						y += speed;
					
					if (game.getKeyManager().d_down  && (!nextMoveDWN.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						yMove = speed;
					if (game.getKeyManager().d_down  && (nextMoveDWN.intersects(grid[j][i])) && grid[j][i].isBlocked())
						y -= speed;
					
					if (game.getKeyManager().d_left && (!nextMoveLFT.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						xMove = -speed;
					if (game.getKeyManager().d_left && (nextMoveLFT.intersects(grid[j][i])) && grid[j][i].isBlocked())
						x += speed;
					
					if (game.getKeyManager().d_right && (!nextMoveRT.intersects(grid[j][i])) && !grid[j][i].isBlocked())
						xMove = speed;
					if (game.getKeyManager().d_right && (nextMoveRT.intersects(grid[j][i])) && grid[j][i].isBlocked())
						x -= speed;
				}
				
				if(projectiles.size() > 0) {
					for (int a = 0; a < projectiles.size(); a++) {
						UP.setBounds((int)projectiles.get(a).getPx(), (int)projectiles.get(a).getPy()-(int)speed, 1, 1);
						DOWN.setBounds((int)projectiles.get(a).getPx(), (int)projectiles.get(a).getPy()+(int)speed, 1, 1);
						LEFT.setBounds((int)projectiles.get(a).getPx()-(int)speed,(int)projectiles.get(a).getPy(), 1, 1);
						RIGHT.setBounds((int)projectiles.get(a).getPx()+(int)speed, (int)projectiles.get(a).getPy(), 1, 1);
						
						if(UP.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
							projectiles.get(a).setActive(false);
						}
						if(DOWN.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
							projectiles.get(a).setActive(false);
						}
						if(LEFT.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
							projectiles.get(a).setActive(false);
						}
						if(RIGHT.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
							projectiles.get(a).setActive(false);
						}
						if(!projectiles.get(a).isActive()) {
							projectiles.remove(a);
						}
					}
				}
			}
		}
		

		if (id == 1)
		{
			if(game.getKeyManager().space){		
				long elapsed = (System.nanoTime() - firingTimer) / 1000000;
				if (elapsed > firingDelay) {
					switch(getDirection()){
					case 1:
						projectile = new PlayerProjectile(48 + x, 16 + y, -Math.PI/2);
						bulletActive = true;
						break;//up
					case 2:
						projectile = new PlayerProjectile(22 + x, 16 + y, (-3*Math.PI)/4);
						bulletActive = true;
						break;//upleft
					case 3:
						projectile = new PlayerProjectile(16 + x, 16 + y, Math.PI);
						bulletActive = true;
						break;//left
					case 4:
						projectile = new PlayerProjectile(16 + x, 48 + y, (3*Math.PI)/4);
						bulletActive = true;
						break;//downleft
					case 5:
						projectile = new PlayerProjectile(16 + x, 48 + y, Math.PI/2);
						bulletActive = true;
						break;//down
					case 6:
						projectile = new PlayerProjectile(50 + x, 56 + y, Math.PI/4);
						bulletActive = true;
						break;//downright
					case 7:
						projectile = new PlayerProjectile(48 + x, 48 + y, 0);
						bulletActive = true;
						break;//right
					case 8:
						projectile = new PlayerProjectile(52 + x, 25 + y, -Math.PI/4);
						bulletActive = true;
						break;//upright
					default: 
						bulletActive = false;
					}
					projectiles.add(projectile);
					firingTimer = System.nanoTime();
				}
			}
			if(game.getKeyManager().up && game.getKeyManager().left) {
				setDirection(2);
			}
			else if(game.getKeyManager().down && game.getKeyManager().left) {				
				setDirection(4);
			}
			else if(game.getKeyManager().down && game.getKeyManager().right) {
				setDirection(6);
			}
			else if(game.getKeyManager().up && game.getKeyManager().right) {
				setDirection(8);
			}
			
			else if(game.getKeyManager().up) {
				setDirection(1);
			}
			else if(game.getKeyManager().left) {
				setDirection(3);
			}
			else if(game.getKeyManager().down) {
				setDirection(5);	
			}
			else if(game.getKeyManager().right) {
				setDirection(7);
			}
		
		}
		else if (id == 2)
		{
			if (game.getKeyManager().enter) {
				long elapsed = (System.nanoTime() - firingTimer) / 1000000;
				if (elapsed > firingDelay) {
					switch(getDirection()){
					case 1:
					{
						projectile = new PlayerProjectile(48 + x, 16 + y, -Math.PI/2);//up
						bulletActive = true;
						break;
					}
					case 2:
						projectile = new PlayerProjectile(22 + x, 16 + y, (-3*Math.PI)/4);
						bulletActive = true;
						break;//upleft
					case 3:
						projectile = new PlayerProjectile(16 + x, 16 + y, Math.PI);
						bulletActive = true;
						break;//left
					case 4:
						projectile = new PlayerProjectile(16 + x, 48 + y, (3*Math.PI)/4);
						bulletActive = true;
						break;//downleft
					case 5:
						projectile = new PlayerProjectile(16 + x, 48 + y, Math.PI/2);
						bulletActive = true;
						break;//down
					case 6:
						projectile = new PlayerProjectile(50 + x, 56 + y, Math.PI/4);
						bulletActive = true;
						break;//downright
					case 7:
						projectile = new PlayerProjectile(48 + x, 48 + y, 0);
						bulletActive = true;
						break;//right
					case 8:
						projectile = new PlayerProjectile(52 + x, 25 + y, -Math.PI/4);
						bulletActive = true;
						break;//upright
					default:
						bulletActive = false;
					}
					projectiles.add(projectile);
					firingTimer = System.nanoTime();
				}
			}
			if(game.getKeyManager().d_up && game.getKeyManager().d_left) {
				setDirection(2);
			}
			else if(game.getKeyManager().d_down && game.getKeyManager().d_left) {
				setDirection(4);
			}
			else if(game.getKeyManager().d_down && game.getKeyManager().d_right) {
				setDirection(6);
			}
			else if(game.getKeyManager().d_up && game.getKeyManager().d_right) {
				setDirection(8);
			}
			else if(game.getKeyManager().d_up) {
				setDirection(1);
			}
			
			else if(game.getKeyManager().d_left) {
				setDirection(3);
			}
			else if(game.getKeyManager().d_down) {
				setDirection(5);	
			}
			else if(game.getKeyManager().d_right) {
				setDirection(7);
			}
		}
		
		health.update(x, y);
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		
		if(id == 1) {
			if(projectiles.size() > 0) {
				for (PlayerProjectile p: projectiles) {
					p.draw(g);	
				}
			}
		}
		else {
			if(projectiles.size() > 0) {
				for (PlayerProjectile p: projectiles) {
					p.draw2(g);	
				}
			}
		}
		health.draw(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		switch(direction) {
		case 1:
			return Assets.pU;
		case 2:
			return Assets.pUL;
		case 3:
			return Assets.pL;
		case 4:
			return Assets.pDL;
		case 5:
			return Assets.pD;
		case 6:
			return Assets.pDR;
		case 7:
			return Assets.pR;
		case 8:
			return Assets.pUR;
		default:
			return null;
			}			
		
	}
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle(x, y, width - 16, height - 16);
	}
	
	/*public void takeDamage()
	{
		setInvincible(true);
		health.takeDamage();
	}*/
	
	public Rectangle getProjectile()
	{
		return projectile.getBoundingBox();
	}
	
	public void setBulletActive(boolean f)
	{
		projectile.setActive(f);
	}
	
	public boolean isBulletActive()
	{
		return bulletActive;
	}
	
	public void deleteProjectile()
	{
		projectile = null;
		bulletActive = false;
	}
	
	public void takeSetDamage(int indamage)
	{
		if(getInvincible() == true) {
		//health.takeSetDamage(0);
		}
		else {
		health.takeSetDamage(indamage);
		}
	}
	
	public void heal(int h)
	{
		health.healDamage(h);
	}
	
	public int getHealth()
	{
		return health.getHealth();
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
//	public HighScore score()
//	{
//		return score;
//	}

	public void InvincibilityFrames(Graphics g){
		g.drawImage(Assets.invincibility, (int) x - 8, (int) y - 8, width + 20, height + 20, null);		
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
	public boolean getInvincible() {
		return invincible;
	}
	
	public ArrayList<PlayerProjectile> getProjectiles() {
		return projectiles;
	}
	
	public void removeProjectileAt(int x) {
		projectiles.remove(x);
	}
		

}



