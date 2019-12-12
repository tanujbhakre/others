package dependencies;

import java.util.List;

import io.Reader;
import io.Writer;
import io.file.FileDataReader;
import io.file.FileDataWriter;

public class Solution {

	public static void main(String args[]) throws Exception {
		if (args == null || args.length < 2) {
			System.out.println("The program requires 2 arguments:");
			System.out.println("Location of input file from which instructions are read");
			System.out.println("Location of ouput file where the results are written");
		}
		// Reader reader = new ScannerDataReader();
		// Writer writer = new ScannerDataWriter();
		Reader reader = new FileDataReader(args[0]);
		Writer writer = new FileDataWriter(args[1]);
		try {
			CommandExecutor commandExecutor = new CommandExecutor();
			while (reader.hasNext()) {
				List<String> input = reader.read();
				writer.write(input.toString());
				List<StringBuilder> outputs = commandExecutor.execute(input);
				for (StringBuilder output : outputs) {
					writer.write(output);
				}
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

}
