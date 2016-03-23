package queue.impl;

import queue.Queue;
import domain.Node;
import exceptions.EmptyQueueException;

public class LinkedListQueue<E> implements Queue<E> {
	private Node<E> front;
	private Node<E> rear;
	private int size;

	public LinkedListQueue() {
		front = new Node<E>();
		rear = new Node<E>();
		size = 0;
	}

	@Override
	public void enqueue(E element) {
		Node<E> newNode = new Node<E>();
		newNode.setData(element);
		Node<E> rearNode = rear.getNext();
		if (rearNode != null) {
			rearNode.setNext(newNode);
		} else {
			front.setNext(newNode);
		}
		rear.setNext(newNode);
		size++;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if (size() != 0) {
			Node<E> frontNode = front.getNext();
			front.setNext(frontNode.getNext());
			if (frontNode == rear.getNext()) {
				rear.setNext(null);
			}
			frontNode.setNext(null);
			size--;
			return frontNode.getData();
		} else {
			throw new EmptyQueueException();
		}
	}

	@Override
	public E front() throws EmptyQueueException {
		if (size() != 0) {
			Node<E> frontNode = front.getNext();
			return frontNode.getData();
		} else {
			throw new EmptyQueueException();
		}
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int size() {
		return size;
	}

}
