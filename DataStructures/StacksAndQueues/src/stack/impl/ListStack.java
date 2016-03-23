package stack.impl;

import java.util.ArrayList;
import java.util.List;

import stack.Stack;
import exceptions.EmptyStackException;


public class ListStack<E> implements Stack<E> {

	private List<E> stack;
	private int top;
	
	public ListStack(){
		stack=new ArrayList<E>();
		top=-1;
	}
	
	@Override
	public void push(E element) {
		stack.add(++top, element);
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if(!isEmpty()){
			E element= stack.get(top);
			stack.remove(top--);
			return element;
		}else{
			throw new EmptyStackException();
		}
	}

	@Override
	public E peek() throws EmptyStackException {
		if(!isEmpty()){
			E element= stack.get(top);
			return element;
		}else{
			throw new EmptyStackException();
		}
	}

	@Override
	public boolean isEmpty() {
		if(top==-1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int size() {
		return top+1;
	}

}
