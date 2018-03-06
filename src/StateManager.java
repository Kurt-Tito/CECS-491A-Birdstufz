import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class StateManager extends Observable implements ActionListener{
	private State currentState;
	public StateManager()
	{
		currentState = State.MENU;
	}
	
	public State getState()
	{
		return currentState;
	}
	public void setState(State s)
	{
		currentState = s;
		System.out.println(countObservers());
		setChanged();
		notifyObservers(currentState);
		System.out.println("Notifying observers...");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setState(State.valueOf(e.getActionCommand()));
		System.out.println(e.getActionCommand());
	}
}
