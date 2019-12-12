package fs.instructions.executors;

import java.util.Iterator;
import java.util.List;

import fs.Directory;
import fs.FileSystem;
import fs.instructions.BaseInstructionExecutor;

public class ListDirectoryExecutor extends BaseInstructionExecutor {

	public ListDirectoryExecutor(FileSystem filesystem) {
		super(filesystem);
	}

	@Override
	public String run(List<String> args) {
		StringBuilder sb = new StringBuilder();
		Iterator<Directory> iterator = this.filesystem.getCurrent().getSubDirectories();
		if (iterator.hasNext()) {
			while (iterator.hasNext()) {
				sb.append(iterator.next().getName() + "\t");
			}
		} else {
			sb.append("No subdirectories");
		}
		sb.append("\n");
		return sb.toString();
	}

}
