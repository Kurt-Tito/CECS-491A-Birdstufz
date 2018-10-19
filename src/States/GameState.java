package States;

import CECS491B.*;
import Enemies.SkeletonController;
import Enemies.ZombieController;

import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.creatures.Player;
import game.Game;

public class GameState extends State {
	
	private EggHuntArenaFloor floor = new EggHuntArenaFloor();
	private Player player;
	private EggHuntArena arena = new EggHuntArena();
	private ZombieController zombies;
	private HighScore score = new HighScore();
	
	int col = 1;
	int row = 6;
	
	public GameState(Game game){
		super(game);
		player = new Player(game, col*64, row*64, arena);
		zombies = new ZombieController(player, player, arena);
	}
	
	@Override
	public void tick() {
		player.tick();
		zombies.tick();
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		//arena.draw(g2);
		floor.draw(g2);
		//player.render(g2);
		arena.draw(g2);
		player.render(g2);
		zombies.draw(g2);
		score.DrawScore(g2);
	}

}
