
import controller.PlayerController;
import controller.UserController;
import utils.AsciiArt;
import controller.MusicController;
import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class MainPage {
	public static void main(String[] args) {
		PlayerDAO playerDao = new PlayerDAO();
		USER_DAO userDao = new USER_DAO();
		UserController userCon = new UserController(userDao);
		PlayerController playerCon = new PlayerController(playerDao);
		MusicController musicCon = new MusicController();
		View view = new View();

		while (true) {
			int s = userCon.Start();
			if (s == 1) {
				userCon.handleJoin();
			} else if (s == 2) {
				String id = userCon.returnId();
				int Pwd = userCon.returnPwd();
				USER_VO check = userDao.selectOneID(id);
				if (check != null) {
					if (id.equals(check.getID()) && Pwd == check.getPASSWORD()) {
						if (playerDao.checkPlayer(id) == false) {
							playerCon.enrollPlayers(id);
						}
						playerCon.showHitterList(id); // ���̵� ��ϵ� ��ü ���� ����Ʈ ���
						int strike = 0;
						int score = 0;
						int totalScore = 0;
						int victory = 0;
						String continu = "";
						String prePlayer = "";
						// musicCon.Intro();
						while (true) {
							PlayerDTO Hitter = playerCon.inputHitters(id, playerDao);
							if (Hitter.getPlayerName().equals(prePlayer)) {
								view.needRestPlayer();
								continue;
							}
							prePlayer = Hitter.getPlayerName();
							PlayerDTO pitcher = playerCon.getPitcherList(id, playerDao);
							if (pitcher == null) {
								view.noPicher(); // ���� ��ϵ� �ٸ� ������ ������ ������ ���ٴ� �޽��� ���
								break;
							}
							if (Hitter != null) {
								int pitcherAbil = pitcher.getPlayerAbility();

								view.playerEnter();
								if (pitcherAbil > Hitter.getPlayerAbility()
										|| Hitter.getPlayerAbility() - pitcherAbil <= 10) { // ��Ʈ����ũ
									strike++;
									// musicCon.StrikePlay();
									playerCon.handleStrike(Hitter, pitcherAbil, strike, score);
								} else if (Hitter.getPlayerAbility() - pitcherAbil <= 50) { // ��Ÿ
									score++;
									// musicCon.HitPlay();
									playerCon.handleSafety(Hitter, pitcherAbil, strike, score);
								} else if (Hitter.getPlayerAbility() - pitcherAbil > 50) { // Ȩ��
									score += 2;
									// musicCon.HomeRunPlay();
									playerCon.handleHomerun(Hitter, pitcherAbil, strike, score);
								}
								if (strike == 3) { // ��Ʈ����ũ 3�� => �й�
									view.defeatPrint();
									totalScore += score;
									view.showTotalscore(totalScore);
									userCon.bestScoreUpdate(score, totalScore, id, userDao);
									strike = 0;
									score = 0;
									continu = view.isContinue();
									if (continu.equals("N") || continu.equals("n")) {
										break;
									}
								} else if (score >= 10) {
									view.victoryPrint();
									totalScore += score;
									view.showTotalscore(totalScore);
									userCon.bestScoreUpdate(score, totalScore, id, userDao);
									continu = view.isContinue();
									victory++;
									strike = 0;
									score = 0;
									if (continu.equals("N") || continu.equals("n")) {
										break;
									}

								}
								if (victory == 2) {
									playerCon.enrollBonusPlayer(playerDao, id); // 2�����ϸ� ���� �Ѹ��� �߰��� ��� �� �� �ִ�.
									victory = 0;
								}

							} else {
								view.hitterInputError();
							}
						}
					} else {
						view.passwordError();
					}
				} else {
					view.idError();
				}
			} else if (s == 3) {
				userCon.rankCheck();
			} else if (s == 4) {
				userCon.finish();
				break;
			} else {
				userCon.inputError();
			}

		}

	}

}
