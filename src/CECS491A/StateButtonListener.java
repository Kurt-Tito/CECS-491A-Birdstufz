package CECS491A;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StateButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		State s = State.valueOf(e.getActionCommand());
		
		
	}
	
}
