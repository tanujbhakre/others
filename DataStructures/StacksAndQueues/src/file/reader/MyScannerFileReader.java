package file.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyScannerFileReader implements MyFileReader{

	private Scanner scanner = null;

	public MyScannerFileReader(String fileName){
		try {
			 scanner = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public String readNextLine() {
		String sCurrentLine=null;
			if (!isNothingToRead()) {
				sCurrentLine= scanner.nextLine();
			}

		return sCurrentLine;
	}

	@Override
	public boolean isNothingToRead() {
		boolean nothingToRead=false;
			if (!scanner.hasNextLine()) {
				nothingToRead=true; 
			}
		return nothingToRead;
	}
	
}
