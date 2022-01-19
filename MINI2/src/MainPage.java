
import controller.PlayerController;
import controller.UserController;
import controller.MusicController;
import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class MainPage {
	public static void main(String[] args) {
		PlayerDAO pdao = new PlayerDAO();
		USER_DAO udao = new USER_DAO();
		UserController uco = new UserController(udao);
		PlayerController pco = new PlayerController(pdao);
		MusicController mco= new MusicController();
		View view = new View();

		while (true) {
			int s = uco.Start();
			if (s == 1) {
				uco.handleJoin();
			} else if (s == 2) {
				String id = uco.returnId();
				int Pwd = uco.returnPwd();
				USER_VO check = udao.selectOneID(id);
				if (check != null) {
					if (id.equals(check.getID()) && Pwd == check.getPASSWORD()) {
						if (pdao.checkPlayer(id) == false) {
							pco.enrollPlayers(id);
						}
						pco.showHitterList(id);
						int strike = 0;
						int score = 0;
						int totalScore = 0;
						int victory = 0;
						String continu="";
						mco.Intro(); 
						while (true) {
							
							PlayerDTO Hitter = pco.inputHitters(id, pdao);
							PlayerDTO pitcher = pco.getPitcherList(id, pdao);
							if (pitcher == null) {
								view.noPicher();
								break;
							}
							int pitcherAbil = pitcher.getPlayerAbility();

								view.playerEnter();
							if (pitcherAbil > Hitter.getPlayerAbility()
									|| Hitter.getPlayerAbility() - pitcherAbil <= 10) {
								strike++;
								mco.StrikePlay();
								pco.handleStrike(Hitter, pitcherAbil, strike, score);
							} else if (Hitter.getPlayerAbility() - pitcherAbil <= 50) {
								score++;
								mco.HitPlay();
								pco.handleSafety(Hitter, pitcherAbil, strike, score);
							} else if (Hitter.getPlayerAbility() - pitcherAbil > 50) {
								score += 2;
								mco.HomeRunPlay();
								pco.handleHomerun(Hitter, pitcherAbil, strike, score);
							}
							if (strike >= 3) {
								view.defeatPrint();
								totalScore += score;
								uco.bestScoreUpdate(score, totalScore, id, udao);
								strike = 0;
								score = 0;
								 continu = view.isContinue();
								if (continu.equals("N") || continu.equals("n")) {
									break;
								} else if (score >= 10) {
									view.victoryPrint();
									totalScore += score;
									uco.bestScoreUpdate(score, totalScore, id, udao);
									continu = view.isContinue();
									victory++;
									strike = 0;
									score = 0;
									if (continu.equals("N") || continu.equals("n")) {
										break;
									}

								}
								if (victory == 2) {
									pco.enrollBonusPlayer(pdao, id);
									victory = 0;
								}
							}
						}					
					}else {
						view.passwordError();
					}
				}else {
					view.idError();
				}
			} else if (s == 3) {
				uco.rankCheck();
			} else if (s == 4) {
				uco.finish();
			} else {
				uco.inputError();
			}

		}

	}

}
