import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StatusBar extends JPanel{
	private static final String PATH_BRANDING = "images/branding.png"; 
	protected static BufferedImage brand, banner;
	private JButton menuButton;
	static
	{
		try
		{
			brand = ImageIO.read(new File(PATH_BRANDING));
		} catch(IOException e)
		{
			System.out.println("Files for status bar not found");
		}
	}
	public StatusBar()
	{
		setLayout(null);
		setBackground(Color.white);
		setPreferredSize(new Dimension(0, 60));
		menuButton = new JButton("Home");
		menuButton.setActionCommand(State.MENU.toString());
		menuButton.setBounds(15, 15, 100, 30);
		add(menuButton);
	}
	
	public void hideHomeButton()
	{
		remove(menuButton);
	}
	public void showHomeButton()
	{
		remove(menuButton);
		add(menuButton);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f)); // Adjusts opacity;
		g2.drawImage(brand, getWidth()/2 -90, getHeight()/2 - 30, 180, 60, null);
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	public void addListener(ActionListener al)
	{
		menuButton.addActionListener(al);
	}
}
