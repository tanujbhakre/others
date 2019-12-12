package tictactoe.io;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScannerIOProvider implements IOProvider {

	private Scanner scanner;;

	public ScannerIOProvider() {
		scanner = new Scanner(System.in);
	}

	@Override
	public List<String> getUserInput(StringBuilder message) {
		display(message);
		String string = scanner.nextLine();
		List<String> input = Arrays.asList(string.split(" "));
		return input;
	}

	@Override
	public void display(StringBuilder message) {
		System.out.println(message);
	}

}
