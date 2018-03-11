import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessPawnPromotion extends JFrame {
	private JLabel label, space, tab, tab1;
	private static final int FRAME_WIDTH = 440;
	private static final int FRAME_HEIGHT = 120;
	JPanel Panel = new JPanel();
    /*--unable to get tile of pawn for pawn promotion from ChessTile
	  --not sure how to get it from the ChessTile class since there is a double parameter specification
	  --ChessTile tile is null by default, so there is null pointer exception */
	ChessTile tile;
	//int row = tile.getRow();
	//int col = tile.getCol();
	
	public ChessPawnPromotion(){
		label = new JLabel("Select Promotion Piece:");
		label.setFont(new Font("Courier New", Font.PLAIN, 30)); 	
		space = new JLabel("     ");
		tab = new JLabel("	");
		tab1 = new JLabel(" ");
		Panel.add(tab);
		Panel.add(label);
		Panel.add(space);
		Panel.add(Rook());
		Panel.add(tab1);
		Panel.add(Knight());
		Panel.add(tab);
		Panel.add(Queen());	
		add(Panel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	
	}
	
	public void close(){
		 WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	private JButton Rook() {
		JButton RookButton = new JButton("Rook");
		RookButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		RookButton.setForeground(Color.darkGray);
		RookButton.setBorder(null);
		class Back implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				if(tile.getPiece().getColorAlignment() == true){
					//replacePiece method might be unusable
					tile.replacePiece(new ChessRook(true));
				}
				else{
					tile.replacePiece(new ChessRook(false));
				}
				close();
			}
	}
		ActionListener listener = new Back();
		RookButton.addActionListener(listener);
		return RookButton;
	}

	private JButton Knight() {
		JButton KnightButton = new JButton("Knight");
		KnightButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		KnightButton.setForeground(Color.darkGray);
		KnightButton.setBorder(null);
		class Back implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				if(tile.getPiece().getColorAlignment() == true){
					//replacePiece method might be unusable
					tile.replacePiece(new ChessKnight(true));
				}
				else{
					tile.replacePiece(new ChessKnight(false));
				}
				close();	
			}
	}
		ActionListener listener = new Back();
		KnightButton.addActionListener(listener);
		return KnightButton;
	}
	
	private JButton Queen() {
		JButton QueenButton = new JButton("Queen");
		QueenButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		QueenButton.setForeground(Color.darkGray);
		QueenButton.setBorder(null);
		class Back implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				if(tile.getPiece().getColorAlignment() == true){
					//replacePiece method might be unusable
					tile.replacePiece(new ChessQueen(true));
				}
				else{
					tile.replacePiece(new ChessQueen(false));
				}
				close();
			}
	}
		ActionListener listener = new Back();
		QueenButton.addActionListener(listener);
		return QueenButton;
	}
}

