import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MenuPanel extends JPanel implements MouseListener{
	private final String PATH_BRANDING = "images/branding.png"; 
	private final String PATH_BANNER = "images/panel.png";
	
	private BufferedImage brand, banner;
	public MenuPanel()
	{
		setBackground(new Color(121, 189, 255));
		try
		{
			brand = ImageIO.read(new File(PATH_BRANDING));
			banner = ImageIO.read(new File(PATH_BANNER));
		} catch(IOException e)
		{
			
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBanner(g2);
		drawStatusBar(g2);
	}
	
	public void drawBanner(Graphics2D g2)
	{
		int w = 800, h = 600;
		g2.drawImage(banner, (getWidth() - w)/2, (getHeight() - h)/2, w, h, null);
	}
	
	public void drawStatusBar(Graphics2D g2)
	{
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f)); // Adjusts opacity;
		g2.drawImage(brand, getWidth()-180, getHeight()-60, 180, 60, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

