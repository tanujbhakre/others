package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class CountOperation implements Operation {
	private OperationExecutor operationExecutor;

	public CountOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		StringBuilder result = new StringBuilder();
		if (commands != null && commands.size() > 1) {
			Integer count = this.operationExecutor.getVariable().getCount(commands.get(1));
			if (count != null)
				result.append(count);
			else
				result.append(0);
		}
		return result;
	}

}
