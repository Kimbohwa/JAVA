package ksnu.bohwa1378.lecture08;

public class Professor extends Researcher {

	protected String position = null;
	
	public Professor() {}
	public Professor(String citizenNumber, String name, int birthYear, int employeeNumber, String dept, String position) {
		this.citizenNumber=citizenNumber;
		this.name=name;
		this.birthYear=birthYear;
		this.employeeNumber=employeeNumber;
		this.dept=dept;
		this.position=position;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getPosition();
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	//toString() 오버라이딩
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
