package tictactoe.io;

import java.util.List;

public interface IOProvider {
	List<String> getUserInput(StringBuilder message);

	void display(StringBuilder message);
}
