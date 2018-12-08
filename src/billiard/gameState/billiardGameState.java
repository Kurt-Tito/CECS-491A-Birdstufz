package billiard.gameState;

import java.awt.Graphics;
import java.awt.Graphics2D;

import billiard.player.billiardPlayer;
import billiard.player.playerBunny;
import billiard.game.billiardGame;
import billiardbunnies.BunniesGrid;
import billiardbunnies.BunniesPanel;
import billiardbunnies.Fireball;

public class billiardGameState extends State {
	private billiardPlayer heroPlayer;
	private playerBunny playerBunny;
	private Fireball fireball;
	private BunniesGrid map;
	
	int col = 1;
	int row = 6;
	

	public billiardGameState(billiardGame game) {
		super(game);
		heroPlayer = new billiardPlayer(game, col*64, row*64);
		playerBunny = new playerBunny(game, col*100, row*100);
		map = new BunniesGrid();
		fireball = new Fireball(550, 430);
		fireball.setRotation(Math.toRadians(90));
	}

	@Override
	public void tick() {
		heroPlayer.tick();
		playerBunny.tick();
		fireball.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
		heroPlayer.render(g2);
		playerBunny.render(g2);
		fireball.draw(g2);
	}

}
