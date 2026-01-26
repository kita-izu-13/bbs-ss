package model;

import dao.MemosDAO;

public class Memos {

	public void execute(Memo memo) {
		MemosDAO dao = new MemosDAO();
		dao.findAll();
	}
}