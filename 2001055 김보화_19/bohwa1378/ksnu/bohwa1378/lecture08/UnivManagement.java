package ksnu.bohwa1378.lecture08;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


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
                System.out.println("������ �Է��ϼ���!");
                sc.nextLine();
            }
		}
        //sc.close(); //�޼ҵ� ����, scanner close
	}
	
	public void printProgram() {
		System.out.println("1. ���� ������ ������ �Է�");
		System.out.println("2. ���� ������ ������ ��ü ���");
		System.out.println("3. ���� ������ ������ ���� �˻�");
		System.out.println("4. ���� ������ ������ ����");
		System.out.println("5. ���� ������ ������ ����");
		System.out.println("0. ����");
        System.out.print("���� : ");
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
	            	System.out.println("���� ��ȣ�Դϴ�. �ٽ� �����ϼ���.");
	                break;
	
	        }
    	} catch(NullPointerException e) {
    		System.out.println("�л� �����ͺ��� �����ϼ���!");
    		System.out.println();
    	}
        return persons;
	}
	
	//1��
	public Person[] createMember() {
		Person[] persons = null;
		int n=-1;
		
		System.out.println(">�� ���� �Է��Ͻðڽ��ϱ�?");
        while(true) {
	        try {
	        	n=sc.nextInt();
	        	sc.nextLine();
		        persons = new Person[n]; 
		        
				return persons;
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("���� ���� �Է��ϼ���.");
	        	sc.nextLine();
	        }
        }
	}
	
	public void selectType(Person[] persons) {
		int selection=-1;
		for (int i=0; i<persons.length; i++) {
			System.out.println("> �������� ���¸� �����ϼ���.");
			System.out.println("1. �Ϲ���  2. �л�  3. ������  4. ����");
			try{
				selection=sc.nextInt();
				if (selection<1 || selection>4) {
					System.out.println("���� ��ȣ�Դϴ�. �ٽ� �����ϼ���.");
					continue;
				}
				System.out.println("> ������ �Է��ϼ���");
				persons[i]=inputData(persons[i], selection); //��ȯ�� �޾ƾ� ��
			}
			catch(InputMismatchException e) {
				System.out.println("������ �Է��ϼ���");
			}
		}
	}
	
	public Person inputData(Person person, int selection) { //�����ϱ� //��ȯ�� �ؾ� ��
		String citizenNumber=null, name=null;
		int birthYear=-1;
		
		System.out.print("�ֹι�ȣ : ");
		citizenNumber=sc.next();
		System.out.print("�̸� : ");
		name=sc.next();
		System.out.print("�¾ ���� : ");
		birthYear=sc.nextInt();
		
		switch(selection) {
		case 1: //�Ϲ���
			person=new Person(citizenNumber,name,birthYear);
			break;
		case 2: //�л�
			person = inputDataStud(person,citizenNumber, name, birthYear);
			break; 
		case 3: //������
			person = inputDataResearcher(person,citizenNumber, name, birthYear);
			break;
		case 4: //����
			person = inputDataProfessor(person,citizenNumber, name, birthYear);
			break;
		}
		return person;
	}
	
	//�л�
	public Person inputDataStud(Person person, String citizenNumber, String name, int birthYear) {
		int studentNumber=-1;
		String dept = null;
		System.out.print("�й� : ");
		studentNumber=sc.nextInt();
		System.out.print("�а� : ");
		dept=sc.next();
		person=new Student(citizenNumber,name,birthYear,studentNumber,dept);
		
		return person;
	}
	
	//������
	public Person inputDataResearcher(Person person, String citizenNumber, String name, int birthYear) {
		int employeeNumber=-1;
		String dept = null;
		System.out.print("��ȣ : ");
		employeeNumber=sc.nextInt();
		System.out.print("�μ� : ");
		dept=sc.next();
		person=new Researcher(citizenNumber,name,birthYear,employeeNumber,dept);
		
		return person;
	}
	
	//����
	public Person inputDataProfessor(Person person, String citizenNumber, String name, int birthYear) {
		int employeeNumber=-1;
		String dept = null, position=null;
		System.out.print("��ȣ : ");
		employeeNumber=sc.nextInt();
		System.out.print("�μ� : ");
		dept=sc.next();System.out.print("��ȣ : ");
		employeeNumber=sc.nextInt();
		System.out.print("�μ� : ");
		dept=sc.next();
		System.out.print("���� : ");
		position=sc.next();
		person=new Professor(citizenNumber,name,birthYear,employeeNumber,dept,position);
		
		return person;
	}
	
	//2�� 
	public void printAll(Person[] plist) {
		int n = -1;
		Person[] personCopy = (Person[]) plist.clone();
		
		System.out.println("1. �ֹι�ȣ��  2. birthYear��  3. �̸���  4. �̸� ����");
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
				System.out.println("���� ��ȣ �Դϴ�. ���� ȭ������ ���ư��ϴ�.");
				break;
			}
			for (Person p : personCopy) {
				showMember(p);
			}
		}
		catch(InputMismatchException e) {
			System.out.println("������ �Է����� �ʾ� ���� ȭ������ ���ư��ϴ�.");
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
	public Person[] sortByCitizenNumber(Person[] personCopy) { //return �ؾ� ��
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
	
	//3��
	public void searchMember(Person[] persons) {
		int selection = -1;
		System.out.println("> ���� �߿� �����ϼ���.");
		System.out.println("1. �̸����� �˻�  2. ���̷� �˻�  3. birthYear ������ �˻�");
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
				System.out.println("���� ��ȣ�� �����Ͽ����ϴ�. ���� ȭ������ ���ư��ϴ�.");
				break;
			}
		}
		catch(InputMismatchException e) {
			System.out.println("������ �Է����� �ʾ� ���� ȭ������ ���ư��ϴ�.");
		}
	}
	
	public void searchByName(Person[] persons) {
		String name = null;
		System.out.print("�̸� �Է� : ");
		name=sc.next();
		for (Person p : persons) {
			if (name.equals(p.getName())) {
				showMember(p);
			}
		}
	}
	
	public void searchByAge(Person[] persons) {
		int ageMax = -1, ageMin = -1;
		System.out.print("���� ���� �Է� : ");
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
		System.out.print("�¾ ���� ���� �Է� : ");
		birthYearMin=sc.nextInt();
		birthYearMax=sc.nextInt();
		sc.nextLine();
		
		for (Person p : persons) {
			if (birthYearMin<=p.getBirthYear() && p.getBirthYear()<=birthYearMax) {
				showMember(p);
			}
		}
	}
	
	//4��
	public void updateMember(Person[] persons) {
		String citizenNumber = null, name=null;
		int num = -1, birthYear = -1;
		
		System.out.print("> ������ ������ ����� �ֹι�ȣ : ");
		citizenNumber=sc.next();
		
		for (Person p : persons) {
			if (citizenNumber.equals(p.getCitizenNumber())) {
				System.out.println("� ������ �����Ͻðڽ��ϱ�?");
				System.out.println("1. �̸�  2. birthYear");
				
				try {
					num=sc.nextInt();
					switch(num) {
					case 1:
						System.out.println("���� ������ �̸��� �Է��ϼ���.");
						name = sc.next();
						p.setName(name);
						break;
					case 2:
						System.out.println("���� ������ birthYear�� �Է��ϼ���.");
						birthYear = sc.nextInt();
						sc.nextLine();
						p.setBirthYear(birthYear);
						break;
					default:
						System.out.println("���� ��ȣ�Դϴ�. ���� ȭ������ ���ư��ϴ�.");
						break;
					}
				} 
				catch(InputMismatchException e) {
					System.out.println("������ �Է����� �ʾ� ���� ȭ������ ���ư��ϴ�.");
				}
				break; //for �� �ٱ����� �ֹι�ȣ�� primary key�̴ϱ� ������.
			}
		}
	}

	//5��
	public Person[] deleteMember(Person[] persons) {
		Person[] personNoNull = null;
		String citizenNumber = null;
		boolean isFound=false;
		
		System.out.print("> ������ ����� �ֹι�ȣ : ");
		citizenNumber=sc.next();
		
		for (int i=0; i<persons.length; i++) {
			if (citizenNumber.equals(persons[i].getCitizenNumber())) {
				System.out.println(persons[i].getCitizenNumber()+" "+persons[i].getName()+"��(��) �����Ͽ����ϴ�.");
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
			System.out.println("ã���ô� �ֹι�ȣ�� �����ϴ�.");
		
		return personNoNull;
	}
	
	
	public static void main(String[] args) {
		UnivManagement manager = new UnivManagement();
		manager.runProgram();
	}

}
