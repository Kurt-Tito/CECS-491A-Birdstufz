package States;

import CECS491B.*;
import Enemies.SkeletonController;
import Enemies.ZombieController;

import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.creatures.Bird;
import entities.creatures.BirdController;
import entities.creatures.Player;
import game.Game;

public class GameState extends State {
	
	private EggHuntArenaFloor floor = new EggHuntArenaFloor();
	private EggHuntHTPPanel htp = new EggHuntHTPPanel();
	private Player player, player2;
	private EggHuntArena arena = new EggHuntArena();
	private ZombieController zombies;
	private SkeletonController skeletons;
	private BirdController bird;
	public static HighScore score = new HighScore();
	
	private PlayerHealthBar health;
	private int c1 = 0, c2 = 0;
	int col = 1;
	int row = 6;
	
	int timer1 = 0;
	int timer2 = 0;
	int duration = 120;
	int delta = 1;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, col*64, row*64, arena, 1);
		player2 = new Player(game, col*64, (row+1)*64, arena, 2);
		
		zombies = new ZombieController(player, player2, arena);
		skeletons = new SkeletonController(player, player2, arena);
		bird = new BirdController(player, player2, arena);
	}
	
	@Override
	public void tick() {
		
		if (player.getHealth() > 0)
			player.tick();
		else
		{
			player.setX(-5000);
			player.setY(-5000);
		}
		
		if (player2.getHealth() > 0)
			player2.tick();
		else
		{
			player2.setX(-5000);
			player2.setY(-5000);
		}
		
		zombies.tick();
		skeletons.tick();
		bird.tick();
		score.setScore(zombies.zScore() + skeletons.sScore() + bird.getScore());
		
		if(player.getHealth() <= 0)
		{
			timer1 += delta;
		}
		
		if(player2.getHealth() <= 0)
		{
			timer2 += delta;
		}
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
		
		if (player.getHealth() > 0)
			player.render(g2);
		
		if (player2.getHealth() > 0)
			player2.render(g2);
		
		score.DrawScore(g2);
		bird.draw(g);
		
		if (timer1 < duration)
		{
			if(player.getHealth() <= 0)
			{
				g.drawString("Player 1 is DEAD", 700, 900/2);
			}
		}
		
		if (timer2 < duration)
		{
			if(player2.getHealth() <= 0)
			{
				g.drawString("Player 2 is DEAD", 700, 900/2);
			}
		}
		
		if(player.getHealth() <= 0 && player2.getHealth() <= 0) {
			floor.drawGameOver(g2, zombies.zScore()+skeletons.sScore()+bird.getScore(), score.checkScore(zombies.zScore()+skeletons.sScore()+bird.getScore()));	
		}
	}

}
