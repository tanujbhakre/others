package stack.application.parallelprogramming;

public interface StackSynchronizationManager {
	
	public void cleanUp();
	public void doOperation(int threadNumber,String instruction);

}
