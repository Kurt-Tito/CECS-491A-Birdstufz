package CECS491A;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessRook extends ChessPiece {
	private static final String PATH_WHITE_IMAGE = "images/chess/w_rook.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_rook.png";
	public ChessRook(boolean isWhite)
	{
		type = ChessPieceType.ROOK;
		colorAlignment = isWhite;
		loadImage();
	}

	@Override
	protected void loadImage() {
		try
		{
		if(colorAlignment)
		{
			image = ImageIO.read(new File(PATH_WHITE_IMAGE));
		}
		else
		{
			image = ImageIO.read(new File(PATH_BLACK_IMAGE));
		}
		}catch(IOException e)
		{
			System.out.println("Rook image not found");
		}
	}

	@Override
		public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
			List<ChessTile> validMoves = new ArrayList<ChessTile>();
			ChessTile tile;
			//south
			if(location.getRow() < 5)
			{
				tile = board.getBoard()[location.getCol()][location.getRow() + 1];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				else if(tile.hasPiece())
				{
					if(colorAlignment != tile.getPiece().getColorAlignment())
					{
						validMoves.add(tile);
					}
				}
			
				if(location.getRow() < 4)
				{
					tile = board.getBoard()[location.getCol()][location.getRow() + 2];
					if(!tile.hasPiece())
					{
					 tile = board.getBoard()[location.getCol()][location.getRow() + 1];
					  if(!tile.hasPiece())
					   {
						tile = board.getBoard()[location.getCol()][location.getRow() + 2];
						validMoves.add(tile);
					   }
					}
					else if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
						 tile = board.getBoard()[location.getCol()][location.getRow() + 1];
						  if(!tile.hasPiece())
						  {
						   tile = board.getBoard()[location.getCol()][location.getRow() + 2];
						   validMoves.add(tile);
						  }
						}
					}
				}
			}
			 //north
				if(location.getRow() > 0)
					{
						tile = board.getBoard()[location.getCol()][location.getRow() - 1];
						if(tile.hasPiece())
						{
							if(colorAlignment != tile.getPiece().getColorAlignment())
							{
								validMoves.add(tile);
							}
						}
						else if(!tile.hasPiece())
						{
							validMoves.add(tile);
						}
					
					
				if(location.getRow() > 1)
				{
					tile = board.getBoard()[location.getCol()][location.getRow() - 2];
					if(!tile.hasPiece())
					{
						tile = board.getBoard()[location.getCol()][location.getRow() - 1];
						if(!tile.hasPiece())
						{
						 tile = board.getBoard()[location.getCol()][location.getRow() - 2];
						 validMoves.add(tile);
						}
					}
					else if(tile.hasPiece())
					{
						if(colorAlignment != tile.getPiece().getColorAlignment())
						{
						 tile = board.getBoard()[location.getCol()][location.getRow() - 1];
						  if(!tile.hasPiece())
						  {
						   tile = board.getBoard()[location.getCol()][location.getRow() - 2];
						   validMoves.add(tile);
						  }
						}
					}
				}
			}
				
				
				
				   //east
					if(location.getCol() < 5)
					{
						tile = board.getBoard()[location.getCol() + 1][location.getRow()];
						if(!tile.hasPiece())
						{
							validMoves.add(tile);
						}
						else if(tile.hasPiece())
						{
						 if(colorAlignment != tile.getPiece().getColorAlignment())
						  {
						   validMoves.add(tile);
						  }
						}
					
					if(location.getCol() < 4)
						{
						tile = board.getBoard()[location.getCol() + 2][location.getRow()];
						  if(!tile.hasPiece())
							{
							tile = board.getBoard()[location.getCol() + 1][location.getRow()];
							 if(!tile.hasPiece())
							 {
							  tile = board.getBoard()[location.getCol() + 2][location.getRow()];
							  validMoves.add(tile);
							 }
							}
						  else if(tile.hasPiece())
							{
							 if(colorAlignment != tile.getPiece().getColorAlignment())
							 {
							  tile = board.getBoard()[location.getCol() + 1][location.getRow()];
							  if(!tile.hasPiece())
							  {
							   tile = board.getBoard()[location.getCol() + 2][location.getRow()];
							   validMoves.add(tile);
							  }
							}
						}
					}
				}				
					//west
					if(location.getCol() > 0)
					{
						tile = board.getBoard()[location.getCol()-1][location.getRow()];
						if(tile.hasPiece())
						{
							if(colorAlignment != tile.getPiece().getColorAlignment())
							{
								validMoves.add(tile);
							}
						}
						else if(!tile.hasPiece())
						{
							validMoves.add(tile);
						}
	
					
					
					if(location.getCol() > 1)
					{
						tile = board.getBoard()[location.getCol() - 2][location.getRow()];
						if(!tile.hasPiece())
						{
							tile = board.getBoard()[location.getCol() - 1][location.getRow()];
							if(!tile.hasPiece()){
							tile = board.getBoard()[location.getCol() - 2][location.getRow()];
							validMoves.add(tile);
							}
						}
						else if(tile.hasPiece())
						{
							if(colorAlignment != tile.getPiece().getColorAlignment())
							{
							 tile = board.getBoard()[location.getCol() - 1][location.getRow()];
							  if(!tile.hasPiece())
							  {
							   tile = board.getBoard()[location.getCol() - 2][location.getRow()];
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
