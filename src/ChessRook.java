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
		// TODO Auto-generated method stub
		return validMoves;
	}
}
