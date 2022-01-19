package controller;

import java.util.ArrayList;

import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class UserController {
	
	private View view = new View();
	private USER_DAO udao = new USER_DAO();
	
	public UserController() {
		
	}
	public UserController(USER_DAO udao) {
		this.udao = udao;
	}
	
	public void handleJoin () {
		String id = view.inputId();
		int pw = view.inputPwd();
		String user = view.inputName();
		boolean userin = udao.insertId(id, pw, user);
		if (userin == true) {
			System.out.println("회원가입이 성공했습니다");
			udao.updateScore(id, 0);
		} else {
			System.out.println("회원가입이 실패했습니다");
		}
	}

	public String returnId() {
		String userId = view.inputId();
		return userId;
	}
	
	public int returnPwd() {
		int userPwd = view.inputPwd();
		return userPwd;
	}
		
	public void rankCheck() {
		ArrayList<USER_VO> al = udao.rankCheck();
		view.showrankList();
		for (USER_VO vo : al) {
			System.out.println("ID:" + vo.getID() + "\t Name:" + vo.getNAME() + "\t Score:" + vo.getSCORE());
		}
		String setId=view.chooseRank();
		USER_VO v = udao.selectRank(setId);
		if (v == null) {
			view.idError();
		} else {
			view.chooseList(v);
		}
	}
	
	public void bestScoreUpdate(int score, int totalscore, String id, USER_DAO udao ) {
		if (score > udao.currentScore(id)) {
			udao.updateScore(id, totalscore);
		}
	}
		
	public int Start() {
		int s=view.StartBaseBall();
		return s;
	}

	public void finish() {
		// TODO Auto-generated method stub
		view.finish();	
	}
	public void inputError() {
		// TODO Auto-generated method stub
		view.inputError();
	}
}
