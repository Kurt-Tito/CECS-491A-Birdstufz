package CECS491A;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TankGamePanel extends GamePanel{
	private TankMainPanel mainpanel;
	private TankInfoPanel infopanel;
	private TankMazeGame game;

	public TankGamePanel()
	{
		game = new TankMazeGame();
		mainpanel = new TankMainPanel(game);
		infopanel = new TankInfoPanel(game);
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, mainpanel);
		add(BorderLayout.EAST, infopanel);
		addKeyListener(game.getPlayer());

	}
	@Override
	public void reset() {
		game.reset();
		addKeyListener(game.getPlayer());
	}

	@Override
	public void addListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

}
