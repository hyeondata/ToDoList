package com.my.todo;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class Member {

	
	private String Id;
	private String pw;
	private String name;
	private int Num;
	
	public void setNum(int num) {
		this.Num = num;
	}
	
	public Integer getNum() {
		return Num;
	}
	
	
	public String getId() {
		return Id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		this.Id = id;
		System.out.println(Id);
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
