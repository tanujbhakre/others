package dependencies.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dependencies.components.ComponentsService;
import dependencies.graph.Graph;

public class RemoveOperation extends BaseOperation {

	private final Graph graph;
	private final ComponentsService componentsService;

	private static final int MIN_SIZE = 2;

	public RemoveOperation(Graph graph, ComponentsService componentsService) {
		super(MIN_SIZE);
		this.graph = graph;
		this.componentsService = componentsService;
	}

	@Override
	public List<StringBuilder> executeOperation(List<String> args) {
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		String component = args.get(1);
		if (!componentsService.isInstalled(component)) {
			result.add(getNotInstalledMessage(component));
		} else if (isChildInstalled(component)) {
			result.add(getStillNeededMessage(component));
		} else {
			remove(result, component);
		}
		return result;
	}

	private void remove(List<StringBuilder> result, String component) {
		result.add(getRemovingMessage(component));
		componentsService.uninstall(component);
		Set<String> parentComponents = graph.getParents(component);
		for (String parentComponent : parentComponents) {
			if (componentsService.isInstalled(parentComponent)
					&& !componentsService.isExplicitlyInstalled(parentComponent)
					&& !isChildInstalled(parentComponent)) {
				remove(result, parentComponent);
			}
		}

	}

	private boolean isChildInstalled(String component) {
		Set<String> childComponents = this.graph.getChildren(component);
		for (String childComponent : childComponents) {
			if (componentsService.isInstalled(childComponent)) {
				return true;
			}
		}
		return false;
	}

	private StringBuilder getRemovingMessage(String component) {
		StringBuilder response = new StringBuilder();
		response.append("Removing ");
		response.append(component);
		return response;
	}

	private StringBuilder getStillNeededMessage(String component) {
		StringBuilder response = new StringBuilder();
		response.append(component);
		response.append(" is still needed");
		return response;
	}

	private StringBuilder getNotInstalledMessage(String component) {
		StringBuilder response = new StringBuilder();
		response.append(component);
		response.append(" is not installed");
		return response;
	}
}
