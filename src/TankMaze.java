import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;

public class TankMaze {
	public int size;
	private ArrayList<Edge> paths;
	public ArrayList<Edge> walls;
	public ArrayList<Node> nodes;
	public TankMaze()
	{
		size = 11;
		nodes= new ArrayList<Node>();
		paths = new ArrayList<Edge>();
		walls = new ArrayList<Edge>();
		
		initializeMaze();
		
	}
	
	private Node getNode(int x, int y)
	{
		Node n = nodes.get((nodes.indexOf(new Node(x,y))));
		return n;
	}
	
	public void initializeMaze()
	{
		for(int i = 0; i < size; i++)	//Add nodes
		{
			for(int j = 0; j < size; j++) {
				Node node = new Node(j,i);
				nodes.add(node);
			}
		}
		
		for(int i = 0; i < size; i++)	//Add wall segments between all nodes
		{
			for(int j = 0; j < size; j++) {
				if(j < size-1)
				{
					Node n1 = getNode(j,i);
					Node n2 = getNode(j+1,i);
					walls.add(new Edge(n1,n2));
				}
				if(i < size-1)
				{
					Node n1 = getNode(j,i);
					Node n2 = getNode(j,i+1);
					walls.add(new Edge(n1,n2));
				}
				
			}
		}
		addCenter();
		addInnerCorridors();
		addOuterCorridors();
		
		
		ArrayList<Node> area1 = getMazeArea(1,1,4,4);	//Generate 4 inner maze areas
		ArrayList<Node> area2 = getMazeArea(6,6,9,9);
		ArrayList<Node> area3 = getMazeArea(1,6,4,9);
		ArrayList<Node> area4 = getMazeArea(6,1,9,4);
		area1.remove(new Node(4,4));
		area2.remove(new Node(6,6));
		area3.remove(new Node(4,6));
		area4.remove(new Node(6,4));
		generateMazeArea(area1);
		generateMazeArea(area2);
		generateMazeArea(area3);
		generateMazeArea(area4);
		
		addMazeEntrances();
	}
	private void addCenter()
	{
		for(int i = 4; i < size - 4; i++)	//Remove center square walls
		{
			for(int j = 4; j < size-4; j++)
			{
				if(j < size-5)
				{
					Node n1 = getNode(j,i);
					Node n2 = getNode(j+1,i);
					walls.remove(new Edge(n1,n2));
				}
				if(i < size-5)
				{
					Node n1 = getNode(j,i);
					Node n2 = getNode(j,i+1);
					walls.remove(new Edge(n1,n2));
				}
			}
		}
	}
	
	private void addInnerCorridors()
	{
		for(int i = 0; i < size - 1; i++)	//Remove center corridor walls
		{
			
				Node node1, node2;
				/**
				node1 = getNode(i, 4);
				node2 = getNode(i, 5);
				walls.remove(new Edge(node1, node2));
				node1 = getNode(4,i);
				node2 = getNode(5, i);
				walls.remove(new Edge(node1, node2));
				
				node1 = getNode(i, 4);
				node2 = getNode(i+1, 4);
				walls.remove(new Edge(node1, node2));
				node1 = getNode(4,i);
				node2 = getNode(4, i+1);
				walls.remove(new Edge(node1, node2));
				*/
				
				node1 = getNode(i, 5);
				node2 = getNode(i+1, 5);
				walls.remove(new Edge(node1, node2));
				node1 = getNode(5,i);
				node2 = getNode(5, i+1);
				walls.remove(new Edge(node1, node2));
		}
	}
	
	private void addOuterCorridors()
	{
		for(int i = 0; i < size - 1; i++)	//Remove outer corridor walls
		{
			Node node1, node2;
			node1 = getNode(i,0);
			node2 = getNode(i+1, 0);
			walls.remove(new Edge(node1, node2));
			
			node1 = getNode(i,size-1);
			node2 = getNode(i+1, size-1);
			walls.remove(new Edge(node1, node2));
			
			node1 = getNode(0,i);
			node2 = getNode(0, i+1);
			walls.remove(new Edge(node1, node2));
			
			node1 = getNode(size-1,i);
			node2 = getNode(size-1, i+1);
			walls.remove(new Edge(node1, node2));
		}
	}
	
	private void addMazeEntrances()
	{
		Random random = new Random(System.nanoTime());
		int rnum, num1, num2;
		rnum = random.nextInt(4);
		num1 = rnum + 1; num2 = 0;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(4);
		num1 = rnum + 6; num2 = 0;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(4);
		num1 = rnum + 1; num2 = size-2;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(4);
		num1 = rnum + 6; num2 = size-2;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		
		rnum = random.nextInt(4);
		num1 = rnum + 1; num2 = 0;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(4);
		num1 = rnum + 6; num2 = 0;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(4);
		num1 = rnum + 1; num2 = size-2;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(4);
		num1 = rnum + 6; num2 = size-2;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		
		rnum = random.nextInt(3);
		num1 = rnum + 1; num2 = 4;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(3);
		num1 = rnum + 7; num2 = 4;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(3);
		num1 = rnum + 1; num2 = size-6;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		rnum = random.nextInt(3);
		num1 = rnum + 7; num2 = size-6;
		walls.remove(new Edge(getNode(num1,num2),getNode(num1, num2+1)));
		
		rnum = random.nextInt(2);
		num1 = rnum + 1; num2 = 4;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(2);
		num1 = rnum + 7; num2 = 4;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(2);
		num1 = rnum + 1; num2 = size-6;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
		rnum = random.nextInt(2);
		num1 = rnum + 7; num2 = size-6;
		walls.remove(new Edge(getNode(num2,num1),getNode(num2+1, num1)));
	}
	
