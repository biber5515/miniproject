

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;
import Model.USER_VO;
import controller.PlayerController;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		PlayerDAO pdao = new PlayerDAO();
		USER_DAO udao = new USER_DAO();
		PlayerController playerController = new PlayerController(pdao);

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
				if (userin == true) {
					System.out.println("ȸ�������� �����߽��ϴ�");
					udao.updateScore(id, 0);
				} else {
					System.out.println("ȸ�������� �����߽��ϴ�");
				}
				//��Ʈ�ѷ�ȭ
			} else if (s == 2) {
				System.out.print("���̵� �Է� : ");
				String id = sc.next(); // -> String id = UserController.returnId();
				System.out.print("��й�ȣ �Է� : ");
				int pw = sc.nextInt(); // -> String Pwd = UserController.returnPwd();
				USER_VO check = udao.selectOneID(id);
				if (check != null) {
					if (id.equals(check.getID()) && pw == check.getPASSWORD()) {
						if (pdao.checkPlayer(id) == false) {
							for (int i = 0; i < 3; i++) {
								System.out.println("[���� ���]");
								System.out.print("���� �̸� �Է� : ");
								String pName = sc.next();
								int pAbility = rd.nextInt(100) + 1;
								boolean isOverlap = pdao.enrollPlayer(pName, pAbility, id);
								if (!isOverlap) {
									i = i-1;
									continue;
								}
								System.out.println("�ɷ�ġ >> " + pAbility);
							} // -> PlayerController.enrollPlayers(id);
						}
						// ==============================================
						System.out.println("[Ÿ�� ���]");
						ArrayList<PlayerDTO> selectH = pdao.selectAllHitter(id);
						for (PlayerDTO al : selectH) {
							System.out.println(
									"Player_Name:" + al.getPlayerName() + "\t Abillity:" + al.getPlayerAbility());
						}
						// =======> PlayerController.showHitterList(id);

						int strike = 0;
						int score = 0;
						int totalScore = 0;
						int victory = 0;
						while (true) {
							//
							System.out.print("���Ե� Ÿ���̸��� �Է����ּ���:");
							String setname = sc.next();
							PlayerDTO Hitter = pdao.selectOneHitter(setname);
							// -> PlayerDTO Hitter = PlayerController.inputHitters(id);
							PlayerDTO pitcher = playerController.getPitcherList(id,pdao);
							if (pitcher == null) {
								System.out.println("�ҷ��� ������ �����ϴ�. �ٸ� ������ ���� ������ ������ּ���.");
								break;
							}
							int pitcherAbil = pitcher.getPlayerAbility();

							System.out.println("Ÿ�ڿ� ���� �����մϴ�!!");
							if (pitcherAbil > Hitter.getPlayerAbility() || Hitter.getPlayerAbility() - pitcherAbil <= 10) {
								strike++;
								// ======================
								System.out.println("===============================");
								System.out.println("��Ʈ����ũ!!!!");
								System.out.println("===============================");
								System.out.println("Ÿ�� �ɷ�ġ:" + Hitter.getPlayerAbility());
								System.out.println("���� �ɷ�ġ:" + pitcherAbil);
								System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
								// =========> handleStrike(Hitter, pitcherAbil, strike, score);
							} else if (Hitter.getPlayerAbility() - pitcherAbil <= 50) {
								score++;
								//
								System.out.println("===============================");
								System.out.println("��Ÿ");
								System.out.println("===============================");
								System.out.println("Ÿ�� �ɷ�ġ:" + Hitter.getPlayerAbility());
								System.out.println("���� �ɷ�ġ:" + pitcherAbil);
								System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
								// =========> handleSafety(Hitter, pitcherAbil, strike, score);
							} else if (Hitter.getPlayerAbility() - pitcherAbil > 50) {
								score += 2;
								//
								System.out.println("===============================");
								System.out.println("Ȩ��");
								System.out.println("===============================");
								System.out.println("Ÿ�� �ɷ�ġ:" + Hitter.getPlayerAbility());
								System.out.println("���� �ɷ�ġ:" + pitcherAbil);
								System.out.println("��Ʈ����ũ Ƚ��:" + strike + " Score:" + score);
								// =========> handleHomerun(Hitter, pitcherAbil, strike, score);
							}
							//
							if (strike >= 3) {
								System.out.println("�й��Ͽ����ϴ�");
								totalScore += score;
								//
								if (score > udao.currentScore(id)) {
									udao.updateScore(id, totalScore);
								}
								// --> UserController.bestScoreUpdate(score, totalScore, id, udao);
								strike = 0;
								score = 0;
								//
								System.out.print("����Ͻðڽ��ϱ�?(Y/N)");
								String continu = sc.next();
								// --> String continue = View.isContinue();
								
								if (continu.equals("N") || continu.equals("n")) {
									break;
								}

							} else if (score >= 10) {
								System.out.println("�¸��Ͽ����ϴ�");
								totalScore += score;
								//
								if (score > udao.currentScore(id)) {
									udao.updateScore(id, totalScore);
								}
								// -> UserController.bestScoreUpdate(score, totalScore, id, udao);
								//
								System.out.print("����Ͻðڽ��ϱ�?(Y/N)");
								String continu = sc.next();
								// String continue = View.isContinue();
								victory++;
								strike = 0;
								score = 0;
								if (continu.equals("N") || continu.equals("n")) {
									break;
								}

							}
							if (victory == 2) {
								//
								System.out.println("[���� ���]");
								System.out.print("���� �̸� �Է� : ");
								String pName = sc.next();
								int pAbility = rd.nextInt(100) + 1;
								System.out.println("�ɷ�ġ >> " + pAbility);
								pdao.enrollPlayer(pName, pAbility, id);
								// ====> PlayerController.enrollBonusPlayer(playerDao, id);
								victory = 0;
							}
						}
					} else {
						System.out.println("��й�ȣ�� �߸� �Է��ϼ̽��ϴ�."); // -> View.passwordError();
					}
				} else {
					System.out.println("��ϵ� ID�� �����ϴ�."); // -> View.idError();
				}
			} else if (s == 3) {
				ArrayList<USER_VO> al = udao.rankCheck();
				System.out.println("�ְ����� ��ŷ 10��");

				for (USER_VO vo : al) {
					System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
				}
				System.out.println("==========================================");
				System.out.print("ID�� �Է��Ͻø� �Է��� ���̵��� �ְ������� ��µ˴ϴ�:");
				String setId = sc.next();
				USER_VO v = udao.selectRank(setId);
				if (v == null) {
					System.out.println("���� ���̵��Դϴ�");
				} else {
					System.out.println("=======================================");
					System.out.println("ID:" + v.getID() + "\t Name:" + v.getNAME() + "\t Score:" + v.getSCORE());
				}
				//��Ʈ�ѷ�ȭ
			} else if (s == 4) {
				System.out.println("����Ǿ����ϴ�");
				break;
			} else {
				System.out.println("�߸� �Է��Ͽ����ϴ�");
			}
		}

	}


}
