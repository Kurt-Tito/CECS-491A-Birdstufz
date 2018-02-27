import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Game extends JFrame{

	JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
	JPanel mazepanel = new MazePanel();
	
	public Game(String title)
	{
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1600, 900);
//		JPanel panel = new MenuPanel(); // change assignment to new MazePanel() for maze game
//		JPanel mazepanel = new MazePanel();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setContentPane(panel);
		panel.requestFocus();
		setVisible(true);
		
		addMazeButton();
		addConcentrationButton();
		
		repaint();
	}
	
	public void addMazeButton()
	{
		ImageIcon buttonbg = new ImageIcon("images/ButtonFrame.png");
		setLayout(null);
		
		JButton mazebutton = new JButton("MAZE GAME", buttonbg);
		mazebutton.setBounds(600, 350, 380, 50);
		mazebutton.setHorizontalTextPosition(JButton.CENTER);
		mazebutton.setVerticalTextPosition(JButton.CENTER);
		add(mazebutton);
		
		mazebutton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			JPanel mazepanel = new MazePanel();
			
			System.out.println("Going to maze game"); //debugging purposes
			remove(panel);
			add(mazepanel);
			setContentPane(mazepanel);
			mazepanel.requestFocus();
			invalidate();
			validate();
			repaint();
			}
		});
	}
	
	public void addConcentrationButton()
	{
		ImageIcon buttonbg = new ImageIcon("images/ButtonFrame.png");
		setLayout(null);
		
		JButton concentration = new JButton("CONCENTRATION GAME", buttonbg);
		concentration.setBounds(600, 450, 380, 50);
		concentration.setHorizontalTextPosition(JButton.CENTER);
		concentration.setVerticalTextPosition(JButton.CENTER);
		add(concentration);
	}
	
	public void changeState(State s)
	{
		
	}
}