	/**
	public void generateMaze()
	{
		walls = new ArrayList<Edge>();
		paths = new ArrayList<Edge>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<HashSet> regions = new ArrayList<HashSet>();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(maze[j][i] == true)
				{
					Point pt1 = new Point(j, i);
					if(i != size-1 && maze[j][i+1] == true)
					{
						Point pt2 = new Point(j, i+1);
						Edge edge = new Edge(pt1, pt2);
						edges.add(edge);
					}
					if(j != size-1 && maze[j+1][i] == true)
					{
						Point pt2 = new Point(j+1, i);
						Edge edge = new Edge(pt1, pt2);
						edges.add(edge);
					}
					
					
				}
			}
		}
		System.out.println("Edges!!!! " + edges.size());
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				Point pt = new Point(j, i);
				HashSet<Point> newRegion = new HashSet<Point>();
				newRegion.add(pt);
				regions.add(newRegion);
			}
			
		}
		
		while(edges.size() > 0 )
		{
			Random random = new Random(System.nanoTime());
			int num = random.nextInt(edges.size());
			Edge edge = edges.remove(num);
			Point pt1 = edge.getPoint1();
			Point pt2 = edge.getPoint2();
			System.out.println(pt1 + "-------" + pt2);
			HashSet<Point> region1 = null, region2 = null;
			System.out.println(regions.size());
			for(int i = 0; i < regions.size(); i++)
			{
				if(regions.get(i).contains(pt1))
				{
					region1 = regions.get(i);
				}
				if(regions.get(i).contains(pt2))
				{
					region2 = regions.get(i);
				}
			}
			if(region1 != region2)
			{
				for(int i = 0; i < regions.size(); i++)
				{
					System.out.print(Arrays.toString(regions.get(i).toArray()) + "           ");
				}
				System.out.println("");
				System.out.println(Arrays.toString(region1.toArray()));
				System.out.println(Arrays.toString(region2.toArray()));
				region1.addAll(region2);
				regions.remove(region2);
				System.out.println(Arrays.toString(region1.toArray()));
				paths.add(edge);
			}
			else
			{
				walls.add(edge);
			}
		}
	}
	**/
	public ArrayList<Node> getMazeArea(int x1, int y1, int x2, int y2)
	{
		ArrayList<Node> area = new ArrayList<Node>();
		for(int i = y1; i <= y2; i++)
		{
			for(int j = x1; j <= x2; j++)
			{
				area.add(nodes.get(nodes.indexOf(new Node(j, i))));
			}
		}
		return area;
	}
	
	
	
	public void generateMazeArea(ArrayList<Node> nodes)
	{
		ArrayList<Edge> mazeWalls = new ArrayList<Edge>();
		ArrayList<Edge> mazePaths = new ArrayList<Edge>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(Node node1: nodes)
		{
			for(Node node2: nodes)
			{
				if(node1.getDistance(node2) == 1)
				{
					if(node1.compareTo(node2) == -1)
					{
						Edge edge = new Edge(node1, node2);	
						edges.add(edge);
						walls.remove(edge);
						paths.remove(edge);
					}
				}
			}
		}
		System.out.println(edges.size());
		
		ArrayList<HashSet> regions = new ArrayList<HashSet>();
		for(Node node: nodes)
		{
			HashSet<Node> newRegion = new HashSet<Node>();
			newRegion.add(node);
			regions.add(newRegion);
		}
		
		while(edges.size() > 0 )
		{
			Random random = new Random(System.nanoTime());
			int num = random.nextInt(edges.size());
			Edge edge = edges.remove(num);
			Node node1 = edge.getNode1();
			Node node2 = edge.getNode2();
			HashSet<Node> region1 = null, region2 = null;
			for(int i = 0; i < regions.size(); i++)
			{
				if(regions.get(i).contains(node1))
				{
					region1 = regions.get(i);
				}
				if(regions.get(i).contains(node2))
				{
					region2 = regions.get(i);
				}
			}
			if(region1 != region2)
			{
				region1.addAll(region2);
				regions.remove(region2);
				mazePaths.add(edge);
			}
			else
			{
				mazeWalls.add(edge);
			}
		}
		
		paths.addAll(mazePaths);
		walls.addAll(mazeWalls);
	}
	
	public static void main(String[] args)
	{
		TankMaze maze = new TankMaze();
		System.out.println(maze.paths);
		System.out.println(maze.paths.size());
		System.out.println(maze.walls.size());
	}
}