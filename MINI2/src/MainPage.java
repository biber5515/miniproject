
import controller.PlayerController;
import controller.UserController;
import Model.PlayerDAO;
import Model.USER_DAO;
import Model.USER_VO;
import View.View;

public class MainPage {

	public static void main(String[] args) {
		UserController uco = new UserController();
		PlayerController pco = new PlayerController();
		PlayerDAO pdao = new PlayerDAO();
		USER_DAO udao = new USER_DAO();

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
					}

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
