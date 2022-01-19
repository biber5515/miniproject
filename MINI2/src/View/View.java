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
			System.out.print("1.회원가입 2.로그인 3.랭크확인 4.게임종료 >> ");
			int s = sc.nextInt();
			if (s == 1) {
				System.out.println("[회원가입]");
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				int pw = sc.nextInt();
				System.out.print("이름 입력 : ");
				String user = sc.next();
				// 입력 받은 유저정보 db에 insert
				boolean userin = udao.insertId(id, pw, user);
				if(userin==true) {
					System.out.println("회원가입이 성공했습니다");
				}else {
					System.out.println("회원가입이 실패했습니다");
				}
			} else if (s == 2) {
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				int pw = sc.nextInt();
				
				USER_VO check = udao.selectOneID(id);
				if (id.equals(check.getID()) && pw == check.getPASSWORD()) {
					if(pdao.checkPlayer(id)==false) {
						for (int i = 0; i < 3; i++) {
							System.out.println("[선수 등록]");
							System.out.print("선수 이름 입력 : ");
							String pName = sc.next();
							System.out.print("능력치 >> ");
							int pAbility = rd.nextInt(100) + 1;
							System.out.print(pAbility+"\n");
							boolean playerin = pdao.enrollPlayer(pName, pAbility, id);
						}
					}
					System.out.println("[타자 목록]");
					ArrayList<PlayerDTO> selectH = pdao.selectAllHitter(id);
					for (PlayerDTO al : selectH) {
						System.out
								.println("Player_Name:" + al.getPlayerName() + "\t Abillity:" + al.getPlayerAbility());
					}
					int strike = 0;
					int score = 0;
					int victory=0;
					while (true) {
						System.out.print("투입될 타자이름을 입력해주세요:");
						String setname = sc.next();
						PlayerDTO dto = pdao.selectOneHitter(setname);

						System.out.println("타자와 투수 입장합니다!!");
						int pitcher = rd.nextInt(100) + 1;
						if (pitcher > dto.getPlayerAbility() || dto.getPlayerAbility() - pitcher <= 10) {
							System.out.println("스트라이크!!!!");
							strike++;
							System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
						} else if (dto.getPlayerAbility() - pitcher <= 50) {
							System.out.println("안타");
							score++;
							System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
						} else if (dto.getPlayerAbility() - pitcher > 50) {
							System.out.println("홈런");
							score += 2;
							System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
						}
						if (strike >= 3) {
							System.out.println("패배하였습니다");
							if(score>udao.currentScore(id)) {
								udao.updateScore(id, score);
								}
							System.out.print("계속하시겠습니까?(Y/N)");
							String continu = sc.next();
							if (continu.equals("N") || continu.equals("n")) {
								break;
							}
						} else if (score >= 10) {
							System.out.println("승리하였습니다");
							if(score>udao.currentScore(id)) {
							udao.updateScore(id, score);
							}
							System.out.print("계속하시겠습니까?(Y/N)");
							String continu = sc.next();
							victory++;
							if (continu.equals("N") || continu.equals("n")) {
								break;
							}
						}
						if(victory==2) {
							System.out.println("[선수 등록]");
							System.out.print("선수 이름 입력 : ");
							String pName = sc.next();
							System.out.println("능력치 >> ");
							int pAbility = rd.nextInt(100) + 1;
							boolean playerin = pdao.enrollPlayer(pName, pAbility, id);
						}
					}
				} else {
					System.out.println("잘못 입력하였습니다");
				}
			} else if (s == 3) {
				ArrayList<USER_VO> al = udao.rankCheck();
				System.out.println("최고점수 랭킹 10위");

				for (USER_VO vo : al) {
					System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
				}
				System.out.print("==========================================");
				System.out.print("ID를 입력하시면 입력한 아이디의 최고점수가 출력됩니다:");
				String setId = sc.next();
				USER_VO v = udao.selectRank(setId);
				if (v == null) {
					System.out.println("없는 아이디입니다");
				} else {
					System.out.println("=================");
					System.out.println("ID:" + v.getID() + "\t Name:" + v.getNAME() + "\t Score:" + v.getSCORE());
				}
			} else if (s == 4) {
				System.out.println("종료되었습니다");
				break;
			} else {
				System.out.println("잘못 입력하였습니다");
			}
		}

	}

}
