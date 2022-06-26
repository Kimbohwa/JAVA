package ksnu.bohwa1378.lecture07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManager {

	public void runProgram() {
		Scanner scanner = new Scanner(System.in);
		
		int number = -1;
		Book[] books=null;
		
		do {
			printProgram();
			try{
				number=scanner.nextInt();
				books = selectProgram(number, books);
			}
			catch(InputMismatchException e) {
				System.out.println("정수가 아닙니다. 다시 입력하세요!");
				scanner.nextLine();
			}
			
		} while(number != 0);
	}
	
	public void printProgram() {
		System.out.println();
		System.out.println("<클래스 기반 Book 관리 프로그램>");
		System.out.println("1) Book 입력");
		System.out.println("2) Book 전체 리스트 보기");
		System.out.println("3) Book 번호로 보기");
		System.out.println("4) 최고 가격, 최저 가격 보기");
		System.out.println("5) 평균 가격, 중위 가격 보기");
		System.out.println("6) 제목 순 정렬해서 보기");
		System.out.println("7) 제목으로 검색");
		System.out.println("0) 메인 메뉴로 돌아감(종료)");
		System.out.print("입력 : ");
	}
	
	public Book[] selectProgram(int num, Book[] books) {
		switch(num){
		case 1:
			books = makeArray();
			enterBookName(books);
			break;
		case 2:
			showList(books);
			break;
		case 3:
			showSpecificBook(books);
			break;
		case 4 : 
			showHighestLowestPrice(books);
			break;
		case 5 : 
			showAverageMedian(books);
			break;
		case 6 : 
			Book[] targetBooks = createTargetBooks(books);
			sortByTitle(targetBooks);
			break;
		case 7:
			showSpecificTitle(books);
			break;
		case 0:
			break;
		default:
			System.out.println("없는 번호입니다. 다시 입력하세요!");
			break;	
		}
		return books;
	}
	
	public Book[] makeArray() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("처리할 Book의 수를 입력하세요 : ");
		while(true) {
			try{
				int num = scanner.nextInt();
				Book[] books = new Book[num];
				
				return books;
			}
			catch(InputMismatchException e) {
				System.out.println("정수를 입력하세요!");
				scanner.nextLine();
			}
		}
		
		
	}
	
	public void enterBookName(Book[] books) {
		Scanner scanner = new Scanner(System.in);
		
		for (int i=0; i<books.length; i++, System.out.println()) {
			int id = i + 1;
			System.out.println(id+"번 Book");
			System.out.print("제목 : ");
			String title = scanner.nextLine();
			System.out.print("저자 : ");
			String author = scanner.nextLine();
			System.out.print("가격 : ");
			int price = -1;
			
			do {
				try {
					price = scanner.nextInt();
					scanner.nextLine();
					books[i] = new Book(id, title, author, price);
				} 
				catch(InputMismatchException e) {
					System.out.println("가격을 올바르게 기입하세요");
					scanner.nextLine();
				}	
			} while(price < 0);
		}
	}
	
	public void showList(Book[] books) {
		System.out.println("- 전체 Book 리스트 보기");
		for (int i=0; i<books.length; i++,System.out.println()) {
			System.out.println("id : " + books[i].getId());
			System.out.println("title : " + books[i].getTitle());
			System.out.println("author : " + books[i].getAuthor());
			System.out.println("price : " + books[i].getPrice()+"원");
		}
	}
	
	public void showSpecificBook(Book[] books) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색할 책의 번호를 입력하세요 : ");
		int number = -1;
		do{
			try {
				number = scanner.nextInt();
				System.out.println("id : " + books[number-1].getId());
				System.out.println("제목 : " + books[number-1].getTitle());
				System.out.println("저자 : " + books[number-1].getAuthor());
				System.out.println("가격 : " + books[number-1].getPrice()+"원");
			}
			catch(InputMismatchException e) {
				System.out.println("정수 값을 입력하세요.");
				scanner.nextLine();
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("인덱스 범위를 넘었습니다. 다시 입력하세요");
			}
		} while(number <= 0 || number>books.length);
	}
	
	public void showHighestLowestPrice(Book[] books) {
		int max = books[0].getPrice(), min = books[0].getPrice();
		
		for (int i=1; i<books.length; i++) {
			if (max<books[i].getPrice()) max=books[i].getPrice();
			if (min>books[i].getPrice()) min=books[i].getPrice();
		}
		System.out.println("최고 가격 : "+max+"원");
		System.out.println("최저 가격 : "+min+"원");
	}
	
	public void showAverageMedian(Book[] books) {
		int sum = 0;
		double average = 0.0, median = 0.0;
		int len = books.length;
		
		for (int i=0; i<len; i++) 
			sum += books[i].getPrice();
		average = (sum*1.0)/(len*1.0);
		
		Book[] sortedBooks = sortArray(books);
		
		if (len%2==1) 
			median = sortedBooks[len/2].getPrice();
		else 
			median = (sortedBooks[len/2 - 1].getPrice() + sortedBooks[len/2].getPrice())*0.1/2.0;
		
		System.out.println("평균 가격 : "+average+"원");
		System.out.println("중위 가격 : "+median+"원");
	}
	
	public Book[] sortArray(Book[] books) {
		Book[] sortedBooks = books.clone();
		int len = sortedBooks.length;
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len-i-1; j++) {
				if (sortedBooks[j].getPrice()>sortedBooks[j+1].getPrice()) {
					int tmp=sortedBooks[j].getPrice();
					sortedBooks[j].setPrice(sortedBooks[j+1].getPrice());
					sortedBooks[j+1].setPrice(tmp);
				}
			}
		}
		return books;
	}
	
	public Book[] createTargetBooks(Book[] books) {
		Book[] targetBooks = new Book[books.length];
		
		for (int i=0; i<targetBooks.length; i++) {
			targetBooks[i] = new Book();
		}
		
		for (int i=0; i<targetBooks.length; i++) {
			targetBooks[i].setId(books[i].getId());
			targetBooks[i].setTitle(books[i].getTitle());
			targetBooks[i].setAuthor(books[i].getAuthor());
			targetBooks[i].setPrice(books[i].getPrice());
		}
		return targetBooks;
	}
	
	public void sortByTitle(Book[] targetBooks) 
	{
		for (int i=1; i<targetBooks.length; i++) {
			for (int j=0; j<targetBooks.length-i; j++) {
				String title1 = targetBooks[j].getTitle();
				String title2 = targetBooks[j+1].getTitle();
				if (title1.compareTo(title2)>0) {
					int id1 = targetBooks[j].getId(), id2 = targetBooks[j+1].getId();
					String author1 = targetBooks[j].getAuthor(), author2 = targetBooks[j+1].getAuthor();
					int price1 = targetBooks[j].getPrice(), price2 = targetBooks[j+1].getPrice();
					
					targetBooks[j].setId(id2);
					targetBooks[j].setTitle(title2);
					targetBooks[j].setAuthor(author2);
					targetBooks[j].setPrice(price2);
					
					targetBooks[j+1].setId(id1);
					targetBooks[j+1].setTitle(title1);
					targetBooks[j+1].setAuthor(author1);
					targetBooks[j+1].setPrice(price1);
				}
			}
		}
		
		printSortedBooks(targetBooks);
	}
	
	
	public void printSortedBooks(Book[] targetBooks) {
		System.out.println();
		System.out.println("<정렬해서 보기>");
		for (int i=0; i<targetBooks.length; i++,System.out.println()) {
			System.out.println("ID "+targetBooks[i].getId());
			System.out.println("Title "+targetBooks[i].getTitle());
			System.out.println("Author "+targetBooks[i].getAuthor());
			System.out.println("Price "+targetBooks[i].getPrice());
		}
	}
	
	public void showSpecificTitle(Book[] books) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("찾을 책을 입력하세요 : ");
		String title = scanner.nextLine(); //문자열 띄어써도 되도록 nextLine()으로 문자열 받음
		for (int i=0; i<books.length; i++) {
			if (title.equals(books[i].getTitle())) {  //문자열 비교 주의!! deep
				System.out.println("id : " + books[i].getId());
				System.out.println("제목 :  "+books[i].getTitle());
				System.out.println("저자 :  "+books[i].getAuthor());
				System.out.println("가격 :  "+books[i].getPrice() + "원");
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookManager manager = new BookManager();
		manager.runProgram();
	}

}
