package com.my.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;import com.mysql.fabric.xmlrpc.base.Struct;




@Configuration
public class DB {

	private static final String Login = "select _name from member where _id = ? and _pw = ? ;";
	private static final String Register_SQL = "INSERT INTO member(_id,_pw,_name) VALUES (?,?,?)";
	private static final String Create_SQL = "INSERT INTO description(_Desc,_Date,_Desc_ID) VALUES (?,?,?); ";
	private static final String Create_SQL1 = "INSERT INTO todo(Do) VALUES (?);";
	private static final String _Do = "select DISTINCT _Desc,_Date from description __desc left join todo _todo on __desc._Desc_ID = _todo.Do WHERE _Desc_ID = ?";
	
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
	
	public String CreateWork(String Desc, String Date , String Desc_ID) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		try(PreparedStatement pstml = (PreparedStatement) con.prepareStatement(Create_SQL)) {
			pstml.setString(1, Desc); //1��° ����ǥ�� name ����
			pstml.setString(2, Date); //2��° ����ǥ�� remark ����
			pstml.setString(3, Desc_ID); //2��° ����ǥ�� remark ����
			try(PreparedStatement pstml1 = (PreparedStatement) con.prepareStatement(Create_SQL1)) {
				pstml1.setString(1, Desc_ID);
				pstml1.executeUpdate(); //�������� ��ȯ �� ������ ���� ����
				result = "doSuccess";
			}catch(Exception e){ //����ó��
				e.printStackTrace();
				System.out.println("���̺� �� ���� ����");
				result = "Error";
			}
			
			pstml.executeUpdate(); //�������� ��ȯ �� ������ ���� ����
			result = "Success";
		}catch(Exception e){ //����ó��
			e.printStackTrace();
			System.out.println("���̺� �� ���� ����");
			result = "Error";
		}
		return result;
	}
	
	public ArrayList<ArrayList> Do(String name) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		ArrayList<ArrayList> _result = new ArrayList<ArrayList>();
		ArrayList<String> _desc = new ArrayList<String>();
		ArrayList<String> _date = new ArrayList<String>();
		try (PreparedStatement pstmt = con.prepareStatement(_Do)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery(); // ���� ����
			while(rs.next()) {
			 // �������� �ִ��� Ȯ��
				_desc.add(rs.getString("_Desc")); // name column�� ������
				_date.add(rs.getString("_Date"));
			
			}
				
		} catch (Exception e) {
			System.out.println("Error");
			throw e;

		}
		_result.add(_desc);
		_result.add(_date);
		return _result;
	}
}
