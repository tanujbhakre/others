package exceptions;

public class EmptyQueueException extends Exception{
	
	private static final long serialVersionUID = -7902835226398359650L;

	public EmptyQueueException(){
		super("EmptyQueueException");
	}
}
