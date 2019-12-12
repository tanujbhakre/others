package dependencies.operation;

import java.util.Arrays;
import java.util.List;

public abstract class BaseOperation implements Operation {

	private int minArgSize;

	public BaseOperation(int minArgSize) {
		this.minArgSize = minArgSize;
	}

	@Override
	public List<StringBuilder> execute(List<String> args) {
		if (!isValid(args)) {
			return Arrays.asList(new StringBuilder("Invalid argument"));
		} else {
			return executeOperation(args);
		}
	}

	public abstract List<StringBuilder> executeOperation(List<String> args);

	private boolean isValid(List<String> args) {
		return args != null && args.size() >= minArgSize;
	}

}
