package kunsan.bohwa1378.lecture04;

public class NestedLoop {

	public void run99() {
        for (int i=1; i<=9; i++, System.out.println()){
            for (int j=1; j<=9; j++,System.out.print("\t")){
                System.out.print(i+"*"+j+"="+i*j);
            }
        }
	}
	public static void main(String[] args) {
		NestedLoop test = new NestedLoop();
		test.run99();
	}

}
