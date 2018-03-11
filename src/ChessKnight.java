import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessKnight extends ChessPiece{
	private static final String PATH_WHITE_IMAGE = "images/chess/w_knight.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_knight.png";
	public ChessKnight(boolean isWhite)
	{
		type = ChessPieceType.KNIGHT;
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
			System.out.println("Knight image not found");
		}
	}

	@Override
	public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
		List<ChessTile> validMoves = new ArrayList<ChessTile>();
		// TODO Auto-generated method stub
		ChessTile tile;
		
		
		
		
		//white knight
		if (colorAlignment) 
		{
			
			for (int row = 0; row < 6; row++) 
			{
				for (int col = 0; col < 6; col++) 
				{
					int dx = 0; int dy = 0; //change in x and y
					int possibleMove = 0;
					tile = board.getBoard()[col][row];
					dx = Math.abs(col - location.getCol());
					dy = Math.abs(row - location.getRow());
					possibleMove = dx + dy;
					if (dx != 0 && dy != 0) {
						if (possibleMove == 3) 
						{
							if (tile.hasPiece()) 
							{
								if (colorAlignment != tile.getPiece().getColorAlignment())
								{
									validMoves.add(tile);
								}
							}
							if (!tile.hasPiece()) 
							{
								validMoves.add(tile);
							}
						
						}
					}
				}//end of inner for loop
			}//end of outer for loop
		}//end of white night
		
		//black knight
		else
		{
				
			for (int row = 0; row < 6; row++) 
			{
				for (int col = 0; col < 6; col++) 
				{
					int dx = 0; int dy = 0; //change in x and y
					int possibleMove = 0;
					tile = board.getBoard()[col][row];
					dx = Math.abs(col - location.getCol());
					dy = Math.abs(row - location.getRow());
					possibleMove = dx + dy;
					if (dx != 0 && dy != 0) {
						if (possibleMove == 3) 
						{
							if (tile.hasPiece()) 
							{
								if (colorAlignment != tile.getPiece().getColorAlignment())
								{
									validMoves.add(tile);
								}
							}
							if (!tile.hasPiece()) 
							{
								validMoves.add(tile);
							}
							
						}
					}
				}//end of inner for loop
			}//end of outer for loop
		}//end of black knight
			
			
			
		
		return validMoves;
	}
}
