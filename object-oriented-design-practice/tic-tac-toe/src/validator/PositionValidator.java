package validator;

public class PositionValidator implements Validator {
	private String[][] matrix;
	private int row;
	private int col;

	public PositionValidator(String[][] matrix, int row, int col) {
		this.matrix = matrix;
		this.row = row;
		this.col = col;
	}

	@Override
	public StringBuilder validate() {
		StringBuilder value = new StringBuilder();
		if (matrix[row][col] != null) {
			value.append("Invalid Move:");
			value.append(row);
			value.append(" - ");
			value.append(col);
			value.append(" already played ");
		}
		return value;
	}

}
