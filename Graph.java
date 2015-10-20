package graph;
/*The flow of this project is clear in the constructor of Graph
 * 
 * Brief Explanation:
 * 1.User enters the value of n .
 * 2.Our graph is  n*n . 
 * 3.initializeGraphAndCreateCells() method is called to initialize the 
 * vertices,edges and assign each cell to its respective vertex.
 * 4.dfsRandom() method is called which operates on the graph created.
 *        1.Keeps track of broken walls(cellList),
 *        2.pathVertexListDfs-keeps track of the vertices visited in the 
 *        exact order the algorithm(dfs) visits.Later used to print the order of
 *        visiting the cells.(2nd image).
 * 5.vertexList-contains all the vertices created and respective parents,used later
 * to print the solution to maze.(3rd image).
 * 6.drawDFS()- creates the images on JPanel.
 * 
 * 
 * 7.initializeNewGraphAndCreateCells() method is called to initialize the 
 * vertices,edges and assign each cell to its respective vertex.
 * 		  1.Edges stored in edgesTravelled variable are used to create edges in the graph.
 * 8.bfsRandomNewGraph() method is called to operate on the new graph created.
 * 		  1.Keeps track of broken walls(cellListNewGraph).
 * 		  2.pathVertexListBfsNewGraphkeeps track of the vertices visited in the 
 *        exact order the algorithm(bfs) visits.Later used to print the order of
 *        visiting the cells.(2nd image).
 * 9.vertexListNewGraph-contains all the vertices created and respective parents,used later
 * to print the solution to maze.(3rd image).
 * 10.drawBFS()-creates the images on JPanel.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Graph {
	// maximum possible vertices in the graph
	private final int MAX_VERTS = 120;
	// user entered row
	private int userRow ;
	// user entered column
	private int userCol ;
	// magnification of the displayed maze
	private int magnification ;
	
	
	
	// list of all cells 
	private Cell[] cellList;
	// array of Vertices
	private Vertex[] vertexList;
	// # of vertices currently in the graph
	private int nVerts;
	// adjMat to store the edges information
	private int adjMat[][];
	// a list of adjacent vertices in the path of algorithm
	private Vertex[] pathVertexListDfs ;
	// initialize a stack for DFS
	private Stack theStack;
	
	
	//traversed edges in DfsRandom() method are stored to create a new graph
	private Edge[] edgesTravelled ;
	int edgesTravelledCount ;
	
	
	//holds the list of cells
	private Cell[] cellListNewGraph;
	// new graph vertexList
	private Vertex[] vertexListNewGraph;
	//# of vertices count
	private int nVertsNewGraph ;
	//adjacency matrix to store the edges information
	private int[][] adjMatNewGraph ;
	//holds the vertices in the path while doing BFS
	private Vertex[] pathVertexListBfsNewGraph;
	//Queue for BFS
	private Queue theQueueNewGraph ;
	
	
	

	public Graph(int x , int y) {
		userRow = x ;
		userCol = y ;
		vertexList = new Vertex[MAX_VERTS];
		vertexListNewGraph = new Vertex[MAX_VERTS];
		cellList = new Cell[MAX_VERTS];
		cellListNewGraph = new Cell[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		adjMatNewGraph = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		nVertsNewGraph = 0;
		theStack = new Stack();
		theQueueNewGraph = new Queue();
		pathVertexListDfs = new Vertex[MAX_VERTS] ;
		pathVertexListBfsNewGraph = new Vertex[MAX_VERTS];
		edgesTravelled = new Edge[MAX_VERTS];
		edgesTravelledCount = 0;
		
		// set adjMat to zero initially
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMat[i][j] = 0;
			}
		}
		// set adjMat to zero initially for new graph
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int j = 0; j < MAX_VERTS; j++) {
				adjMatNewGraph[i][j] = 0;
			}
		}

		//running the dfs and bfs
		initializeGraphAndCreateCells(x,y);
		dfsRandom();	
		
		initializeNewGraphAndCreateCells(x,y);
		bfsRandomNewGraph();	
	}

	// adding a vertex

	public void addVertex(int lab) {

		vertexList[nVerts++] = new Vertex(lab);

	}
	
	// adding a vertex to new graph
	public void addVertexNewGraph(int lab){
		vertexListNewGraph[nVertsNewGraph++] = new Vertex(lab);
	}

	// adding an edge
	public void addEdge(int start, int end) {

		adjMat[start][end] = 1;
		adjMat[end][start] = 1;

	}
	
	// adding an edge to newgraph
	public void addEdgeNewGraph(int start , int end){
		adjMatNewGraph[start][end] = 1;
		adjMatNewGraph[end][start] = 1;

	}

	// to display the vartes label
	public void displayVertex(int v) {

		System.out.println(vertexList[v].label);

	}

	// finding unvisted adjacent vertices for a particular vertex???
	public int getAdjUnvisitedVertex(int v) {

		for (int j = 0; j < nVerts; j++) {
			if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
				return j;
			}
		}
		// if no such vertex exists
		return -1;
	}

	// returns a set of vertices adjacent and unvisited to vertex V
	public Vertex[] getAdjUnvisitedVertexAll(Vertex v) {
		// counting number of such vertices
		int i = 0;
		for (int j = 0; j < nVerts; j++) {

			if (adjMat[v.label][j] == 1 && vertexList[j].wasVisited == false) {
				i++;

			}
		}
		if (i == 0) {
			return null;
		}

		else {
			// creating the array with the count, and inserting into them the
			// vertices
			Vertex[] s = new Vertex[i];
			int k = 0;
			for (int j = 0; j < nVerts; j++) {

				if (adjMat[v.label][j] == 1
						&& vertexList[j].wasVisited == false) {
					s[k] = vertexList[j];
					k++;

				}
			}

			return s;
		}
	}
	
	
	// returns a set of vertices adjacent and unvisited to vertex V for  new graph
	public Vertex[] getAdjUnvisitedVertexAllNewGraph(Vertex v) {
		// counting number of such vertices
		int i = 0;
		for (int j = 0; j < nVertsNewGraph; j++) {

			if (adjMatNewGraph[v.label][j] == 1 && vertexListNewGraph[j].wasVisited == false) {
				i++;

			}
		}
		if (i == 0) {
			return null;
		}

		else {
			// creating the array with the count, and inserting into them the
			// vertices
			Vertex[] s = new Vertex[i];
			int k = 0;
			for (int j = 0; j < nVertsNewGraph; j++) {

				if (adjMatNewGraph[v.label][j] == 1
						&& vertexListNewGraph[j].wasVisited == false) {
					s[k] = vertexListNewGraph[j];
					k++;

				}
			}

			return s;
		}
	}
	
	
	
	

 // random DFS used to generate a maze
	public void dfsRandom() {

		// begin at vertex 0
		Vertex initialVertex = vertexList[0];
		// mark it
		initialVertex.wasVisited = true;
		// make parent as null
		initialVertex.parent = initialVertex ;
		// display it
		displayVertex(initialVertex.label);
		// push it onto stack
		theStack.push(initialVertex);
		//variable to track current vertex we are in
		Vertex currentVertex = null;
		//variable to track adjacent vertex we will go from current vertex
		Vertex adjacentVertex = null;
		
		/*starting adjacent element is initial vertex.
		 *This array is used to generate a perfect solution. */ 
		pathVertexListDfs[0] = initialVertex ;
		int adjVertexCount = 1;
		
		
		while (!theStack.isEmpty()) {

			currentVertex = theStack.peek();

			// get all the vertices adjacent to current vertex
			Vertex[] vertexSet = getAdjUnvisitedVertexAll(currentVertex);
			
			if (vertexSet == null) {

				theStack.pop();

			} else {

				// Random Selection of Adjacent vertices
				Random r = new Random();
				int selectIndex = r.nextInt(vertexSet.length);
				adjacentVertex = vertexSet[selectIndex];
				
				// edges travelled are replicated(created) for the new graph, stored in the array below
				edgesTravelled[edgesTravelledCount++] = new Edge(currentVertex.label,adjacentVertex.label);
				
				// mark it as visited
				adjacentVertex.wasVisited = true;
				
				//store in the list the path traversed .
				pathVertexListDfs[adjVertexCount++] = adjacentVertex ; 

				// marked it as parent
				adjacentVertex.parent = currentVertex;

				//finding coordinates of cell
				int xCurrent = currentVertex.label % userRow;
				int yCurrent = currentVertex.label / userRow;
				

				int xAdjacent = adjacentVertex.label % userRow;
				int yAdjacent = adjacentVertex.label / userRow;
				
				// break a wall
				// north
				if ((xCurrent + 1 == xAdjacent) && (yAdjacent == yCurrent)) {
					cellList[currentVertex.label].setEast(0);
					cellList[adjacentVertex.label].setWest(0);

				}

				// east
				if ((xCurrent == xAdjacent) && (yAdjacent == yCurrent + 1)) {

					cellList[currentVertex.label].setSouth(0);
					cellList[adjacentVertex.label].setNorth(0);

				}

				// south
			
				if ((xCurrent == xAdjacent + 1) && (yAdjacent == yCurrent)) {
					cellList[currentVertex.label].setWest(0);
					cellList[adjacentVertex.label].setEast(0);

				}

				// west
				if ((xCurrent == xAdjacent) && (yAdjacent + 1 == yCurrent)) {

					cellList[currentVertex.label].setNorth(0);
					cellList[adjacentVertex.label].setSouth(0);
				}

				// push the currentCell location on the CellStack
				theStack.push(adjacentVertex);

				// display it as (from a to b)
				currentVertex = adjacentVertex;

			}
		}
		
		// setting the entry and exit points open 
		cellList[0].setNorth(0);
		cellList[(userRow * userCol)-1].setSouth(0);

		drawDFS();
		
		//reset all vertices 
		for (int i = 0; i < nVerts; i++) {

			vertexList[i].wasVisited = false;

		}

		
	}
	
	
	// bfsRandomNewGraph() - generates a maze traversal using bfs logic on newGraph
	public void bfsRandomNewGraph(){
		
		
				// begin at vertex 0
				// mark it
				vertexListNewGraph[0].wasVisited = true;
				
				//parent of first element is itself
				vertexListNewGraph[0].parent = vertexListNewGraph[0];
				// display it
				//displayVertex(0);
				// insert in queue
				theQueueNewGraph.insert(vertexListNewGraph[0]);
				
				// to store adjacent vertices to a current vertex
				Vertex[] adjVertex;
				
				// to put all the adjacent vertices in an array List
				ArrayList<Vertex>  trackList ;
				
				pathVertexListBfsNewGraph[0] = vertexListNewGraph[0];
				
				int pathCounter = 1;
				
			
				while (!theQueueNewGraph.isEmpty()) {
					// remove vertex at head
					Vertex headVertex = theQueueNewGraph.remove();
					adjVertex = getAdjUnvisitedVertexAllNewGraph(headVertex);
					if(adjVertex == null){
					continue;	
					}else{
					trackList = new ArrayList<Vertex>(adjVertex.length);
					}
					
					for(int i = 0 ; i < adjVertex.length ; i++){
						trackList.add(adjVertex[i]);
						//System.out.println(adjVertex[i].label);
					}
					
					while (!trackList.isEmpty()) {
						
						Vertex goingToVertex ;
						
						Random r = new Random();
						int sIndex = r.nextInt(trackList.size());
						
						// mark it as visited
						trackList.get(sIndex).wasVisited = true;
						
						//assigning parents to vertices
						trackList.get(sIndex).parent= headVertex;
						
						//pathList
						pathVertexListBfsNewGraph[pathCounter++] = trackList.get(sIndex);
						
						// add to queue
						theQueueNewGraph.insert(trackList.get(sIndex));
						
						
						
						goingToVertex = trackList.get(sIndex);
						
						// finding coordinates of cell
						int xCurrent = headVertex.label % userRow;
						int yCurrent = headVertex.label / userRow;
						

						int xAdjacent = goingToVertex.label % userRow;
						int yAdjacent = goingToVertex.label / userRow;
						
						// breaking walls
						// north
						if ((xCurrent + 1 == xAdjacent) && (yAdjacent == yCurrent)) {
							cellListNewGraph[headVertex.label].setEast(0);
							//System.out.println("final point");
							cellListNewGraph[goingToVertex.label].setWest(0);

						}

						// east
						if ((xCurrent == xAdjacent) && (yAdjacent == yCurrent + 1)) {

							cellListNewGraph[headVertex.label].setSouth(0);
							cellListNewGraph[goingToVertex.label].setNorth(0);

						}

						// south
					
						if ((xCurrent == xAdjacent + 1) && (yAdjacent == yCurrent)) {
							cellListNewGraph[headVertex.label].setWest(0);
							cellListNewGraph[goingToVertex.label].setEast(0);

						}

						// west
						if ((xCurrent == xAdjacent) && (yAdjacent + 1 == yCurrent)) {

							cellListNewGraph[headVertex.label].setNorth(0);
							cellListNewGraph[goingToVertex.label].setSouth(0);
						}

						
						
						trackList.remove(sIndex);
						
						
					}
				}
				
				cellListNewGraph[0].setNorth(0);
				cellListNewGraph[(userRow * userCol)-1].setSouth(0);

				drawBFS();
				
				for (int i = 0; i < nVerts; i++) {

					vertexListNewGraph[i].wasVisited = false;

				}
		
		
	}
	
	
	
	/*method that initializes a graph(VERTICES,EDGES) ,also Each 
	 Vertex is assigned to its respective cell(used to draw) we work on.*/ 
	public void initializeGraphAndCreateCells(int r, int c) {
		
		userRow = r;
		userCol = c ;
		
		// to change the size of display edit this
		magnification = 30;

		// initialize the cell
		int cellCount = 0;
		
		int current ;
		int previous = -1;
		
		int curr;
		int prev = -1 ;
		
	
		
		// set all values to  and generate vertices for graph
		for (int x = 0; x < r; x++) {

			for (int y = 0; y < c; y++) {
				//cells are created
				cellList[cellCount] = new Cell(y * magnification + 30, x * magnification + 30);
				
				//vertex are created
				int v = x * c + y;
				current = v;
				addVertex(v);
				
				//each vertex belongs to a cell
				vertexList[nVerts-1].setVertexCell(cellList[cellCount]);
				
				cellCount = cellCount + 1 ;
				
				//adding edges to the graph
				if(current>=0 && previous>=0){
				addEdge(previous,current);
				}
				
				int vColumn = c*y + x ;
				curr = vColumn ;
				if(curr>=0 && prev>=0){
				addEdge(prev,curr);
				}
				previous = current ;
				
				prev = curr ;
				}
			
			
			 	
			previous = -1;
			current  =  0;
			
			prev = -1;
			curr = 0 ;
		}

	}

	/*method that initializes a newGraph(VERTICES,EDGES) ,also Each 
	 Vertex is assigned to its respective cell(used to draw) we work on.*/ 
	public void initializeNewGraphAndCreateCells(int r , int c){
		
		int cellCount = 0;
		
		for (int x = 0; x < r; x++) {

			for (int y = 0; y < c; y++) {
				cellListNewGraph[cellCount] = new Cell(y * magnification + 30, x * magnification + 30);

				//Vertices are added
				int v = x * c + y;
				addVertexNewGraph(v);
				
				//respective cell
				vertexListNewGraph[nVertsNewGraph-1].setVertexCell(cellListNewGraph[cellCount]);
			
				cellCount = cellCount + 1 ;
			}
		}
		
		// edges are added
		// the array that stored the edgesTravelled,is now used to create edges for the new graph
		for(int i = 0 ; i < edgesTravelledCount ; i++){
			addEdgeNewGraph(edgesTravelled[i].getStart(), edgesTravelled[i].getEnd());
		}
		
		
		
	}
		
	/* 1.Draw a random maze
	 * 2.Its DFS path
	 * 3.Solution  to maze 
	 */
	public void drawDFS() {

		JFrame f = new JFrame();
		f.setSize(480, 480);
		f.setVisible(true);
		f.setTitle("OUTPUT FROM DFS ON CONSTRUCTED GRAPH");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawingDFS n = new DrawingDFS(cellList,userRow,userCol,magnification,vertexList,pathVertexListDfs);
		f.add(n);
		n.drawing();

	}
	
	/* 1.Draw the same random maze as above
	 * 2.Its BFS path
	 * 3.Solution to maze
	 */
	public void drawBFS() {

		JFrame f = new JFrame();
		f.setSize(480, 480);
		f.setVisible(true);
		f.setTitle("OUTPUT FROM BFS ON CONSTRUCTED GRAPH");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawingDFS n = new DrawingDFS(cellListNewGraph,userRow,userCol,magnification,vertexListNewGraph,pathVertexListBfsNewGraph);
		f.add(n);
		n.drawing();

	}
	
	


	public static void main(String[] args) {
		
		
		//user input row and column
		Scanner s = new Scanner(System.in);
		System.out.println("Size of graph n cross n , n is:");
		 int n =s.nextInt();
		 Graph g = new Graph(n,n);
		
		
		
			
			
		
	}
}
