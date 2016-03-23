package stack.application.expressions;

public class ExpressionUtil {
	
	public static Operator getOperator(String character){
		Operator operator=null;
		if(character!=null){
			character=character.trim();
			if(character.contains(Operator.ADD.representation())){
				operator=Operator.ADD;
			}if(character.contains(Operator.SUBSTRACT.representation())){
				operator=Operator.SUBSTRACT;
			}if(character.contains(Operator.MUTILPLY.representation())){
				operator=Operator.MUTILPLY;
			}if(character.contains(Operator.DIVIDE.representation())){
				operator=Operator.DIVIDE;
			}
		}else{
			throw new IllegalArgumentException("Null string is not a valid argument");
		}
		
		return operator;
	}
	
	public static Integer getIntegerValue(String character){
		Integer value=null;
		if(character!=null){
			value=Integer.parseInt(character);
		}else{
			throw new IllegalArgumentException("Null is string is not a valid argument");
		}
		return value;
	}
	
	public static boolean hasPrecedence(Operator stackTop,Operator current){
		boolean hasPrecedence=false;
		hasPrecedence=stackTop.presedence()>=current.presedence();
		return hasPrecedence;
	}
	
	public static boolean hasPrecedence(Object stackTop,Operator current){
		boolean hasPrecedence=false;
		if(isLeftParanthesis(stackTop)){
			return false;
		}else if(stackTop instanceof Operator){
			Operator stacktopOperator=(Operator) stackTop;
			hasPrecedence=stacktopOperator.presedence()>=current.presedence();
		}
		return hasPrecedence;
	}
	
	public static boolean isLeftParanthesis(Object element){
		boolean hasPrecedence=false;
		if(element!=null && !(element instanceof Operator)){
			hasPrecedence=element.equals("(");
		}else{
			new IllegalArgumentException("Null character not allowed");
		}
		return hasPrecedence;
	}
	
	public static boolean isRightParanthesis(Object element){
		boolean hasPrecedence=false;
		if(element!=null && !(element instanceof Operator)){
			hasPrecedence=element.equals(")");
		}else{
			new IllegalArgumentException("Null character not allowed");
		}
		return hasPrecedence;
	}
	
}
