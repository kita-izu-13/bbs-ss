package model;

import java.sql.Date;

public class MemoAdd {
	private Date date;
	private String title;
	private String memo;
	
	public MemoAdd(Date date,String title, String memo) {
		this.date = date;
		this.title = title;
		this.memo = memo;
	}
	
	public Date getDate() { return date; }
	public String getTitle() { return title; }
	public String getMemo() { return memo; }
}
