package billiard.player;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.*;

import billiard.game.billiardGame;
import entities.creatures.Creature;
import billiard.gfx.Assets;
import billiardbunnies.Collisions;
import billiardbunnies.LineSegment;
import billiardbunnies.Fireball;


public class playerBunny extends Creature {
	private ArrayList<Fireball> fireballs = new ArrayList<Fireball>();
	private billiardGame game;
	private billiardPlayer player;
	private static final int SHOOT_DELAY = 240; //number of frames
	private int shootDelay;
	public playerBunny(billiardGame game, int x, int y, billiardPlayer player) {
		super(x+755, y+755, Creature.DEFAULT_CREATURE_WIDTH-16, Creature.DEFAULT_CREATURE_HEIGHT-16);
		shootDelay = SHOOT_DELAY;
		this.game = game;
		this.player = player;
		// TODO Auto-generated constructor stub
	}
	
public void tick() {
		getInput();
		move();
		shootDelay--;
	
		for(LineSegment line: LineSegment.walls)
		{
			if(Collisions.CircleLine(line, x + (width/2), y + (width/2), width/2))
			{
				x -= xMove;
				y -= yMove;
			}
		}
		/* this code below is not working */
		if(shootDelay <= 0)
		{
			//System.out.println("SHOOOT");
			Fireball fb = new Fireball((int) x + width/2, (int)y + height/2);
			fb.setRotation(Math.atan2(-(player.getCenterY() - y), player.getCenterX() - x));
			fireballs.add(fb);		
			shootDelay += SHOOT_DELAY;
		}
		else
		{
			shootDelay--;
		}
		for(int i = 0; i < fireballs.size(); i++)
		{
			if(fireballs.get(i).isActive())
			{
				fireballs.get(i).tick();
				
				for(int l = 0; l < 48; l++)
				{
					for (int k = 0; k < 48; k++)
					{
						if(fireballs.get(i).getX() == player.getX()+l)
						{
							if(fireballs.get(i).getY() == player.getY()+k)
							{
								//System.out.println("hit");
								player.takeDamage();
								fireballs.get(i).setActive(false);
							}
						}
					}
				}
			}
			else
			{
				fireballs.remove(i);
				i--;
			}
		}
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
	
	public Rectangle getBoundingBox()
	{
		return new Rectangle(x, y, width - 16, height - 16);
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.playerBunny, (int) x, (int) y, width, height, null);
		for(Fireball fb: fireballs)
		{
			fb.draw((Graphics2D) g);
		}
	}
	

}
