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
		if(colorAlignment) {
			//white rook north direction
			if(location.getRow() > 0){
				for(int i = 0; i < 6; i++){
				tile = board.getBoard()[location.getCol()][location.getRow()-i];
				System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
				if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
					break;
				}
				else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
						tile != board.getBoard()[location.getCol()][location.getRow()])
				{
					tile = board.getBoard()[location.getCol()][location.getRow()-i+1];
					validMoves.add(tile);
					break;
			  	}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][5]){ //check if row is 5
					validMoves.add(tile);
					break;
				}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][0]){//check if row is 0
					validMoves.add(tile);
					break;
				}
			}
			}
			//white rook south direction
			if(location.getRow() < 5){
				for(int i = 5; i > 0; i--){
				tile = board.getBoard()[location.getCol()][i];
				System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
				if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
					break;
				}
				else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
						tile != board.getBoard()[location.getCol()][location.getRow()])
				{
					tile = board.getBoard()[location.getCol()][location.getRow()-i];
					validMoves.add(tile);
					break;
			  	}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][5]){ //check if row is 5
					validMoves.add(tile);
					break;
				}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][0]){//check if row is 0
					validMoves.add(tile);
					break;
				}
			}
			}
	      /*
		  //white rook west direction
			if(location.getCol() > 0)
			{
				for(int i = 0; i < 6; i++){
					tile = board.getBoard()[location.getCol()-1][location.getRow()];
					System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
					if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
					{
						validMoves.add(tile);
						break;
					}
					else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
							tile != board.getBoard()[location.getCol()][location.getRow()])
					{
						tile = board.getBoard()[location.getCol()-i+1][location.getRow()];
						validMoves.add(tile);
						break;
				  	}
					if(!tile.hasPiece() && tile == board.getBoard()[5][location.getRow()]){ //check if row is 5
						validMoves.add(tile);
						break;
					}
					if(!tile.hasPiece() && tile == board.getBoard()[0][location.getRow()]){//check if row is 0
						validMoves.add(tile);
						break;
					}
				}
			}
			//white rook east direction
			if(location.getCol() < 5){
				for(int i = 5; i > 0; i--){
				tile = board.getBoard()[i][location.getRow()];
				System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
				if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
					break;
				}
				else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
						tile != board.getBoard()[location.getCol()][location.getRow()])
				{
					tile = board.getBoard()[location.getCol()-i][location.getRow()];
					validMoves.add(tile);
					break;
			  	}
				if(!tile.hasPiece() && tile == board.getBoard()[5][location.getRow()]){ //check if row is 5
					validMoves.add(tile);
					break;
				}
				if(!tile.hasPiece() && tile == board.getBoard()[0][location.getRow()]){//check if row is 0
					validMoves.add(tile);
					break;
				}
			}
			}
			*/
		}
		else
		 {
			//black rook south
			if(location.getRow() < 5){
				for(int i = 0; i < 6; i++){
				tile = board.getBoard()[location.getCol()][location.getRow()+i];
				System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
				if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
					break;
				}
				else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
						tile != board.getBoard()[location.getCol()][location.getRow()])
				{
					tile = board.getBoard()[location.getCol()][location.getRow()+i-1];
					validMoves.add(tile);
					break;
			  	}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][5]){ //check if row is 5
					validMoves.add(tile);
					break;
				}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][0]){//check if row is 0
					validMoves.add(tile);
					break;
				} 
			}
			}
			//black rook north
			if(location.getRow() > 0){
				for(int i = 0; i > 5; i++){
				tile = board.getBoard()[location.getCol()][i];
				System.out.println("Row:" + location.getRow() + " Col: " + location.getCol() + " " + i);
				if(tile.hasPiece() && colorAlignment != tile.getPiece().getColorAlignment())
				{
					validMoves.add(tile);
					break;
				}
				else if(tile.hasPiece() && colorAlignment == tile.getPiece().getColorAlignment() && 
						tile != board.getBoard()[location.getCol()][location.getRow()])
				{
					tile = board.getBoard()[location.getCol()][location.getRow()-i+1];
					validMoves.add(tile);
					break;
			  	}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][5]){ //check if row is 5
					validMoves.add(tile);
					break;
				}
				if(!tile.hasPiece() && tile == board.getBoard()[location.getCol()][0]){//check if row is 0
					validMoves.add(tile);
					break;
				} 
			}
			}
		}
		// TODO Auto-generated method stub
		return validMoves;
	}
}
