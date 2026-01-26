package model;

import java.util.List;

public class MemoDeleteLogic {
	public void delete(MemosDelete id, List<MemosDelete> deleteList) {
		deleteList.add(0, id);	//先頭に追加
	}
}
