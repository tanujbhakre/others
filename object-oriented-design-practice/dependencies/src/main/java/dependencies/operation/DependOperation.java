package dependencies.operation;

import java.util.ArrayList;
import java.util.List;

import dependencies.graph.Graph;

public class DependOperation extends BaseOperation {

	private final Graph graph;

	private static final int MIN_SIZE = 3;

	public DependOperation(Graph graph) {
		super(MIN_SIZE);
		this.graph = graph;
	}

	@Override
	public List<StringBuilder> executeOperation(List<String> args) {
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		String child = args.get(1);
		for (int index = 2; index < args.size(); index++) {
			String parent = args.get(index);
			boolean isAdded = graph.addParentChild(parent, child);
			if (!isAdded) {
				result.add(getNotAddedMessage(parent, child));
			}
		}
		return result;
	}

	private StringBuilder getNotAddedMessage(String parent, String child) {
		StringBuilder response = new StringBuilder();
		response.append(parent);
		response.append(" depends on ");
		response.append(child);
		response.append(", ignoring command");
		return response;
	}
}
