package net.musecom.spbbs.dto;

import java.sql.Timestamp;

public class SpDto {
   private int num;
   private int orNum;
   private int grNum;
   private int grLayer;
   private String writer;
   private String userid;
   private String userpass;
   private String title;
   private String contents;
   private int hit;
   private Timestamp wdate;
   private int fileCount;
   private int memoCount;
   private Long imnum;
   
   public SpDto() {
	   
   }

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getOrNum() {
		return orNum;
	}
	
	public void setOrNum(int orNum) {
		this.orNum = orNum;
	}
	
	public int getGrNum() {
		return grNum;
	}
	
	public void setGrNum(int grNum) {
		this.grNum = grNum;
	}
	
	public int getGrLayer() {
		return grLayer;
	}
	
	public void setGrLayer(int grLayer) {
		this.grLayer = grLayer;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserpass() {
		return userpass;
	}
	
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public Timestamp getWdate() {
		return wdate;
	}
	
	public void setWdate(Timestamp wdate) {
		this.wdate = wdate;
	}
	
	public int getFileCount() {
		return fileCount;
	}
	
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	
	public int getMemoCount() {
		return memoCount;
	}
	
	public void setMemoCount(int memoCount) {
		this.memoCount = memoCount;
	}
	
	public Long getImnum() {
		return imnum;
	}
	
	public void setImnum(Long imnum) {
		this.imnum = imnum;
	}
	
	@Override
	public String toString() {
		return "SpDto [num=" + num + ", orNum=" + orNum + ", grNum=" + grNum + ", grLayer=" + grLayer + ", writer=" + writer
				+ ", userid=" + userid + ", userpass=" + userpass + ", title=" + title + ", contents=" + contents + ", hit="
				+ hit + ", wdate=" + wdate + ", fileCount=" + fileCount + ", memoCount=" + memoCount + ", imnum=" + imnum
				+ ", getNum()=" + getNum() + ", getOrNum()=" + getOrNum() + ", getGrNum()=" + getGrNum() + ", getGrLayer()="
				+ getGrLayer() + ", getWriter()=" + getWriter() + ", getUserid()=" + getUserid() + ", getUserpass()="
				+ getUserpass() + ", getTitle()=" + getTitle() + ", getContents()=" + getContents() + ", getHit()="
				+ getHit() + ", getWdate()=" + getWdate() + ", getFileCount()=" + getFileCount() + ", getMemoCount()="
				+ getMemoCount() + ", getImnum()=" + getImnum() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	   
	   
	  
   
}
