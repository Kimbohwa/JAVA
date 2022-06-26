package ksnu.bohwa1378.lecture08;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


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

public class UnivManagement {
	Scanner sc = null;
	
	public UnivManagement() {
		sc = new Scanner(System.in);
	}
	
	public void runProgram() {
		int num = -1;
		Person[] persons = null;
		
		while(num != 0) {
			printProgram();
			try{
                num=sc.nextInt();
                persons = selectProgram(num, persons);
            } 
            catch(InputMismatchException e){
                System.out.println("정수를 입력하세요!");
                sc.nextLine();
            }
		}
        //sc.close(); //메소드 종료, scanner close
	}
	
	public void printProgram() {
		System.out.println("1. 대학 구성원 데이터 입력");
		System.out.println("2. 대학 구성원 데이터 전체 출력");
		System.out.println("3. 대학 구성원 데이터 조건 검색");
		System.out.println("4. 대학 구성원 데이터 변경");
		System.out.println("5. 대학 구성원 데이터 삭제");
		System.out.println("0. 종료");
        System.out.print("선택 : ");
	}
	
	public Person[] selectProgram(int num, Person[] persons) {
		try {
	    	switch(num){
	            case 1:
	                persons = createMember();
	                selectType(persons);
	                break;
	            case 2:
	            	printAll(persons);
	                break;
	            case 3:
	            	searchMember(persons);
	                break;
	            case 4:
	            	updateMember(persons);
	                break;
	            case 5:
	            	persons = deleteMember(persons);
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
        return persons;
	}
	
	//1번
	public Person[] createMember() {
		Person[] persons = null;
		int n=-1;
		
		System.out.println(">몇 명을 입력하시겠습니까?");
        while(true) {
	        try {
	        	n=sc.nextInt();
	        	sc.nextLine();
		        persons = new Person[n]; 
		        
				return persons;
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("정수 값을 입력하세요.");
	        	sc.nextLine();
	        }
        }
	}
	
	public void selectType(Person[] persons) {
		int selection=-1;
		for (int i=0; i<persons.length; i++) {
			System.out.println("> 구성원의 형태를 선택하세요.");
			System.out.println("1. 일반인  2. 학생  3. 연구원  4. 교수");
			try{
				selection=sc.nextInt();
				if (selection<1 || selection>4) {
					System.out.println("없는 번호입니다. 다시 선택하세요.");
					continue;
				}
				System.out.println("> 정보를 입력하세요");
				persons[i]=inputData(persons[i], selection); //반환을 받아야 함
			}
			catch(InputMismatchException e) {
				System.out.println("정수를 입력하세요");
			}
		}
	}
	
	public Person inputData(Person person, int selection) { //수정하기 //반환을 해야 함
		String citizenNumber=null, name=null;
		int birthYear=-1;
		
		System.out.print("주민번호 : ");
		citizenNumber=sc.next();
		System.out.print("이름 : ");
		name=sc.next();
		System.out.print("태어난 연도 : ");
		birthYear=sc.nextInt();
		
		switch(selection) {
		case 1: //일반인
			person=new Person(citizenNumber,name,birthYear);
			break;
		case 2: //학생
			person = inputDataStud(person,citizenNumber, name, birthYear);
			break; 
		case 3: //연구원
			person = inputDataResearcher(person,citizenNumber, name, birthYear);
			break;
		case 4: //교수
			person = inputDataProfessor(person,citizenNumber, name, birthYear);
			break;
		}
		return person;
	}
	
	//학생
	public Person inputDataStud(Person person, String citizenNumber, String name, int birthYear) {
		int studentNumber=-1;
		String dept = null;
		System.out.print("학번 : ");
		studentNumber=sc.nextInt();
		System.out.print("학과 : ");
		dept=sc.next();
		person=new Student(citizenNumber,name,birthYear,studentNumber,dept);
		
		return person;
	}
	
	//연구원
	public Person inputDataResearcher(Person person, String citizenNumber, String name, int birthYear) {
		int employeeNumber=-1;
		String dept = null;
		System.out.print("번호 : ");
		employeeNumber=sc.nextInt();
		System.out.print("부서 : ");
		dept=sc.next();
		person=new Researcher(citizenNumber,name,birthYear,employeeNumber,dept);
		
		return person;
	}
	
	//교수
	public Person inputDataProfessor(Person person, String citizenNumber, String name, int birthYear) {
		int employeeNumber=-1;
		String dept = null, position=null;
		System.out.print("번호 : ");
		employeeNumber=sc.nextInt();
		System.out.print("부서 : ");
		dept=sc.next();System.out.print("번호 : ");
		employeeNumber=sc.nextInt();
		System.out.print("부서 : ");
		dept=sc.next();
		System.out.print("직위 : ");
		position=sc.next();
		person=new Professor(citizenNumber,name,birthYear,employeeNumber,dept,position);
		
		return person;
	}
	
	//2번 
	public void printAll(Person[] plist) {
		int n = -1;
		Person[] personCopy = (Person[]) plist.clone();
		
		System.out.println("1. 주민번호순  2. birthYear순  3. 이름순  4. 이름 역순");
		try {
			n=sc.nextInt();
			sc.nextLine();
			
			switch(n) {
			case 1:
				personCopy = sortByCitizenNumber(personCopy);
				break;
			case 2:
				personCopy = sortByBirthYear(personCopy);
				break;
			case 3:
				personCopy = sortByName(personCopy);
				break;
			case 4:
				personCopy = sortByNameReverse(personCopy);
				break;
			default:
				System.out.println("없는 번호 입니다. 이전 화면으로 돌아갑니다.");
				break;
			}
			for (Person p : personCopy) {
				showMember(p);
			}
		}
		catch(InputMismatchException e) {
			System.out.println("정수를 입력하지 않아 이전 화면으로 돌아갑니다.");
		}
	}
	
	public void showMember(Person p) {
		if (p instanceof Professor)
			System.out.print("Professor : " + ((Professor)p).toString());
		else if (p instanceof Researcher)
			System.out.print("Researcher : " + ((Researcher)p).toString());
		else if (p instanceof Student)
				System.out.print("Student : " + ((Student)p).toString());
		else if (p instanceof Person) 
			System.out.print("Person : " + p.toString());
		
		System.out.println();
	}
	
	//comparable
	public Person[] sortByCitizenNumber(Person[] personCopy) { //return 해야 함
		Arrays.sort(personCopy);
		return personCopy;
	}
	
	//comparator
	public Person[] sortByBirthYear(Person[] personCopy) {
		BirthYearComparator birthYearCompare = new BirthYearComparator();
		Arrays.sort(personCopy,birthYearCompare);
		return personCopy;
	}
	
	public Person[] sortByName(Person[] personCopy) {
		NameComparator nameCompare = new NameComparator();
		Arrays.sort(personCopy, nameCompare);
		return personCopy;
	}
	
	public Person[] sortByNameReverse(Person[] personCopy) {
		ReverseNameComparator nameCompareRev = new ReverseNameComparator();
		Arrays.sort(personCopy, nameCompareRev);
		return personCopy;
	}
	
	//3번
	public void searchMember(Person[] persons) {
		int selection = -1;
		System.out.println("> 다음 중에 선택하세요.");
		System.out.println("1. 이름으로 검색  2. 나이로 검색  3. birthYear 범위로 검색");
		try {
			selection=sc.nextInt();
			switch(selection) {
			case 1:
				searchByName(persons);
				break;
			case 2:
				searchByAge(persons);
				break;
			case 3:
				searchByBirthYear(persons);
				break;
			default:
				System.out.println("없는 번호를 선택하였습니다. 이전 화면으로 돌아갑니다.");
				break;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("정수를 입력하지 않아 이전 화면으로 돌아갑니다.");
		}
	}
	
	public void searchByName(Person[] persons) {
		String name = null;
		System.out.print("이름 입력 : ");
		name=sc.next();
		for (Person p : persons) {
			if (name.equals(p.getName())) {
				showMember(p);
			}
		}
	}
	
	public void searchByAge(Person[] persons) {
		int ageMax = -1, ageMin = -1;
		System.out.print("나이 범위 입력 : ");
		ageMin=sc.nextInt();
		ageMax=sc.nextInt(); 
		sc.nextLine();
		
		for (Person p : persons) {
			if (ageMin<=(2022-p.getBirthYear()+1) && (2022-p.getBirthYear()+1)<=ageMax) {
				showMember(p);
			}
		}

	}
	
	public void searchByBirthYear(Person[] persons) {
		int birthYearMin = -1, birthYearMax = -1;
		System.out.print("태어난 연도 범위 입력 : ");
		birthYearMin=sc.nextInt();
		birthYearMax=sc.nextInt();
		sc.nextLine();
		
		for (Person p : persons) {
			if (birthYearMin<=p.getBirthYear() && p.getBirthYear()<=birthYearMax) {
				showMember(p);
			}
		}
	}
	
	//4번
	public void updateMember(Person[] persons) {
		String citizenNumber = null, name=null;
		int num = -1, birthYear = -1;
		
		System.out.print("> 정보를 변경할 사람의 주민번호 : ");
		citizenNumber=sc.next();
		
		for (Person p : persons) {
			if (citizenNumber.equals(p.getCitizenNumber())) {
				System.out.println("어떤 정보를 변경하시겠습니까?");
				System.out.println("1. 이름  2. birthYear");
				
				try {
					num=sc.nextInt();
					switch(num) {
					case 1:
						System.out.println("새로 변경할 이름을 입력하세요.");
						name = sc.next();
						p.setName(name);
						break;
					case 2:
						System.out.println("새로 변경할 birthYear를 입력하세요.");
						birthYear = sc.nextInt();
						sc.nextLine();
						p.setBirthYear(birthYear);
						break;
					default:
						System.out.println("없는 번호입니다. 이전 화면으로 돌아갑니다.");
						break;
					}
				} 
				catch(InputMismatchException e) {
					System.out.println("정수를 입력하지 않아 이전 화면으로 돌아갑니다.");
				}
				break; //for 문 바깥으로 주민번호는 primary key이니까 끝낸다.
			}
		}
	}

	//5번
	public Person[] deleteMember(Person[] persons) {
		Person[] personNoNull = null;
		String citizenNumber = null;
		boolean isFound=false;
		
		System.out.print("> 삭제할 사람의 주민번호 : ");
		citizenNumber=sc.next();
		
		for (int i=0; i<persons.length; i++) {
			if (citizenNumber.equals(persons[i].getCitizenNumber())) {
				System.out.println(persons[i].getCitizenNumber()+" "+persons[i].getName()+"을(를) 삭제하였습니다.");
				persons[i]=null;
				isFound=true;
				break;
			}
		}
		
		if (isFound) {
			personNoNull=Arrays.stream( persons ).filter( n -> n!=null ).toArray(Person[]::new);
	    	System.out.println( Arrays.toString( personNoNull ));
	        
		}
		else
			System.out.println("찾으시는 주민번호가 없습니다.");
		
		return personNoNull;
	}
	
	
	public static void main(String[] args) {
		UnivManagement manager = new UnivManagement();
		manager.runProgram();
	}

}
