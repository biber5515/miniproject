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
			System.out.println("ȸ�������� �����߽��ϴ�");
			udao.updateScore(id, 0);
		} else {
			System.out.println("ȸ�������� �����߽��ϴ�");
		}
	}
}
