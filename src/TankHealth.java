import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TankHealth extends JPanel{
	
	//Rectangle health;
	int HEALTH;
	int loc_x;
	int loc_y;
	int DAMAGE = 12;
	
	public TankHealth(int x, int y)
	{	
		setLayout(null);
		HEALTH = 240;
		loc_x = x;
		loc_y = y;
	}
	
	public void paintComponent(Graphics g)
	{
		//g2.draw(currentImage(), x, y, WIDTH, HEIGHT);
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(loc_x, loc_y, 250, 20);
		g.setColor(Color.RED);
		g.fillRect(loc_x + 5, loc_y + 5, HEALTH, 10);
		
	}
	
	public void takeDamage()
	{	
		HEALTH -= DAMAGE;
		
		if(HEALTH == 0)
			DAMAGE = 0;
	}
	
}
