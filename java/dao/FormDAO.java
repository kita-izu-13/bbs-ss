package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Account;
import model.Form;

public class FormDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/memoapp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Form insert(Form form) {
		Account account = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			//INSERT文を準備
			String sql = "INSERT INTO ACCOUNTS (USER_ID, PASS, NAME) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, form.getUserId());
			pStmt.setString(2, form.getPass());
			pStmt.setString(3, form.getName());
			
			//
			int result =pStmt.executeUpdate();
			pStmt.close();
			conn.close();

		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return form;
	}
}