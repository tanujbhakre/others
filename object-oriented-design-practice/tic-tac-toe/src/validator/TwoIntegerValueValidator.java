package validator;

import java.util.List;

public class TwoIntegerValueValidator implements Validator {
	private List<String> values;

	public TwoIntegerValueValidator(List<String> values) {
		this.values = values;
	}

	@Override
	public StringBuilder validate() {
		StringBuilder value = new StringBuilder();
		boolean isValid = true;
		if (values != null && value.length() > 2) {
			try {
				Integer.parseInt(values.get(0));
				Integer.parseInt(values.get(1));
			} catch (NumberFormatException nf) {
				isValid = false;
			}
		} else {
			isValid = false;
		}
		if (!isValid) {
			value.append("Invalid Arguments:");
			value.append("Please provide 2 integer values in a line seperated by space");
		}
		return value;
	}

}
