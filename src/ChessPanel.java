import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class ChessPanel extends GamePanel implements MouseListener, MouseMotionListener{
	private final int TILE_SIZE = 100;
	private ChessBoard board;
	private ChessTile tileSelected;
	private List<ChessTile> validMoves;
	public ChessPanel()
	{
		board = new ChessBoard();
		setBackground(new Color(211, 116, 38));
		setPreferredSize(new Dimension(600, 600));
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < board.getBoard()[0].length; i++)
		{
			for(int j = 0; j < board.getBoard().length; j++)
			{
				g2.setColor(board.getBoard()[j][i].getColor());
				g2.fillRect(TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE);
				ChessPiece piece = board.getBoard()[j][i].getPiece();
				if(piece != null)
				{
					g2.drawImage(piece.getPieceImage(), TILE_SIZE * j, TILE_SIZE * i, TILE_SIZE, TILE_SIZE, null);
				}
			}
		}
		
		if(tileSelected != null)
		{
			g2.setColor(Color.green);
			g2.drawRect(tileSelected.getCol() * TILE_SIZE, tileSelected.getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			g2.setColor(Color.red);
			for(int i = 0; i < validMoves.size(); i++)
			{
				ChessTile tile = validMoves.get(i);
				g2.fillOval(tile.getCol() * TILE_SIZE + (TILE_SIZE/3), tile.getRow() * TILE_SIZE + (TILE_SIZE/3), TILE_SIZE/3, TILE_SIZE/3);
			}
		}
		
		if(board.isWhiteTurn())
		{
			g2.setColor(Color.white);
		}
		else
		{
			g2.setColor(Color.black);
		}
		g2.fillRect(0, 0, TILE_SIZE/10, TILE_SIZE/10);
	}
	
	public void selectTile(ChessTile selectedTile)
	{
		if(selectedTile.hasPiece())
		{
			tileSelected = selectedTile;
			validMoves = tileSelected.getPiece().getValidMoves(board, tileSelected);
		}
		
	}
	
	public void deselectTile()
	{
		tileSelected = null;
		validMoves = null;
	}
	
	public ChessTile getTileOnClick(int x, int y)
	{
		return board.getBoard()[x/100][y/100];
	}

	@Override
	public void reset() {
		board = new ChessBoard();
		tileSelected = null;
		validMoves = null;
		
	}

	@Override
	public void addListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(ActionListener al) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ChessTile tile = getTileOnClick(e.getX(), e.getY());
		// TODO Auto-generated method stub
		if(tileSelected == null)
		{
			if(tile.hasPiece())
			{
				if(tile.getPiece().getColorAlignment() == board.isWhiteTurn())
				{
					selectTile(tile);
				}
			}
		}
		else if(tileSelected.equals(tile))
		{
			deselectTile();
		}
		else
		{
			if(validMoves.contains(tile))
			{
				tile.replacePiece(tileSelected.getPiece());
				tileSelected.replacePiece(null);
				deselectTile();
				board.changeTurn();
			}
			else
			{
				System.out.println("That is not a valid move!");
			}
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
