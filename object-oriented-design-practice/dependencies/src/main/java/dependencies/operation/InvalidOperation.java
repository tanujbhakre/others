package dependencies.operation;

import java.util.ArrayList;
import java.util.List;

public class InvalidOperation implements Operation {

	@Override
	public List<StringBuilder> execute(List<String> args) {
		return new ArrayList<>();
	}

}
