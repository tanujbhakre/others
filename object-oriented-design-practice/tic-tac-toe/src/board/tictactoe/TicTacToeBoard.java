package board.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.Board;
import model.Result;

public class TicTacToeBoard implements Board {
	private String[][] board;
	private int moves = 0;

	private static String X = "X";
	private static String O = "O";

	public TicTacToeBoard(int m, int n) {
		this.board = new String[m][n];
	}

	@Override
	public Result play(List<String> args) {
		StringBuilder output = new StringBuilder();
		output.append("Invalid argument");
		int row = 0;
		int col = 0;
		if (args != null && args.size() < 3) {
			new Result(true, output);
		} else {
			try {
				row = Integer.parseInt(args.get(0));
				col = Integer.parseInt(args.get(1));
			} catch (NumberFormatException e) {
				new Result(true, output);
			}
			if (!X.equals(args.get(2)) && !O.equals(args.get(2))) {
				new Result(true, output);
			}
		}
		if (board[row][col] != null) {
			new Result(true, output);
		}

		board[row][col] = args.get(2);
		moves++;
		Result result = new Result(false, null);
		if (isWon(row, col)) {
			result.setWon(true);
			return result;
		} else if (isDraw()) {
			result.setDraw(true);
			return result;
		} else {
			return result;
		}
	}

	private boolean isDraw() {
		// TODO Check if game is draw
		return moves == board.length * board[0].length;
	}

	public boolean isWon(int row, int col) {
		List<List<Integer>> rules = new ArrayList<>();
		rules.add(Arrays.asList(1,2));
		return false;
	}

	@Override
	public String getPlayer1Value() {
		return X;
	}

	@Override
	public String getPlayer2Value() {
		return O;
	}

}
