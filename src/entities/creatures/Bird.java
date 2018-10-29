package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.Assets;

public class Bird extends Creature {
	private final int MOVE_SPEED = 2;
	private Animation idle;

	public Bird(int x, int y, int width, int height) {
		super(x, y, width, height);
		idle = new Animation(100, Assets.idleBird);
		// TODO Auto-generated constructor stub
		setxMove(MOVE_SPEED);
		setyMove(0);
	}

	@Override
	public void tick() {
		idle.tick();
		move();
		// TODO Auto-generated method stub
	}

	

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - width/2), (int) (y - height/2), width, height, null);
		
		// TODO Auto-generated method stub
		
	} 
	
	private BufferedImage getCurrentAnimationFrame() {
		return idle.getCurrentFrame();
	}
	

}
