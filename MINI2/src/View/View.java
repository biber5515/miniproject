package View;

import java.util.Scanner;

import Model.PlayerDTO;
import Model.USER_VO;


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
	
	public void homerunResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("Ȩ��");
		System.out.println("===============================");
		System.out.println("Ÿ�� �ɷ�ġ:" + dto.getPlayerAbility());
		System.out.println("���� �ɷ�ġ:" + pitcherAbil);
	}
	public void defeatPrint() {
		System.out.println("�й��Ͽ����ϴ�");
	}
	public void victoryPrint() {
		System.out.println("�¸��Ͽ����ϴ�");
	}
	public String continu() {
		System.out.print("����Ͻðڽ��ϱ�?(Y/N)");
		String continu = sc.next();
		return continu;
	}
	public void passwordError() {
		System.out.println("��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
	}
	public void idError() {
		System.out.println("��ϵ� ID�� �����ϴ�.");
	}
	public void showrankList () {
		System.out.println("�ְ����� ��ŷ 10��");
	}
	public void showRank (USER_VO vo) {
		System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
	}
	public String chooseRank() {
		System.out.println("==========================================");
		System.out.print("ID�� �Է��Ͻø� �Է��� ���̵��� �ְ������� ��µ˴ϴ�:");
		String setId = sc.next();
		return setId;
	}
	public void chooseError() {
		System.out.println("���� ���̵��Դϴ�");
	}
	
	public void chooseList(USER_VO v) {
		System.out.println("=======================================");
		System.out.println("ID:" + v.getID() + "\t Name:" + v.getNAME() + "\t Score:" + v.getSCORE());
	}
	public void finish() {
		System.out.println("����Ǿ����ϴ�");
	}
	public void inputError() {
		System.out.println("�߸� �Է��Ͽ����ϴ�");
	}

}

