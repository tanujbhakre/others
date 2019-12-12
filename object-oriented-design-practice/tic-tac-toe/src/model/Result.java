package model;

public class Result {
	private boolean samePlayerAgain;
	private StringBuilder message;
	private boolean draw;
	private boolean won;

	public Result(boolean samePlayerAgain, StringBuilder message) {
		this.samePlayerAgain = samePlayerAgain;
		this.message = message;
		this.setDraw(false);
		this.setWon(false);
	}

	public boolean isSamePlayerAgain() {
		return samePlayerAgain;
	}

	public StringBuilder getMessage() {
		return message;
	}

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

}
