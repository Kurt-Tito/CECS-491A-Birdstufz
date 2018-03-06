import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StateButton extends JButton {
	public StateButton(String s, ImageIcon img, State state) {
		super(s, img);
		setActionCommand(state.toString());
	}
}
