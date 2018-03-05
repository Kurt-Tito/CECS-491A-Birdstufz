import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ChessBishop extends ChessPiece {
	private static final String PATH_WHITE_IMAGE = "images/chess/w_bishop.png";
	private static final String PATH_BLACK_IMAGE = "images/chess/b_bishop.png";
	public ChessBishop(boolean isWhite)
	{
		type = ChessPieceType.BISHOP;
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
			System.out.println("Bishop image not found");
		}
	}

	@Override
	public List<ChessTile> getValidMoves(ChessBoard board, ChessTile location) {
		List<ChessTile> validMoves = new ArrayList<ChessTile>();
		// TODO Auto-generated method stub
		return validMoves;
	}

}
