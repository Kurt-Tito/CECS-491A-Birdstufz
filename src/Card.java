import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton implements MouseListener{
	
	private int value;
	private boolean match = false;
	String imgpath = "";
	
	public Card()
	{
		addMouseListener(this);
	}
	
	public void setValue(int invalue)
	{
		value = invalue;
	}
	
	public void setImage(String img)
	{
		imgpath = img;
	}
	
	public void setMatched(boolean inmatch)
	{
		match = inmatch;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean getMatched()
	{
		return match;
	}
	
	public void showCard()
	{
		ImageIcon image = new ImageIcon(imgpath);
		setIcon(image);
		setDisabledIcon(image);
	}
	
	public void hideCard()
	{
		setIcon(null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		showCard();	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
