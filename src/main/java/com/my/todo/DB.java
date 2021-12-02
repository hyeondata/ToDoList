package com.my.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
public class DB {

	private static final String Login = "select _name from member where _id = ? and _pw = ? ;";
	private static final String Register_SQL = "INSERT INTO member(_id,_pw,_name) VALUES (?,?,?)";
	@Autowired
	private DBConfig _db;

	// ����

	/*
	 * public String selectDB(String select , String from , String where) throws
	 * Exception { DataSource ds = _db.DataSource(); Connection con = null; con =
	 * ds.getConnection(); String result = null; try (PreparedStatement pstmt =
	 * con.prepareStatement(SQL2)) { pstmt.setString(1, select.toString());
	 * pstmt.setString(2, select.toString()); pstmt.setString(3, select.toString());
	 * ResultSet rs = pstmt.executeQuery(); // ���� ���� if (rs.next()) // �������� �ִ��� Ȯ��
	 * result = rs.getString("rold_id"); // name column�� ������ } catch (Exception e) {
	 * throw e; } return result; }
	 */

	public String Login(String id, String pw) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		try (PreparedStatement pstmt = con.prepareStatement(Login)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery(); // ���� ����
			if (rs.next()) // �������� �ִ��� Ȯ��
				result = rs.getString("_name"); // name column�� ������
		} catch (Exception e) {
			System.out.println("Error");
			result = "Error";
			throw e;

		}
		return result;
	}
	
	public String Register(String id, String pw , String name) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		try(PreparedStatement pstml = (PreparedStatement) con.prepareStatement(Register_SQL)) {
			pstml.setString(1, id); //1��° ����ǥ�� name ����
			pstml.setString(2, pw); //2��° ����ǥ�� remark ����
			pstml.setString(3, name); //2��° ����ǥ�� remark ����
			pstml.executeUpdate(); //�������� ��ȯ �� ������ ���� ����
			result = "Success";
		}catch(Exception e){ //����ó��
			e.printStackTrace();
			System.out.println("���̺� �� ���� ����");
			result = "Error";
		}
		return result;
	}
}
