package billiard.player;

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

import entities.creatures.Creature;
import billiard.gfx.Assets;
import billiard.game.billiardGame;
import billiardbunnies.BunniesPanel;

public class billiardPlayer extends Creature {

	//private Game game;
	private billiardGame game;
	private int direction;
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
//	private long firingTimer;
//	private long firingDelay;
	
	public billiardPlayer(billiardGame game, int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
		
		
//		health = new PlayerHealthBar(id);
//		
//		projectiles = new ArrayList<PlayerProjectile>();
//		firingTimer = System.nanoTime();
//		firingDelay = 200; //change firing delay in ms
	}
	

	public void tick() {
		
		getInput();
		move();
//		for (PlayerProjectile p: projectiles) {
//			if(p.isActive()) {
//				p.update();
//			}
//		}
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		System.out.println(game.getKeyManager2().pressed.size());
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
		
		//health.update(x, y);
	}

	@Override
	public void render(Graphics g) {
		
		//g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		g.drawImage(Assets.heroForward,  (int) x, (int) y, width, height, null);
		
		
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
//	public ArrayList<PlayerProjectile> getProjectiles() {
//		return projectiles;
//	}
//	
//	public void removeProjectileAt(int x) {
//		projectiles.remove(x);
//	}
		
}



