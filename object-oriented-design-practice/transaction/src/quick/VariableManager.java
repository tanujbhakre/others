package quick;
public interface VariableManager {
	StringBuilder setVariable(String key, String value);

	StringBuilder getVariable(String key);

	StringBuilder delete(String key);

	StringBuilder count(String value);

	VariableManager clone();
}