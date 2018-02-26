import javax.swing.JFrame;


public class WinScreenViewer extends JFrame{			
	public static void main(String[] args){
		JFrame frame = new WinScreenPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
