package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class InvalidOperation implements Operation {
	private OperationExecutor operationExecutor;

	public InvalidOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		// TODO Auto-generated method stub
		return new StringBuilder();
	}

}
