package graph;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


/*DrawingDFS class extends JPanel : is the class used to draw/represent 
 * our graph on the JPanel. 
 * 
 * cellList: List of cells in our graph(one for each vertex).
 * rowValue , colVlaue : user entered n*n .
 *  magFactor : it is used to magnify the image on the JPanel
 * vList : list of vertices in our graph
 * pathList: list of vertices visited in the exact order the algorithm traversed
 * 
 * paintComponent() - is the method used to draw the graph on our JPanel.
 */

public class DrawingDFS extends JPanel {

    public Cell[] cellListt;
    public int rowValue ;
    public int colValue ;
    public int magFactor;
    public Vertex[] vList;
    public Vertex[] pathList ;
    

    public DrawingDFS(Cell[] t,int x,int y,int z,Vertex[] s,Vertex[] s1) {
      
      this.cellListt = t ;
      this.rowValue = x ;
      this.colValue = y ;
      this.magFactor = z ;
      this.vList = s; 
      this.pathList = s1 ;
     

    }

    public void drawing() {
       
        repaint();

    }

    
    // this method is used to draw our maze(Java Swing )
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      
        // g is our graphics object which can be used to draw on our JPanel
        g.setColor(Color.BLACK);
        
        // used to draw 3 images one beside the other 
        int xFinalPosition = cellListt[((rowValue * colValue)-1)].getX();
        int myStartPosition = xFinalPosition + 30 ;
        int myStartPosition2 = ((rowValue)*magFactor)+myStartPosition+30;
        
        
        // drawing the empty maze created
		for (int i = 0; i < ((rowValue * colValue)); i++) {
			
					/*1.Every cell(square) is made up of 4 lines(north,south,east,west) 
					 *2.Depending on the line broken to make the maze, each cell is drawn
					 */
		        	
		            if (cellListt[i].getNorth() == 1) {
		            	// method to draw a line g.drawLine(x1,y1,x2,y2)- draws a line from (x1,y1) to (x2,y2)
		                g.drawLine(cellListt[i].getX(), cellListt[i].getY(), cellListt[i].getX() + magFactor, cellListt[i].getY());
		                
		            }
		            if (cellListt[i].getEast() == 1) {
		            	g.drawLine(cellListt[i].getX() + magFactor, cellListt[i].getY(), cellListt[i].getX() + magFactor, cellListt[i].getY() + magFactor);
		            }
		            if (cellListt[i].getSouth() == 1) {
		            	
		                g.drawLine(cellListt[i].getX() + magFactor, cellListt[i].getY() + magFactor, cellListt[i].getX(), cellListt[i].getY() + magFactor);
		            }
		            if (cellListt[i].getWest() == 1) {
		            	
		                g.drawLine(cellListt[i].getX(), cellListt[i].getY() + magFactor, cellListt[i].getX(), cellListt[i].getY());
		                
		            }
		        }
		
		
		
		
		// draw the order of visiting the maze till we reach the end of the maze
		for (int i = 0; i < ((rowValue * colValue)); i++) {
        	
	            if (cellListt[i].getNorth() == 1) {
	            	
	                g.drawLine(cellListt[i].getX()+myStartPosition , cellListt[i].getY(), cellListt[i].getX() + magFactor+myStartPosition , cellListt[i].getY());
	                
	            }
	            if (cellListt[i].getEast() == 1) {
	            	g.drawLine(cellListt[i].getX() + magFactor+myStartPosition , cellListt[i].getY(), cellListt[i].getX() + magFactor+myStartPosition , cellListt[i].getY() + magFactor);
	            }
	            if (cellListt[i].getSouth() == 1) {
	            	
	                g.drawLine(cellListt[i].getX() + magFactor+myStartPosition , cellListt[i].getY() + magFactor, cellListt[i].getX()+myStartPosition , cellListt[i].getY() + magFactor);
	            }
	            if (cellListt[i].getWest() == 1) {
	            	
	                g.drawLine(cellListt[i].getX()+myStartPosition , cellListt[i].getY() + magFactor, cellListt[i].getX()+myStartPosition , cellListt[i].getY());
	                
	            }
	        }
		
		
			/*1.Using the pathList[] array which consists the list of vertices in order
			 * visited during creation of maze.
			 *2.This array when traversed will give us the order of visiting the vertices,here
			 *  the for loop breaks  when we reach the exit cell.
			 * 3. This for loop marks the order(with 1,2,3...) of visiting on the maze created.
			 */  
		
			g.setColor(Color.BLUE);
       for(int p = 0 ; p < (rowValue * colValue) ; p++ ){
    	   Integer number = p;
    	   String s1 = number.toString() ;
    	   g.drawString(s1,pathList[p].getVertexCell().getX()+magFactor/2+myStartPosition ,pathList[p].getVertexCell().getY()+magFactor/2);
    	   if(pathList[p].label == ((rowValue * colValue)-1)){
   		   break;
   	   		} 
       }
        
		
		
		
		// draw the exact solution of maze
      
		 g.setColor(Color.BLACK);
        for (int i = 0; i < ((rowValue * colValue)); i++) {
        	
            if (cellListt[i].getNorth() == 1) {
            	
                g.drawLine(cellListt[i].getX()+myStartPosition2, cellListt[i].getY(), cellListt[i].getX() + magFactor+myStartPosition2, cellListt[i].getY());
                
            }
            if (cellListt[i].getEast() == 1) {
            	g.drawLine(cellListt[i].getX() + magFactor+myStartPosition2, cellListt[i].getY(), cellListt[i].getX() + magFactor + myStartPosition2, cellListt[i].getY() + magFactor);
            }
            if (cellListt[i].getSouth() == 1) {
            	
                g.drawLine(cellListt[i].getX() + magFactor + myStartPosition2, cellListt[i].getY() + magFactor, cellListt[i].getX() + myStartPosition2, cellListt[i].getY() + magFactor);
            }
            if (cellListt[i].getWest() == 1) {
            	
                g.drawLine(cellListt[i].getX() + myStartPosition2, cellListt[i].getY() + magFactor, cellListt[i].getX() + myStartPosition2, cellListt[i].getY());
                
            }
        }
       
        /*1.The vList consists of all vertex objects in the graph.
         *2.The vertex objects have a parent Vertex assigned to them during traversal.
         *3.So starting at the right bottom , using the parent the while loop traverses
         * to the start.
         */ 
        
        g.setColor(Color.RED);
        int value = -1;
        String s = "X" ;
        Vertex startVertex = vList[(this.rowValue*this.colValue)-1];
   		g.drawString(s,startVertex.getVertexCell().getX()+magFactor/2+myStartPosition2,startVertex.getVertexCell().getY()+magFactor/2);
   		 
        while(value != 0){
        Vertex temp5 = startVertex.parent ;
        value = temp5.label ;
   		g.drawString(s,temp5.getVertexCell().getX()+magFactor/2+myStartPosition2,temp5.getVertexCell().getY()+magFactor/2);
   		startVertex = temp5 ;
      }
        
       
        
        

        
    }

}
