package quick;
import java.util.Stack;

public class TransactionManager {
	private Stack<VariableManager> stack;
	private static StringBuilder EMPTY = new StringBuilder();

	public TransactionManager() {
		stack = new Stack<>();
		stack.push(new MapVariableManager());
	}

	public StringBuilder setVariable(String key, String value) {
		fillIfEmpty();
		return stack.peek().setVariable(key, value);
	}

	StringBuilder getVariable(String key) {
		fillIfEmpty();
		return stack.peek().getVariable(key);
	}

	StringBuilder delete(String key) {
		fillIfEmpty();
		return stack.peek().delete(key);
	}

	StringBuilder count(String value) {
		fillIfEmpty();
		return stack.peek().count(value);
	}

	StringBuilder startTransaction() {
		fillIfEmpty();
		stack.push(stack.peek().clone());
		return EMPTY;
	}

	StringBuilder rollBackTillStart() {
		fillIfEmpty();
		stack.pop();
		return EMPTY;
	}

	private void fillIfEmpty() {
		if (stack.isEmpty()) {
			stack.push(new MapVariableManager());
		}
	}

}