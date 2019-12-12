package designed.operation;

import java.util.List;

import designed.OperationExecutor;
import designed.Variable;

public class SetOperation implements Operation {
	private OperationExecutor operationExecutor;

	public SetOperation(OperationExecutor operationExecutor) {
		this.operationExecutor = operationExecutor;
	}

	@Override
	public StringBuilder execute(List<String> commands) {
		StringBuilder result = new StringBuilder();
		if (commands != null && commands.size() > 2) {
			Variable variable = this.operationExecutor.getVariable();
			String key = commands.get(1);
			if (variable.getValue(key) != null) {
				variable.deleteValue(key);
			}
			variable.setValue(commands.get(1), commands.get(2));
		}
		return result.append("SUCCESS");
	}

}
