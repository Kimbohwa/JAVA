package ksnu.bohwa1378.lecture10;

import java.util.*;
import ksnu.bohwa1378.lecture08.*;

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

public class UnivManagementArrayList{
	Scanner sc = null;
	
	public UnivManagementArrayList() {
		sc = new Scanner(System.in);
	}
	
	public void runProgram() {
		int num = -1;
		ArrayList<Person> persons = new ArrayList<Person>(5);
		
		while(num != 0) {
			printProgram();
			try{
                num=sc.nextInt();
                selectProgram(num, persons);
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
	
	public void selectProgram(int num, ArrayList<Person> persons) {
		try {
	    	switch(num){
	            case 1:
	                createMember(persons);
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
	            	deleteMember(persons);
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
	}
	
	//1번
	public void createMember(ArrayList<Person> persons) {
		int n=-1;
		
		for(;n<0;) {
			System.out.println(">몇 명을 입력하시겠습니까?");
			try {
	        	n=sc.nextInt();
	        	sc.nextLine();
	        	selectType(persons,n);
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("정수 값을 입력하세요.");
	        	sc.nextLine();
	        	continue;
	        }
			if (n<0) System.out.println("다시 입력하세요");
		}
	}
	
	public void selectType(ArrayList<Person> persons, int n) {
		int selection=-1;
		for (int i=0; i<n; i++) {
    		System.out.println("> 구성원의 형태를 선택하세요.");
    		System.out.println("1. 일반인  2. 학생  3. 연구원  4. 교수");
    		try{
    			selection=sc.nextInt();
    			if (selection<1 || selection>4) {
    				System.out.println("없는 번호입니다. 다시 선택하세요.");
    				continue;
    			}
    			System.out.println("> 정보를 입력하세요");
    			inputData(persons, selection); 
    		}
    		catch(InputMismatchException e) {
    			System.out.println("정수를 입력하세요");
    			i--;
    			sc.nextLine();
    		}
    	}
	}
	
	public void inputData(ArrayList<Person> persons, int selection) { 
		String citizenNumber=null, name=null;
		int birthYear=-1;
		Person person=null;
		
		System.out.print("주민번호 : ");
		citizenNumber=sc.next();
		System.out.print("이름 : ");
		name=sc.next();
		System.out.print("태어난 연도 : ");
		birthYear=sc.nextInt();
		
		switch(selection) {
		case 1: //일반인
			person=new Person(citizenNumber,name,birthYear);
			persons.add(person);
			break;
		case 2: //학생
			person = inputDataStud(person,citizenNumber, name, birthYear);
			persons.add(person);
			break; 
		case 3: //연구원
			person = inputDataResearcher(person,citizenNumber, name, birthYear);
			persons.add(person);
			break;
		case 4: //교수
			person = inputDataProfessor(person,citizenNumber, name, birthYear);
			persons.add(person);
			break;
		}
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
		dept=sc.next();
		System.out.print("직위 : ");
		position=sc.next();
		person=new Professor(citizenNumber,name,birthYear,employeeNumber,dept,position);
		
		return person;
	}
	
	//2번 
	public void printAll(ArrayList<Person> person) {
		int n = -1;
		@SuppressWarnings("unchecked")
		ArrayList<Person> personCopy = (ArrayList<Person>) person.clone();
		boolean state = false;
		
		System.out.println("1. 주민번호순  2. birthYear순  3. 이름순  4. 이름 역순");
		try {
			n=sc.nextInt();
			sc.nextLine();
			
			switch(n) {
			case 1:
				personCopy = sortByCitizenNumber(personCopy);
				state=true;
				break;
			case 2:
				personCopy = sortByBirthYear(personCopy);
				state=true;
				break;
			case 3:
				personCopy = sortByName(personCopy);
				state=true;
				break;
			case 4:
				personCopy = sortByNameReverse(personCopy);
				state=true;
				break;
			default:
				System.out.println("없는 번호 입니다.");
				break;
			}
			if (state)
				for (Person p : personCopy) {
					showMember(p);
				}
		}
		catch(InputMismatchException e) {
			System.out.println("정수를 입력하지 않았습니다.");
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
	public ArrayList<Person> sortByCitizenNumber(ArrayList<Person> personCopy) { //return 해야 함
		Collections.sort(personCopy);
		return personCopy;
	}
	
	//comparator
	public ArrayList<Person> sortByBirthYear(ArrayList<Person> personCopy) {
		BirthYearComparator birthYearCompare = new BirthYearComparator();
		Collections.sort(personCopy,birthYearCompare);
		return personCopy;
	}
	
	public ArrayList<Person> sortByName(ArrayList<Person> personCopy) {
		NameComparator nameCompare = new NameComparator();
		Collections.sort(personCopy, nameCompare);
		return personCopy;
	}
	
	public ArrayList<Person> sortByNameReverse(ArrayList<Person> personCopy) {
		ReverseNameComparator nameCompareRev = new ReverseNameComparator();
		Collections.sort(personCopy, nameCompareRev);
		return personCopy;
	}
	
	//3번
	public void searchMember(ArrayList<Person> persons) {
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
			System.out.println("정수를 입력하지 않았습니다.");
		}
	}
	
	public void searchByName(ArrayList<Person> persons) {
		String name = null;
		System.out.print("이름 입력 : ");
		name=sc.next();
		for (Person p : persons) {
			if (name.equals(p.getName())) {
				showMember(p);
			}
		}
	}
	
	public void searchByAge(ArrayList<Person> persons) {
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
	
	public void searchByBirthYear(ArrayList<Person> persons) {
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
	public void updateMember(ArrayList<Person> persons) {
		String citizenNumber = null, name=null;
		int num = -1, birthYear = -1;
		boolean isFound=false;
		
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
						isFound=true;
						break;
					case 2:
						System.out.println("새로 변경할 birthYear를 입력하세요.");
						birthYear = sc.nextInt();
						sc.nextLine();
						p.setBirthYear(birthYear);
						isFound=true;
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
		if (isFound) 
			for (Person p:persons) {
				showMember(p);
			}
	}

	//5번
	public void deleteMember(ArrayList<Person> persons) {
		String citizenNumber = null;
		boolean isFound=false;
		
		System.out.print("> 삭제할 사람의 주민번호 : ");
		citizenNumber=sc.next();
		
		for (int i=0; i<persons.size(); i++) {
			if (citizenNumber.equals((persons.get(i)).getCitizenNumber())) {
				System.out.println((persons.get(i)).getCitizenNumber()+" "+(persons.get(i)).getName()+"을(를) 삭제하였습니다.");
				persons.remove(i);
				isFound=true;
				break;
			}
		}
		
		if (!isFound) 
			System.out.println("찾으시는 주민번호가 없습니다.");
		
		else 
			for (Person p:persons) {
				showMember(p);
			}
	        
	}
	
	
	public static void main(String[] args) {
		UnivManagementArrayList manager = new UnivManagementArrayList();
		manager.runProgram();
	}

}
