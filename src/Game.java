import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JFrame implements ActionListener{
	private GamePanel currentPanel;
	private final MenuPanel menupanel = new MenuPanel(); // change assignment to new MazePanel() for maze game
	private final MazePanel mazepanel = new MazePanel();
	private final ConcentrationPanel concentrationpanel = new ConcentrationPanel();
	private final ChessPanel chesspanel = new ChessPanel();
	private final StatusBar statusbar = new StatusBar();
	
	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setSize(1600, 900);
		setResizable(false);
//		JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
//		JPanel mazepanel = new MazePanel();
		
		setLayout(new BorderLayout());
		add(BorderLayout.SOUTH, statusbar);
		
		
		
		
		menupanel.addListener(this);
		mazepanel.addListener(this);
		chesspanel.addListener(this);
		statusbar.addListener(this);
		
		currentPanel = menupanel;
		updatePanel();
		
		setVisible(true);
		
		repaint();
	}
	
	
	public void changeState(State s)
	{
		getContentPane().remove(currentPanel);
		switch(s)
		{
			case MENU:
				currentPanel = menupanel;
				break;
			case MAZE:
				currentPanel = mazepanel;
				break;
			case CONCENTRATION:
				currentPanel = concentrationpanel;
				break;
			case CHESS:
				currentPanel = chesspanel;
				break;
		}
		updatePanel();
	}
	
	private void updatePanel()
	{
		//getContentPane().removeAll();
		add(BorderLayout.CENTER, currentPanel);
		pack();
		currentPanel.reset();
		currentPanel.requestFocus();
		currentPanel.removeListener(this);
		currentPanel.addListener(this);
		revalidate();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			changeState(State.valueOf(e.getActionCommand()));
			System.out.println("Action received: " + State.valueOf(e.getActionCommand()));
		}
		catch(IllegalArgumentException exc)
		{
			System.out.println("Error: Not a valid state.");
		}
	}
}
