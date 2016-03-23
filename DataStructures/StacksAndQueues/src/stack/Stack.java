package stack;

import exceptions.EmptyStackException;


public interface Stack<E> {
	
	public void push(E element);
	public E pop() throws EmptyStackException;
	public E peek() throws EmptyStackException;
	public boolean isEmpty();
	public int size();
}
