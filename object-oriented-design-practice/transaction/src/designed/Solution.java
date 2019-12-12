package designed;

import java.util.List;

import io.Reader;
import io.Writer;
import io.file.FileDataReader;
import io.file.FileDataWriter;

public class Solution {

	public static void main(String args[]) throws Exception {
		// Reader reader = new ScannerDataReader();
		// Writer writer = new ScannerDataWriter();
		Reader reader = new FileDataReader(args[0]);
		Writer writer = new FileDataWriter(args[1]);
		try {
			OperationExecutor operationExecutor = new OperationExecutor();
			while (reader.hasNext()) {
				List<String> input = reader.read();
				StringBuilder output = operationExecutor.execute(input);
				writer.write(input.toString());
				writer.write(output);
			}
		} finally {
			reader.close();
			writer.close();
		}
	}
}
