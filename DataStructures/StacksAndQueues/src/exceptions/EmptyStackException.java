package exceptions;

public class EmptyStackException extends Exception{
	
	private static final long serialVersionUID = -7902835226392359650L;

	public EmptyStackException(){
		super("EmptyStackException");
	}
}
