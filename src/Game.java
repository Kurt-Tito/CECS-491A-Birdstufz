import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JFrame{

	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);
		JPanel panel = new MenuPanel();
		setContentPane(panel);
		panel.requestFocus();
		setVisible(true);

	}
	
	public void changeState(State s)
	{
		
	}
}
