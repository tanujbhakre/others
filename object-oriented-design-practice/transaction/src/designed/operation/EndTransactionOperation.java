package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class EndTransactionOperation implements Operation {
	private OperationExecutor operationExecutor;

	public EndTransactionOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		this.operationExecutor.endTransaction();
		return new StringBuilder();
	}

}
