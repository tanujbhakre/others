package queue.impl;

import java.util.ArrayList;
import java.util.List;

import queue.Queue;
import exceptions.EmptyQueueException;

public class ListQueue<E> implements Queue<E> {
	
	List<E> queue;
	
	public ListQueue(){
		queue=new ArrayList<E>();
	}
	
	@Override
	public void enqueue(E element) {
		queue.add(element);
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(size()>0){
			E element=queue.get(0);
			queue.remove(0);
			return element;
		}else{
			throw new EmptyQueueException();
		}
	}

	@Override
	public E front() throws EmptyQueueException {
		if(size()>0){
			E element=queue.get(0);
			return element;
		}else{
			throw new EmptyQueueException();
		}
	}

	@Override
	public boolean isEmpty() {
		if(size()==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int size() {
		return queue.size();
	}

}
