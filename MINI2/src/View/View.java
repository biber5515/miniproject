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
			System.out.print("1.ȸ������ 2.�α��� >> ");
			int s = sc.nextInt();
			if(s==1) {
				System.out.println("[ȸ������]");
				System.out.print("���̵� �Է� : ");
				String id = sc.next();
				System.out.print("��й�ȣ �Է� : ");
				int pw = sc.nextInt();
				System.out.print("�̸� �Է� : ");
				String user = sc.next();
				//�Է� ���� �������� db�� insert
				boolean userin = udao.insertStd(id, pw, user);
			}else if(s==2) {
				System.out.print("���̵� �Է� : ");
				String id = sc.next();
				System.out.print("��й�ȣ �Է� : ");
				String pw = sc.next();
//				if()
				System.out.println("[���� ���]");
				System.out.print("���� �̸� �Է� : ");
				String pName = sc.next();
				System.out.println("�ɷ�ġ >> ");
				int pAbility = rd.nextInt();
				//�Է� ���� Ÿ������ db�� insert
				boolean playerin = pdao.enrollPlayer(pName, pAbility);
				System.out.println("[Ÿ�� ���]");
				//Ÿ�� ��� Ȯ��
				ArrayList<PlayerDTO> selectH = pdao.selectHitter(id);
				
				System.out.println("[Game Start]");
				//���� �ɷ�ġ ����
				int b;
				//Ÿ�ڿ� ���� ���� �� ��
				if(pAbility>b) {
					System.out.println("[��Ʈ����ũ]");
				}else {
					if(10<b-pAbility && b-pAbility<50) {
						System.out.println("[��Ÿ]");
					}else if(b-pAbility>=50) {
						System.out.println("[Ȩ��]");
					}
				}
			}
		}
		
	}

}
