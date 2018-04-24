import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TankHealth extends JPanel{
	
	//Rectangle health;
	int HEALTH;
	int loc_x;
	int loc_y;
	int DAMAGE = 12;
	String playername_string = "";
	
	public TankHealth(int x, int y, String inplayername)
	{	
		setLayout(null);
		HEALTH = 240;
		loc_x = x;
		loc_y = y;
		
		playername_string = inplayername;
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(loc_x, loc_y, 250, 20);
		g.setColor(Color.RED);
		g.fillRect(loc_x + 5, loc_y + 5, HEALTH, 10);
		g.setColor(Color.BLUE);
		g.drawString(playername_string, loc_x + 10, loc_y + 15);
	}
	
	public void takeDamage()
	{	
		HEALTH -= DAMAGE;
		
		if(HEALTH == 0)
			DAMAGE = 0;
	}
	
}
