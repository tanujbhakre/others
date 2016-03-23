package queue;

import exceptions.EmptyQueueException;

/**
 * This interface exposes the operations possible with Queue
 * 
 * @author Tanuj Bhakre
 * 
 * @param <E>
 */
public interface Queue<E> {
	/**
	 * Inserts an element in the queue
	 * 
	 * @param element
	 */
	public void enqueue(E element);

	/**
	 * Removes last element from the Quque and returns it
	 * 
	 * @return
	 * @throws EmptyQueueException
	 */
	public E dequeue() throws EmptyQueueException;

	/**
	 * Gets the last element from the quque
	 * 
	 * @return
	 * @throws EmptyQueueException
	 */
	public E front() throws EmptyQueueException;

	/**
	 * This method returns true if there are no elements in the Queue
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * This method returns number of elements in the Queue
	 * 
	 * @return
	 */
	public int size();
}
