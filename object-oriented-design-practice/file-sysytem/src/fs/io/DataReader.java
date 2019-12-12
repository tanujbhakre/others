package fs.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataReader {

	private Scanner scanner;

	public DataReader(String file) throws FileNotFoundException {
		this.scanner = new Scanner(new File(file));
	}

	public boolean hasNext() {
		return this.scanner.hasNextLine();
	}

	public List<String> getNext() {
		List<String> arguments = new ArrayList<>();
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			arguments = Arrays.asList(line.split(" "));
			arguments.stream().filter((value) -> !value.isEmpty()).collect(Collectors.toList());
		}
		return arguments;
	}

	public void close() {
		this.scanner.close();
	}
}
