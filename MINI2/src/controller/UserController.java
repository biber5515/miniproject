package controller;

import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class UserController {
	
	private View view = new View();
	private USER_DAO udao = new USER_DAO();
	
	public void handleJoin () {
		String id = view.inputId();
		int pw = view.inputPwd();
		String user = view.inputName();
		boolean userin = udao.insertId(id, pw, user);
		if (userin == true) {
			System.out.println("ȸ�������� �����߽��ϴ�");
			udao.updateScore(id, 0);
		} else {
			System.out.println("ȸ�������� �����߽��ϴ�");
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
}
