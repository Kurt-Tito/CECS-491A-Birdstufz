import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
	
	private void movePiece(ChessTile fromTile, ChessTile toTile)
	{
		ChessPiece p = fromTile.getPiece();
		toTile.replacePiece(p);
		fromTile.replacePiece(null);
	}
	
	public boolean pieceHasTurn(ChessTile tile)
	{
		if(tile.hasPiece())
			return tile.getPiece().getColorAlignment() == isWhiteTurn;
		return false;
	}
	public boolean doTurn(ChessTile fromTile, ChessTile toTile)
	{
		if(pieceHasTurn(fromTile))
		{
			if(fromTile.getPiece().getValidMoves(this, fromTile).contains(toTile))
			{
				movePiece(fromTile, toTile);
				changeTurn();
				return true;
			}
			else 
			{
				System.out.println("Invalid path");
				return false;
			}
			
		}
		else System.out.println("That piece doesn't belong to you");
		return false;
	}
	
	public boolean hasCheck(boolean color)
	{
		//Find king
		ChessTile kingPos = null;
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				ChessTile tile = board[j][i];
				if(tile.hasPiece() && tile.getPiece().getColorAlignment() == color  &&
						tile.getPiece().type == ChessPieceType.KING)
				{
					kingPos = tile;
				}
			}
		}
		//Check if king is attacked
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				ChessTile tile = board[j][i];
				if(tile.hasPiece() && tile.getPiece().getColorAlignment() != color)
				{
					if(tile.getPiece().getValidMoves(this, tile).contains(kingPos))
					{
						return true;
					}
				}
			}
		}
		return false;
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
