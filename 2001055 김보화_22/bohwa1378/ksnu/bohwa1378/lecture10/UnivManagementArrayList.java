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
	            	System.out.println("���� ��ȣ�Դϴ�. �ٽ� �����ϼ���.");
	                break;
	
	        }
    	} catch(NullPointerException e) {
    		System.out.println("�л� �����ͺ��� �����ϼ���!");
    		System.out.println();
    	}
	}
	
	//1��
	public void createMember(ArrayList<Person> persons) {
		int n=-1;
		
		for(;n<0;) {
			System.out.println(">�� ���� �Է��Ͻðڽ��ϱ�?");
			try {
	        	n=sc.nextInt();
	        	sc.nextLine();
	        	selectType(persons,n);
	        }
	        catch(InputMismatchException e) {
	        	System.out.println("���� ���� �Է��ϼ���.");
	        	sc.nextLine();
	        	continue;
	        }
			if (n<0) System.out.println("�ٽ� �Է��ϼ���");
		}
	}
	
	public void selectType(ArrayList<Person> persons, int n) {
		int selection=-1;
		for (int i=0; i<n; i++) {
    		System.out.println("> �������� ���¸� �����ϼ���.");
    		System.out.println("1. �Ϲ���  2. �л�  3. ������  4. ����");
    		try{
    			selection=sc.nextInt();
    			if (selection<1 || selection>4) {
    				System.out.println("���� ��ȣ�Դϴ�. �ٽ� �����ϼ���.");
    				continue;
    			}
    			System.out.println("> ������ �Է��ϼ���");
    			inputData(persons, selection); 
    		}
    		catch(InputMismatchException e) {
    			System.out.println("������ �Է��ϼ���");
    			i--;
    			sc.nextLine();
    		}
    	}
	}
	
	public void inputData(ArrayList<Person> persons, int selection) { 
		String citizenNumber=null, name=null;
		int birthYear=-1;
		Person person=null;
		
		System.out.print("�ֹι�ȣ : ");
		citizenNumber=sc.next();
		System.out.print("�̸� : ");
		name=sc.next();
		System.out.print("�¾ ���� : ");
		birthYear=sc.nextInt();
		
		switch(selection) {
		case 1: //�Ϲ���
			person=new Person(citizenNumber,name,birthYear);
			persons.add(person);
			break;
		case 2: //�л�
			person = inputDataStud(person,citizenNumber, name, birthYear);
			persons.add(person);
			break; 
		case 3: //������
			person = inputDataResearcher(person,citizenNumber, name, birthYear);
			persons.add(person);
			break;
		case 4: //����
			person = inputDataProfessor(person,citizenNumber, name, birthYear);
			persons.add(person);
			break;
		}
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
		dept=sc.next();
		System.out.print("���� : ");
		position=sc.next();
		person=new Professor(citizenNumber,name,birthYear,employeeNumber,dept,position);
		
		return person;
	}
	
	//2�� 
	public void printAll(ArrayList<Person> person) {
		int n = -1;
		@SuppressWarnings("unchecked")
		ArrayList<Person> personCopy = (ArrayList<Person>) person.clone();
		boolean state = false;
		
		System.out.println("1. �ֹι�ȣ��  2. birthYear��  3. �̸���  4. �̸� ����");
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
				System.out.println("���� ��ȣ �Դϴ�.");
				break;
			}
			if (state)
				for (Person p : personCopy) {
					showMember(p);
				}
		}
		catch(InputMismatchException e) {
			System.out.println("������ �Է����� �ʾҽ��ϴ�.");
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
	public ArrayList<Person> sortByCitizenNumber(ArrayList<Person> personCopy) { //return �ؾ� ��
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
	
	//3��
	public void searchMember(ArrayList<Person> persons) {
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
			System.out.println("������ �Է����� �ʾҽ��ϴ�.");
		}
	}
	
	public void searchByName(ArrayList<Person> persons) {
		String name = null;
		System.out.print("�̸� �Է� : ");
		name=sc.next();
		for (Person p : persons) {
			if (name.equals(p.getName())) {
				showMember(p);
			}
		}
	}
	
	public void searchByAge(ArrayList<Person> persons) {
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
	
	public void searchByBirthYear(ArrayList<Person> persons) {
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
	public void updateMember(ArrayList<Person> persons) {
		String citizenNumber = null, name=null;
		int num = -1, birthYear = -1;
		boolean isFound=false;
		
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
						isFound=true;
						break;
					case 2:
						System.out.println("���� ������ birthYear�� �Է��ϼ���.");
						birthYear = sc.nextInt();
						sc.nextLine();
						p.setBirthYear(birthYear);
						isFound=true;
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
		if (isFound) 
			for (Person p:persons) {
				showMember(p);
			}
	}

	//5��
	public void deleteMember(ArrayList<Person> persons) {
		String citizenNumber = null;
		boolean isFound=false;
		
		System.out.print("> ������ ����� �ֹι�ȣ : ");
		citizenNumber=sc.next();
		
		for (int i=0; i<persons.size(); i++) {
			if (citizenNumber.equals((persons.get(i)).getCitizenNumber())) {
				System.out.println((persons.get(i)).getCitizenNumber()+" "+(persons.get(i)).getName()+"��(��) �����Ͽ����ϴ�.");
				persons.remove(i);
				isFound=true;
				break;
			}
		}
		
		if (!isFound) 
			System.out.println("ã���ô� �ֹι�ȣ�� �����ϴ�.");
		
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
