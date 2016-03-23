package stack.application.parallelprogramming;

import file.reader.MyFileReader;
import file.reader.MyScannerFileReader;

public class StackOperatoinThread implements Runnable {

	private String path;
	private int threadNumber;
	private StackSynchronizationManager stackManager;

	public StackOperatoinThread(StackSynchronizationManager stackManager,String path,
			int threadNumber) {
		this.stackManager=stackManager;
		this.path = path;
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		MyFileReader reader = new MyScannerFileReader(path);
		while (!reader.isNothingToRead()) {
			String instruction = reader.readNextLine();
			stackManager.doOperation(threadNumber, instruction);
		}
	}

}
