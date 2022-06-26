package ksnu.bohwa1378.lecture13;

public class Member {

	private int classification=-1; //administrator=1, member=0
	private String id=null;
	private String pass=null;
	private String name=null;
	
	public Member() {
		
	}
	public Member(int classification, String id, String pass, String name) {
		this.classification=classification;
		this.id=id;
		this.pass=pass;
		this.name=name;
	}
	
	public int getClassification() {
		return classification;
	}

	public void setClassification(int classification) {
		this.classification = classification;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
	}

}
