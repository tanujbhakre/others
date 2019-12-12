package dependencies.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dependencies.components.ComponentsService;
import dependencies.graph.Graph;

public class InstallOperation extends BaseOperation {

	private final Graph graph;
	private final ComponentsService componentsService;

	private static final int MIN_SIZE = 2;

	public InstallOperation(Graph graph, ComponentsService componentsService) {
		super(MIN_SIZE);
		this.graph = graph;
		this.componentsService = componentsService;
	}

	@Override
	public List<StringBuilder> executeOperation(List<String> args) {
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		String component = args.get(1);
		if (componentsService.isInstalled(component)) {
			result.add(getAlreadyInstalledMessage(component));
		} else {
			install(result, component, true);
		}
		return result;
	}

	private void install(List<StringBuilder> message, String component, boolean isExplicit) {
		Set<String> needsComponents = this.graph.getParents(component);
		for (String needsComponent : needsComponents) {
			if (!componentsService.isInstalled(needsComponent)) {
				install(message, needsComponent, false);
			}
		}
		componentsService.installWithType(component, isExplicit);
		message.add(getInstallingMessage(component));
	}

	private StringBuilder getInstallingMessage(String component) {
		StringBuilder response = new StringBuilder();
		response.append("Installing ");
		response.append(component);
		return response;
	}

	private StringBuilder getAlreadyInstalledMessage(String component) {
		StringBuilder response = new StringBuilder();
		response.append(component);
		response.append(" is already installed");
		return response;
	}
}
