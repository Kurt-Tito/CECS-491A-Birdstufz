import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessPawn extends ChessPiece{
	private static final String PATH_WHITE_IMAGE = "images/chess/w_pawn.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_pawn.png";
	
	public ChessPawn(boolean isWhite)
	{
		type = ChessPieceType.PAWN;
		colorAlignment = isWhite;
		loadImage();
	}
	
	@Override
	protected void loadImage()
	{
		try
		{
		if(colorAlignment)
		{
			image = ImageIO.read(new File(PATH_WHITE_IMAGE));
			System.out.println("Loaded pawn");
		}
		else
		{
			image = ImageIO.read(new File(PATH_BLACK_IMAGE));
			System.out.println("Loaded pawn");
		}
		}catch(IOException e)
		{
			System.out.println("Pawn image not found");
		}
	}
	@Override
	public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
		List<ChessTile> validMoves = new ArrayList<ChessTile>();
		ChessTile tile;
		if(colorAlignment) {
			if(location.getRow() > 0)
			{
				tile = board.getBoard()[location.getCol()][location.getRow()-1];
				if(!tile.hasPiece())
				{
					validMoves.add(tile);
				}
				//Check left diagonal
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
				//Check right diagonal
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
