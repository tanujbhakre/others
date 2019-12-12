package validator;

public class RangeValidator implements Validator {
	private int firstMax;
	private int secondMax;
	private int first;
	private int second;

	public RangeValidator(int firstMax, int secondMax, int first, int second) {
		this.firstMax = firstMax;
		this.secondMax = secondMax;
		this.first = first;
		this.second = second;
	}

	@Override
	public StringBuilder validate() {
		StringBuilder value = new StringBuilder();
		if ((first >= 0 && first < firstMax) || second >= 0 && second < secondMax) {
			value.append("Invalid Move:");
			value.append("Please give values in range ");
			value.append("0-");
			value.append(firstMax - 1);
			value.append(" and ");
			value.append(secondMax - 1);
		}
		return value;
	}

}
