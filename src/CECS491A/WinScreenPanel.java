package CECS491A;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class WinScreenPanel extends JFrame {
	private JLabel label, space, tab;
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 150;
	JPanel Panel = new JPanel();
	
	public WinScreenPanel() {
		label = new JLabel("You Win!");
		label.setFont(new Font("Courier New", Font.PLAIN, 30)); 	
		space = new JLabel("                                                                             ");
		tab = new JLabel("   ");
		Panel.add(label);
		Panel.add(space);
		Panel.add(createAcceptButton());	
		Panel.add(tab);
		Panel.add(createExitButton());
		add(Panel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
public void close(){
	 WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
	 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
}

private JButton createAcceptButton() {
	JButton AcceptButton = new JButton("Accept");
	AcceptButton.setFont(new Font("Courier New", Font.PLAIN, 20));
	AcceptButton.setForeground(Color.BLUE);
	AcceptButton.setBorder(null);
	
	class Back implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			close();	
		}
}
	ActionListener listener = new Back();
	AcceptButton.addActionListener(listener);
	return AcceptButton;
}

private JButton createExitButton() {
	JButton ExitButton = new JButton("Exit ->");
	ExitButton.setFont(new Font("Courier New", Font.PLAIN, 20));
	ExitButton.setForeground(Color.GRAY);
	ExitButton.setBorder(null);
	
	class Exit implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
}
	ActionListener listener = new Exit();
	ExitButton.addActionListener(listener);
	return ExitButton;
}
}
