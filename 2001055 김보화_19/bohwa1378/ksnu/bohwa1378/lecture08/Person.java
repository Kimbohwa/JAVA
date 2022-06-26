package ksnu.bohwa1378.lecture08;

import java.util.Comparator;

class BirthYearComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2) {
		return o1.getBirthYear()-o2.getBirthYear();
	}
}

class NameComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2) {
		return (o1.getName()).compareTo(o2.getName());
	}
}

class ReverseNameComparator implements Comparator<Person>{
	@Override
	public int compare(Person o1, Person o2) {
		return (o2.getName()).compareTo(o1.getName());
	}
}


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
