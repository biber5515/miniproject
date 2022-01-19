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
		// DB 동적 로딩
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
		// 선수 등록
		// playerAbility는 컨트롤러에서 랜덤값으로 넘겨주셈!

		boolean check = false; // 데이터 처리가 잘 되었는지 확인하는 변수
		try {
			connect();
			// 대괄호로 감싼 부분 나중에 맞는 테이블명으로 수정.
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
		// 등록된 타자들 조회하기 (아이디 별로 등록된 타자)

		// 선택된 타자들을 담을 ArrayList
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
//플레이어 이미 등록이 되어있으면 다시 등록을 하지 않기위해 확인하기위한 쿼리
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
		// 선택할 타자 이름을 입력받음.
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
		// DB에서 랜덤으로 투수 뽑아와야함.
		// 현재 ID에 등록된 선수를 제외한 모든 선수들을 조회.
		// 컨트롤러에서 이 중에서 랜덤으로 선수를 뽑아오는걸로..
		
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
