//getter/setter 만들어야 하나
package ksnu.bohwa1378.lecture08;

public class Person implements Comparable<Person>{
	
	protected String citizenNumber = null; 
	protected String name = null;
	protected int birthYear = -1;
	
	public Person(){}
	public Person(String citizenNumber, String name, int birthYear){
		this.citizenNumber = citizenNumber;
		this.name=name;
		this.birthYear=birthYear;
	}

	@Override
	public String toString() {
		return citizenNumber + " " + name + " " + birthYear;
	}
	
	@Override
	public int compareTo(Person o) {
		return (this.getCitizenNumber()).compareTo(o.getCitizenNumber());
	}

	public String getCitizenNumber() {
		return citizenNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public int getBirthYear() {
		return birthYear;
	}

	public void setCitizenNumber(String citizenNumber) {
		this.citizenNumber = citizenNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public static void main(String[] args) {

	}
}
