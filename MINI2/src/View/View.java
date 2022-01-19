package View;

import java.util.Scanner;

import Model.PlayerDTO;
import Model.USER_VO;


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
	
	public void homerunResult (PlayerDTO dto, int pitcherAbil) {
		System.out.println("===============================");
		System.out.println("홈런");
		System.out.println("===============================");
		System.out.println("타자 능력치:" + dto.getPlayerAbility());
		System.out.println("투수 능력치:" + pitcherAbil);
	}
	public void defeatPrint() {
		System.out.println("패배하였습니다");
	}
	public void victoryPrint() {
		System.out.println("승리하였습니다");
	}
	public String continu() {
		System.out.print("계속하시겠습니까?(Y/N)");
		String continu = sc.next();
		return continu;
	}
	public void passwordError() {
		System.out.println("비밀번호를 잘못 입력하셨습니다.");
	}
	public void idError() {
		System.out.println("등록된 ID가 없습니다.");
	}
	public void showrankList () {
		System.out.println("최고점수 랭킹 10위");
	}
	public void showRank (USER_VO vo) {
		System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
	}
	public String chooseRank() {
		System.out.println("==========================================");
		System.out.print("ID를 입력하시면 입력한 아이디의 최고점수가 출력됩니다:");
		String setId = sc.next();
		return setId;
	}
	public void chooseError() {
		System.out.println("없는 아이디입니다");
	}
	
	public void chooseList(USER_VO v) {
		System.out.println("=======================================");
		System.out.println("ID:" + v.getID() + "\t Name:" + v.getNAME() + "\t Score:" + v.getSCORE());
	}
	public void finish() {
		System.out.println("종료되었습니다");
	}
	public void inputError() {
		System.out.println("잘못 입력하였습니다");
	}

}

