package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class StartTransactionOperation implements Operation {
	private OperationExecutor operationExecutor;

	public StartTransactionOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		this.operationExecutor.beginTransaction();
		return new StringBuilder();
	}

}
