package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class GetOperation implements Operation {
	private OperationExecutor operationExecutor;

	public GetOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		StringBuilder result = new StringBuilder();
		if (commands != null && commands.size() > 1) {
			String value = this.operationExecutor.getVariable().getValue(commands.get(1));
			if (value != null)
				result.append(value);
		}
		return result;
	}

}
