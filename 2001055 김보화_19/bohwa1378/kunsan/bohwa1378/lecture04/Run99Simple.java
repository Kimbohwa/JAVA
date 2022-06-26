package kunsan.bohwa1378.lecture04;

import java.util.Scanner;

public class Run99Simple {
	public void run99(int start, int end, int from, int to) {
        for (int i=start; i<=end; i++, System.out.println()){
            for (int j=from; j<=to; j++, System.out.print("\t")){
                System.out.print(i+"*"+j+"="+i*j);
             }
         }
	}
	
	public void doDialog() {
		int start = 0, end = 0, from = 0, to = 0;
        Scanner scanner = new Scanner(System.in); 
        System.out.println("몇 단부터 몇 단까지 구구단을 출력할까요? (int, int)");
        start=scanner.nextInt();
        end=scanner.nextInt();
        System.out.println("어느 수부터 어느 수까지 곱할까요? (int, int)");
        from=scanner.nextInt();
        to=scanner.nextInt();
        
        run99(start, end, from, to);
	}
	
	public static void main(String[] args) {
		Run99Simple test = new Run99Simple();
		test.doDialog();
	}

}
