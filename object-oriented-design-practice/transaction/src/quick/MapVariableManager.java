package quick;
import java.util.HashMap;
import java.util.Map;

public class MapVariableManager implements VariableManager {
	private Map<String, String> valueMap;
	private Map<String, Integer> countMap;
	private static StringBuilder SUCCESS = new StringBuilder("SUCCESS");
	private static StringBuilder FALIURE = new StringBuilder();

	public MapVariableManager() {
		valueMap = new HashMap<>();
		countMap = new HashMap<>();
	}

	public MapVariableManager(Map<String, String> valueMap, Map<String, Integer> countMap) {
		this.valueMap = valueMap;
		this.countMap = countMap;
	}

	@Override
	public StringBuilder setVariable(String key, String value) {
		valueMap.put(key, value);
		Integer count = countMap.get(value);
		count = count == null ? 1 : count + 1;
		countMap.put(value, count);
		return SUCCESS;
	}

	@Override
	public StringBuilder getVariable(String key) {
		String value = valueMap.get(key);
		if (value != null)
			return new StringBuilder(value);
		else
			return FALIURE;

	}

	@Override
	public StringBuilder delete(String key) {
		String value = valueMap.get(key);
		if (value != null) {
			valueMap.remove(key);
			Integer count = countMap.get(value);
			if (count == null || count == 1) {
				countMap.remove(value);
			} else {
				countMap.put(value, count - 1);
			}
			return SUCCESS;
		} else {
			return FALIURE;
		}
	}

	@Override
	public StringBuilder count(String value) {
		Integer count = countMap.get(value);
		if (count == null) {
			count = 0;
		}
		StringBuilder sb = new StringBuilder(String.valueOf(count));
		return sb;
	}

	@Override
	public VariableManager clone() {
		Map<String, String> cloneValueMap = new HashMap<>();
		Map<String, Integer> cloneCountMap = new HashMap<>();
		valueMap.forEach((key, value) -> cloneValueMap.put(key, value));
		countMap.forEach((key, value) -> cloneCountMap.put(key, value));
		VariableManager vm = new MapVariableManager(cloneValueMap, cloneCountMap);
		return vm;
	}

}