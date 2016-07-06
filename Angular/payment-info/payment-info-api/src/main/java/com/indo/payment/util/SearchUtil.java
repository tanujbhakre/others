package com.indo.payment.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains all utility the methods required for searching
 * 
 * @author tbhakre
 *
 */
public class SearchUtil {

	/**
	 * Gets the integer value for key in the map
	 * 
	 * @param map
	 *            Map in which key is to be searched
	 * @param key
	 *            Key to look for in the map
	 * @return
	 */
	public static Integer getInteger(Map<String, String> map, String key) {
		Integer value = null;
		if (map.containsKey(key)) {
			value = Integer.parseInt(map.get(key));
		}
		return value;
	}

	/**
	 * Iterates over map to search for keys having passed prefix and suffix
	 * 
	 * @param map
	 *            Map in which keys are to be searched
	 * @param keyPrefix
	 *            Key prefix
	 * @param keySuffix
	 *            Key suffix
	 * @return Map having the key without suffix and prefix
	 */
	public static Map<String, String> getMap(Map<String, String> map,
			String keyPrefix, String keySuffix) {

		Map<String, String> filterMap = new HashMap<String, String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();

			if (key.startsWith(keyPrefix) && key.endsWith(keySuffix)) {

				String actualKey = key.substring(keyPrefix.length(),
						(key.length() - keySuffix.length()));
				filterMap.put(actualKey, entry.getValue());

			}

		}
		return filterMap;
	}

	/**
	 * Looks for sorting details in the map and creates a map containing all the
	 * sort related informations
	 * 
	 * @param map
	 *            Map in which sort information is to be searched
	 * @param key
	 *            Key for the sorting details
	 * @return Map containing key as the field name and value as boolean
	 *         indicating if sort on the field is ascending or not
	 */
	public static Map<String, Boolean> getSort(Map<String, String> map,
			String key) {
		final String separator = ",";
		final String sortDescending = "-";
		Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
		if (map.containsKey(key)) {
			String[] sorts = map.get(key).split(separator);
			for (String sort : sorts) {
				boolean isAscending = true;
				String field = sort;
				if (sort.startsWith(sortDescending)) {
					isAscending = false;
					field = field.substring(sortDescending.length());
				}
				sortMap.put(field, isAscending);
			}
		}
		return sortMap;

	}
}
