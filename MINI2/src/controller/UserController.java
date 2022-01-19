package controller;

import Model.USER_DAO;
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
}
