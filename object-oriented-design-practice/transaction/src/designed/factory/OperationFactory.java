package designed.factory;

import java.util.List;

import designed.OperationExecutor;
import designed.operation.CommitOperation;
import designed.operation.CountOperation;
import designed.operation.DeleteOperation;
import designed.operation.EndTransactionOperation;
import designed.operation.GetOperation;
import designed.operation.InvalidOperation;
import designed.operation.Operation;
import designed.operation.SetOperation;
import designed.operation.StartTransactionOperation;

public class OperationFactory {
	private static String SET = "SET";
	private static String GET = "GET";
	private static String DELETE = "DELETE";
	private static String COUNT = "COUNT";
	private static String COMMIT = "COMMIT";
	private static String START_TRANSACTION = "START TRANSACTION";
	private static String END_TRANSACTION = "END TRANSACTION";

	private OperationExecutor operationExecutor;

	public OperationFactory(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	public Operation getOperation(List<String> commands) {
		if (commands.size() == 0)
			return new InvalidOperation(this.operationExecutor);
		if (SET.equals(commands.get(0))) {
			return new SetOperation(this.operationExecutor);
		} else if (GET.equals(commands.get(0))) {
			return new GetOperation(this.operationExecutor);
		} else if (DELETE.equals(commands.get(0))) {
			return new DeleteOperation(this.operationExecutor);
		} else if (COUNT.equals(commands.get(0))) {
			return new CountOperation(this.operationExecutor);
		} else if (START_TRANSACTION.equals(concatParams(commands))) {
			return new StartTransactionOperation(this.operationExecutor);
		} else if (END_TRANSACTION.equals(concatParams(commands))) {
			return new EndTransactionOperation(this.operationExecutor);
		} else if (COMMIT.equals(commands.get(0))) {
			return new CommitOperation(this.operationExecutor);
		} else {
			return new InvalidOperation(this.operationExecutor);
		}
	}

	static String concatParams(List<String> parameters) {
		if (parameters.size() >= 2) {
			return parameters.get(0) + " " + parameters.get(1);
		}
		return new String();
	}
}
