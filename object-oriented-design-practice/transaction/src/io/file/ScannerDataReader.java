package io.file;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import io.Reader;

public class ScannerDataReader implements Reader {

	private Scanner scanner;;

	public ScannerDataReader() {
		scanner = new Scanner(System.in);
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public List<String> read() {
		String string = scanner.nextLine();
		List<String> input = Arrays.asList(string.split(" "));
		return input;
	}

	@Override
	public void close() {
	}

}
