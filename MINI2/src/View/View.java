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
	
	// 게임 시작
	public int StartBaseBall () {
		System.out.println("[BaseBall Game]");
		System.out.print("1.회원가입 2.로그인 3.랭크확인 4.게임종료 >> ");
		int s = sc.nextInt();
		return s;
	}
	
	// 회원가입 출력, 및 ID 입력
	public String inputId () {
		System.out.println("[회원가입]");
		System.out.print("아이디 입력 : ");
		String id = sc.next();
		return id;
	}
	
	// 비밀번호 입력
	public int inputPwd() {
		System.out.print("비밀번호 입력 : ");
		int pw = sc.nextInt();
		return pw;
	}
	
	// 이름 입력
	public String inputName() {
		System.out.print("이름 입력 : ");
		String user = sc.next();
		return user;
	}
	
	public void successJoin () {
		System.out.println("회원가입이 성공했습니다");
	}
	
	public void failureJoin () {
		System.out.println("회원가입이 실패했습니다");
	}
	
	public String enrollPlayer () {
		System.out.println("[선수 등록]");
		System.out.print("선수 이름 입력 : ");
		String pName = sc.next();
		return pName;
	}
	
	public void showAbility (int pAbility) {
		System.out.println("능력치 >> " + pAbility);
	}
	
	public void showHitterList () {
		System.out.println("[타자 목록]");
	}
	
	public void showHitters (PlayerDTO al) {
		System.out.println(
				"Player_Name:" + al.getPlayerName() + "\t Abillity:" + al.getPlayerAbility());
	}
	
	public String inputHitterName () {
		System.out.print("투입될 타자이름을 입력해주세요:");
		String setname = sc.next();
		return setname;
	}
	
	public void cantLoadPitcher () {
		System.out.println("불러올 투수가 없습니다. 다른 계정을 만들어서 선수를 등록해주세요.");
	}
	
	public void joinHitAndPit () {
		System.out.println("타자와 투수 입장합니다!!");
	}
	
	public void strikeResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("스트라이크!!!!");
		System.out.println("===============================");
		System.out.println("타자 능력치:" + dto.getPlayerAbility());
		System.out.println("투수 능력치:" + pitcherAbil);
	}
	
	public void showStrike (int strike, int score) {
		System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
	}
	
	public void safetyResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("안타");
		System.out.println("===============================");
		System.out.println("타자 능력치:" + dto.getPlayerAbility());
		System.out.println("투수 능력치:" + pitcherAbil);
	}
	
	public void showSafety (int strike, int score) {
		System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
	}
	
	public void homerunResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("홈런");
		System.out.println("===============================");
		System.out.println("타자 능력치:" + dto.getPlayerAbility());
		System.out.println("투수 능력치:" + pitcherAbil);
	}
	
	public void showHomerun (int strike, int score) {
		System.out.println("스트라이크 횟수:" + strike + " Score:" + score);
	}
	
}
