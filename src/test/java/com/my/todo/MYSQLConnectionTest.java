package com.my.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class MYSQLConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver"; //Connection�� ������ Ŭ������ �̸�
	private static final String URL = "JDBC:MYSQL://localhost:3306/todolist?useSSL=false"; //mysql ���� �ּ�
	private static final String USER = "root"; //����
	private static final String PW = "1234567890"; // ��й�ȣ
	
	@Test //jUnit�� �׽�Ʈ��
	public void testConnection() throws Exception{
		Class.forName(DRIVER); //DRIVER��� �̸��� ���� Ŭ������ ã��
        
		//DB ������ ����� ��ü�� Connection Ŭ������ �ν��Ͻ��� con�� ����
		try(Connection con = DriverManager.getConnection(URL,USER,PW)){ 
			int id = 1;
			String name = selectName(con,id);
			System.out.println("id�� "+id+"�� ���� name �� " + name);
		}catch(Exception e) { //������ ���� ���� ����ó��
			e.printStackTrace();
		}
	}	
	private static final String SQL2 = "select rold_id from role where idrole = ?;"; // sql ����

	public String selectName(Connection con, Integer id) throws Exception {
		String result = null;
		try (PreparedStatement pstmt = con.prepareStatement(SQL2)) {
			pstmt.setString(1,id.toString());
			ResultSet rs = pstmt.executeQuery(); // ���� ����
			if (rs.next()) // �������� �ִ��� Ȯ��
				result = rs.getString("rold_id"); // name column�� ������
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}