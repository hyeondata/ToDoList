package com.my.todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	@Autowired
	JdbcTemplate db;

	@Autowired
	Board board;

	public List<Board> board(Member member) { // rs.getInt ¿À·ù??

		String sql = "select DISTINCT _Desc, _Date, _id, status from description WHERE MemberID = ?";
		List<Board> result = db.query(sql, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();

				board.setstatus(rs.getInt("status"));
				board.setDesc(rs.getString("_Desc"));
				board.setDate(rs.getString("_Date"));
				board.setid(rs.getInt("_id"));

				return board;
			}

		}, member.getNum());

		return result;

	}

	public void Submit(String _id) {
		String sql = "select status from description where _id = ?;";
		List<Integer> result = db.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer name = rs.getInt("status");
				// TODO Auto-generated method stub
				return name;
			}

		}, _id);
		int _status = result.get(0);
		if (_status < 3) {
			_status++;
		}
		String sql1 = "UPDATE description SET status = ? where _id = ?";
		db.update(sql1, _status, _id);
	}

	public void Cancel(String _id) {
		String sql = "select status from description where _id = ?;";
		List<Integer> result = db.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer name = rs.getInt("status");
				// TODO Auto-generated method stub
				return name;
			}

		}, _id);
		int _status = result.get(0);
		if (_status > 0) {
			_status--;
		}
		String sql1 = "UPDATE description SET status = ? where _id = ?";
		db.update(sql1, _status, _id);
		if (_status == 0) {
			String sql2 = "DELETE FROM todolist.description where _id = ?";
			db.update(sql2, _id);
		}
	}
	
	
	public void CreateWork(String Desc , String Date, Member member) {
		String sql = "INSERT INTO description(_Desc,_Date,_Desc_ID,MemberID) VALUES (?,?,?,?); ";
		db.update(sql, Desc, Date, member.getName(),member.getNum());
	}
}
