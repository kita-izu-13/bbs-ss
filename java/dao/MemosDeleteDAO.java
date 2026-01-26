package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemosDeleteDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memoapp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Integer> delete(List<Integer> memodelete) {
		System.out.println(memodelete);
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql="";
			
			//idがある分だけ繰り返す
			while(memodelete!=null) {
				for(int id:memodelete) {

				//DELETE文を準備
				sql = "DELETE FROM MEMOS WHERE ID=?;";
				PreparedStatement pStmt= conn.prepareStatement(sql);
				
				pStmt.setInt(1, id);

				int result =pStmt.executeUpdate();
				
				pStmt.close();	
				}
			conn.close();
			break;
				
			}	
		}catch(SQLException e) {
			e.printStackTrace();
				System.out.println("データベース接続エラーキャッチ");
			return null;
		}
		return memodelete;
	}

}
