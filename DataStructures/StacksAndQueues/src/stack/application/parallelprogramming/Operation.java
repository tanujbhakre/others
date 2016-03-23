package stack.application.parallelprogramming;

public enum Operation {
	PUSH("push"),
	POP("pop"),
	PEEK("peek");
	
	private String text;
	
	Operation(String text){
		this.text=text;
	}
	
	public String text(){
		return text;
	}
}
