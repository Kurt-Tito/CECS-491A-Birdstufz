package billiardbunnies;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class BunniesPanel extends JPanel implements Runnable{
	private BunniesGrid map;
	
	private boolean running = false;
	private Thread thread;
	public BunniesPanel()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(800, 800));
		
		map = new BunniesGrid();

	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Billiard Bunnies");
		BunniesPanel panel = new BunniesPanel();
		frame.add(panel);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		panel.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		render(g2);
	}
	@Override
	public void run() {
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				repaint();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}
	
	private void tick()
	{
	}
	
	private void render(Graphics2D g2)
	{
		map.draw(g2);
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
