package dependencies.graph;

import java.util.Set;

public interface Graph {
	boolean addParentChild(String parent, String child);

	Set<String> getParents(String node);

	Set<String> getChildren(String node);
}
