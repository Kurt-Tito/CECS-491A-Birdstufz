import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class MenuPanel extends GamePanel implements MouseListener{
	private JButton mazebutton, concentrationbutton, chessbutton;
	public MenuPanel()
	{
		setBackground(new Color(121, 189, 255));
		setPreferredSize(new Dimension(1600, 900));
		
		ImageIcon buttonbg = new ImageIcon("images/ButtonFrame.png");
		setLayout(null);
		
		mazebutton = new JButton("MAZE GAME", buttonbg);
		mazebutton.setActionCommand(State.MAZE.toString());
		mazebutton.setBounds(600, 350, 380, 50);
		mazebutton.setHorizontalTextPosition(JButton.CENTER);
		mazebutton.setVerticalTextPosition(JButton.CENTER);
		
		concentrationbutton = new JButton("CONCENTRATION GAME", buttonbg);
		concentrationbutton.setActionCommand(State.CONCENTRATION.toString());
		concentrationbutton.setBounds(600, 450, 380, 50);
		concentrationbutton.setHorizontalTextPosition(JButton.CENTER);
		concentrationbutton.setVerticalTextPosition(JButton.CENTER);
		
		chessbutton = new JButton("CHESS GAME", buttonbg);
		chessbutton.setActionCommand(State.CHESS.toString());
		chessbutton.setBounds(600, 550, 380, 50);
		chessbutton.setHorizontalTextPosition(JButton.CENTER);
		chessbutton.setVerticalTextPosition(JButton.CENTER);
	}
	
	@Override
	public void reset() {
		addButtons();
	}

	@Override
	public void addListener(ActionListener al) {
		mazebutton.addActionListener(al);
		concentrationbutton.addActionListener(al);
		chessbutton.addActionListener(al);
	}
	
	public void removeListener(ActionListener al)
	{
		mazebutton.removeActionListener(al);
		concentrationbutton.removeActionListener(al);
		chessbutton.removeActionListener(al);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawBanner(g2);
	}
	
	public void drawBanner(Graphics2D g2)
	{
		int w = 800, h = 600;
		g2.drawImage(banner, (getWidth() - w)/2, (getHeight() - h)/2, w, h, null);
	}
	
	public void addButtons()
	{	
		add(mazebutton);
		add(concentrationbutton);
		add(chessbutton);
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

