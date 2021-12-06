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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mysql.fabric.xmlrpc.base.Struct;



@EnableTransactionManagement
@Configuration
public class DB {

	private static final String Login = "select _name from member where _id = ? and _pw = ? ;";
	private static final String Register_SQL = "INSERT INTO member(_id,_pw,_name) VALUES (?,?,?)";
	
	private static final String Create_SQL = "INSERT INTO description(_Desc,_Date,_Desc_ID) VALUES (?,?,?); ";
	private static final String Create_SQL1 = "INSERT INTO todo(Do) VALUES (?);";
	
	private static final String _Do = "select DISTINCT _Desc, _Date, _id,status from description __desc left join todo _todo on __desc._Desc_ID = _todo.Do WHERE  _Desc_ID = ?";
	
	private static final String _Submit = "select status from description where _id = ?;";
	private static final String _Submit1 ="UPDATE description SET status = ? where _id = ?;";
	
	private static final String _cancel = "select status from description where _id = ?;";
	private static final String _cancel1 ="UPDATE description SET status = ? where _id = ?;";
	
	
	@Autowired
	private DBConfig _db;

	public String Login(String id, String pw) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		try (PreparedStatement pstmt = con.prepareStatement(Login)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery(); 
			if (rs.next()) 
				result = rs.getString("_name"); 
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
			pstml.setString(1, id); 
			pstml.setString(2, pw); 
			pstml.setString(3, name); 
			pstml.executeUpdate(); 
			result = "Success";
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("테이블에 행 삽입 실패");
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
			pstml.setString(1, Desc); 
			pstml.setString(2, Date); 
			pstml.setString(3, Desc_ID); 
			try(PreparedStatement pstml1 = (PreparedStatement) con.prepareStatement(Create_SQL1)) {
				pstml1.setString(1, Desc_ID);
				pstml1.executeUpdate(); 
				result = "doSuccess";
			}catch(Exception e){ 
				e.printStackTrace();
				System.out.println("테이블에 행 삽입 실패");
				result = "Error";
			}
			
			pstml.executeUpdate(); 
			result = "Success";
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("테이블에 행 삽입 실패");
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
		ArrayList<String> _id = new ArrayList<String>();
		ArrayList<Integer> _status = new ArrayList<Integer>();
		try (PreparedStatement pstmt = con.prepareStatement(_Do)) {
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				_desc.add(rs.getString("_Desc")); 
				_date.add(rs.getString("_Date"));
				_id.add(rs.getString("_id"));
				_status.add(rs.getInt("status"));
			}
				
		} catch (Exception e) {
			System.out.println("Error");
			throw e;

		}

		_result.add(_desc);
		_result.add(_date);
		_result.add(_id);
		_result.add(_status);
		return _result;
	}
	
	public String Submit(String _id) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		int status = 0;
		try(PreparedStatement pstml = (PreparedStatement) con.prepareStatement(_Submit)) {
			pstml.setString(1, _id); 
			ResultSet rs =  pstml.executeQuery(); 
			if(rs.next()) {
				status = rs.getInt("status");
				System.out.println(status);
			}
			result = "Success";
			try(PreparedStatement pstml1 = (PreparedStatement) con.prepareStatement(_Submit1)) {
				if(status<3) {
					status++;
				}
				pstml1.setString(1, String.valueOf(status));
				pstml1.setString(2, _id);
				pstml1.executeUpdate(); 
				result = "SubmitSuccess";
			}catch(Exception e){ 
				e.printStackTrace();
				System.out.println("테이블에 행 삽입 실패");
				result = "Error";
			}
			
			
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("테이블에 행 삽입 실패");
			result = "Error";
		}
		return result;
	}
	
	public String Cancel(String _id) throws Exception {
		DataSource ds = _db.DataSource();
		Connection con = null;
		con = ds.getConnection();
		String result = null;
		int status = 0;
		try(PreparedStatement pstml = (PreparedStatement) con.prepareStatement(_cancel)) {
			pstml.setString(1, _id); 
			ResultSet rs =  pstml.executeQuery(); 
			if(rs.next()) {
				status = rs.getInt("status");
				System.out.println(status);
			}
			result = "Success";
			try(PreparedStatement pstml1 = (PreparedStatement) con.prepareStatement(_cancel1)) {
				if(status>0) {
					status--;
				}
				pstml1.setString(1, String.valueOf(status));
				pstml1.setString(2, _id);
				pstml1.executeUpdate(); 
				result = "SubmitSuccess";
			}catch(Exception e){ 
				e.printStackTrace();
				System.out.println("테이블에 행 삽입 실패");
				result = "Error";
			}
			
			
		}catch(Exception e){ 
			e.printStackTrace();
			System.out.println("테이블에 행 삽입 실패");
			result = "Error";
		}
		return result;
	}
	
}
