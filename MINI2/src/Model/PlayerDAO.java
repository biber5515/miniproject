package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerDAO {
	final String DB_URL = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524";
	final String DB_USER = "campus_d_3_0115";
	final String DB_PWD = "smhrd3";

	// JDBCs
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public void connect() {
		// DB ���� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// JDBC

	public boolean enrollPlayer(String playerName, int playerAbility, String ID) {
		// ���� ���
		// playerAbility�� ��Ʈ�ѷ����� ���������� �Ѱ��ּ�!

		boolean check = false; // ������ ó���� �� �Ǿ����� Ȯ���ϴ� ����
		try {
			connect();
			// ���ȣ�� ���� �κ� ���߿� �´� ���̺������ ����.
			String query = "insert into player(PLAYER_NAME,Abillity,ID) values (?, ?,?)";
			pst = conn.prepareStatement(query);

			pst.setString(1, playerName);
			pst.setInt(2, playerAbility);
			pst.setString(3, ID);

			int cnt = pst.executeUpdate();

			if (cnt > 0) {
				check = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return check;
	}

	public ArrayList<PlayerDTO> selectAllHitter(String userId) {
		// ��ϵ� Ÿ�ڵ� ��ȸ�ϱ� (���̵� ���� ��ϵ� Ÿ��)

		// ���õ� Ÿ�ڵ��� ���� ArrayList
		ArrayList<PlayerDTO> selectedHitter = new ArrayList<PlayerDTO>();
		try {
			connect();

			String query = "select PLAYER_NAME,Abillity from player where ID=?";
			pst = conn.prepareStatement(query);
			pst.setString(1, userId);

			rs = pst.executeQuery();

			while (rs.next()) {
				String playerName = rs.getString("PLAYER_NAME");
				int playerAbility = rs.getInt("Abillity");

				selectedHitter.add(new PlayerDTO(playerName, playerAbility));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return selectedHitter;
	}
//�÷��̾� �̹� ����� �Ǿ������� �ٽ� ����� ���� �ʱ����� Ȯ���ϱ����� ����
	public boolean checkPlayer(String ID) {
		boolean check=false;
		try {
			connect();

			String query = "select PLAYER_NAME,Abillity from Player where ID=?";

			pst = conn.prepareStatement(query);
			pst.setString(1, ID);

			rs = pst.executeQuery();
			
			if (rs.next()) {
				check=true;
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return check;
	}
	
	public PlayerDTO selectOneHitter(String hitterName) {
		// ������ Ÿ�� �̸��� �Է¹���.
		PlayerDTO selected = null;

		try {
			connect();

			String query = "select PLAYER_NAME,Abillity from PLAYER where PLAYER_NAME=?";

			pst = conn.prepareStatement(query);
			pst.setString(1, hitterName);

			rs = pst.executeQuery();

			if (rs.next()) {
				String playerName = rs.getString("PLAYER_NAME");
				int playerAbility = rs.getInt("Abillity");

				selected = new PlayerDTO(playerName, playerAbility);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return selected;
	}
	
	public ArrayList<PlayerDTO> PitcherSelect(String userId) {
		// DB���� �������� ���� �̾ƿ;���.
		// ���� ID�� ��ϵ� ������ ������ ��� �������� ��ȸ.
		// ��Ʈ�ѷ����� �� �߿��� �������� ������ �̾ƿ��°ɷ�..
		
		ArrayList<PlayerDTO> pitcherList = new ArrayList<PlayerDTO>();
		
		try {
			
			connect();
			
			String query = "select PLAYER_NAME,Abillity from PLAYER where ID != ?";
			pst = conn.prepareStatement(query);
			pst.setString(1, userId);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String playerName = rs.getString("PLAYER_NAME");
				int playerAbility = rs.getInt("Abillity");
				
				pitcherList.add(new PlayerDTO(playerName, playerAbility));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return pitcherList;
	}

}
