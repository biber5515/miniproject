package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;
import Model.USER_VO;
import controller.PlayerController;

public class View {
	
	Scanner sc = new Scanner(System.in);
	
	// ���� ����
	public int StartBaseBall () {
		System.out.println("[BaseBall Game]");
		System.out.print("1.ȸ������ 2.�α��� 3.��ũȮ�� 4.�������� >> ");
		int s = sc.nextInt();
		return s;
	}
	
	// ȸ������ ���, �� ID �Է�
	public String inputId () {
		System.out.println("[ȸ������]");
		System.out.print("���̵� �Է� : ");
		String id = sc.next();
		return id;
	}
	
	// ��й�ȣ �Է�
	public int inputPwd() {
		System.out.print("��й�ȣ �Է� : ");
		int pw = sc.nextInt();
		return pw;
	}
	
	// �̸� �Է�
	public String inputName() {
		System.out.print("�̸� �Է� : ");
		String user = sc.next();
		return user;
	}
	
	public void successJoin () {
		System.out.println("ȸ�������� �����߽��ϴ�");
	}
	
	public void failureJoin () {
		System.out.println("ȸ�������� �����߽��ϴ�");
	}
	
	public String enrollPlayer () {
		System.out.println("[���� ���]");
		System.out.print("���� �̸� �Է� : ");
		String pName = sc.next();
		return pName;
	}
	
	public void showAbility (int pAbility) {
		System.out.println("�ɷ�ġ >> " + pAbility);
	}
	
	public void showHitterList () {
		System.out.println("[Ÿ�� ���]");
	}
	
	public void showHitters (PlayerDTO al) {
		System.out.println(
				"Player_Name:" + al.getPlayerName() + "\t Abillity:" + al.getPlayerAbility());
	}
	
	public String inputHitterName () {
		System.out.print("���Ե� Ÿ���̸��� �Է����ּ���:");
		String setname = sc.next();
		return setname;
	}
	
	public void cantLoadPitcher () {
		System.out.println("�ҷ��� ������ �����ϴ�. �ٸ� ������ ���� ������ ������ּ���.");
	}
	
	public void joinHitAndPit () {
		System.out.println("Ÿ�ڿ� ���� �����մϴ�!!");
	}
	
	public void strikeResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("��Ʈ����ũ!!!!");
		System.out.println("===============================");
		System.out.println("Ÿ�� �ɷ�ġ:" + dto.getPlayerAbility());
		System.out.println("���� �ɷ�ġ:" + pitcherAbil);
	}
	
	public void showStrike (int strike, int score) {
		System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
	}
	
	public void safetyResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("��Ÿ");
		System.out.println("===============================");
		System.out.println("Ÿ�� �ɷ�ġ:" + dto.getPlayerAbility());
		System.out.println("���� �ɷ�ġ:" + pitcherAbil);
	}
	
	public void showSafety (int strike, int score) {
		System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
	}
	
	public void homerunResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("Ȩ��");
		System.out.println("===============================");
		System.out.println("Ÿ�� �ɷ�ġ:" + dto.getPlayerAbility());
		System.out.println("���� �ɷ�ġ:" + pitcherAbil);
	}
	
	public void showHomerun (int strike, int score) {
		System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
	}
	
}
