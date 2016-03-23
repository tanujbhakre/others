package stack.application.expressions;

public enum Operator {
	ADD("+",1),
	SUBSTRACT("-",1),
	MUTILPLY("*",2),
	DIVIDE("/",2),
	POWER("$",3);
	
	private String representation;
	private Integer presedence;

	Operator(String representation,Integer presedence){
		this.representation=representation;
		this.presedence=presedence;
	}
	
	public String representation(){
		return representation;
	}
	
	public Integer presedence(){
		return presedence;
	}
	
	
}
