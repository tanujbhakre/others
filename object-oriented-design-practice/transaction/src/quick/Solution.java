package quick;
import java.util.List;

import io.Reader;
import io.Writer;
import io.file.FileDataReader;
import io.file.FileDataWriter;

public class Solution {
	private static String SET = "SET";
	private static String GET = "GET";
	private static String DELETE = "DELETE";
	private static String COUNT = "COUNT";
	private static String START_TRANSACTION = "START TRANSACTION";
	private static String END_TRANSACTION = "END TRANSACTION";

	public static void main(String args[]) throws Exception {
		// Reader reader = new ScannerDataReader();
		// Writer writer = new ScannerDataWriter();
		Reader reader = new FileDataReader(args[0]);
		Writer writer = new FileDataWriter(args[1]);
		try {
			TransactionManager transactionManager = new TransactionManager();
			while (reader.hasNext()) {
				List<String> input = reader.read();
				StringBuilder output = run(input, transactionManager);
				writer.write(input.toString());
				writer.write(output);
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

	public static StringBuilder run(List<String> commands, TransactionManager transactionManager) {
		if (commands.size() >= 2) {
			if (SET.equals(commands.get(0))) {
				if (commands.size() == 3) {
					return transactionManager.setVariable(commands.get(1), commands.get(2));
				}
			} else if (GET.equals(commands.get(0))) {
				return transactionManager.getVariable(commands.get(1));
			} else if (DELETE.equals(commands.get(0))) {
				return transactionManager.delete(commands.get(1));
			} else if (COUNT.equals(commands.get(0))) {
				return transactionManager.count(commands.get(1));
			} else if (START_TRANSACTION.equals(concatParams(commands))) {
				return transactionManager.startTransaction();
			} else if (END_TRANSACTION.equals(concatParams(commands))) {
				return transactionManager.rollBackTillStart();
			} else {
				// Ignoring invalid commands
			}
		}
		return new StringBuilder();
	}

	static String concatParams(List<String> parameters) {
		if (parameters.size() >= 2) {
			return parameters.get(0) + " " + parameters.get(1);
		}
		return new String();
	}
}
