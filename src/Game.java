import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JFrame{

	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);
<<<<<<< HEAD
		JPanel panel = new MenuPanel(); // Set assignment to new MazePanel to display maze game
=======
		JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
>>>>>>> f2a30c462c928f52c82ef5aa9378f37e7d6e041c
		setContentPane(panel);
		panel.requestFocus();
		setVisible(true);

	}
	
	public void changeState(State s)
	{
		
	}
}
