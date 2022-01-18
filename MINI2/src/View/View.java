package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Model.PlayerDAO;
import Model.PlayerDTO;
import Model.USER_DAO;

public class View {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		PlayerDAO pdao = new PlayerDAO();
		USER_DAO udao = new USER_DAO();
		
		while(true) {
			System.out.println("[BaseBall Game]");
			System.out.print("1.회원가입 2.로그인 >> ");
			int s = sc.nextInt();
			if(s==1) {
				System.out.println("[회원가입]");
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				int pw = sc.nextInt();
				System.out.print("이름 입력 : ");
				String user = sc.next();
				//입력 받은 유저정보 db에 insert
				boolean userin = udao.insertStd(id, pw, user);
			}else if(s==2) {
				System.out.print("아이디 입력 : ");
				String id = sc.next();
				System.out.print("비밀번호 입력 : ");
				String pw = sc.next();
//				if()
				System.out.println("[선수 등록]");
				System.out.print("선수 이름 입력 : ");
				String pName = sc.next();
				System.out.println("능력치 >> ");
				int pAbility = rd.nextInt();
				//입력 받은 타자정보 db에 insert
				boolean playerin = pdao.enrollPlayer(pName, pAbility);
				System.out.println("[타자 목록]");
				//타자 목록 확인
				ArrayList<PlayerDTO> selectH = pdao.selectHitter(id);
				
				System.out.println("[Game Start]");
				//투수 능력치 정보
				int b;
				//타자와 투수 점수 차 비교
				if(pAbility>b) {
					System.out.println("[스트라이크]");
				}else {
					if(10<b-pAbility && b-pAbility<50) {
						System.out.println("[안타]");
					}else if(b-pAbility>=50) {
						System.out.println("[홈런]");
					}
				}
			}
		}
		
	}

}
