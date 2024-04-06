package net.musecom.spbbs.dto;

import java.sql.Timestamp;

public class SpCommentDto {
	
	private int num;
	private int ndboardNum;
	private String username;
	private String userpass;
	private String userid;
	private String comment;
	private Timestamp cdate;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getNdboardNum() {
		return ndboardNum;
	}
	public void setNdboard_num(int ndboardNum) {
		this.ndboardNum = ndboardNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
	
	@Override
	public String toString() {
		return "SpCommentDto [num=" + num + ", ndboardNum=" + ndboardNum + ", username=" + username + ", userpass="
				+ userpass + ", userid=" + userid + ", comment=" + comment + ", cdate=" + cdate + "]";
	}
	
	
	
}
