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

public class ChessPawnPromotion extends JFrame{
	private JLabel label, space, tab, tab1;
	private static final int FRAME_WIDTH = 440;
	private static final int FRAME_HEIGHT = 120;
	JPanel Panel = new JPanel();
	ChessPanel chesspanel = new ChessPanel();
	
	public ChessPawnPromotion(ChessTile tile){
		label = new JLabel("Select Promotion Piece:");
		label.setFont(new Font("Courier New", Font.PLAIN, 30)); 	
		space = new JLabel("     ");
		tab = new JLabel("	");
		tab1 = new JLabel(" ");
		Panel.add(tab);
		Panel.add(label);
		Panel.add(space);
		Panel.add(Rook(tile));
		Panel.add(tab1);
		Panel.add(Knight(tile));
		Panel.add(tab);
		Panel.add(Queen(tile));	
		add(Panel);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	
	}
	
	public void close(){
		 WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
	
	private JButton Rook(ChessTile tile) {
		JButton RookButton = new JButton("Rook");
		RookButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		RookButton.setForeground(Color.darkGray);
		RookButton.setBorder(null);
		
		class R implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//ChessTile tile = new ChessTile(col, row);
				//white pawn
				if(tile.getPiece().getColorAlignment()){
					tile.replacePiece(new ChessRook(true));
				}
				//black pawn
				else{
					tile.replacePiece(new ChessRook(false));
				}
				close();
			}
	}
		ActionListener listener = new R();
		RookButton.addActionListener(listener);
		return RookButton;
	}

	private JButton Knight(ChessTile tile) {
		JButton KnightButton = new JButton("Knight");
		KnightButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		KnightButton.setForeground(Color.darkGray);
		KnightButton.setBorder(null);
		
		class K implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//ChessTile tile = new ChessTile(col, row);
				//white pawn
				if(tile.getPiece().getColorAlignment()){
					tile.replacePiece(new ChessKnight(true));
				}
				//black pawn
				else{
					tile.replacePiece(new ChessKnight(false));
				}
				close();
			}
	}
		ActionListener listener = new K();
		KnightButton.addActionListener(listener);
		return KnightButton;
	}
	
	private JButton Queen(ChessTile tile) {
		JButton QueenButton = new JButton("Queen");
		QueenButton.setFont(new Font("Courier New", Font.PLAIN, 20));
		QueenButton.setForeground(Color.darkGray);
		QueenButton.setBorder(null);
		
		class Q implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				//ChessTile tile = new ChessTile(col, row);
				//white pawn
				if(tile.getPiece().getColorAlignment()){
					tile.replacePiece(new ChessQueen(true));
				}
				//black pawn
				else{
					tile.replacePiece(new ChessQueen(false));
				}
             close();
		 	}
	}
		repaint();
		ActionListener listener = new Q();
		QueenButton.addActionListener(listener);
		return QueenButton;
	}
}

