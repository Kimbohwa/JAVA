package kunsan.bohwa1378.lecture04;

import java.util.Scanner;
import kunsan.bohwa1378.lecture05.*;
import java.util.InputMismatchException;

public class Program1{
    public void printFunctionType(){
        System.out.println();
        System.out.println("o 다음의 프로그램들 중에서 수행할 함수를 선택하세요.");
        System.out.println("1) 커피 메뉴를 넣으면 가격을 출력해 주는 프로그램");
        System.out.println("2) 입력된 수의 평균과 개수 구해서 화면에 프린트하기");
        System.out.println("3) 알파벳 A부터 Z까지 프린트하기");
        System.out.println("4) 정수 x와 y를 입력 받아, x부터 y까지 더하는 과정과 결과 보이기");
        System.out.println("5) 99단 단순 찍기");
        System.out.println("6) 99단 출력단수와 수의 범위 설정해서 프린트하기");
        System.out.println("7) 안전한 프로그램");
        System.out.println("0) 종료");
        System.out.print("선택 : ");
    }
    
    public void selectFunction(){
        printFunctionType();
        Scanner scanner = new Scanner(System.in); 
        int number = 1;
        while(number!=0) {
        	try {
        	    number=scanner.nextInt();
            	    selectFunctionCase(number);
        	}
        	catch(InputMismatchException e) {
            	    System.out.println("정수가 아닙니다. 다시 입력하세요!");
            	    scanner.next();
              }
        }
        scanner.close();
    }
    
    public void selectFunctionCase(int number) {
        switch(number){
            case 1 : 
                selectFunctionType1();
                break;
            case 2 : 
                selectFunctionType2();
                break;
            case 3 : 
                selectFunctionType3();
                break;
            case 4 : 
                selectFunctionType4();
                break;
            case 5 :
                selectFunctionType5();
                break;
            case 6 : 
                selectFunctionType6();
                break;
            case 7 : 
        	    Function7 test7 = new Function7();
                test7.selectFunctionType7();
                break;
            case 0:
        	    break;
            default:
                System.out.println("없는 번호입니다. 다시 선택하세요.");
                break;
        }
        if (number!=0) printFunctionType();
    }

    public void selectFunctionType1(){
        Scanner scanner = new Scanner(System.in); 
        System.out.print("무슨 커피 드릴까요? ");
        String order = scanner.next();
        int price = 0;
        switch(order){
            case "에스프레소":
            case "카푸치노":
            case "카페라떼":
                price = 3500;
                break;
             case "아메리카노":
                price = 2000;
                break;
               default:
                 System.out.println("메뉴에 없습니다!");
        }
        if (price!=0)
            System.out.println(order+"는 "+price+"원입니다.");
    }
    public void selectFunctionType2() {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("숫자를 입력하세요. 종료하려면 -1을 입력하세요.");
        int n = 0;
        double sum1 = 0;
        int input = 0;
        while(true) {
            try{
                input=scanner.nextInt();
                if (input==-1) break;
                sum1+=input;
                n++;
            }
            catch(InputMismatchException e) {
                System.out.println("정수가 아닙니다. 다시 입력하세요!");
                scanner.next();
            }
        }
        System.out.println("입력된 수의 개수는 "+n+"개이며 평균은 "+sum1/n+"입니다.");
    }
  
    public void selectFunctionType3(){
        char a = 'a';
        do {
            System.out.print(a);
            a=(char)(a+1);
        } while(a<='z');
        System.out.println();
    }
    public void selectFunctionType4(){
        Scanner scanner = new Scanner(System.in); 
        System.out.print("두 수 x, y(x<y)를 입력하세요.");
        int number1=scanner.nextInt();
        int number2=scanner.nextInt();
        int sum2=0;
        for (int i=number1; i<=number2; i++){
            sum2+=i;
            if (i<number2) 
                System.out.print(i+"+");
             else 
                System.out.println(i+"="+sum2);
        }
    }

    public void selectFunctionType5(){
        for (int i=1; i<=9; i++, System.out.println()){
            for (int j=1; j<=9; j++,System.out.print("\t")){
                System.out.print(i+"*"+j+"="+i*j);
            }
        }
    }

    public void selectFunctionType6(){
        Scanner scanner = new Scanner(System.in); 
        System.out.println("몇 단부터 몇 단까지 구구단을 출력할까요? (int, int)");
        int start=scanner.nextInt();
        int end=scanner.nextInt();
        System.out.println("어느 수부터 어느 수까지 곱할까요? (int, int)");
        int from=scanner.nextInt();
        int to=scanner.nextInt();
        for (int i=start; i<=end; i++, System.out.println()){
            for (int j=from; j<=to; j++, System.out.print("\t")){
                System.out.print(i+"*"+j+"="+i*j);
             }
         }
    }

    public static void main(String args[]){
        Program1 test = new Program1();
        test.selectFunction();
    }
}