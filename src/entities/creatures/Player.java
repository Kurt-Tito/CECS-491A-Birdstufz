package entities.creatures;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import CECS491B.EggHuntArena;
import CECS491B.EggHuntArenaCell;
import CECS491B.PlayerProjectile;
import game.Game;
import gfx.Animation;
import gfx.Assets;
import input.KeyManager;

public class Player extends Creature {

	private Game game;
	private PlayerProjectile projectile;
	
	private Animation idle, meleeAttack, rifleMove, rifleShoot;
	
	private Rectangle nextMoveUP = new Rectangle();
	private Rectangle nextMoveDWN = new Rectangle();
	private Rectangle nextMoveLFT = new Rectangle();
	private Rectangle nextMoveRT = new Rectangle();
	
	private Rectangle UP = new Rectangle();
	private Rectangle DOWN = new Rectangle();
	private Rectangle LEFT = new Rectangle();
	private Rectangle RIGHT = new Rectangle();


	private int direction;
	private EggHuntArenaCell[][] grid;
	
	public Player(Game game, int x, int y, EggHuntArena arena) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
		this.grid = arena.getGrid();
		
		direction = 7;
		
		idle = new Animation(30, Assets.idle);
		meleeAttack = new Animation(50, Assets.meleeAttack);
		rifleMove = new Animation(20, Assets.rifleMove);
		rifleShoot = new Animation(100, Assets.rifleShoot);
	}
	

	public void tick() {
		//animations
		idle.tick();
		meleeAttack.tick();
		rifleMove.tick();
		rifleShoot.tick();
		if(projectile != null && projectile.isActive())
		{
			projectile.update();
		}
		getInput();
		move();
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		nextMoveUP.setBounds(x+5, y-5-(int)speed, 48, 48);
		nextMoveDWN.setBounds(x+5, y+5+(int)speed, 48, 48);
		nextMoveLFT.setBounds(x-5-(int)speed, y+5, 48, 48);
		nextMoveRT.setBounds(x+5+(int)speed, y+5, 48, 48);
		
		if(projectile != null) {
		UP.setBounds((int)projectile.getPx(), (int)projectile.getPy()-(int)speed, 5, 5);
		DOWN.setBounds((int)projectile.getPx(), (int)projectile.getPy()+(int)speed, 5, 5);
		LEFT.setBounds((int)projectile.getPx()-(int)speed,(int)projectile.getPy(), 5, 5);
		RIGHT.setBounds((int)projectile.getPx()+(int)speed, (int)projectile.getPy(), 5, 5);
		}

       
		System.out.println(getDirection());
		for (int i = 0; i < grid[0].length; i++)
		{
			for (int j = 0; j < grid.length; j++)
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
				if(UP.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
					projectile.setActive(false);
						}
				if(DOWN.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
					projectile.setActive(false);
				}
				if(LEFT.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
					projectile.setActive(false);
				}
				if(RIGHT.intersects(grid[j][i]) && grid[j][i].isBlocked()) {
					projectile.setActive(false);
				}
			}
		}
	
		if(game.getKeyManager().space){
			switch(getDirection()){
			case 1:
				projectile = new PlayerProjectile(48 + x, y, -Math.PI/2); break;//up
			case 2:
				projectile = new PlayerProjectile(16 + x, 16 + y, (-3*Math.PI)/4); break;//upleft
			case 3:
				projectile = new PlayerProjectile(x, 16 + y, Math.PI); break;//left
			case 4:
				projectile = new PlayerProjectile(x, 48 + y, (3*Math.PI)/4); break;//downleft
			case 5:
				projectile = new PlayerProjectile(16 + x, 48 + y, Math.PI/2); break;//down
			case 6:
				projectile = new PlayerProjectile(48 + x, 48 + y, Math.PI/4); break;//downright
			case 7:
				projectile = new PlayerProjectile(48 + x, 48 + y, 0); break;//right
			case 8:
				projectile = new PlayerProjectile(48 + x, 16 + y, -Math.PI/4); break;//upright
			}
		}
		if (game.getKeyManager().pressed.size() > 1) {
			if(game.getKeyManager().up && game.getKeyManager().left) {
				yMove = -speed;
				xMove = -speed;
				setDirection(2);
				System.out.println("Moving: up left");
				System.out.println(getDirection());
				
			}
			if(game.getKeyManager().down && game.getKeyManager().left) {
				yMove = speed;
				xMove = -speed;
				setDirection(4);
				System.out.println("Moving: down left");
			}
			if(game.getKeyManager().down && game.getKeyManager().right) {
				yMove = speed;
				xMove = speed;
				setDirection(6);
				System.out.println("Moving: down right");
			}
			if(game.getKeyManager().up && game.getKeyManager().right) {
				yMove = -speed;
				xMove = speed;
				setDirection(8);
				System.out.println("Moving: up right");
				System.out.println(getDirection());
			}
		}
		
		else {
		
			if(game.getKeyManager().up) {
				setDirection(1);
				yMove = -speed;
			}
			
			if(game.getKeyManager().left) {
				xMove = -speed;
				setDirection(3);
			}
			if(game.getKeyManager().down) {
				yMove = speed;
				setDirection(5);	
			}
			if(game.getKeyManager().right) {
				xMove = speed;
				setDirection(7);
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 48, 48);
		if(projectile != null)
		{
			projectile.draw(g);
		}
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
				
//		if (direction == 2) { 
//			return Assets.pUL;
//		}
//		else if (direction == 4) { 
//			return Assets.pDL;
//		}
//		else if (direction == 6) {
//			return Assets.pDR;
//		}
//		else if (direction == 8) { 
//			return Assets.pUR;
//		}
//		else if (direction == 1) { 
//			return Assets.pU;
//		}
//		else if (direction == 3) {
//			return Assets.pL;
//		}
//		else if (direction == 5) { 
//			return Assets.pD;
//		}
//		else if (direction == 7) { 
//			return Assets.pR;
//		}
//		else{
//			return null;
//		}
		
		
			
	}
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle(x, y, width - 16, height - 16);
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	

}



