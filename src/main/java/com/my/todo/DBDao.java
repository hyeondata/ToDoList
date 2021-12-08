package com.my.todo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class DBDao {

	@Autowired
	JdbcTemplate db;

	@Autowired
	Member member;
	
	@Autowired
	Board board;


	public void memberInsert(Member member) {
		String sql = "INSERT INTO member(_id,_pw,_name) VALUES (?,?,?)";
		db.update(sql, member.getId(), member.getPw(), member.getName());
		// TODO Auto-generated method stub
	}

	public String Login(Member member) {
		String sql = "select _name from member where _id = ? and _pw = ? ;";
		List<String> result = db.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String name = rs.getString("_name");
				// TODO Auto-generated method stub
				return name ;
			}
			
		},member.getId(),member.getPw());
		
		if (result.size() == 0)
			return null;
		else
			return result.get(0); 
	}
	
	public void CreateWork(String Desc , String Date, Member member) {
		String sql = "INSERT INTO description(_Desc,_Date,_Desc_ID) VALUES (?,?,?); ";
		db.update(sql, Desc, Date, member.getName());
	}
	
	public List<Board> board(Member member) {		//rs.getInt ¿À·ù??
		
		String sql ="select DISTINCT _Desc, _Date, _id, status from description WHERE _Desc_ID = ?";
		List<Board> result = db.query(sql, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();

				board.setstatus(rs.getInt("status"));
				board.setDesc(rs.getString("_Desc"));
				board.setDate(rs.getString("_Date"));
				board.setid(rs.getString("_id"));
		
				return board ;
			}
			
		},member.getName());

		return result;
		
	}
	
	public void Submit(String _id) {
		String sql = "select status from description where _id = ?;";
		List<Integer> result = db.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer name = rs.getInt("status");
				// TODO Auto-generated method stub
				return name ;
			}
			
		},_id);
		int _status = result.get(0);
		if (_status < 3) {
			_status++;
		}
		String sql1 = "UPDATE description SET status = ? where _id = ?";
		db.update(sql1,_status,_id);
	}
	
	
	public void Cancel(String _id) {
		String sql = "select status from description where _id = ?;";
		List<Integer> result = db.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer name = rs.getInt("status");
				// TODO Auto-generated method stub
				return name ;
			}
			
		},_id);
		int _status = result.get(0);
		if (_status > 0) {
			_status--;
		}
		String sql1 = "UPDATE description SET status = ? where _id = ?";
		db.update(sql1,_status,_id);
		if(_status == 0 ) {
			String sql2 = "DELETE FROM todolist.description where _id = ?";
			db.update(sql2,_id);
		}
	}
}
