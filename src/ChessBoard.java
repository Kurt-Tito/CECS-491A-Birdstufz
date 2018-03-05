
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
		
		// Add pawns
		for(int j = 0; j < size; j++)
		{
			board[j][4].replacePiece(new ChessPawn(true));
			board[j][1].replacePiece(new ChessPawn(false));
		}
		// Add bishops
		board[2][5].replacePiece(new ChessBishop(true));
		board[3][5].replacePiece(new ChessBishop(true));
		board[2][0].replacePiece(new ChessBishop(false));
		board[3][0].replacePiece(new ChessBishop(false));
		//Add knights
		board[1][5].replacePiece(new ChessKnight(true));
		board[4][5].replacePiece(new ChessKnight(true));
		board[1][0].replacePiece(new ChessKnight(false));
		board[4][0].replacePiece(new ChessKnight(false));
		// Add rooks
		board[0][5].replacePiece(new ChessRook(true));
		board[5][5].replacePiece(new ChessRook(true));
		board[0][0].replacePiece(new ChessRook(false));
		board[5][0].replacePiece(new ChessRook(false));
		
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
