package kunsan.bohwa1378.lecture04;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WhileSample {

	public void getAverage() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("숫자를 입력하세요. 종료하려면 -1을 입력하세요.");
        int n = 0;
        double sum = 0;
        int input = 0;
        while(true) {
            try{
                input=scanner.nextInt();
                if (input==-1) break;
                sum+=input;
                n++;
            }
            catch(InputMismatchException e) {
                System.out.println("정수가 아닙니다. 다시 입력하세요!");
                scanner.next();
            }
        }
        System.out.println("입력된 수의 개수는 "+n+"개이며 평균은 "+sum/n+"입니다.");
	}
	
	public static void main(String[] args) {
		WhileSample test = new WhileSample();
		test.getAverage();
	}

}
