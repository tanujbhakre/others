package designed.operation;

import java.util.List;

import designed.OperationExecutor;

public class CommitOperation implements Operation {
	private OperationExecutor operationExecutor;

	public CommitOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		this.operationExecutor.commit();
		return new StringBuilder("SUCCESS");
	}

}
