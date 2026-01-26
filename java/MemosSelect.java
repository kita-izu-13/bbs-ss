import java.util.List;

import dao.MemosDAO;
import model.Memo;

public class MemosSelect {
	public static void main(String[] args) {
		//MEMOテーブルの全レコードを取得
		MemosDAO empDAO = new MemosDAO();
		List<Memo> empList = empDAO.findAll();
		
		//取得したレコードの内容を出力
		for (Memo emp : empList) {
			System.out.println("ID:" + emp.getId());
			System.out.println("日付:" + emp.getDate());
			System.out.println("タイトル:" + emp.getTitle());
			System.out.println("メモ:" + emp.getMemo()+ "\n");
		}
	}
}
