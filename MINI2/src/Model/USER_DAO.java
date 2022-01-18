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
	

	//�����ͺ��̽� ����
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
// db ��Ŀ��Ʈ
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
// db�� ȸ������ ���� �ֱ�
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

		if (cnt > 0) { // �߰�����
			check = true;
		} else {// �߰� ����
			check = false;
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		close();
	}

	return check;

}
//�Է��� �α��� �ϱ� ���� ���̵��� ��� ��������
public USER_VO selectOneID(String ID) {

	USER_VO vo = null;
	try {
		connect();

		String sql = "select ID,PASSWORD from USER_INFO where ID= ?";

		pst = conn.prepareStatement(sql);

		pst.setString(1, ID);

		rs = pst.executeQuery();

		// sql�� ����� where���������� Ư���� ��ȣ�� ���� �л��� �˻�
		// ->resultest �� �ش� ��ȣ�� table �� �����ϸ� 1�� ���� �����͸� ����

		if (rs.next()) {
			String id = rs.getString("ID");// ���� Ŀ���� ����Ű�� �ִ� ���� ù��° �÷����� �о���ڴ�!
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

//ž 10��ũ Ȯ���ϱ�
public ArrayList rankCheck() {

	
	ArrayList<USER_VO> al=new ArrayList<USER_VO>();
	try {
		connect();

		String sql = "select ROWNUM,ID,NAME,SCORE from (SELECT ID,NAME,SCORE from USER_INFO order by Score desc) where ROWNUM<=10";

		pst = conn.prepareStatement(sql);

		rs = pst.executeQuery();

		// sql�� ����� where���������� Ư���� ��ȣ�� ���� �л��� �˻�
		// ->resultest �� �ش� ��ȣ�� table �� �����ϸ� 1�� ���� �����͸� ����

		while(rs.next()) {
			String ID = rs.getString("ID");// ���� Ŀ���� ����Ű�� �ִ� ���� ù��° �÷����� �о���ڴ�!
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

//������ ���̵� ��ũ Ȯ���ϱ�
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

