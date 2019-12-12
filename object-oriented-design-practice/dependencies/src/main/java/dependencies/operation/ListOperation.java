package dependencies.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dependencies.components.ComponentsService;

public class ListOperation extends BaseOperation {

	private final ComponentsService componentsService;

	private static final int MIN_SIZE = 1;

	public ListOperation(ComponentsService componentsService) {
		super(MIN_SIZE);
		this.componentsService = componentsService;
	}

	@Override
	public List<StringBuilder> executeOperation(List<String> args) {
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		Set<String> components = componentsService.getComponents();
		for (String component : components) {
			result.add(new StringBuilder(component));
		}
		return result;
	}
}
