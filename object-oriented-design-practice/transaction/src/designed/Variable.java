package designed;

import java.util.HashMap;
import java.util.Map;

public class Variable {
	private Map<String, String> valueMap;
	private Map<String, Integer> countMap;

	public Variable() {
		valueMap = new HashMap<>();
		countMap = new HashMap<>();
	}

	public Variable(Map<String, String> valueMap, Map<String, Integer> countMap) {
		this.valueMap = valueMap;
		this.countMap = countMap;
	}

	public String getValue(String key) {
		return this.valueMap.get(key);
	}

	public Integer getCount(String value) {
		return this.countMap.get(value);
	}

	public void setValue(String key, String value) {
		this.valueMap.put(key, value);
		Integer count = this.countMap.get(value) == null ? 1 : this.countMap.get(value) + 1;
		this.countMap.put(value, count);
	}

	public void deleteValue(String key) {
		String value = this.valueMap.get(key);
		if (value != null) {
			Integer count = this.countMap.get(value) == null ? 0 : this.countMap.get(value) - 1;
			this.countMap.put(value, count);
			this.valueMap.remove(key);
		}
	}

	public Variable copy() {
		Map<String, String> cloneValueMap = new HashMap<>();
		Map<String, Integer> cloneCountMap = new HashMap<>();
		valueMap.forEach((key, value) -> cloneValueMap.put(key, value));
		countMap.forEach((key, value) -> cloneCountMap.put(key, value));
		Variable v = new Variable(cloneValueMap, cloneCountMap);
		return v;

	}
}
