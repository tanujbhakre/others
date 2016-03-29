package util;

import java.util.ArrayList;
import java.util.List;

public class SortingUtil {
	public static final String INPUT_FILE = "./resources/files/input/insertionInput.txt";
	public static final String OUTPUT_FILE = "./resources/files/output/insertionOutput.txt";

	public static boolean isSorted(String[] list) {
		boolean flag = true;
		List<Integer> lSortedList = new ArrayList<Integer>();
		for (String s : list) {
			lSortedList.add(Integer.parseInt(s));
		}
		int iCounter = 0;
		int oldValue = 0;
		for (Integer i : lSortedList) {

			if (iCounter == 0) {
				oldValue = i;
				iCounter++;

			} else {
				if (oldValue > i) {
					flag = false;
					break;
				}
			}

		}

		return flag;
	}
}
