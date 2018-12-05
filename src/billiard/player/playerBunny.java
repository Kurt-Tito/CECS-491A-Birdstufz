package billiard.player;

import java.awt.Graphics;

import billiard.game.billiardGame;
import entities.creatures.Creature;
import billiard.gfx.Assets;


public class playerBunny extends Creature {
	
	private billiardGame game;

	public playerBunny(billiardGame game, int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		getInput();
		move();
		// TODO Auto-generated method stub
		
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		
		//System.out.println(game.getKeyManager2().pressed2.size());
		if (game.getKeyManager2().d_up && game.getKeyManager2().pressed2.size() <= 1 ) {
			yMove = -speed;
		}
		if (game.getKeyManager2().d_down && game.getKeyManager2().pressed2.size() <= 1) {
			yMove = speed;
		}
		if (game.getKeyManager2().d_left && game.getKeyManager2().pressed2.size() <= 1) {
			xMove = -speed;
		}
		if (game.getKeyManager2().d_right && game.getKeyManager2().pressed2.size() <= 1) {
			xMove = speed;
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.playerBunny, (int) x, (int) y, width, height, null);
	}
	

}
