package billiardbunnies;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bunny extends GameEntity{
	private static final int MOVESPEED = 2;
	private static final int RADIUS = 20;
	public Bunny(int x, int y) {
		super(x, y);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillOval((int)x - RADIUS, (int)y - RADIUS, RADIUS*2, RADIUS*2);
	}

}
