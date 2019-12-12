package dependencies.operation;

import java.util.List;

public interface Operation {
	List<StringBuilder> execute(List<String> args);
}
