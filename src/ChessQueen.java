import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessQueen extends ChessPiece {
	private static final String PATH_WHITE_IMAGE = "images/chess/w_queen.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_queen.png";
	
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
		// TODO Auto-generated method stub
		return validMoves;
	}

}
