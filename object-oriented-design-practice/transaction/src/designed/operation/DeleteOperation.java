package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class DeleteOperation implements Operation {
	private OperationExecutor operationExecutor;

	public DeleteOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		StringBuilder result = new StringBuilder();
		if (commands != null && commands.size() > 1) {
			this.operationExecutor.getVariable().deleteValue(commands.get(1));
		}
		return result.append("SUCCESS");
	}

}
