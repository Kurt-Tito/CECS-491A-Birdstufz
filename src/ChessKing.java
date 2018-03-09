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
		//north
		if(location.getRow() > 0)
		{
			tile = board.getBoard()[location.getCol()][location.getRow()-1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//south
		if(location.getRow() < board.getBoard().length - 1)
		{
			tile = board.getBoard()[location.getCol()][location.getRow()+1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//west
		if(location.getCol() > 0)
		{
			tile = board.getBoard()[location.getCol()-1][location.getRow()];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//east
		if(location.getCol() < board.getBoard().length - 1)
		{
			tile = board.getBoard()[location.getCol()+1][location.getRow()];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//north west
		if(location.getRow() > 0 && location.getCol() > 0)
		{
			tile = board.getBoard()[location.getCol()-1][location.getRow()-1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//north east
		if(location.getRow() > 0 && location.getCol() < board.getBoard().length - 1)
		{
			tile = board.getBoard()[location.getCol()+1][location.getRow()-1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//south west
		if(location.getRow() < board.getBoard().length - 1 && location.getCol() > 0)
		{
			tile = board.getBoard()[location.getCol()-1][location.getRow()+1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		//south east
		if(location.getRow() < board.getBoard().length - 1 && location.getCol() < board.getBoard().length - 1)
		{
			tile = board.getBoard()[location.getCol()+1][location.getRow()+1];
			if(!tile.hasPiece())
			{
				validMoves.add(tile);
			}
			if(tile.hasPiece())
			{
				if(colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
				}
			}
		}
		// TODO Auto-generated method stub
		return validMoves;
	}

}
