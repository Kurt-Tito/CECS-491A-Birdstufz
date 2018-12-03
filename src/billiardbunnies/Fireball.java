package billiardbunnies;

import java.awt.Graphics2D;

public class Fireball extends GameEntity {
	private static final int MOVESPEED = 5;
	private static final int RADIUS = 10;
	
	public Fireball(int x, int y)
	{
		super(x,y);
		this.speed = MOVESPEED;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.fillOval((int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2);
	}
}
