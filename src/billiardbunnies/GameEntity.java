package billiardbunnies;

import java.awt.Graphics2D;

public abstract class GameEntity {
	protected double x, y, dx, dy, rotation, speed;
	
	public abstract void tick();
	public abstract void draw(Graphics2D g2);
	
	public GameEntity(int x, int y)
	{
		this.x = x;
		this.y = y;
		setRotation(0);
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getSpeedX()
	{
		return dx;
	}
	
	public double getSpeedY()
	{
		return dy;
	}
	
	public void setRotation(double angle)
	{
		this.rotation = angle;
		dx = speed * Math.cos(rotation);
		dy = -speed * Math.sin(rotation);
	}
	
	public void move()
	{
		x += dx;
		y += dy;
		
		if(x < 0)
		{
			x += 800;
		}
		else if(x > 800)
		{
			x-= 800;
		}
		if(y < 0)
		{
			y += 800;
		}
		else if(y > 800)
		{
			y-= 800;
		}
	}
	
	public void setDirection(Direction d)
	{
		switch(d)
		{
		case UP:
			setRotation(Math.PI/2);
			break;
		case LEFT:
			setRotation(Math.PI);
			break;
		case DOWN:
			setRotation(3*Math.PI/2);
			break;
		case RIGHT:
			setRotation(0);
			break;
		}
	}
}
