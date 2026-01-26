package model;

import dao.MemosUpdateformDAO;

public class MemoUpdateLogic {
	public boolean update(MemoUpdate memoupdate){
		MemosUpdateformDAO dao = new MemosUpdateformDAO();
		MemoUpdate memoupdate2 = dao.update(memoupdate);
		return memoupdate2 !=null;
	}
}
