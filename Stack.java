package graph;

public class Stack {

private final int SIZE = 120 ;

private Vertex[] st ;
private int top;

Stack(){

	st = new Vertex[SIZE];
	top = -1 ;
	
}
	
public void push(Vertex j){
	
st[++top] = j ;
	
}

public Vertex pop(){

return st[top--] ;
	
}

public Vertex peek(){
	
return st[top]	;
}

public boolean isEmpty(){
	
return (top == -1);	

}

}
