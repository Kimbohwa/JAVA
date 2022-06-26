package kunsan.bohwa1378.lecture04;

import java.util.Scanner;

public class ForSum {

	public void printSum() {
		Scanner scanner = new Scanner(System.in); 
        System.out.print("두 수 x, y(x<y)를 입력하세요.");
        int number1=scanner.nextInt();
        int number2=scanner.nextInt();
        int sum=0;
        for (int i=number1; i<=number2; i++){
            sum+=i;
            if (i<number2) 
                System.out.print(i+"+");
             else 
                System.out.println(i+"="+sum);
        }
	}
	public static void main(String[] args) {
		ForSum test = new ForSum();
		test.printSum();
	}

}
