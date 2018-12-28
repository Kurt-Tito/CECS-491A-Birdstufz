package billiard.player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import entities.creatures.Creature;
import billiard.gfx.Assets;
import billiard.game.billiardGame;
import billiardbunnies.BunniesPanel;
import billiardbunnies.Collisions;
import billiardbunnies.Fireball;
import billiardbunnies.LineSegment;

public class billiardPlayer extends Creature {

	//private Game game;
	private billiardGame game;
	private int direction;
	private ArrayList<Fireball> projectiles;
	private Fireball projectile;
	private int halfDiagonal = (int) Math.sqrt(Math.pow(64, 2) + Math.pow(64, 2))/2;
//	private PlayerProjectile projectile;
//	private ArrayList<PlayerProjectile> projectiles;
//	private PlayerHealthBar health;
//	private Animation idle, meleeAttack, rifleMove, rifleShoot;
//	
//	private Rectangle nextMoveUP = new Rectangle();
//	private Rectangle nextMoveDWN = new Rectangle();
//	private Rectangle nextMoveLFT = new Rectangle();
//	private Rectangle nextMoveRT = new Rectangle();
//	
//	private Rectangle UP = new Rectangle();
//	private Rectangle DOWN = new Rectangle();
//	private Rectangle LEFT = new Rectangle();
//	private Rectangle RIGHT = new Rectangle();
//
//    private int counter = 0, active = 0;
//	private EggHuntArenaCell[][] grid;
//	private boolean invincible;
//	private boolean bulletActive;
//	
	private long firingTimer;
	private long firingDelay;
	private int health;
	
	public billiardPlayer(billiardGame game, int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH-24, Creature.DEFAULT_CREATURE_HEIGHT-24);
		this.game = game;
		
		
//		health = new PlayerHealthBar(id);
//		
		projectiles = new ArrayList<Fireball>();
		firingTimer = System.nanoTime();
		firingDelay = 500; //change firing delay in ms
		health = 50;
	}
	

	public void tick() {
		
		getInput();
		move();
		for(LineSegment line: LineSegment.walls)
		{
			if(Collisions.CircleLine(line, x + (width/2), y + (width/2), width/2))
			{
				x -= xMove;
				y -= yMove;
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isActive()) {
				projectiles.get(i).tick();
			}
			else
			{
				projectiles.remove(i);
				i--;
			}
		}
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		if (game.getKeyManager2().up && game.getKeyManager2().pressed.size() <= 1 ) {
			yMove = -speed;
		}
		if (game.getKeyManager2().down && game.getKeyManager2().pressed.size() <= 1) {
			yMove = speed;
		}
		if (game.getKeyManager2().left && game.getKeyManager2().pressed.size() <= 1) {
			xMove = -speed;
		}
		if (game.getKeyManager2().right && game.getKeyManager2().pressed.size() <= 1) {
			xMove = speed;
		}
		
		if (game.getMouseManager().isLeftPressed()) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			System.out.println("ASDFASD");
			if (elapsed > firingDelay) {
				
				double Cx =  (x +  halfDiagonal * Math.cos(Math.toRadians(-45)));
				double Cy =  (y + halfDiagonal * Math.cos(Math.toRadians(-45)));
				
				double theta = (Math.atan2((double)game.getMouseManager().getMouseY()  - Cy, 
						(double)game.getMouseManager().getMouseX() - Cx));
				
				
				
				double angle = Math.toDegrees(-theta);

			   
			    if (angle < 0) {
			        angle += 360;
			    }
			    System.out.println(angle);
				
				
				projectile = new Fireball((int)Cx, (int)Cy);
				projectile.setRotation(Math.toRadians(angle));
				projectiles.add(projectile);
				firingTimer = System.nanoTime();
			}
			
		}
		
		//health.update(x, y);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		g.fillOval(x, y, width, width);
		g.drawImage(Assets.heroForward,  (int) x, (int) y, width, height, null);
		if (projectiles.size() > 0) {
			for (Fireball f: projectiles) {
				if(f.isActive())
				{
					f.draw(g2);
				}
			}
		}
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x - 5, (int)y - 25, 50, 7);
		g.setColor(Color.GREEN);
		g.fillRect((int)x - 5, (int)y - 25, health, 7);
		
		
		//health.draw(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		switch(direction) {
		case 1:
			return Assets.heroForward;
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
	
//	public Rectangle getProjectile()
//	{
//		return projectile.getBoundingBox();
//	}
//	
//	public void setBulletActive(boolean f)
//	{
//		projectile.setActive(f);
//	}
//	
//	public boolean isBulletActive()
//	{
//		return bulletActive;
//	}
//	
//	public void deleteProjectile()
//	{
//		projectile = null;
//		bulletActive = false;
//	}
//	
//	public void takeSetDamage(int indamage)
//	{
//		if(getInvincible() == true) {
//		//health.takeSetDamage(0);
//		}
//		else {
//		health.takeSetDamage(indamage);
//		}
//	}
//	
//	public void heal(int h)
//	{
//		health.healDamage(h);
//	}
//	
//	public int getHealth()
//	{
//		return health.getHealth();
//	}
	
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

//	public void InvincibilityFrames(Graphics g){
//		g.drawImage(Assets.invincibility, (int) x - 8, (int) y - 8, width + 20, height + 20, null);		
//	}
//
//	public void setInvincible(boolean invincible) {
//		this.invincible = invincible;
//	}
//	public boolean getInvincible() {
//		return invincible;
//	}
//	
	public ArrayList<Fireball> getProjectiles() {
		return projectiles;
	}
	
	public void takeDamage()
	{
		health -= 2;
	}
	
	public int getHealth()
	{
		return health;
	}
//	public void removeProjectileAt(int x) {
//		projectiles.remove(x);
//	}
		
}



