package States;

import CECS491B.*;
import Enemies.SkeletonController;
import Enemies.ZombieController;

import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.creatures.Bird;
import entities.creatures.Player;
import game.Game;

public class GameState extends State {
	
	private EggHuntArenaFloor floor = new EggHuntArenaFloor();
	private EggHuntHTPPanel htp = new EggHuntHTPPanel();
	private Player player, player2;
	private EggHuntArena arena = new EggHuntArena();
	private ZombieController zombies;
	private SkeletonController skeletons;
	private HighScore score = new HighScore();
	private PlayerHealthBar health;
	private Bird bird;
	private int c1 = 0, c2 = 0;
	int col = 1;
	int row = 6;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, col*64, row*64, arena, 1);
		player2 = new Player(game, col*64, (row+1)*64, arena, 2);
		
		bird = new Bird(0, row * 64, 64, 64);
		zombies = new ZombieController(player, player2, arena);
		skeletons = new SkeletonController(player, player2, arena);
	}
	
	@Override
	public void tick() {
		player.tick();
		player2.tick();
		zombies.tick();
		skeletons.tick();
		bird.tick();
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		//arena.draw(g2);
		floor.draw(g2);
		//player.render(g2);
		arena.draw(g2);
		zombies.draw(g2);
		skeletons.draw(g2);
		if(player.getInvincible() == true){	
		c1++;
		player.InvincibilityFrames(g2);
		if(c1 > 100){
		player.setInvincible(false);
		c1 = 0;
		}
		}
		if(player2.getInvincible() == true){	
			c2++;
			player2.InvincibilityFrames(g2);
			if(c2 > 100){
			player2.setInvincible(false);
			c2 = 0;
			}
			}
		player.render(g2);
		player2.render(g2);
		score.DrawScore(g2);
		bird.render(g2);
		if(player.getHealth() <= 0 && player2.getHealth() <= 0) {
		floor.drawGameOver(g2);	
		}
	}

}
