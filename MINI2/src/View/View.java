package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.StudentVO;
import Model.USER_DAO;
import Model.USER_VO;
import jdk.internal.misc.FileSystemOption;

public class View {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		PlayerDAO pdao = new PlayerDAO();
		USER_DAO udao = new USER_DAO();

		while (true) {
			System.out.println("[BaseBall Game]");
			System.out.print("1.ȸ������ 2.�α��� 3.��ũȮ�� 4.�������� >> ");
			int s = sc.nextInt();
			if (s == 1) {
				System.out.println("[ȸ������]");
				System.out.print("���̵� �Է� : ");
				String id = sc.next();
				System.out.print("��й�ȣ �Է� : ");
				int pw = sc.nextInt();
				System.out.print("�̸� �Է� : ");
				String user = sc.next();
				// �Է� ���� �������� db�� insert
				boolean userin = udao.insertId(id, pw, user);
			} else if (s == 2) {
				System.out.print("���̵� �Է� : ");
				String id = sc.next();
				System.out.print("��й�ȣ �Է� : ");
				int pw = sc.nextInt();

				USER_VO check = udao.selectOneID(id);
				if (id == check.getID() && pw == check.getPASSWORD()) {
					for (int i = 0; i < 3; i++) {
						System.out.println("[���� ���]");
						System.out.print("���� �̸� �Է� : ");
						String pName = sc.next();
						System.out.println("�ɷ�ġ >> ");
						int pAbility = rd.nextInt(100) + 1;
						boolean playerin = pdao.enrollPlayer(pName, pAbility, id);
					}
					System.out.println("[Ÿ�� ���]");
					 ArrayList<PlayerDTO> selectH = pdao.selectAllHitter(id);
					 for(PlayerDTO al: selectH) {
						 System.out.println("Player_Name:"+al.getPlayerName()+"\t Abillity:"+al.getPlayerAbility());
					 }
		           


				} else {
					System.out.println("�߸� �Է��Ͽ����ϴ�");
				}
				ArrayList<PlayerDTO> selectH = pdao.selectHitter(id);

				System.out.println("[Game Start]");
				// ���� �ɷ�ġ ����
				int b;
				// Ÿ�ڿ� ���� ���� �� ��
				if (pAbility > b) {
					System.out.println("[��Ʈ����ũ]");
				} else {
					if (10 < b - pAbility && b - pAbility < 50) {
						System.out.println("[��Ÿ]");
					} else if (b - pAbility >= 50) {
						System.out.println("[Ȩ��]");
					}
				}
			} else if (s == 3) {
				ArrayList<USER_VO> al = udao.rankCheck();
				System.out.println("�ְ����� ��ŷ 10��");

				for (USER_VO vo : al) {
					System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
				}
				System.out.print("==========================================");
				System.out.print("ID�� �Է��Ͻø� �Է��� ���̵��� �ְ������� ��µ˴ϴ�:");
				String setId = sc.next();
				USER_VO v = udao.selectRank(setId);
				if (v == null) {
					System.out.println("���� ���̵��Դϴ�");
				} else {
					System.out.println("=================");
					System.out.println("ID:" + v.getID() + "\t Name:" + v.getNAME() + "\t Score:" + v.getSCORE());
				}
			} else if (s == 4) {
				System.out.println("����Ǿ����ϴ�");
				break;
			} else {
				System.out.println("�߸� �Է��Ͽ����ϴ�");
			}
		}

	}

}
