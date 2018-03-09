import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessKing extends ChessPiece{
	private static final String PATH_WHITE_IMAGE = "images/chess/w_king.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_king.png";
	
	public ChessKing(boolean isWhite)
	{
		type = ChessPieceType.KING;
		colorAlignment = isWhite;
		loadImage();
	}
	@Override
	protected void loadImage() {
		// TODO Auto-generated method stub
		try
		{
		if(colorAlignment)
		{
			image = ImageIO.read(new File(PATH_WHITE_IMAGE));
			System.out.println("Loaded white king");
		}
		else
		{
			image = ImageIO.read(new File(PATH_BLACK_IMAGE));
			System.out.println("Loaded black king");
		}
		}catch(IOException e)
		{
			System.out.println("Queen image not found");
		}
	}

	@Override
	public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
		List<ChessTile> validMoves = new ArrayList<ChessTile>();
		ChessTile tile;
		if(colorAlignment) {
			if(location.getRow() > 0)
			{	
				//-------------STANDARD MOVES--------------//
				
				tile = board.getBoard()[location.getCol()][location.getRow()-1];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				tile = board.getBoard()[location.getCol()+1][location.getRow()-1];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				tile = board.getBoard()[location.getCol()+1][location.getRow()];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				tile = board.getBoard()[location.getCol()-1][location.getRow()];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				tile = board.getBoard()[location.getCol()-1][location.getRow()-1]; 
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				//BACKWARD MOVES
				if(location.getRow() != board.getBoard().length-1)
				{	
					//Check down-left
					tile = board.getBoard()[location.getCol()-1][location.getRow()+1]; 
					if(!tile.hasPiece())
					{
						validMoves.add(tile);
					}
					//Check down
					tile = board.getBoard()[location.getCol()][location.getRow()+1]; 
					if(!tile.hasPiece())
					{
						validMoves.add(tile);
					}
					//Check down-right
					tile = board.getBoard()[location.getCol()+1][location.getRow()+1]; 
					if(!tile.hasPiece())
					{
						validMoves.add(tile);
					}
				}
				
				//-----------------EAT MOVES--------------------//
				
				//Check up-left
				if(location.getCol() > 0)
				{
					tile = board.getBoard()[location.getCol()-1][location.getRow()-1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//Check up
				if(location.getCol() < board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()][location.getRow()-1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//Check up-right diagonal
				if(location.getCol() < board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()+1][location.getRow()-1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//Check left 
				if(location.getCol() < board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()-1][location.getRow()];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//Check right 
				if(location.getCol() < board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()+1][location.getRow()];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//BACKWARD MOVES
				if(location.getRow() != board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()-1][location.getRow()+1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
					tile = board.getBoard()[location.getCol()][location.getRow()+1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
					tile = board.getBoard()[location.getCol()+1][location.getRow()+1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
			}
			
		}
		else
		{
			if(location.getRow() < board.getBoard()[0].length - 1)
			{
				tile = board.getBoard()[location.getCol()][location.getRow()+1];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				//Check left diagonal
				if(location.getCol() > 0)
				{
					tile = board.getBoard()[location.getCol()-1][location.getRow()+1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
				//Check right diagonal
				if(location.getCol() < board.getBoard().length - 1)
				{
					tile = board.getBoard()[location.getCol()+1][location.getRow()+1];
					if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
							validMoves.add(tile);
						}
					}
				}
			}
		}
		// TODO Auto-generated method stub
		return validMoves;
	}

}
