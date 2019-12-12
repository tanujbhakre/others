package designed;

import java.util.List;
import java.util.Stack;

import designed.factory.OperationFactory;

public class OperationExecutor {
	private OperationFactory operationFactory;
	private Stack<Variable> stack;

	public OperationExecutor() {
		operationFactory = new OperationFactory(this);
		stack = new Stack<>();
		stack.push(new Variable());
	}

	public Variable getVariable() {
		fillIfEmpty();
		return stack.peek();
	}

	public Variable beginTransaction() {
		fillIfEmpty();
		return stack.push(stack.peek().copy());
	}

	public Variable endTransaction() {
		fillIfEmpty();
		return stack.pop();
	}

	public void commit() {
		fillIfEmpty();
		Variable current = stack.pop();
		fillIfEmpty();
		stack.pop();
		stack.push(current.copy());
		stack.push(current);
	}

	private void fillIfEmpty() {
		if (stack.isEmpty()) {
			stack.push(new Variable());
		}
	}

	public StringBuilder execute(List<String> commands) {
		return operationFactory.getOperation(commands).execute(commands);
	}
}
