package CECS491A;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import CECS491B.EggHuntArenaPanel;
import CECS491B.EggHuntHTPPanel;
import CECS491B.EggHuntMenuPanel;

public class Game extends JFrame implements ActionListener{
	private GamePanel currentPanel;
	private final MenuPanel menupanel = new MenuPanel(); // change assignment to new MazePanel() for maze game
	private final MazePanel mazepanel = new MazePanel();
	private final ConcentrationPanel concentrationpanel = new ConcentrationPanel();
	private final ChessPanel chesspanel = new ChessPanel();
	private final TankGamePanel tankpanel = new TankGamePanel();
	private final StatusBar statusbar = new StatusBar();
	private final EggHuntMenuPanel egghuntmenupanel = new EggHuntMenuPanel();
	private final EggHuntArenaPanel egghuntarenapanel = new EggHuntArenaPanel();
	private final EggHuntHTPPanel egghunthtppanel = new EggHuntHTPPanel();
	
	private java.util.Timer timer;
	private boolean isRunning = false;
	
	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setSize(1600, 900);
		setResizable(false);
		setMaximumSize(new Dimension(1600, 900));
//		JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
//		JPanel mazepanel = new MazePanel();
		
		setLayout(new BorderLayout());
		add(BorderLayout.SOUTH, statusbar);
		
		menupanel.addListener(this);
		mazepanel.addListener(this);
		chesspanel.addListener(this);
		statusbar.addListener(this);
		tankpanel.addListener(this);
		egghuntmenupanel.addListener(this);
	        egghuntarenapanel.addListener(this); 

		currentPanel = menupanel;
		updatePanel();
		setVisible(true);
		repaint();
		System.out.println("hihi");
		
	}
	
	private void initLoop()
	{
		//Game Loop
		timer = new Timer();
		timer.schedule(new GameLoop(), 0, 1000 / 60);
	}
	
	private class GameLoop extends java.util.TimerTask
	{
		public void run() {
			
			while(isRunning){
				//currentPanel.update(getGraphics());
				currentPanel.repaint();
				//System.out.println("updating");
			}
			
			if(!isRunning)
			{
				timer.cancel();
			}
		}
			
	}
	
	public void GameLoopSwitch(boolean s)
	{	
		isRunning = s;
	}
	
	public void changeState(State s)
	{
		getContentPane().remove(currentPanel);
		switch(s)
		{
			case MENU:
				GameLoopSwitch(false);
				currentPanel = menupanel;
				break;
			case MAZE:
				currentPanel = mazepanel;
				break;
			case CONCENTRATION:
				currentPanel = concentrationpanel;
				break;
			case CHESS:
				GameLoopSwitch(true);
				initLoop();
				currentPanel = chesspanel;
				break;
			case TANK:
				GameLoopSwitch(true);
				initLoop();
				currentPanel = tankpanel;
				break;
			case EGGHUNTMENU:
				currentPanel = egghuntmenupanel;
				break;
			case EGGHUNT:
				GameLoopSwitch(true);
				currentPanel = egghuntarenapanel;
				break;
			case EGGHUNTHTP:
				currentPanel = egghunthtppanel;
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
		if(currentPanel.equals(menupanel))
		{
			statusbar.hideHomeButton();
		}
		else
		{
			statusbar.showHomeButton();
		}
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
