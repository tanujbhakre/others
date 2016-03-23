package stack.application.expressions;

import exceptions.EmptyStackException;
import stack.Stack;
import stack.impl.LinkedListStack;
import stack.impl.ListStack;

public class InfixToPostfix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String expression="1+2*3+4*5+6";
		try {
			String value=convertInfixToPostFix(expression);
			System.out.println("Infix expression "+expression+" = Postfix expression "+value);
		}catch (EmptyStackException e) {
			System.out.println("Illegal Expression");
		}catch (IllegalArgumentException e) {
			System.out.println("Illegal Expression");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String convertInfixToPostFix(String expression) throws EmptyStackException{
		String postFixExpression="";
		Stack<Operator> stack=new LinkedListStack<Operator>(); 
		for(int iCount=0;iCount<expression.length();iCount++){
			String character=expression.substring(iCount,iCount+1);
			Operator operator=ExpressionUtil.getOperator(character);
			if(operator!=null){
				if(!stack.isEmpty()){
					Operator stackTop=stack.peek();
					while(!stack.isEmpty() && ExpressionUtil.hasPrecedence(stackTop, operator)){
						postFixExpression+=stack.pop().representation();
						if(!stack.isEmpty()){
							stackTop=stack.peek();
						}
					}
					stack.push(operator);
				}else{
					stack.push(operator);
				}
			}else{
				postFixExpression+=character;
			}
		}
		while(!stack.isEmpty()){
			postFixExpression+=stack.pop().representation();
		}
		return postFixExpression;
		
	}
}
