package dependencies;

import java.util.ArrayList;
import java.util.List;

import dependencies.operation.Operation;
import dependencies.operation.factory.OperationFactory;

public class CommandExecutor {
	private OperationFactory operationFactory;

	public CommandExecutor() {
		this.operationFactory = new OperationFactory();
	}

	public List<StringBuilder> execute(List<String> args) {
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		if (args.size() > 0) {
			Operation operation = operationFactory.getOperation(args.get(0));
			result = operation.execute(args);
		}
		return result;
	}

}
