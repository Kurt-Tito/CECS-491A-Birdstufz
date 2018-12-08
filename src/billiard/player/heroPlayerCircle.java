package billiard.player;

import java.awt.Graphics2D;

import billiard.game.billiardGame;
import billiardbunnies.GameEntity;

public class heroPlayerCircle extends GameEntity {
	//r = 20
	private billiardGame game;
	public heroPlayerCircle(billiardGame game, int x, int y) {
		super(x, y);
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		
	}
	
	private void getInput(){
		dx = 0;
		dy = 0;
		//System.out.println(game.getKeyManager2().pressed.size());
		if (game.getKeyManager2().up && game.getKeyManager2().pressed.size() <= 1 ) {
			dy = -speed;
		}
		if (game.getKeyManager2().down && game.getKeyManager2().pressed.size() <= 1) {
			dy = speed;
		}
		if (game.getKeyManager2().left && game.getKeyManager2().pressed.size() <= 1) {
			dx = -speed;
		}
		if (game.getKeyManager2().right && game.getKeyManager2().pressed.size() <= 1) {
			dx = speed;
		}
		
		//health.update(x, y);
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

}
