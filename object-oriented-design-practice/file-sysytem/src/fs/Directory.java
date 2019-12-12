package fs;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Directory {
	private String name;
	private Directory parent;
	private Map<String, Directory> subDirectories;

	public Directory(String name, Directory parent) {
		this.name = name;
		this.parent = parent;
		this.subDirectories = new TreeMap<>();
	}

	public String getName() {
		return name;
	}

	public Directory getParent() {
		return parent;
	}

	public Iterator<Directory> getSubDirectories() {
		return subDirectories.values().iterator();
	}

	public void createSubDirectory(Directory directory) {
		this.subDirectories.put(directory.name, directory);
	}

	public Directory getSubDirectory(String name) {
		return this.subDirectories.get(name);
	}
}
