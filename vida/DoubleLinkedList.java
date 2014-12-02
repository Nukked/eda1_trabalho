package vida;

public class DoubleLinkedList<E> implements Iterable<E> {
	
	private DoubleNode<E> header;
	private DoubleNode<E> footer;
	int theSize;
	
	public DoubleLinkedList(){
		header = new DoubleNode<E>();
		footer = new DoubleNode<E>();
		header.next = footer;
		footer.previous = header;
		theSize = 0;
	}
	
	public java.util.Iterator<E> iterator(){
		return new DoubleLinkedListIterator(header.next);
	}
	
	public void add(E x){
		DoubleNode<E> novo = new DoubleNode<E>(x, footer.previous, footer);
		footer.previous.next = novo;
		footer.previous = novo;
		theSize++;
	}	
	
	public E remove(DoubleNode<E> n){
		DoubleNode<E> ant = n.previous;
		ant.next = n.next;
		n.next.previous = ant;
		theSize--;
		return n.elemento;
	}
	
	public int size(){
		return theSize;
	}
	
	public DoubleNode<E> getFooter(){
		return footer;
	}
	
	public DoubleNode<E> getHeader(){
		return header;
	}
	
	public class DoubleLinkedListIterator implements java.util.Iterator<E>{
		
		DoubleNode<E> current;
		
		public DoubleLinkedListIterator(DoubleNode<E> x){
			current = x;
		}
		
		public boolean hasNext(){
			return current!= footer;
		}
		
		public E next(){
			if(hasNext()){
				E x = current.elemento;
				current = current.next;
				return x;
			}
			throw new java.util.NoSuchElementException();
		}
		
		public void remove(){
			DoubleLinkedList.this.remove(current);
		}	
	}
}

