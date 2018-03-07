
public class ChessBoard {
	private final int rows = 6, cols = 6;
	private ChessTile[][] board;
	private boolean isWhiteTurn;
	public ChessBoard()
	{
		board = new ChessTile[cols][rows];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				board[j][i] = new ChessTile(i, j);
			}
		}
		
		// Add pawns
		for(int j = 0; j < cols; j++)
		{
			board[j][rows-2].replacePiece(new ChessPawn(true));
			board[j][1].replacePiece(new ChessPawn(false));
		}
		// Add King
		board[2][rows-1].replacePiece(new ChessQueen(true));
		board[cols-3][rows-1].replacePiece(new ChessKing(true));
		board[2][0].replacePiece(new ChessQueen(false));
		board[cols-3][0].replacePiece(new ChessKing(false));
		//Add knights
		board[1][rows-1].replacePiece(new ChessKnight(true));
		board[cols-2][rows-1].replacePiece(new ChessKnight(true));
		board[1][0].replacePiece(new ChessKnight(false));
		board[cols-2][0].replacePiece(new ChessKnight(false));
		// Add rooks
		board[0][rows-1].replacePiece(new ChessRook(true));
		board[cols-1][rows-1].replacePiece(new ChessRook(true));
		board[0][0].replacePiece(new ChessRook(false));
		board[cols-1][0].replacePiece(new ChessRook(false));
		
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
