package ksnu.bohwa1378.lecture08;

public class Researcher extends Person {

	int employeeNumber = -1;
	String dept = null;
	
	public Researcher() {}
	public Researcher(String citizenNumber, String name, int birthYear, int employeeNumber, String dept) {
		this.citizenNumber=citizenNumber;
		this.name=name;
		this.birthYear=birthYear;
		this.employeeNumber=employeeNumber;
		this.dept=dept;
	}
	
	@Override
	public String toString() { 
		return super.toString() + " " + getEmployeeNumber() + " " + getDept();
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public static void main(String[] args) {
	}

}
