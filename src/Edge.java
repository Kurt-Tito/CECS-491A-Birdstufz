public class Edge {
	Node from, to;
	public Edge(Node from, Node to)
	{
		this.from = from;
		this.to= to;
	}
	
	public Node getNode1()
	{
		return from;
	}
	
	public Node getNode2()
	{
		return to;
	}
	
	public boolean contains(Node Node)
	{
		return from.equals(Node) || to.equals(Node);
	}
	@Override
	public String toString()
	{
		return "[" + from + ", " + to + "]";
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Edge)
		{
			Edge e = (Edge) o;
			return from.equals(e.from) && to.equals(e.to);
		}
		return false;
	}
}
