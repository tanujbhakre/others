package stack.impl;

import stack.Stack;
import domain.Node;
import exceptions.EmptyStackException;

public class LinkedListStack<E> implements Stack<E> {

	private Node<E> head;
	private int size=0;
	
	public LinkedListStack(){
		head=new Node<E>();
	}
	
	@Override
	public void push(E element) {
		Node<E> node=new Node<E>();
		node.setData(element);
			Node<E> top=head.getNext();
			node.setNext(top);
			head.setNext(node);
			size++;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(head.getNext()!=null){
			Node<E> top=head.getNext();
			head.setNext(top.getNext());
			top.setNext(null);
			size--;
			return top.getData();
		}else{
			throw new EmptyStackException();
		}
	}

	@Override
	public E peek() throws EmptyStackException {
		if(head.getNext()!=null){
			Node<E> top=head.getNext();
			return top.getData();
		}else{
			throw new EmptyStackException();
		}
	}

	@Override
	public boolean isEmpty() {
		if(head.getNext()==null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int size() {
		return size;
	}

}
