package model;

import java.sql.Date;

public class Memo {

		private int id;
		private Date date;
		private String title;
		private String memo;
		
		public Memo() {}
		
		public Memo(Date date, String title, String memo) {
			this.date = date;
			this.title = title;
			this.memo = memo;
		}
		
		public Memo(int id, Date date, String title, String memo) {
			this.id = id;
			this.date = date;
			this.title = title;
			this.memo = memo;
		}
		public int getId() { return id; }
		public Date getDate() { return date; }
		public String getTitle() { return title; }
		public String getMemo() { return memo; }
	}
	