package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;
import Model.USER_VO;

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
				if(userin==true) {
					System.out.println("ȸ�������� �����߽��ϴ�");
				}else {
					System.out.println("ȸ�������� �����߽��ϴ�");
				}
			} else if (s == 2) {
				System.out.print("���̵� �Է� : ");
				String id = sc.next();
				System.out.print("��й�ȣ �Է� : ");
				int pw = sc.nextInt();
				
				USER_VO check = udao.selectOneID(id);
				if (id.equals(check.getID()) && pw == check.getPASSWORD()) {
					if(pdao.checkPlayer(id)==false) {
						for (int i = 0; i < 3; i++) {
							System.out.println("[���� ���]");
							System.out.print("���� �̸� �Է� : ");
							String pName = sc.next();
							System.out.print("�ɷ�ġ >> ");
							int pAbility = rd.nextInt(100) + 1;
							System.out.print(pAbility+"\n");
							boolean playerin = pdao.enrollPlayer(pName, pAbility, id);
						}
					}
					System.out.println("[Ÿ�� ���]");
					ArrayList<PlayerDTO> selectH = pdao.selectAllHitter(id);
					for (PlayerDTO al : selectH) {
						System.out
								.println("Player_Name:" + al.getPlayerName() + "\t Abillity:" + al.getPlayerAbility());
					}
					int strike = 0;
					int score = 0;
					int victory=0;
					while (true) {
						System.out.print("���Ե� Ÿ���̸��� �Է����ּ���:");
						String setname = sc.next();
						PlayerDTO dto = pdao.selectOneHitter(setname);

						System.out.println("Ÿ�ڿ� ���� �����մϴ�!!");
						int pitcher = rd.nextInt(100) + 1;
						if (pitcher > dto.getPlayerAbility() || dto.getPlayerAbility() - pitcher <= 10) {
							System.out.println("��Ʈ����ũ!!!!");
							strike++;
							System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
						} else if (dto.getPlayerAbility() - pitcher <= 50) {
							System.out.println("��Ÿ");
							score++;
							System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
						} else if (dto.getPlayerAbility() - pitcher > 50) {
							System.out.println("Ȩ��");
							score += 2;
							System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
						}
						if (strike >= 3) {
							System.out.println("�й��Ͽ����ϴ�");
							if(score>udao.currentScore(id)) {
								udao.updateScore(id, score);
								}
							System.out.print("����Ͻðڽ��ϱ�?(Y/N)");
							String continu = sc.next();
							if (continu.equals("N") || continu.equals("n")) {
								break;
							}
						} else if (score >= 10) {
							System.out.println("�¸��Ͽ����ϴ�");
							if(score>udao.currentScore(id)) {
							udao.updateScore(id, score);
							}
							System.out.print("����Ͻðڽ��ϱ�?(Y/N)");
							String continu = sc.next();
							victory++;
							if (continu.equals("N") || continu.equals("n")) {
								break;
							}
						}
						if(victory==2) {
							System.out.println("[���� ���]");
							System.out.print("���� �̸� �Է� : ");
							String pName = sc.next();
							System.out.println("�ɷ�ġ >> ");
							int pAbility = rd.nextInt(100) + 1;
							boolean playerin = pdao.enrollPlayer(pName, pAbility, id);
						}
					}
				} else {
					System.out.println("�߸� �Է��Ͽ����ϴ�");
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
