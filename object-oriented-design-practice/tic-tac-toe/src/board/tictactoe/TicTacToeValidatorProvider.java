package board.tictactoe;

import validator.Validator;
import validator.ValidatorProvider;

public class TicTacToeValidatorProvider implements ValidatorProvider {
	private String board[][];
	private int row;
	private int column;

	public TicTacToeValidatorProvider(String board[][]) {
		this.board = board;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Validator next() {
		// TODO Auto-generated method stub
		return null;
	}

}
