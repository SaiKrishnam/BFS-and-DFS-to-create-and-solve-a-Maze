package graph;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingBFS extends JPanel{

	public Cell[] cellListt;
    public int rowValue ;
    public int colValue ;
    public int magFactor;
    public Vertex[] vListDFS;
    public Vertex[] pathListBFS;

    public DrawingBFS(Cell[] t,int x,int y,int z,Vertex[] s1,Vertex[] s2) {
      
      this.cellListt = t ;
      this.rowValue = x ;
      this.colValue = y ;
      this.magFactor = z ;
      this.vListDFS = s1 ;
      this.pathListBFS = s2 ;

    }

    public void drawing() {
       
        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
      

        g.setColor(Color.BLACK);
        
        int xFinalPosition = cellListt[((rowValue * colValue)-1)].getX();
        int myStartPosition = xFinalPosition + 30 ;
        int myStartPosition2 = ((rowValue)*magFactor)+myStartPosition+30;
        
       
        
        // draw the empty maze generated
		for (int i = 0; i < ((rowValue * colValue)); i++) {
		        	
		            if (cellListt[i].getNorth() == 1) {
		            	
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
		
		
		
		
		/*1.Using the pathListBFS[] array which consists the list of vertices in order
		 * visited during BFS on new Graph.
		 *2.This array when traversed will give us the order of visiting the vertices,here
		 *  the for loop breaks  when we reach the exit cell.
		 * 3. This for loop marks the order(with 1,2,3...) of visiting on the maze created.
		 */  		
			g.setColor(Color.BLACK);
	        
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
				g.setColor(Color.RED);
	       for(int p = 0 ; p < (rowValue * colValue) ; p++ ){
	    	   Integer number = p;
	    	   String s1 = number.toString() ;
	    	   g.drawString(s1,pathListBFS[p].getVertexCell().getX()+magFactor/2+myStartPosition ,pathListBFS[p].getVertexCell().getY()+magFactor/2);
	    	  if(pathListBFS[p].label == ((rowValue * colValue)-1)){
	   		   break;
	   	   		} 
	       }
		
		
		
	       
	       // draw the perfect solution to the maze.
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
       
        /*1.The vListDFS consists of all vertex objects in the graph.
         *2.The vertex objects have a parent Vertex assigned to them during traversal.
         *3.So starting at the right bottom , using the parent the while loop traverses
         * to the start.
         */ 
        
        g.setColor(Color.RED);
        int val = -1 ;
        String s = "X" ;
        Vertex currVertex = vListDFS[(this.rowValue*this.colValue)-1];
   		g.drawString(s,currVertex.getVertexCell().getX()+magFactor/2+myStartPosition2,currVertex.getVertexCell().getY()+magFactor/2);
   		
        while(val != 0){
        Vertex temp1 = currVertex.parent ;
        val = temp1.label ;
   		g.drawString(s,temp1.getVertexCell().getX()+magFactor/2+myStartPosition2,temp1.getVertexCell().getY()+magFactor/2);
        currVertex = temp1 ;
      }
        
       
       
    }
}
