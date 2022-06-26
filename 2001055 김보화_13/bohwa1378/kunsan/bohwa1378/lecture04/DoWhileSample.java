package kunsan.bohwa1378.lecture04;

public class DoWhileSample {

	public void printAtoZ() {
		char a = 'a';
        do {
            System.out.print(a);
            a=(char)(a+1);
        } while(a<='z');
        System.out.println();
	}
	
	public static void main(String[] args) {
		DoWhileSample test = new DoWhileSample();
		test.printAtoZ();
	}

}
