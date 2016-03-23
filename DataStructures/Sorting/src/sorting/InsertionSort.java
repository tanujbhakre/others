package sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.SortingUtil;

public class InsertionSort {

	public static Integer[] getUnsortedList() throws IOException {
		File outputFile = new File(SortingUtil.INPUT_FILE);
		FileReader fr = new FileReader(outputFile);
		BufferedReader reader = new BufferedReader(fr);
		try {
			String currLine = null;
			while ((currLine = reader.readLine()) != null) {
				String[] sList = currLine.split(",");
				Integer[] list = new Integer[sList.length];
				int iCount = 0;
				for (String s : sList) {
					list[iCount++] = Integer.parseInt(s);
				}
				for(Integer i:list){
					System.out.print(i+",");
				}
				System.out.println();
				return list;
			}
		} catch (IOException e) {
			throw e;
		} finally {
			reader.close();
		}
		return null;
	}
	
	public static void writeSortedList(Integer [] sortedList) throws IOException{
		File outputFile=new File(SortingUtil.OUTPUT_FILE);
		FileWriter fw=new FileWriter(outputFile);
		BufferedWriter writer=new BufferedWriter(fw);
		String outPut=new String();
		int iCounter=0;
		for(Integer i:sortedList){
			outPut+=i;
			if(iCounter++<sortedList.length-1){
				outPut+=",";
			}
		}
		System.out.println(outPut);
		writer.write(outPut);
		writer.close();
	}
	public static void main(String args[]) throws IOException {
		Integer [] list=getUnsortedList();
		
		for(int iCounter1=1;iCounter1<list.length;iCounter1++){
			int key=list[iCounter1];
			for(int iCounter2=iCounter1-1;iCounter2>-1 && list[iCounter2]>key;iCounter2--){
				list[iCounter2+1]=list[iCounter2];
				list[iCounter2]=key;
			}
		}
		writeSortedList(list);		
	}
}
