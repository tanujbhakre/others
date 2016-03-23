package stack.application.expressions;

import exceptions.EmptyStackException;
import stack.Stack;
import stack.impl.ListStack;

public class PostFixEvaluation {
	
	public static void main(String [] args){
		//String expression="234+56--*";
		//String expression="13*2+1+3/";
		//String expression="13*2+1+3/2";
		String expression="123*+45*6++";
		try {
			Integer value=evaluatePostFixExpression(expression);
			System.out.println("Postfix expression "+expression+" evaluates to "+value);
		} catch (EmptyStackException e) {
			System.out.println("Illegal Expression");
		}catch (IllegalArgumentException e) {
			System.out.println("Illegal Expression");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static int evaluatePostFixExpression(String expression) throws EmptyStackException{
		int value=0;
		Stack<Integer> stack=new ListStack<Integer>();
		for(int iCount=0;iCount<expression.length();iCount++){
			String character=expression.substring(iCount,iCount+1);
			Operator operator=ExpressionUtil.getOperator(character);
			if(operator!=null){
				Integer result=null;
				Integer value2=stack.pop();
				Integer value1=stack.pop();
				switch (operator) {
				case ADD:
					result=value1+value2;
					break;
				case SUBSTRACT:
					result=value1-value2;
					break;
				case MUTILPLY:
					result=value1*value2;
					break;
				case DIVIDE:
					result=value1/value2;
					break;
				}
				stack.push(result);
			}else{
				Integer operand=ExpressionUtil.getIntegerValue(character);
				stack.push(operand);
			}
		}
		value=stack.pop();
		if(!stack.isEmpty()){
			throw new IllegalArgumentException("Incomplete Expression");
		}
		return value;
	}

}
