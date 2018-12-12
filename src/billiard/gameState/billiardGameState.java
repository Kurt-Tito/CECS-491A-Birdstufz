package billiard.gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;

import billiard.player.billiardPlayer;
import billiard.player.playerBunny;
import billiard.game.billiardGame;
import billiardbunnies.BunniesGrid;
import billiardbunnies.BunniesPanel;
import billiardbunnies.Bunny;
import billiardbunnies.Fireball;

public class billiardGameState extends State {
	private billiardPlayer heroPlayer;
	private playerBunny playerBunny;
	private BunniesGrid map;
	private Bunny bunny;
	
	int col = 0;
	int row = 0;
	

	public billiardGameState(billiardGame game) {
		super(game);
		heroPlayer = new billiardPlayer(game, col*64, row*64);
		playerBunny = new playerBunny(game, col*100, row*100);
		map = new BunniesGrid();
		bunny = new Bunny(40,40,map, heroPlayer);
	}

	@Override
	public void tick() {
		heroPlayer.tick();
		playerBunny.tick();
		bunny.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
		heroPlayer.render(g2);
		playerBunny.render(g2);
		bunny.draw(g2);
	}

}
