package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MuttersDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//これまでのつぶやきの名前、タイトル、内容、idを
	//DBから取得するクラス
	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();
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
			String sql = "SELECT * FROM MUTTERS ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//muttersテーブルのnameとusersテーブルのnameが一致するUIDを取得
			String sql2 = "SELECT UID FROM USERS INNER JOIN MUTTERS ON USERS.NAME = MUTTERS.NAME";
			PreparedStatement pStmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pStmt2.executeQuery();
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			//結果表に格納されたレコードの内容を
			//インスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				if (rs.getInt("DELETE_FLAG") == 0) {
					int id = rs.getInt("ID");
					String userName = rs.getString("NAME");
					String title = rs.getString("TITLE");
					String text = rs.getString("TEXT");
					String time = rs.getString("TIME");
					Mutter mutter = new Mutter(id, userName, title, text, time);
					mutterList.add(mutter);
				} else {
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	//入力されたつぶやきと件名、名前をDBに入れ込むクラス
	public boolean create(Mutter mutter) {
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
			//INSERT文の準備(idは自動連番なので指定しなくてよい)
			String sql = "INSERT INTO MUTTERS (NAME, TITLE, TEXT, TIME) VALUES(?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「？」に使用する値を設定してSQL文を完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getTitle());
			pStmt.setString(3, mutter.getText());
			pStmt.setString(4, mutter.getTime());

			//INSERT文を実行(resultには追加された行数が入る)
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
