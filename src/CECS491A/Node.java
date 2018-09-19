package CECS491A;

public class Node {
	private int row, col;
	public Node(int x, int y)
	{
		col = x;
		row = y;
	}
	public double getDistance(Node n)
	{
		return Math.hypot(col-n.col, row-n.row);
	}
	public int compareTo(Node n)
	{
		return (row + col) - (n.row + n.col);
	}
	public int getX()
	{
		return col;
	}
	
	public int getY()
	{
		return row;
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof Node)
		{
			Node n = (Node) o;
			return col == n.col && row == n.row;
		}
		return false;
	}
	
	public String toString()
	{
		return "[" + col + ", " + row + "]";
	}
}
