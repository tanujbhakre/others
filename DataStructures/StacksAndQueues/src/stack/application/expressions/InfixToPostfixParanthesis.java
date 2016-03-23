package stack.application.expressions;

import exceptions.EmptyStackException;
import stack.Stack;
import stack.impl.ListStack;

public class InfixToPostfixParanthesis {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String expression="A+(B+C)";
		String expression="1$2*3-4+5/6/(7+8)";
		try {
			String value=convertInfixToPostFixParantrhesis(expression);
			System.out.println("Infix expression "+expression+" = Postfix expression "+value);
		}catch (EmptyStackException e) {
			System.out.println("Illegal Expression");
		}catch (IllegalArgumentException e) {
			System.out.println("Illegal Expression");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String convertInfixToPostFixParantrhesis(String expression) throws EmptyStackException{
		String postFixExpression="";
		Stack<Object> stack=new ListStack<Object>(); 
		for(int iCount=0;iCount<expression.length();iCount++){
			String character=expression.substring(iCount,iCount+1);
			Operator operator=ExpressionUtil.getOperator(character);
			if(operator!=null){
				if(!stack.isEmpty()){
					Object stackTop=stack.peek();
					while(!stack.isEmpty() && ExpressionUtil.hasPrecedence(stackTop, operator)){
						postFixExpression+=((Operator)stack.pop()).representation();
						if(!stack.isEmpty()){
							stackTop=stack.peek();
						}
					}
					stack.push(operator);
				}else{
					stack.push(operator);
				}
			}else if(ExpressionUtil.isLeftParanthesis(character)){
				stack.push(character);
			}else if(ExpressionUtil.isRightParanthesis(character)){
				Object elementOnStack=null;
				do{
					elementOnStack=(Operator)stack.pop();
					if(elementOnStack instanceof Operator){
						Operator stackOperator=(Operator)elementOnStack;
						postFixExpression+=stackOperator.representation();
					}
				}while(ExpressionUtil.isLeftParanthesis(elementOnStack));
			}
			else{
				postFixExpression+=character;
			}
		}
		while(!stack.isEmpty()){
			Object stackElement=stack.pop();
			if(stackElement instanceof Operator){
				postFixExpression+=((Operator)stackElement).representation();
			}
		}
		return postFixExpression;
		
	}
}
