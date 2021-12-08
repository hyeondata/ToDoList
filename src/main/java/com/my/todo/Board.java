package com.my.todo;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;


@Repository
public class Board {
	private String _Desc;
	private String _Date;
	private String _id;
	private Integer _status;
	
	
	public String getDesc(){
		return _Desc;
	}
	public String getDate(){
		return _Date;
	}
	public String getid(){
		return _id;
	}
	public Integer getstatus(){
		return _status;
	}
	
	
	public void setDesc(String _desc) {
		this._Desc = _desc;
	}
	
	public void setDate(String _date) {
		this._Date = _date;
	}
	
	public void setid(String _id) {
		this._id = _id;
	}
	
	public void setstatus(Integer _status) {
		this._status = _status;
	}
}/*
@Repository
public class Board {
	private ArrayList<String> _Desc;
	private ArrayList<String> _Date;
	private ArrayList<String> _id;
	private ArrayList<Integer> _status;
	
	
	public ArrayList<String> getDesc(){
		return _Desc;
	}
	public ArrayList<String> getDate(){
		return _Date;
	}
	public ArrayList<String> getid(){
		return _id;
	}
	public ArrayList<Integer> getstatus(){
		return _status;
	}
	
	
	public void setDesc(ArrayList<String> _desc) {
		this._Desc = _desc;
	}
	
	public void setDate(ArrayList<String> _date) {
		this._Date = _date;
	}
	
	public void setid(ArrayList<String> _id) {
		this._id = _id;
	}
	
	public void setstatus(ArrayList<Integer> _stauts) {
		this._status = _status;
	}
}*/
