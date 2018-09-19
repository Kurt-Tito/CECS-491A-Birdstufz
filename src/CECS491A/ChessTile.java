package CECS491A;
import java.awt.Color;

public class ChessTile {
	private static final Color white = new Color(255, 234, 195);
	private static final Color black = new Color(196, 140, 76);
	private int row, col;
	private Color color;
	private ChessPiece piece;
	public ChessTile(int row, int col)
	{
		this.row = row;
		this.col = col;
		if((row + col) % 2 == 0)
		{
			color = white;
		}
		else color = black;
	}
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	
	public Color getColor()
	{
		return color;
	}
	public void replacePiece(ChessPiece piece)
	{
		this.piece = piece;
	}
	public ChessPiece getPiece()
	{
		return piece;
	}
	public boolean hasPiece()
	{
		return piece != null;
	}
	
}
