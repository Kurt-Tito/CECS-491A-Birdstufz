import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WinScreenPanel extends JFrame {
	private JLabel label, space;
	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 150;
	
	public WinScreenPanel() {
		label = new JLabel("You Win!");
		label.setFont(new Font("Courier New", Font.PLAIN, 30)); 	
		space = new JLabel("                                                                             ");
		JPanel Panel = new JPanel();
		Panel.add(label);
		Panel.add(space);
		Panel.add(createExitButton());		
		add(Panel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}


private JButton createExitButton() {
	JButton ExitButton = new JButton("Exit ->");
	ExitButton.setFont(new Font("Courier New", Font.PLAIN, 20));
	ExitButton.setForeground(Color.BLUE);
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