package CECS491B;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import CECS491A.GamePanel;
import CECS491A.State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class EggHuntHTPPanel extends GamePanel implements MouseListener{
	private JButton egghuntjbutton;
	private static final String PATH_HALLOWBANNER = "images/ControlsEH.png";
	protected static BufferedImage hallowbanner;
	static
	{
		try
		{
			hallowbanner = ImageIO.read(new File(PATH_HALLOWBANNER));
		} catch(IOException e)
		{
			System.out.println("Files for status bar not found");
		}
	}
	public EggHuntHTPPanel()
	{
		setBackground(new Color(121, 189, 255));
		setPreferredSize(new Dimension(1401, 900));
		
		ImageIcon button = new ImageIcon("images/background.png");
		
		//Scales buttonbackground to match button size
		Image image = button.getImage(); // transform it 
		Image newimg = image.getScaledInstance(300, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon buttonbg = new ImageIcon(newimg);  // transform it back
		
		setLayout(null);
		
		egghuntjbutton = new JButton("Back", buttonbg);
		egghuntjbutton.setActionCommand(State.EGGHUNTMENU.toString());
		egghuntjbutton.setBounds(1150, 780, 140, 60);
		egghuntjbutton.setHorizontalTextPosition(JButton.CENTER);
		egghuntjbutton.setVerticalTextPosition(JButton.CENTER);
	}
	
	@Override
	public void reset() {
		addButtons();
	}

	@Override
	public void addListener(ActionListener al) {
		egghuntjbutton.addActionListener(al);
	}
	
	public void removeListener(ActionListener al)
	{
		egghuntjbutton.removeActionListener(al);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBanner(g2);
	}
	
	public void drawBanner(Graphics2D g2)
	{
		int w = 1400, h = 900;
		g2.drawImage(hallowbanner, (getWidth() - w)/2, (getHeight() - h)/2, w, h, null);
	}
	
	public void addButtons()
	{	
		add(egghuntjbutton);
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

