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
public class MemberDao {

	@Autowired
	JdbcTemplate db;

	@Autowired
	Member member;
	


	public void memberInsert(Member member) {
		String sql = "INSERT INTO member(_id,_pw,_name) VALUES (?,?,?)";
		db.update(sql, member.getId(), member.getPw(), member.getName());
		// TODO Auto-generated method stub
	}

	public Integer Login(Member member) {
		String sql = "select id from member where _id = ? and _pw = ? ;";
		List<Integer> result = db.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer name = rs.getInt("id");
				// TODO Auto-generated method stub
				return name ;
			}
			
		},member.getId(),member .getPw());
		
		if (result.size() == 0)
			return null;
		else
			return result.get(0); 
	}
	
	public String Name(Member member) {
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
	
	
	
	
}
