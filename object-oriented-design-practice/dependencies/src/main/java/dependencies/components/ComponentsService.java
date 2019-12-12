package dependencies.components;

import java.util.Set;

public interface ComponentsService {

	boolean installWithType(String component, boolean explicit);

	boolean isInstalled(String component);

	boolean isExplicitlyInstalled(String component);

	void uninstall(String component);

	Set<String> getComponents();
}
