package dependencies.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class InMemoryGraph implements Graph {

	private Map<String, Set<String>> parentAdjacencyList;
	private Map<String, Set<String>> childAdjacencyList;

	public InMemoryGraph() {
		this.parentAdjacencyList = new HashMap<String, Set<String>>();
		this.childAdjacencyList = new HashMap<String, Set<String>>();
	}

	@Override
	public boolean addParentChild(String parent, String child) {
		Set<String> parentsParent = getParents(parent);
		if (parentsParent.contains(child) || parent.equals(child)) {
			return false;
		} else {
			Set<String> parents = parentAdjacencyList.getOrDefault(child, new HashSet<>());
			parents.add(parent);
			parentAdjacencyList.put(child, parents);

			Set<String> childs = childAdjacencyList.getOrDefault(parent, new HashSet<>());
			childs.add(child);
			childAdjacencyList.put(parent, childs);
			return true;
		}
	}

	@Override
	public Set<String> getParents(String node) {
		return breadthFirstSearch(node, parentAdjacencyList);
	}

	@Override
	public Set<String> getChildren(String node) {
		return breadthFirstSearch(node, childAdjacencyList);
	}

	private Set<String> breadthFirstSearch(String node, Map<String, Set<String>> adjacencyList) {
		Set<String> result = new LinkedHashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			String currentNode = queue.poll();
			Set<String> neighbours = adjacencyList.getOrDefault(currentNode, new HashSet<>());
			for (String neighbour : neighbours) {
				queue.add(neighbour);
			}
			if (!currentNode.equals(node))
				result.add(currentNode);
		}
		return result;
	}
}
