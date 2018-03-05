import java.awt.image.BufferedImage;
import java.util.List;

public abstract class ChessPiece {
	protected boolean colorAlignment; 	//false - black, true - white
	protected BufferedImage image;
	protected ChessTile location;
	protected ChessPieceType type;
	public ChessPieceType getType()
	{
		return type;
	}
	public boolean getColorAlignment()
	{
		return colorAlignment;
	}
	public ChessTile getLocation()
	{
		return location;
	}
	public BufferedImage getPieceImage()
	{
		return image;
	}
	protected abstract void loadImage();
	public abstract void move(ChessTile tile);
	public abstract List<ChessTile> getValidMoves(ChessBoard board, ChessTile location);
}
