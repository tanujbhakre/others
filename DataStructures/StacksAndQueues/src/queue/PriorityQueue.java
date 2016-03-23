package queue;

/**
 * This interface exposes the operations possible with Priority Quque
 * 
 * @author Tanuj Bhakre
 * 
 */
public interface PriorityQueue<E> {

	/**
	 * This method inserts an element in priority queue
	 * 
	 * @param element
	 */
	public void add(E element);

	/**
	 * This method returns the element with highest priority
	 * 
	 * @return
	 */
	public E peek();

	/**
	 * This method returns the element with highest priority and removes it from
	 * queue
	 * 
	 * @return
	 */
	public E poll();

	/**
	 * This method returns true if there are no elements in the priority queue
	 * 
	 * @return
	 */
	public boolean isEmpty();

	/**
	 * This method returns number of elements in priority queue
	 * 
	 * @return
	 */
	public int size();
}
