package stack.application.parallelprogramming;

import exceptions.FileFormatException;

public class ParallelProgrammingUtil {

	public static final String SPLITTER=":";
	
	
	public static Operation getOperation(String detail) throws FileFormatException{
		
		if(detail!=null ){
			if( detail.contains(Operation.PUSH.text())){
				return Operation.PUSH;
			}
			if( detail.contains(Operation.POP.text())){
				return Operation.POP;
			}
		}
		throw new FileFormatException();
	}
	
public static String getValue(String detail){
		String value=null;
		if(detail!=null ){
			String[] aDetails=detail.split(SPLITTER);
			if(aDetails.length>1){
				value=aDetails[1].trim();
			}
		}
		return value;
	}
}
