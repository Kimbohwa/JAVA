package ksnu.bohwa.lecture15;

import java.util.*;
import java.lang.NullPointerException;

public class StudentManager {
	
	Scanner sc = null;
	
	public StudentManager() {
		sc = new Scanner(System.in);
	}

	public void runProgram(){
        int num = -1;
        Student[] student = null;
        
        while(num != 0) {
            printProgram();
            try{
                num=sc.nextInt();
                student = selectProgram(num, student);
            } 
            catch(InputMismatchException e){
                System.out.println("정수를 입력하세요!");
                sc.nextLine();
            }
        }
        sc.close(); //메소드 종료, scanner close
    }
	
	//메뉴 출력
    public void printProgram(){
        System.out.println("1. Student 데이터 입력");
        System.out.println("2. Student 리스트 보기");
        System.out.println("3. 학과명으로 보기");
        System.out.println("4. Student 정보 삭제");
        System.out.println("5. 입학년도 범위로 검색");
        System.out.println("6. 이름 순으로 정렬해서 보기");
        System.out.println("7. 학과명 변경");
        System.out.println("8. 학생 정보 수정하기");
        System.out.println("0. 종료");
        System.out.print("선택 : ");
    }

    
    public Student[] selectProgram(int num, Student[] student){
        try {
	    	switch(num){
	            case 1:
	                student = createStudent();
	                inputData(student);
	                break;
	            case 2:
	            	showStudent(student);
	                break;
	            case 3:
	            	searchByDepartment(student);
	                break;
	            case 4:
	            	student=deleteStudent(student);
	                break;
	            case 5:
	            	searchByYearOfAdmission(student);
	                break;
	            case 6:
	            	sortByName(student);
	                break;
	            case 7:
	            	modifyDepartment(student);
	                break;
	            case 8:
	            	modifyStudentInformation(student);
	                break;
	            case 0:
	                break;
	            default:
	            	System.out.println("없는 번호입니다. 다시 선택하세요.");
	                break;
	
	        }
    	} catch(NullPointerException e) {
    		System.out.println("학생 데이터부터 생성하세요!");
    		System.out.println();
    	}
        return student;
    }
    
    //1번
    public Student[] createStudent(){
        int num = -1;
        System.out.print("입력할 학생의 명수를 넣으세요(4명 이상 입력) : ");
        while(true) {
	        try {
	        	num=sc.nextInt();
	        	while(num < 4) {
		        	System.out.println("4 이상의 값을 입력하세요");
		        	num=sc.nextInt();
		        }
	        	sc.nextLine();
		        Student[] std = new Student[num];

		        return std;
		        
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("정수 값을 입력하세요.");
	        	sc.nextLine();
	        }
        }
    }
    
    public void inputData(Student[] student){
        String schoolNumber=null, name=null, cityName=null, department=null;
        int yearOfAdmission=-1, yearOfBirth=-1;

        for (int i=0; i<student.length; i++){
            System.out.println((i+1)+"번째 학생");
            try {
	            System.out.print("학번 입력 : ");
	            schoolNumber = sc.next();
	            System.out.print("이름 입력 : ");
	            name=sc.next();
	            System.out.print("도시 입력 : ");
	            cityName=sc.next();
            	System.out.print("입학년도 입력 : ");
            	yearOfAdmission=sc.nextInt();
            	sc.nextLine();
            	System.out.print("출생년도 입력 : ");
            	yearOfBirth=sc.nextInt();
            	sc.nextLine();
                System.out.print("학과명 입력 : ");
                department=sc.next();
                
                student[i]=new Student(schoolNumber,name,cityName,yearOfAdmission,yearOfBirth,department);
            }
            catch(InputMismatchException e) {
            	System.out.println("올바른 값을 입력하지 않았습니다. "+(i+1)+" 번째 학생의 정보를 다시 입력하세요.");
            	i--;
            	sc.nextLine();
            }
        }
    }
    
    //2번
    public void showStudent(Student[] student) {
    	System.out.printf("학번\t  이름\t주소\t입학년도\t태어난년도\t학과명 \n");
    	for (Student std : student) {
    		printStudent(std);
    	}
    }
    
    public void printStudent(Student student) {
    	System.out.println(student.getSchoolNumber() + "  " + student.getName() + "\t" + student.getCityName() + "\t"
				+ student.getYearOfAdmission() + "\t" + student.getYearOfBirth()+"\t"+student.getDepartment());
    }
    
    //3번
    public void searchByDepartment(Student[] student) {
    	String department=null;
    	boolean isFound=false;
    	
    	System.out.print("검색할 학생 학과 : ");
    	department=sc.next();
    	for (Student std:student) {
    		if (department.equals(std.getDepartment())) {
    			printStudent(std);
    			isFound=true;
    		}
    	}
    	if (!isFound) {
    		System.out.println("찾으시는 학과가 없습니다.");
    	}
    }
    
