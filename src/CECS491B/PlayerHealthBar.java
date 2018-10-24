package CECS491B;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerHealthBar {
	
	private int health = 75;
	private int damage = 2;
	private int pid;
	private float locx, locy;
	
	public PlayerHealthBar (int pid)
	{
		this.pid = pid;
	}
	
	public void update(float x, float y)
	{
		this.locx = x;
		this.locy = y;
	}
	
	public void takeDamage()
	{
		if (health > 0)
			health -= damage;
		else 
			health = 0;
	}
	
	public void takeSetDamage(int indamage)
	{
		if (health > 0)
			health -= indamage;
		else 
			health = 0;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect((int)locx, (int)locy - 25, 75, 7);
		g.setColor(Color.GREEN);
		g.fillRect((int)locx, (int)locy - 25, health, 7);
		g.setColor(Color.CYAN);
		if(pid == 1)
			g.drawString("Player 1", (int)locx, (int)locy - 25);
		if(pid == 2)
			g.drawString("Player 2", (int)locx, (int)locy - 25);
	}
	

}
