import java.awt.BasicStroke;
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
		//setBackground(new Color(211, 116, 38));
		setPreferredSize(new Dimension(board.getBoard().length * TILE_SIZE, board.getBoard()[0].length * TILE_SIZE));
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
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(tileSelected.getCol() * TILE_SIZE, tileSelected.getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			g2.setStroke(new BasicStroke(1));
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
		g2.fillRect(board.getBoard().length * TILE_SIZE - TILE_SIZE/5, 0, TILE_SIZE/5, TILE_SIZE/5);
		g2.setColor(Color.black);
		g2.drawRect(board.getBoard().length * TILE_SIZE - TILE_SIZE/5, 0, TILE_SIZE/5, TILE_SIZE/5);
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
		if(board.isPlaying())
		{
			if(tileSelected == null)
			{
				if(board.pieceHasTurn(tile))
				{
					selectTile(tile);
				}
				else
				{
					System.out.println("That isn't a movable piece");
				}
		
			}
			else if(tileSelected.equals(tile))
			{
				deselectTile();
			}
			else
			{
				ChessStatus status = board.doTurn(tileSelected, tile);
				if(status == ChessStatus.VALID_MOVE)
				{
					deselectTile();
				}
				else if(status == ChessStatus.WHITE_WIN)
				{
					deselectTile();
					System.out.println("White wins");
				}
				else if(status == ChessStatus.BLACK_WIN)
				{
					deselectTile();
					System.out.println("Black wins");
				}
			}
			repaint();
		}
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
