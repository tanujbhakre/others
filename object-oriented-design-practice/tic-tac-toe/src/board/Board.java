package board;

import java.util.List;

import model.Result;

public interface Board {
	Result play(List<String> args);

	String getPlayer1Value();

	String getPlayer2Value();
}
