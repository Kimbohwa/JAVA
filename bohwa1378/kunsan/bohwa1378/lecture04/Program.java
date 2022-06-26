package kunsan.bohwa1378.lecture04;

import java.util.InputMismatchException;
import java.util.Scanner;
import kunsan.bohwa1378.lecture05.DevideByZeroHandling;
import ksnu.bohwa1378.lecture07.*; //패키지 내의 어떤 클래스를 부를 지까지 써야 함

public class Program {
	public void runFunctions() {
		Scanner scanner = new Scanner(System.in); 
		int number = 1;
		
		do{
			printFunctionTypes();
			try {
        	    number=scanner.nextInt();
            	selectFunction(number);
        	}
        	catch(InputMismatchException e) {
            	    System.out.println("정수가 아닙니다. 다시 입력하세요!");
            	    scanner.next(); 
            }
		} while(number!=0);
		scanner.close();
	}
    
	public void printFunctionTypes(){
        System.out.println();
        System.out.println("o 다음의 프로그램들 중에서 수행할 함수를 선택하세요.");
        System.out.println("1) 커피 메뉴를 넣으면 가격을 출력해 주는 프로그램");
        System.out.println("2) 입력된 수의 평균과 개수 구해서 화면에 프린트하기");
        System.out.println("3) 알파벳 A부터 Z까지 프린트하기");
        System.out.println("4) 정수 x와 y를 입력 받아, x부터 y까지 더하는 과정과 결과 보이기");
        System.out.println("5) 99단 단순 찍기");
        System.out.println("6) 99단 출력단수와 수의 범위 설정해서 프린트하기");
        System.out.println("7) 안전한 프로그램");
        System.out.println("8) 학생 성적 처리 프로그램(1차원 배열)");
        System.out.println("9) 클래스 기반 Book 관리 프로그램");
        System.out.println("0) 종료");
        System.out.print("선택 : ");
    }
	
    public void selectFunction(int number) {
        switch(number){
            case 1 : 
                CoffeePrice test1 = new CoffeePrice();
                test1.calcPrice();
                break;
            case 2 : 
                WhileSample test2 = new WhileSample();
                test2.getAverage();
                break;
            case 3 : 
                DoWhileSample test3 = new DoWhileSample();
                test3.printAtoZ();
                break;
            case 4 : 
                ForSum test4 = new ForSum();
                test4.printSum();
                break;
            case 5 :
                NestedLoop test5 = new NestedLoop();
                test5.run99();
                break;
            case 6 : 
                Run99Simple test6 = new Run99Simple();
                test6.doDialog();
                break;
            case 7 : 
        	    DevideByZeroHandling test7 = new DevideByZeroHandling();
                test7.testSafeException();
                break;
            case 8 :
            	StudentsScores test8 = new StudentsScores();
            	test8.runFunctions();
            	break;
            case 9:
            	BookManager test9 = new BookManager();
            	test9.runProgram();
            	break;
            case 0:
        	    break;
            default:
                System.out.println("없는 번호입니다. 다시 선택하세요.");
                break;
        }
    }
    
	public static void main(String[] args) {
		Program programTest = new Program();
		programTest.runFunctions();
	}
}













