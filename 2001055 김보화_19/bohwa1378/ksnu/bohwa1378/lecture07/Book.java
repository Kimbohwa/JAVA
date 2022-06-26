package ksnu.bohwa1378.lecture07;

public class Book {
	private int id;
	private String title;
	private String author;
	private int price;

	public Book() {}
	
	public Book(int id, String title, String author, int price) { //모든 값 입력 생성자
		this.id=id;
		this.title=title;
		this.author=author;
		this.price=price;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) { 
		this.title=title;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	
	public void setPrice(int price) {
		this.price=price;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getPrice() {
		return price;
	}
	
	public static void main(String[] args) {
		Book book = new Book(1, "어린왕자", "생텍쥐페리", 700);
	}

}
