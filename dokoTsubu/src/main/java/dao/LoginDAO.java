package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class LoginDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//ログイン機能のサブクラス
	public boolean execute(User user)  {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT * FROM USERS";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			//結果表に格納されたレコードの内容を
			//userセッションと比較、合致でtrueを返す
			int namePassResult = 0;
			while (rs.next()) {
				if(rs.getString("NAME").equals(user.getName())
						&& rs.getString("PASS").equals(user.getPass())) {
					user.setUID(rs.getInt("ID"));
					namePassResult +=1;
					break;
				}
			}
			if(namePassResult == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
