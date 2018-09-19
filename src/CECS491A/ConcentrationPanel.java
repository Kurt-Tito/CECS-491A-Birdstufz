package CECS491A;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class ConcentrationPanel extends GamePanel{
	
	private List<Card> cards = new ArrayList<Card>(24);
	private Card selectedCard;
	private Card c1;
	private Card c2;
	private Timer t;
	
	public ConcentrationPanel()
	{	
		setBackground(Color.black);
		setPreferredSize(new Dimension(1200, 700));
		
		createCards();
		
		t = new Timer(400, new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				checkCards();
			}
		});
		t.setRepeats(false);
		
		createBoard();
	}
	
	public void createCards()
	{	
		int pairs = 12;
		for(int i = 0; i < pairs*2; i++)
		{
			//cards.add(new Card());
			Card c = new Card();
			c.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					selectedCard = c;
					doTurn();
				}
			});
			
			cards.add(c);
		}
		
		//set values
		for(int j = 0; j < pairs*2; j++)
		{
			cards.get(j).setValue(j%12);
		}
		
		//set card images
		for(int i = 0; i < pairs*2; i++)
		{
			cards.get(i).setImage("images/concentration_game/" +Integer.toString(i%12) +".png");
		}
		
		Collections.shuffle(cards);
	}
	
	public void createBoard()
	{
		setLayout(new GridLayout(4, 6));		
		for(int i = 0; i < 24; i++)
			add(cards.get(i));
	}
	
	public void doTurn()
	{
		if(c1 == null && c2 == null)
		{
			c1 = selectedCard;
			c1.showCard();
		}
		if(c1 != null && c1 != selectedCard && c2 == null)
		{
			c2 = selectedCard;
			c2.showCard();
			t.start();
		}
	}
	
	public void checkCards()
	{
		if(c1.getValue() == c2.getValue())
		{
			
			c1.setEnabled(false);
			c2.setEnabled(false);
			
			c1.setMatched(true);
			c2.setMatched(true);
			
			if(checkWin())
			{
				System.out.println("WINNER!");
				WinScreenPanel win = new WinScreenPanel();
				win.setVisible(true);
			}	
		}
		else 
		{
			c1.hideCard();
			c2.hideCard();
		}
		
		c1 = null;
		c2 = null;
	}
	
	public boolean checkWin()
	{
		for(Card c: cards)
		{
			if(c.getMatched() == false)
				return false;
		}
		
		return true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
		//Clears panel of all cards
		for(int i = 0; i < 24; i++)
			remove(cards.get(i));
		//Clears cards list of all cards
		cards.removeAll(cards);
		
		createCards();
		createBoard();
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
