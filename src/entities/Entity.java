package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Enumeration;

public abstract class Entity extends Rectangle{

	protected int x, y;
	protected int width, height;
	protected Orientation dir;
	
	
	public Entity(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setBounds(x, y, 64, 64);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public Orientation getDir() {
		return dir;
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
