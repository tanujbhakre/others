package stack.application.parallelprogramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import file.writer.MyFileWriter;
import file.writer.MyPrintWriter;
import stack.Stack;
import stack.impl.ListStack;

public class Simulate {

	private static final String folder="parallelprogramming";
	public static final String OPERATIONS_FILE_PATH="F:\\Workspace\\Stacks\\StackFiles\\parallelprogramming\\operations-out.txt";
	public static final String OUTPUT_FILE_PATH="F:\\Workspace\\Stacks\\StackFiles\\parallelprogramming\\output.txt";
	
	public static void main(String args[]) throws InterruptedException{
		int iThreadCount=3;
		MyFileWriter oprationWriter=new MyPrintWriter(OPERATIONS_FILE_PATH);
		MyFileWriter outPutWriter=new MyPrintWriter(OUTPUT_FILE_PATH);
		StackSynchronizationManager stackManager=new StackSynchronizationManagerImpl(oprationWriter, outPutWriter);
		   ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int iCount=0;iCount<iThreadCount;iCount++){
			String path=Simulate.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path+=File.separator+folder+File.separator+"operations-"+(iCount+1)+".txt";
			StackOperatoinThread stackOperatorThread=new StackOperatoinThread(stackManager,path,iCount+1);
			Thread thread=new Thread(stackOperatorThread);
			executor.execute(thread);
		}
		executor.shutdown();
		executor.awaitTermination(100, TimeUnit.SECONDS);
		stackManager.cleanUp();
	}
}
