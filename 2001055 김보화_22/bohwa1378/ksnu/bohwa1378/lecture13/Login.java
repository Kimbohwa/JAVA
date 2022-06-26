package ksnu.bohwa1378.lecture13;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
	Scanner sc = null;

	public Login() {
		sc = new Scanner(System.in);
	}

	// 로그인
	public int logIn() {
		ArrayList<Member> members = new ArrayList<Member>(5);
		readFile(members);// ArrayList에 파일 속 멤버들 추가

		String id = null, pass = null;

		// ArrayList에 추가된 멤버 중 id, password 맞는 사람 찾기

		System.out.println("<프로그램 포털>");
		System.out.println("id와 password를 입력하세요.");
		while (true) {
			System.out.print("id : ");
			id = sc.next();
			System.out.print("password : ");
			pass = sc.next();

			for (Member member : members) {
				if (id.equals(member.getId())) {
					if (pass.equals(member.getPass())) {
						System.out.println("성공");
						return member.getClassification();
					}
				}
			}
			System.out.println("비밀번호가 틀립니다.");
		}
	}

	public void readFile(ArrayList<Member> members) {
		try {
			String filePath = "C:\\dev\\bohwa1378\\ksnu\\bohwa1378\\lecture13\\login.txt";
			FileInputStream fis = new FileInputStream(filePath);
			DataInputStream dis = new DataInputStream(fis);

			int classi = -1;
			String idInFile = null, passInFile = null, nameInFile = null;
			boolean EOF = false;

			while (!EOF) {
				try {
					classi = dis.readInt();
					dis.readChar();
					idInFile = dis.readUTF();
					dis.readChar();
					passInFile = dis.readUTF();
					dis.readChar();
					nameInFile = dis.readUTF();
					dis.readChar();
					dis.readChar();
					String dePass = decryptPass(passInFile);
					Member member = new Member(classi, idInFile, dePass, nameInFile);
					members.add(member);// 원래 파일에 있는 멤버들을 ArrayList에 추가
				} catch (EOFException e) {
					EOF = true;
				}
			}
			dis.close();
		} catch (FileNotFoundException e) {
			System.out.println("DataIOTest : " + e);
		} catch (IOException e) {
			System.out.println("DataIOTest : " + e);
		}
	}

	// password decrypt
	public String decryptPass(String pass) {
		String dePass = null;
		try {
			String key = "key";
			dePass = JavaEnCryto.Decrypt(pass, key); // static 메서드 접근 시 클래스 이름으로 접근
		} catch (Exception ex) {
			System.out.println("오류");
		}
		return dePass;
	}

	public void runProgram() {
		int number = -1;
		ArrayList<Member> members = new ArrayList<Member>(5);

		readFile(members);// ArrayList에 파일 속 멤버들 추가

		do {
			printProgram();
			try {
				number = sc.nextInt();
				selectProgram(number, members);
			} catch (InputMismatchException e) {
				System.out.println("정수가 아닙니다. 다시 입력하세요!");
				sc.next();
			}
		} while (number != 5);
		// sc.close();

	}

	public void printProgram() {
		System.out.println("1. 신규 회원가입");
		System.out.println("2. 회원정보 수정");
		System.out.println("\t1. pass 수정");
		System.out.println("\t2. 이름 수정");
		System.out.println("3. 회원 삭제");
		System.out.println("4. 회원정보 보기");
		System.out.println("5. 이전 화면으로");
		System.out.print("선택 : ");
	}

	public void selectProgram(int num, ArrayList<Member> members) {
		try {
			switch (num) {
			case 1:
				addMember(members);
				break;
			case 2:
				updateInformation(members);
				break;
			case 3:
				deleteInformation(members);
				break;
			case 4:
				showInformation(members);
				break;
			case 5:
				terminateProgram(members);
				break;
			default:
				System.out.println("없는 번호 입니다.");
				break;
			}
		} catch (NullPointerException e) {
			System.out.println("멤버부터 생성하세요");
		}
	}

	// 1. 신규 회원가입
	public void addMember(ArrayList<Member> members) {
		int classification = -1;
		String id = null, pass = null, name = null;

		try {
			System.out.println("회원구분(admin-1:member-0)");
			classification = sc.nextInt();
			while (!(classification == 1 || classification == 0)) {
				classification = sc.nextInt();
				System.out.println("0 또는 1을 입력하세요.");
			}

			System.out.println("id : ");
			id = sc.next();
			for (int i=0; i<members.size();) {
				if (id.equals(members.get(i).getId())) {
					System.out.println("이미 존재하는 id 입니다. 다시 입력하세요.");
					id=sc.next();
					i=0;
				}
				else i++;
			}
			
			System.out.println("pass : ");
			pass = sc.next();
			System.out.println("name : ");
			name = sc.next();

			Member member = new Member(classification, id, pass, name);
			members.add(member);
		} catch (InputMismatchException e) {
			System.out.println("정수를 입력하세요");
			sc.nextLine();
		}

		System.out.println("새로운 id : " + id + "을(를) 생성했습니다.");
	}

	// 2. 회원정보 수정
	public void updateInformation(ArrayList<Member> members) {
		int num = -1;
		System.out.println("1. pass 수정");
		System.out.println("2. 이름 수정");
		System.out.print("선택 : ");
		try {
			num = sc.nextInt();
			if (num == 1) {
				updatePass(members);
			} else if (num == 2) {
				updateName(members);
			} else {
				System.out.println("없는 번호입니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("정수를 입력하세요.");
		}
	}

	public void updatePass(ArrayList<Member> members) {
		String id = null, pass = null, pass2 = null;
		boolean isThere = false;

		System.out.println("password를 수정할 회원의 id를 입력하세요");
		id = sc.next();

		for (Member member : members) {
			if (id.equals(member.getId())) {
				isThere = true;
				System.out.println(id + "의 password를 입력하세요");
				pass = sc.next();
				if (pass.equals(member.getPass())) {
					System.out.println("새로운 password를 입력하세요");
					pass = sc.next();
					System.out.println("새로운 password를 입력하세요(again)");
					pass2 = sc.next();
					if (pass.equals(pass2)) {
						member.setPass(pass);
						System.out.println("비밀번호를 변경하였습니다.");
					} else {
						System.out.println("같은 비밀번호를 입력하지 않아 변경할 수 없습니다.");
					}
					break;
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
					break;
				}
			}
		}
		if (!isThere)
			System.out.println("없는 회원입니다.");
	}

	public void updateName(ArrayList<Member> members) {
		String id = null, pass = null, name = null;
		boolean isThere = false;

		System.out.println("password를 수정할 회원의 id를 입력하세요");
		id = sc.next();

		for (Member member : members) {
			if (id.equals(member.getId())) {
				isThere = true;
				System.out.println(id + "의 password를 입력하세요");
				pass = sc.next();
				if (pass.equals(member.getPass())) {
					System.out.println("새로운 name을 입력하세요");
					name = sc.next();
					member.setName(name);
					break;
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
					break;
				}
			}
		}
		if (!isThere)
			System.out.println("없는 회원입니다.");
	}

	// 3. 회원 삭제
	public void deleteInformation(ArrayList<Member> members) {
		String id = null, pass = null;
		boolean isThere = false;
		System.out.println("삭제할 회원의 id를 입력하세요");
		id = sc.next();
		if (id.equals("root")) {
			System.out.println("root는 삭제할 수 없습니다.");
			System.out.println();
		} else {
			for (Member member : members) {
				if (id.equals(member.getId())) {
					isThere = true;
					System.out.println(id + "의 password를 입력하세요");
					pass = sc.next();
					if (pass.equals(member.getPass())) {
						members.remove(member);
						System.out.println(id+"가 삭제되었습니다.");
						break;
					} else {
						System.out.println("비밀번호가 틀렸습니다.");
						break;
					}
				}
			}
			if (!isThere)
				System.out.println("없는 회원입니다.");
		}
	}

	// 4. 회원정보 보기
	public void showInformation(ArrayList<Member> members) {
		System.out.println("id\tpass\tname\t구분");
		System.out.println("---------------------------");
		for (Member member : members) {
			System.out.println(member.getId() + "\t" + member.getPass() + "\t" + member.getName() + "\t"
					+ member.getClassification());
		}
		System.out.println();
	}

	// 5. 이전 화면으로
	// 종료 전 ArrayList 속 멤버들을 파일에 저장
	public void terminateProgram(ArrayList<Member> members) {
		String enPass = null;
		try {
			String filePath = "C:\\dev\\bohwa1378\\ksnu\\bohwa1378\\lecture13\\login.txt";
			FileOutputStream fop = new FileOutputStream(filePath);
			DataOutputStream dos = new DataOutputStream(fop);

			for (Member member : members) {
				dos.writeInt(member.getClassification());
				dos.writeChar('\n');
				dos.writeUTF(member.getId());
				dos.writeChar('\n');

				enPass = encryptPass(member.getPass());
				dos.writeUTF(enPass);
				dos.writeChar('\n');
				dos.writeUTF(member.getName());
				dos.writeChar('\n');
				dos.writeChar('\n');
			}
			dos.close();
		} catch (IOException e) {
			System.out.println("DataIOTest : " + e);
		}
	}

	public String encryptPass(String pass) {
		String enPass = null;
		try {
			String key = "key";
			enPass = JavaEnCryto.Encrypt(pass, key); // static 메서드 접근 시 클래스 이름으로 접근
		} catch (Exception ex) {
			System.out.println("오류");
		}
		return enPass;
	}

	public static void main(String[] args) {
		Login lo = new Login();
		lo.logIn();
		lo.runProgram();
	}

}
