package model;

import dao.MemosAddDAO;

public class MemoAddLogic {
	public boolean execute(MemoAdd memoadd) {
		System.out.println(memoadd);
		MemosAddDAO dao = new MemosAddDAO();
		MemoAdd memoadd2 = dao.insert(memoadd);
		return memoadd2 != null;
	}

}
