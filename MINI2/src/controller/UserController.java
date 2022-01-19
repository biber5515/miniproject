package controller;

import java.util.ArrayList;

import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class UserController {
	
	View view = new View();
	USER_DAO udao = new USER_DAO();
	
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
}
