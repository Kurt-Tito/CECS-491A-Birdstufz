package CECS491A;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessQueen extends ChessPiece {
	private static final String PATH_WHITE_IMAGE = "images/chess/w_queen.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_queen.png";
	private static final int MAX_DISTANCE = 2;
	
	public ChessQueen(boolean isWhite)
	{
		type = ChessPieceType.QUEEN;
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
			System.out.println("Loaded white queen");
		}
		else
		{
			image = ImageIO.read(new File(PATH_BLACK_IMAGE));
			System.out.println("Loaded black queen");
		}
		}catch(IOException e)
		{
			System.out.println("Queen image not found");
		}
	}

	@Override
	public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
		List<ChessTile> validMoves = new ArrayList<ChessTile>();
		// Check NW
		boolean blocked = false;
		int dist = 1;
		while(!blocked && dist <= MAX_DISTANCE)
		{
			int x = location.getCol() - dist;
			int y = location.getRow() - dist;
			if(x >= 0 && y >= 0)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// Check NE
		blocked = false;
		dist = 1;
		while(!blocked && dist <= MAX_DISTANCE)
		{
			int x = location.getCol() + dist;
			int y = location.getRow() - dist;
			if(x < board.getBoard()[0].length && y >= 0)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// Check SW
		blocked = false;
		dist = 1;
		while(!blocked && dist <= MAX_DISTANCE)
		{
			int x = location.getCol() - dist;
			int y = location.getRow() + dist;
			if(x >= 0&& y < board.getBoard().length )
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// Check SE
		blocked = false;
		dist = 1;
		while(!blocked && dist <= MAX_DISTANCE)
		{
			int x = location.getCol() + dist;
			int y = location.getRow() + dist;
			if(x < board.getBoard()[0].length && y < board.getBoard().length)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		//Check N
		int maxDistance = 1;
		blocked = false;
		dist = 1;
		while(!blocked && dist <= maxDistance)
		{
			int x = location.getCol();
			int y = location.getRow() - dist;
			if(y >= 0)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// Check S
		blocked = false;
		dist = 1;
		while(!blocked && dist <= maxDistance)
		{
			int x = location.getCol();
			int y = location.getRow() + dist;
			if(y < board.getBoard().length)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// Check E
		blocked = false;
		dist = 1;
		while(!blocked && dist <= maxDistance)
		{
			int x = location.getCol() + dist;
			int y = location.getRow();
			if(x < board.getBoard().length)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		//Check W
		blocked = false;
		dist = 1;
		while(!blocked && dist <= maxDistance)
		{
			int x = location.getCol() - dist;
			int y = location.getRow();
			if(x >= 0)
			{
				ChessTile tile = board.getBoard()[x][y];
				if(tile.hasPiece())
				{
					blocked = true;
					if(tile.getPiece().colorAlignment != colorAlignment)
					{
						validMoves.add(tile);
					}
				}
				else
				{
					validMoves.add(tile);
				}
			}
			dist++;
		}
		// TODO Auto-generated method stub
		return validMoves;
	}

}
