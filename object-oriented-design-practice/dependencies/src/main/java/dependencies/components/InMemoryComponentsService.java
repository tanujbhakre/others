package dependencies.components;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class InMemoryComponentsService implements ComponentsService {

	private Map<String, Boolean> componentInstallationMap;

	public InMemoryComponentsService() {
		componentInstallationMap = new TreeMap<String, Boolean>();
	}

	@Override
	public boolean installWithType(String component, boolean explicit) {
		if (componentInstallationMap.containsKey(component)) {
			return false;
		} else {
			componentInstallationMap.put(component, explicit);
			return true;
		}
	}

	@Override
	public boolean isInstalled(String component) {
		return componentInstallationMap.containsKey(component);
	}

	@Override
	public boolean isExplicitlyInstalled(String component) {
		if (!componentInstallationMap.containsKey(component)) {
			throw new IllegalArgumentException(component + " component not installed");
		} else {
			return componentInstallationMap.get(component);
		}
	}

	@Override
	public void uninstall(String component) {
		if (!componentInstallationMap.containsKey(component)) {
			throw new IllegalArgumentException(component + " component not installed");
		} else {
			componentInstallationMap.remove(component);
		}

	}

	@Override
	public Set<String> getComponents() {
		Set<String> components = new LinkedHashSet<>();
		componentInstallationMap.forEach((key, value) -> {
			components.add(key);
		});
		return components;
	}

}
