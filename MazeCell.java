
public class MazeCell {
	private boolean north, south, east, west;
	private boolean hidden;
	public MazeCell(boolean n, boolean s, boolean e, boolean w)
	{
		north = n;
		south = s;
		east = e;
		west = w;
		hidden = true;
	}
	public MazeCell()
	{
		north = south = east = west = false;
		hidden = true;
	}

	public boolean hasWall(Direction d)
	{
		if(d == Direction.NORTH)
		{
			return north;
		}
		if(d == Direction.SOUTH)
		{
			return south;
		}
		if(d == Direction.EAST)
		{
			return east;
		}
		if(d == Direction.WEST)
		{
			return west;
		}
		return true;
	}

	public void setWall(Direction d, boolean state)
	{
		if(d == Direction.NORTH)
		{
			north = state;
		}
		else if(d == Direction.SOUTH)
		{
			south = state;
		}
		else if(d == Direction.EAST)
		{
			east = state;
		}
		else if(d == Direction.WEST)
		{
			west = state;
		}
	}
	public boolean isHidden()
	{
		return hidden;
	}
	public void reveal()
	{
		if(hidden)
			hidden = false;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		if(north)
			s += "N ";
		if(south)
			s += "S ";
		if(east)
			s += "E ";
		if(west)
			s += "W ";
		return s;
	}
}
