package model;



import java.util.List;

import dao.MemosDAO;

public class GetMemoListLogic {
	public List<Memo> execute(){
		MemosDAO dao = new MemosDAO();
		List<Memo> memoList = dao.findAll();
		return memoList;
	}
}
