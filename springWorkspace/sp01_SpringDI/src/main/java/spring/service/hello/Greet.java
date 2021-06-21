package spring.service.hello;

public class Greet {
	//field
	private String message;
	
	public Greet() {
		// 객체가 생성되는 시점을 파악하기 위해서 출력문을 하나 넣어두었다.
		System.out.println(getClass().getName() + ".....Instance Create...");
	}
	
	public Greet(String message) {
		this.message = message;
	}

	//setter / getter
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	//message를 출력하는 기능을 정의
	public void printMessage() {
		System.out.println("\n" + getClass().getName() + "===>" + message);
	}
	
	
}
