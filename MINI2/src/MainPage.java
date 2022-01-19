

import controller.PlayerController;
import controller.UserController;
import View.View;

public class MainPage {


	public static void main(String[] args) {
		UserController uco=new UserController();
		View view=new View();
		
		while (true) {		
			int s=uco.Start();
			if(s==1) {
			uco.handleJoin();
			}else if(s==2) {
				
			}else if(s==3) {
				uco.rankCheck();
			}else if(s==4) {
				uco.finish();
			}else {
				uco.inputError();
			}
			
			
		}

	}

}
