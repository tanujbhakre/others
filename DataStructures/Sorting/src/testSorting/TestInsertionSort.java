package testSorting;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import util.SortingUtil;

public class TestInsertionSort {
	@Test
	public void testInsertionSort() throws FileNotFoundException {
		File outputFile = new File(SortingUtil.OUTPUT_FILE);
		FileReader fr = new FileReader(outputFile);
		BufferedReader reader = new BufferedReader(fr);
		try {
			String currLine=null;
			while ((currLine=reader.readLine() )!= null) {
				String [] list=currLine.split(",");
				
				assertEquals(SortingUtil.isSorted(list),true);
			}
		} catch (IOException e) {

		}
	}

}
