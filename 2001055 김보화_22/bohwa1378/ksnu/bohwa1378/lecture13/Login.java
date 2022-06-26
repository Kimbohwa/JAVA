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

	// �α���
	public int logIn() {
		ArrayList<Member> members = new ArrayList<Member>(5);
		readFile(members);// ArrayList�� ���� �� ����� �߰�

		String id = null, pass = null;

		// ArrayList�� �߰��� ��� �� id, password �´� ��� ã��

		System.out.println("<���α׷� ����>");
		System.out.println("id�� password�� �Է��ϼ���.");
		while (true) {
			System.out.print("id : ");
			id = sc.next();
			System.out.print("password : ");
			pass = sc.next();

			for (Member member : members) {
				if (id.equals(member.getId())) {
					if (pass.equals(member.getPass())) {
						System.out.println("����");
						return member.getClassification();
					}
				}
			}
			System.out.println("��й�ȣ�� Ʋ���ϴ�.");
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
					members.add(member);// ���� ���Ͽ� �ִ� ������� ArrayList�� �߰�
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
			dePass = JavaEnCryto.Decrypt(pass, key); // static �޼��� ���� �� Ŭ���� �̸����� ����
		} catch (Exception ex) {
			System.out.println("����");
		}
		return dePass;
	}

	public void runProgram() {
		int number = -1;
		ArrayList<Member> members = new ArrayList<Member>(5);

		readFile(members);// ArrayList�� ���� �� ����� �߰�

		do {
			printProgram();
			try {
				number = sc.nextInt();
				selectProgram(number, members);
			} catch (InputMismatchException e) {
				System.out.println("������ �ƴմϴ�. �ٽ� �Է��ϼ���!");
				sc.next();
			}
		} while (number != 5);
		// sc.close();

	}

	public void printProgram() {
		System.out.println("1. �ű� ȸ������");
		System.out.println("2. ȸ������ ����");
		System.out.println("\t1. pass ����");
		System.out.println("\t2. �̸� ����");
		System.out.println("3. ȸ�� ����");
		System.out.println("4. ȸ������ ����");
		System.out.println("5. ���� ȭ������");
		System.out.print("���� : ");
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
				System.out.println("���� ��ȣ �Դϴ�.");
				break;
			}
		} catch (NullPointerException e) {
			System.out.println("������� �����ϼ���");
		}
	}

	// 1. �ű� ȸ������
	public void addMember(ArrayList<Member> members) {
		int classification = -1;
		String id = null, pass = null, name = null;

		try {
			System.out.println("ȸ������(admin-1:member-0)");
			classification = sc.nextInt();
			while (!(classification == 1 || classification == 0)) {
				classification = sc.nextInt();
				System.out.println("0 �Ǵ� 1�� �Է��ϼ���.");
			}

			System.out.println("id : ");
			id = sc.next();
			for (int i=0; i<members.size();) {
				if (id.equals(members.get(i).getId())) {
					System.out.println("�̹� �����ϴ� id �Դϴ�. �ٽ� �Է��ϼ���.");
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
			System.out.println("������ �Է��ϼ���");
			sc.nextLine();
		}

		System.out.println("���ο� id : " + id + "��(��) �����߽��ϴ�.");
	}

	// 2. ȸ������ ����
	public void updateInformation(ArrayList<Member> members) {
		int num = -1;
		System.out.println("1. pass ����");
		System.out.println("2. �̸� ����");
		System.out.print("���� : ");
		try {
			num = sc.nextInt();
			if (num == 1) {
				updatePass(members);
			} else if (num == 2) {
				updateName(members);
			} else {
				System.out.println("���� ��ȣ�Դϴ�.");
			}
		} catch (InputMismatchException e) {
			System.out.println("������ �Է��ϼ���.");
		}
	}

	public void updatePass(ArrayList<Member> members) {
		String id = null, pass = null, pass2 = null;
		boolean isThere = false;

		System.out.println("password�� ������ ȸ���� id�� �Է��ϼ���");
		id = sc.next();

		for (Member member : members) {
			if (id.equals(member.getId())) {
				isThere = true;
				System.out.println(id + "�� password�� �Է��ϼ���");
				pass = sc.next();
				if (pass.equals(member.getPass())) {
					System.out.println("���ο� password�� �Է��ϼ���");
					pass = sc.next();
					System.out.println("���ο� password�� �Է��ϼ���(again)");
					pass2 = sc.next();
					if (pass.equals(pass2)) {
						member.setPass(pass);
						System.out.println("��й�ȣ�� �����Ͽ����ϴ�.");
					} else {
						System.out.println("���� ��й�ȣ�� �Է����� �ʾ� ������ �� �����ϴ�.");
					}
					break;
				} else {
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					break;
				}
			}
		}
		if (!isThere)
			System.out.println("���� ȸ���Դϴ�.");
	}

	public void updateName(ArrayList<Member> members) {
		String id = null, pass = null, name = null;
		boolean isThere = false;

		System.out.println("password�� ������ ȸ���� id�� �Է��ϼ���");
		id = sc.next();

		for (Member member : members) {
			if (id.equals(member.getId())) {
				isThere = true;
				System.out.println(id + "�� password�� �Է��ϼ���");
				pass = sc.next();
				if (pass.equals(member.getPass())) {
					System.out.println("���ο� name�� �Է��ϼ���");
					name = sc.next();
					member.setName(name);
					break;
				} else {
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					break;
				}
			}
		}
		if (!isThere)
			System.out.println("���� ȸ���Դϴ�.");
	}

	// 3. ȸ�� ����
	public void deleteInformation(ArrayList<Member> members) {
		String id = null, pass = null;
		boolean isThere = false;
		System.out.println("������ ȸ���� id�� �Է��ϼ���");
		id = sc.next();
		if (id.equals("root")) {
			System.out.println("root�� ������ �� �����ϴ�.");
			System.out.println();
		} else {
			for (Member member : members) {
				if (id.equals(member.getId())) {
					isThere = true;
					System.out.println(id + "�� password�� �Է��ϼ���");
					pass = sc.next();
					if (pass.equals(member.getPass())) {
						members.remove(member);
						System.out.println(id+"�� �����Ǿ����ϴ�.");
						break;
					} else {
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
						break;
					}
				}
			}
			if (!isThere)
				System.out.println("���� ȸ���Դϴ�.");
		}
	}

	// 4. ȸ������ ����
	public void showInformation(ArrayList<Member> members) {
		System.out.println("id\tpass\tname\t����");
		System.out.println("---------------------------");
		for (Member member : members) {
			System.out.println(member.getId() + "\t" + member.getPass() + "\t" + member.getName() + "\t"
					+ member.getClassification());
		}
		System.out.println();
	}

	// 5. ���� ȭ������
	// ���� �� ArrayList �� ������� ���Ͽ� ����
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
			enPass = JavaEnCryto.Encrypt(pass, key); // static �޼��� ���� �� Ŭ���� �̸����� ����
		} catch (Exception ex) {
			System.out.println("����");
		}
		return enPass;
	}

	public static void main(String[] args) {
		Login lo = new Login();
		lo.logIn();
		lo.runProgram();
	}

}
