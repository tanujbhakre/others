package stack.application.parallelprogramming;

import stack.Stack;
import stack.impl.LinkedListStack;
import exceptions.EmptyStackException;
import exceptions.FileFormatException;
import file.writer.MyFileWriter;

public class StackSynchronizationManagerImpl implements StackSynchronizationManager{
	
	private Stack<String> stack;
	MyFileWriter operationWriter;
	MyFileWriter outPutWriter;
	
	public StackSynchronizationManagerImpl(MyFileWriter operationWriter,MyFileWriter outPutWriter){
		stack=new LinkedListStack<String>();
		this.operationWriter=operationWriter;
		this.outPutWriter=outPutWriter;
	}

	@Override
	public synchronized void doOperation(int threadNumber,String instruction){
		String operationDetails = null;
		String outPutDetails = null;
		try {
			Operation operation = ParallelProgrammingUtil.getOperation(instruction);
			switch (operation) {
			case PUSH:
				String value = ParallelProgrammingUtil
						.getValue(instruction);
				stack.push(value);
				operationDetails=threadNumber+", "+Operation.PUSH.text()+ParallelProgrammingUtil.SPLITTER+value;
				break;
			case POP:
				try {
					String popedValue=stack.pop();
					operationDetails=threadNumber+", "+Operation.POP.text();
					outPutDetails=threadNumber+", "+popedValue;
				} catch (EmptyStackException e) {
					operationDetails=threadNumber+", "+e.getMessage();
				}
				break;
			}
		} catch (FileFormatException e1) {
			operationDetails=threadNumber+", "+e1.getMessage();
		}
		operationWriter.writeLine(operationDetails);
		if(outPutDetails!=null){
			outPutWriter.writeLine(outPutDetails);
		}
	}
	
	@Override
	public void cleanUp() {
		operationWriter.closeFile();
		outPutWriter.closeFile();
	}
}
