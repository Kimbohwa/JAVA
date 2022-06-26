package ksnu.bohwa1378.lecture08;

public class Student extends Person {

	protected int studentNumber=-1;
	protected String dept=null;
	
	public Student() {}
	public Student(String citizenNumber, String name, int birthYear, int studentNumber, String dept) {
		this.citizenNumber=citizenNumber;
		this.name=name;
		this.birthYear=birthYear;
		this.studentNumber=studentNumber;
		this.dept=dept;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getStudentNumber() + " " + getDept();
	}

	public int getStudentNumber() {
		return studentNumber;
	}
	
	public String getDept() {
		return dept;
	}
	
	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public static void main(String[] args) {

	}
}
