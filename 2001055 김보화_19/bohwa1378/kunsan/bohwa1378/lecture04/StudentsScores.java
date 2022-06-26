package kunsan.bohwa1378.lecture04;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentsScores {
	
	public void runFunctions() {
		Scanner scanner = new Scanner(System.in); 
		int num = 1;
		int[] scores = null; //학생 점수 배열 저장할 변수
		
		do{
			printTypes(); //프로그램 목록 출력
			try {
        	    num=scanner.nextInt(); //번호 선택
            	scores = selectFunction(num, scores); //1. 선택한 번호와 배열을 파라미터로 넘겨줌
        	}
        	catch(InputMismatchException e) { //정수 선택 X -> 예외 처리
            	    System.out.println("정수가 아닙니다. 다시 입력하세요!");
            	    scanner.next();
            }
		} while(num!=0);
		//scanner.close();
	}
	
	public void printTypes() {
		System.out.println();
		System.out.println("<학생 성적 처리 프로그램>");
		System.out.println("1) 학생 성적 입력");		
		System.out.println("2) 학생 전체 성적 리스트 보기");
		System.out.println("3) 학생의 번호로 성적 보기");
		System.out.println("4) 성적 최고 점수, 최저 점수 보기");
		System.out.println("5) 성적 평균 점수, 중위값 점수 보기");
		System.out.println("6) 성적 정렬해서 보기");
		System.out.println("0) 메인 메뉴로 돌아감(종료)");
		System.out.print("입력 : ");
	}

	public int[] selectFunction(int number, int[] score) { //2. 파라미터로 선택 숫자와 배열을 넘겨 받음
		int[] students = null;
		int[] sortedScores = null; //정렬된 배열 저장할 변수
		switch(number) {
		case 1 : 
			score = makeArray(); //점수 할당할 학생 수만큼 배열 생성
			students = enterScores(score); //위에서 생성된 배열을 enterScores 함수에 넘겨 각 학생의 성적 입력 후 반환받기
            break;
        case 2 : 
        	try {
        		students = showAllScores(score);
        	} 
        	catch(NullPointerException e) { //Null exception 처리, 학생들의 점수를 입력하지 않은 상태에서 점수를 보려고 할 때
        		System.out.println("학생의 성적을 먼저 입력해 주세요!");
        	}
            break;
        case 3 : 
        	try {
        		students = showSpecificScores(score);
        	} 
        	catch(NullPointerException e) {
        		System.out.println("학생의 성적을 먼저 입력해 주세요!");
        	}
            break;
        case 4 : 
        	try {
        		students = showHighestLowestScores(score);
        	}
        	catch(NullPointerException e) {
        		System.out.println("학생의 성적을 먼저 입력해 주세요!");
        	}
            break;
        case 5 :
        	try {
        		students = showAverageMedian(score);
        	}
        	catch(NullPointerException e) {
        		System.out.println("학생의 성적을 먼저 입력해 주세요!");
        	}
            break;
        case 6 : 
        	students = score;
        	try {
        		sortedScores = showSortedScores(score);
        		printScores(sortedScores); //정렬된 배열을 파라미터로 전달하여 출력
        	}
        	catch(NullPointerException e) {
        		System.out.println("학생의 성적을 먼저 입력해 주세요!");
        	}
        	break;
        case 0:
    	    break;
        default:
            System.out.println("없는 번호입니다. 다시 선택하세요.");
            break;
		}
		return students; //3. 학생들의 성적이 저장된 배열 반환
	}
	
	public int[] makeArray() { //학생 수 입력, 입력 수만큼 array 생성
		Scanner scanner = new Scanner(System.in);
		System.out.print("처리할 학생의 수를 입력하세요(최대 100점) : ");
		int number = scanner.nextInt();
		int temp[] = new int[number]; 
		
		for (int i=0; i<temp.length; i++) {
			temp[i]=i;
		}
		return temp; //배열 반환
	}
	
	public int[] enterScores(int[] scores) {
		Scanner scanner = new Scanner(System.in);
		
		for (int i=0; i<scores.length; i++) {
			System.out.print(i+1+"번 학생 성적 : ");
			scores[i] = scanner.nextInt(); //성적 입력
			if (scores[i]>100) {
				System.out.println("100점을 초과한 점수는 입력할 수 없습니다. 다시 입력하세요!"); //100점 초과 점수 입력 시 다시 입력
				i--;
			}
		}
		return scores;
	}
	
	public int[] showAllScores(int[] scores) {
		System.out.println("- 전체 학생성적 리스트 보기");
		for (int i=0; i<scores.length; i++)
			System.out.println(i+1+" : "+scores[i]);
		return scores;
	}
	
	public int[] showSpecificScores(int[] scores) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 학생의 번호를 입력하세요 : ");
		int number = scanner.nextInt();
		while (number<0 || number>scores.length) {
			System.out.println("없는 번호입니다. 검색할 학생의 번호를 다시 입력하세요 : "); //없는 번호 검색 시 다시 입력
			number = scanner.nextInt();
		}
		System.out.println(number+"번 학생의 성적은 "+scores[number-1]+"입니다.");
		return scores;
	}
	
	public int[] showHighestLowestScores(int[] scores) {
		int max = scores[0], min = scores[0];
		for (int i=0; i<scores.length; i++) {
			if (scores[i]>max) max=scores[i];
			if (scores[i]<min) min=scores[i];
		}
		System.out.println("최고 점수 : "+max);
		System.out.println("최저 점수 : "+min);
		return scores;
	}
	
	public int[] showAverageMedian(int[] scores) {
		double average=0,median=0; 
		int sum=0;
		
		for (int i = 0; i < scores.length; i++) 
			sum+=scores[i];
		average=(sum*1.0)/(scores.length*1.0); //double형 주의!!!!!!
		
		int[] sortedScoresM = showSortedScores(scores); //점수 정렬하는 함수 호출
		if (sortedScoresM.length % 2 == 1) //배열의 길이가 홀수일 때
			median=sortedScoresM[sortedScoresM.length/2];
		else //배열 길이가 짝수일 때
			median=(sortedScoresM[sortedScoresM.length/2-1]+sortedScoresM[sortedScoresM.length/2])*0.1/0.2; //double 형 주의!!!!!!!!!!
		
		System.out.println("평균 점수 : "+average);
		System.out.println("중위값 점수 : "+median);
		
		return scores;
	}
	
	public int[] showSortedScores(int[] scores) {
		int[] sortedGrades = scores.clone(); //학생들의 점수가 저장된 배열을 deep copy
		int temp = 0;
		for (int i=0; i<sortedGrades.length; i++){ //정렬
			for (int j=0; j<sortedGrades.length-i-1; j++) {
				if (sortedGrades[j]>sortedGrades[j+1]) {
					temp=sortedGrades[j];
					sortedGrades[j]=sortedGrades[j+1];
					sortedGrades[j+1]=temp;
				}
			}
		}
		return sortedGrades; //정렬된 배열 반환
	}
	
	public void printScores(int[] array) {
		for (int i=0;i<array.length;i++)
			System.out.println((i+1)+" : "+array[i]);
	}
	
	public static void main(String[] args) {
		StudentsScores test = new StudentsScores();
		test.runFunctions();
	}

}