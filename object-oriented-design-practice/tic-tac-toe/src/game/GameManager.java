package game;

import java.util.ArrayList;
import java.util.List;

import board.Board;
import model.Player;
import model.Result;
import tictactoe.io.IOProvider;

public class GameManager {
	private Board board;
	private Player player1;
	private Player player2;
	private IOProvider ioProvider;
	private Player current;

	public GameManager(IOProvider ioProvider, Board board, Player player1, Player player2) {
		this.ioProvider = ioProvider;
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}

	public void play() {
		StringBuilder display = new StringBuilder();
		display.append("Game Started:\n");
		ioProvider.display(display);
		current = player1;
		while (true) {
			display = new StringBuilder();
			display.append("Play ");
			display.append(this.current.getName());
			display.append(":\n");
			List<String> userArgs = ioProvider.getUserInput(display);
			List<String> args = new ArrayList<>(userArgs);
			args.add(getCurrentUserArgument());
			Result result = board.play(args);
			if (result.isDraw()) {
				ioProvider.display(new StringBuilder("Game Draw"));
				break;
			} else if (result.isWon()) {
				ioProvider.display(new StringBuilder("Game won by:" + this.current.getName()));
			}
			if (!result.isSamePlayerAgain()) {
				switchTurn();
			}

		}
	}

	private void switchTurn() {
		if (this.current == player1) {
			this.current = player2;
		} else {
			this.current = player1;
		}
	}

	private String getCurrentUserArgument() {
		if (this.current == player1) {
			return board.getPlayer1Value();
		} else {
			return board.getPlayer2Value();
		}
	}

}
