
public class ChessBoard {
	private final int size = 6;
	private ChessTile[][] board;
	private boolean isWhiteTurn;
	public ChessBoard()
	{
		board = new ChessTile[size][size];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				board[j][i] = new ChessTile(i, j);
			}
		}
		
		for(int j = 0; j < size; j++)
		{
			board[j][4].replacePiece(new ChessPawn(true));
			board[j][1].replacePiece(new ChessPawn(false));
		}
		isWhiteTurn = true;
	}
	
	public ChessTile[][] getBoard()
	{
		return board;
	}
	
	public void changeTurn()
	{
		isWhiteTurn = !isWhiteTurn;
	}
	
	public boolean isWhiteTurn()
	{
		return isWhiteTurn;
	}
}
