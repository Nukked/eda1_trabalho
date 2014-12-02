package vida;

public class DoubleNode<E> {
	E elemento;
	DoubleNode<E> previous;
	DoubleNode<E> next;
	
	DoubleNode(){
		this(null, null, null);
	}
	
	DoubleNode(E x, DoubleNode<E> p, DoubleNode<E> n){
		elemento = x;
		previous = p;
		next = n;
	}

}
