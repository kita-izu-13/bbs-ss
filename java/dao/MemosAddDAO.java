package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Memo;
import model.MemoAdd;

public class MemosAddDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memoapp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public MemoAdd insert(MemoAdd memoadd) {
		Memo memo = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				
				//SELECT文を準備
				String sqlselect = "SELECT * FROM MEMOS";
				PreparedStatement pStmtselect = conn.prepareStatement(sqlselect);
				
				//SELECT文を実行し、結果表を取得
				ResultSet rsselect = pStmtselect.executeQuery();
				
				//SELECT文の結果をもとにid取得・付与id番号決定（最大id+1）
				int id=0;
				while(rsselect.next()) {
					if(id<rsselect.getInt("ID")) {
						id=rsselect.getInt("ID");
					}
				}			
				int newid=++id;		
			
			//INSERT文を準備
			String sql = "INSERT INTO MEMOS (ID,DATE, TITLE, MEMO) VALUES("+newid+",?,?,?)";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
				
			pStmt.setDate(1, memoadd.getDate());
			pStmt.setString(2, memoadd.getTitle());
			pStmt.setString(3, memoadd.getMemo());
			System.out.println(sql);
			
			int result =pStmt.executeUpdate();
			pStmt.close();
			conn.close();

		}catch(SQLException e) {
			e.printStackTrace();
				System.out.println("データベース接続エラーキャッチ");
			return null;
		}
		return memoadd;
	}
}
