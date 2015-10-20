package graph;

/*This vertex class is used to create the vertices for our graph.
 * 
 * It is constructed by specifying the label--look at constructor.
 * 
 * vertexCell: Represents the cell assigned to it, which is responsible
 *for drawing it on the JPanel. 
 * 
 * parent: It is used to assign a parent vertex for very vertex we create in our graph
 * -
 * wasVisited : Used to mark it as visited(used during dfs and bfs)
 * ---------------------------------------------------------------
 * Use:
 * 1.initializeGraphAndCreateCells,initializeNewGraphAndCreateCells methods
 * in Graph class create (n*n-1) vertices every time a user enter n value.
 * 
 */



public class Vertex {

	
// label for a vertex	
public int label;
// visiting variable
public boolean wasVisited ;
//parent for the vertex
public Vertex parent ;
//cell that belongs to its respective vertex
private Cell vertexCell ;

public Cell getVertexCell() {
	return vertexCell;
}

public void setVertexCell(Cell vertexCell) {
	this.vertexCell = vertexCell;
}

public Vertex(int label){
	
	this.label = label ;
	this.wasVisited = false ;
	this.parent = null;
	
   }
}
