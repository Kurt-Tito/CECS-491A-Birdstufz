import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JFrame{

	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);
		JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
		setContentPane(panel);
		panel.requestFocus();
		setVisible(true);

	}
	
	public void changeState(State s)
	{
		
	}
}
