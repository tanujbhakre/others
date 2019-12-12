package io.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.Reader;

public class FileDataReader implements Reader {

	private Scanner scanner;

	public FileDataReader(String file) {
		try {
			this.scanner = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		return this.scanner.hasNextLine();
	}

	@Override
	public List<String> read() {
		List<String> arguments = new ArrayList<>();
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			arguments = Arrays.asList(line.split(" "));
			arguments.stream().filter((value) -> !value.isEmpty()).collect(Collectors.toList());
		}
		return arguments;
	}

	@Override
	public void close() {
		this.scanner.close();
	}
}
