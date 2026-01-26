package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Memo;

public class MemosOrderDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memoapp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Memo> order(String order,String radio) {		
		
		//メモリスト作成
		List<Memo> memoList=new ArrayList<>();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			String sql="";
			
		//orderが昇順か降順か判定
			if(order.equals("昇順")) {
				
				//SELECT文を準備
				sql = "SELECT * FROM MEMOS ORDER BY "+radio+" ASC;";			
			}else if(order.equals("降順")){
				
				//SELECT文を準備
				sql = "SELECT * FROM MEMOS ORDER BY "+radio+" DESC;";				
			}
			
			//SELECT文実行
			PreparedStatement pStmt= conn.prepareStatement(sql);	
			ResultSet rs =pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				Date date=rs.getDate("DATE");
				String title=rs.getString("TITLE");
				String memo=rs.getString("MEMO");
				Memo memos=new Memo(id,date,title,memo);
				memoList.add(memos);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return memoList;
	}
}