    //4번
    public Student[] deleteStudent(Student[] student) {
    	String schoolNumber=null;
    	boolean isFound=false;
    	Student[] newStudent=null;
    	
    	System.out.print("=> 삭제할 학생의 학번 : ");
    	schoolNumber=sc.next();
		
    	for (int i=0; i<student.length; i++) {
    		if (schoolNumber.equals(student[i].getSchoolNumber())) {
    			System.out.println(schoolNumber+" "+student[i].getName()+"을(를) 삭제하였습니다..");
    			
    			for (int j=i; j<student.length-1; j++) {
    				student[j]=student[j+1];
    			}
    			
    			newStudent= new Student[student.length-1];
    			for (int k=0; k<newStudent.length; k++) {
    				newStudent[k]=student[k];
    			}
    			
    			isFound=true;
    		}
    		
    	}
    	
    	if (isFound) {
        	for (Student std:newStudent) 
        		printStudent(std);
    	}
    	else {
    		System.out.println("찾으시는 학번이 없습니다.");
    		newStudent=(Student[]) student.clone();
    	}
    	
    	return newStudent;
    }
    
    //5번
    public void searchByYearOfAdmission(Student[] student) {
    	int min=-1, max=-1;
    	boolean isFound=false;
    	
    	System.out.print("최소 입학년도 : ");
    	min=sc.nextInt();
    	sc.nextLine();
    	System.out.print("최대 입학년도 : ");
    	max=sc.nextInt();
    	sc.nextLine();
    	
    	while (min>max) {
    		System.out.println("최소 입학년도와 최대 입학년도를 순서에 맞게 입력하세요.");
    		System.out.print("최소 입학년도 : ");
        	min=sc.nextInt();
        	sc.nextLine();
        	System.out.print("최대 입학년도 : ");
        	max=sc.nextInt();
        	sc.nextLine();
    	}
    	
    	for (Student std : student) {
    		if (min<=std.getYearOfAdmission() && std.getYearOfAdmission()<=max) {
    			printStudent(std);
    			isFound=true;
    		}
    	}
    	if (!isFound) {
    		System.out.println("찾으시는 년도에는 입학한 사람의 데이터가 없습니다.");
    	}
    }
    
    //6번
    public void sortByName(Student[] student) {
    	Student[] studentCopy = (Student[]) student.clone();//student 배열엔 각 주소 값이 들어있음 그것을 순서대로 정렬하여 가지고 있어도 원래 배열에는 영향을 미치지 않음
    	for (int i=0; i<studentCopy.length-1; i++) {
    		for (int j=1; j<studentCopy.length-i; j++) {
    			String name1 = studentCopy[j-1].getName();
    			String name2 = studentCopy[j].getName();
    			if (name1.compareTo(name2)>0) {
    				Student tmp = studentCopy[j-1];
    				studentCopy[j-1]=studentCopy[j];
    				studentCopy[j]=tmp;
    			}
    		}
    	}
    	
    	showStudent(studentCopy);
    	
    }
    
    //7번
    public void modifyDepartment(Student[] student) {
    	String name=null, department=null;
    	boolean isFound = false;
    	
    	System.out.print("검색할 이름 : ");
    	name=sc.next();
    	for (Student std:student) {
    		if (name.equals(std.getName())) {
    	    	System.out.println("현재 "+name+"의 학과명은 "+std.getDepartment()+"입니다.");
    	    	System.out.print("변경할 학과명을 입력하세요 : ");
    	    	department=sc.next();
    	    	std.setDepartment(department);
    	    	printStudent(std);
    	    	isFound=true;
    		}
    	}
    	if (!isFound)
    		System.out.println("없는 이름입니다.");
    	
    }
    
    //8번
    public void modifyStudentInformation(Student[] student) {
    	String schoolNumber=null, name=null, cityName=null, department=null;
    	int yearOfAdmission=-1, yearOfBirth=-1;
    	boolean isFound=false;
    	
    	System.out.print("수정할 학생의 학번 : ");
    	schoolNumber=sc.next();
    	for (Student std:student) {
    		if (schoolNumber.equals(std.getSchoolNumber())){
    			System.out.println("<정보 입력>");
    			System.out.print("학번 입력 : ");
	            schoolNumber = sc.next();
	            System.out.print("이름 입력 : ");
	            name=sc.next();
	            System.out.print("도시 입력 : ");
	            cityName=sc.next();
            	System.out.print("입학년도 입력 : ");
            	yearOfAdmission=sc.nextInt();
            	sc.nextLine();
            	System.out.print("출생년도 입력 : ");
            	yearOfBirth=sc.nextInt();
            	sc.nextLine();
                System.out.print("학과명 입력 : ");
                department=sc.next();
                
                std.setSchoolNumber(schoolNumber);
                std.setName(name);
                std.setCityName(cityName);
                std.setYearOfAdmission(yearOfAdmission);
                std.setYearOfBirth(yearOfBirth);
                std.setDepartment(department);
                isFound=true;
    		}
    	}
    	
    	if (isFound) {
        	for (Student std:student) {
        		printStudent(std);
        	}
    	}
    	else System.out.println("없는 학번입니다.");
    }
    
	public static void main(String[] args) {
		StudentManager manager = new StudentManager();
		manager.runProgram();
	}

}
