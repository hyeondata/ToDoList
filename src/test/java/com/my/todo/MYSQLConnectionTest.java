package com.my.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class MYSQLConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver"; //Connection을 구현한 클래스의 이름
	private static final String URL = "JDBC:MYSQL://localhost:3306/todolist?useSSL=false"; //mysql 서버 주소
	private static final String USER = "root"; //계정
	private static final String PW = "1234567890"; // 비밀번호
	
	@Test //jUnit이 테스트함
	public void testConnection() throws Exception{
		Class.forName(DRIVER); //DRIVER라는 이름을 가진 클래스를 찾음
        
		//DB 계정과 연결된 객체를 Connection 클래스의 인스턴스인 con에 담음
		try(Connection con = DriverManager.getConnection(URL,USER,PW)){ 
			int id = 1;
			String name = selectName(con,id);
			System.out.println("id가 "+id+"인 행의 name 은 " + name);
		}catch(Exception e) { //연결이 되지 않은 예외처리
			e.printStackTrace();
		}
	}	
	private static final String SQL2 = "select rold_id from role where idrole = ?;"; // sql 쿼리

	public String selectName(Connection con, Integer id) throws Exception {
		String result = null;
		try (PreparedStatement pstmt = con.prepareStatement(SQL2)) {
			pstmt.setString(1,id.toString());
			ResultSet rs = pstmt.executeQuery(); // 쿼리 실행
			if (rs.next()) // 다음행이 있는지 확인
				result = rs.getString("rold_id"); // name column을 가져옴
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}