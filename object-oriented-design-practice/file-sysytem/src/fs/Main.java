package fs;

import java.io.IOException;
import java.util.List;

import fs.io.DataReader;
import fs.io.DataWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		// args = new String[2];
		// args[0] =
		// "/Users/1021305/Documents/workspace/eclipse/file-sysytem/resource/infile.txt";
		// args[1] =
		// "/Users/1021305/Documents/workspace/eclipse/file-sysytem/resource/out.txt";
		DataReader reader = new DataReader(args[0]);
		DataWriter writer = new DataWriter(args[1]);
		FileSystem filesystem = new FileSystem();
		try {
			while (reader.hasNext()) {
				List<String> arguments = reader.getNext();
				String output = filesystem.execute(arguments);
				writer.write(output);
			}
		} finally {
			reader.close();
			writer.close();
		}
	}
}
