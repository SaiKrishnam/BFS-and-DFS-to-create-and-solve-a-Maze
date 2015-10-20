package graph;

public class Queue {
	private final int SIZE = 20 ;
	// implementing using array
	private Vertex[] queArray ;
	// to maintain the head and tail
	private int front ;
	private int rear ;

	public Queue(){

	queArray = new Vertex[SIZE];
	front = 0 ;
	rear = -1;

	}

	// insert at rear
	public void insert(Vertex j){

	if(rear == SIZE-1){
	rear = -1;
	}
	
	queArray[++rear] = j ;

	}

	// remove from head
	public Vertex remove(){

	Vertex temp = queArray[front++];
	if(front == SIZE){
		front = 0;
		}
	return temp;

	}

	public boolean isEmpty(){

	return (rear+1 == front || (front +SIZE -1 == rear));

	}

}
