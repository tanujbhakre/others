package file.writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MyPrintWriter implements MyFileWriter{

	PrintWriter writer;
	
	public  MyPrintWriter(String fileName){
		try {
			writer=new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public synchronized void write(String lineContent) {
		writer.print(lineContent);
	}

	@Override
	public synchronized void writeLine(String line) {
		writer.println(line);
	}
	
	@Override
	public void closeFile(){
		writer.close();
	}
}
