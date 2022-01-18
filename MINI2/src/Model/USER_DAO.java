package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class USER_DAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	

	//데이터베이스 연결
public void connect() {
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524";
		String user = "campus_d_3_0115";
		String password = "smhrd3";

		conn = DriverManager.getConnection(url, user, password);
		}catch(Exception e) {
			e.printStackTrace();	
		}
}
// db 디스커넥트
public void close() {
	try {
		if(rs!=null) {
		rs.close();
		}
		if(pst!=null) {
		pst.close();
		}
		if(conn!=null) {
		conn.close();
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
// db에 회원가입 정보 넣기
public boolean insertId(String ID, int PASSWORD, String NAME) {

	boolean check = false;
	try {
		connect();
		
		String sql = "insert into user_info(ID,PASSWORD,NAME) values( ?, ?, ?)";


		pst = conn.prepareStatement(sql);

		pst.setString(1, ID);
		pst.setInt(2, PASSWORD);
		pst.setString(3, NAME);

		int cnt = pst.executeUpdate();

		if (cnt > 0) { // 추가성공
			check = true;
		} else {// 추가 실패
			check = false;
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}

	return check;

}
//입력한 로그인 하기 위해 아이디의 비번 가져오기
public USER_VO selectOneID(String ID) {

	USER_VO vo = null;
	try {
		connect();

		String sql = "select ID,PASSWORD from USER_INFO where ID= ?";

		pst = conn.prepareStatement(sql);

		pst.setString(1, ID);

		rs = pst.executeQuery();

		// sql문 실행시 where조건절으로 특정한 번호를 가진 학생만 검색
		// ->resultest 상에 해당 번호가 table 상에 존재하면 1명에 대한 데이터만 존재

		if (rs.next()) {
			String id = rs.getString("ID");// 현재 커서가 가르키고 있는 행의 첫뻔째 컬럼값을 읽어오겠다!
			int password  = rs.getInt("PASSWORD");
			vo = new USER_VO(id, password);
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return vo;
}

//탑 10랭크 확인하기
public ArrayList rankCheck() {

	
	ArrayList<USER_VO> al=new ArrayList<USER_VO>();
	try {
		connect();

		String sql = "select ROWNUM,ID,NAME,SCORE from (SELECT ID,NAME,SCORE from USER_INFO order by Score desc) where ROWNUM<=10";

		pst = conn.prepareStatement(sql);

		rs = pst.executeQuery();

		// sql문 실행시 where조건절으로 특정한 번호를 가진 학생만 검색
		// ->resultest 상에 해당 번호가 table 상에 존재하면 1명에 대한 데이터만 존재

		while(rs.next()) {
			String ID = rs.getString("ID");// 현재 커서가 가르키고 있는 행의 첫뻔째 컬럼값을 읽어오겠다!
			String NAME  = rs.getString("NAME");
			int SCORE = rs.getInt("SCORE");
			al.add(new USER_VO(ID,NAME,SCORE));
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return al;
}

//선택한 아이디 랭크 확인하기
public USER_VO selectRank(String setID) {

	USER_VO vo = null;
	try {
		connect();

		String sql = "select ID,NAME,SCORE from USER_INFO where ID=?";

		pst = conn.prepareStatement(sql);

		pst.setString(1, setID);

		rs = pst.executeQuery();

		if (rs.next()) {
			String ID = rs.getString("ID");
			String NAME = rs.getString("NAME");
			int SCORE = rs.getInt("SCORE");
			vo = new USER_VO(ID,NAME,SCORE);
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}
	return vo;
}
}

