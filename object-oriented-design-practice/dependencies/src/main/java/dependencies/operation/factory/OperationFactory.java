package dependencies.operation.factory;

import dependencies.components.ComponentsService;
import dependencies.components.InMemoryComponentsService;
import dependencies.graph.Graph;
import dependencies.graph.InMemoryGraph;
import dependencies.operation.DependOperation;
import dependencies.operation.InstallOperation;
import dependencies.operation.InvalidOperation;
import dependencies.operation.ListOperation;
import dependencies.operation.Operation;
import dependencies.operation.RemoveOperation;

public class OperationFactory {

	private Graph graph;
	private ComponentsService componentsService;

	public OperationFactory() {
		this.graph = new InMemoryGraph();
		this.componentsService = new InMemoryComponentsService();

	}

	public Operation getOperation(String operation) {
		switch (operation) {
		case OperationConstants.DEPEND_COMMAND:
			return new DependOperation(graph);
		case OperationConstants.INSTALL_COMMAND:
			return new InstallOperation(graph, componentsService);
		case OperationConstants.REMOVE_COMMAND:
			return new RemoveOperation(graph, componentsService);
		case OperationConstants.LIST_COMMAND:
			return new ListOperation(componentsService);
		default:
			return new InvalidOperation();
		}
	}
}
