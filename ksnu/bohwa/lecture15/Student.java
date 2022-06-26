package ksnu.bohwa.lecture15;

public class Student {
	
	private String schoolNumber;
	private String name;
	private String cityName;
	private int yearOfAdmission;
	private int yearOfBirth;
	private String department;

	public Student() {}
	
	public Student(String schoolNumber, String name, String cityName, int yearOfAdmission, int yearOfBirth, String department) {
		this.schoolNumber=schoolNumber;
		this.name=name;
		this.cityName=cityName;
		this.yearOfAdmission=yearOfAdmission;
		this.yearOfBirth=yearOfBirth;
		this.department=department;
	}
	
	public void setSchoolNumber(String schoolNumber){
		this.schoolNumber=schoolNumber;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setCityName(String cityName) {
		this.cityName=cityName;
	}
	
	public void setYearOfAdmission(int yearOfAdmission) {
		this.yearOfAdmission=yearOfAdmission;
	}
	
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth=yearOfBirth;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getSchoolNumber() {
		return schoolNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public int getYearOfAdmission() {
		return yearOfAdmission;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public String getDepartment() {
		return department;
	}
	
	public static void main(String[] args) {
		//Student student = new Student();
	}


}
