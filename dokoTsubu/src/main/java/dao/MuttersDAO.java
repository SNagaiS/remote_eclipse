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
			String sql = "SELECT U.ID, M.NUM, M.NAME, M.TITLE, M.TEXT, M.TIME, M.DELETE_FLAG FROM MUTTERS AS M INNER JOIN USERS AS U ON U.NAME = M.NAME ORDER BY NUM DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//SELECTを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			//結果表に格納されたレコードの内容を
			//インスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				if (rs.getInt("DELETE_FLAG") == 0) {
					int uId = rs.getInt("ID");
					int delete = rs.getInt("DELETE_FLAG");
					int num = rs.getInt("NUM");
					String userName = rs.getString("NAME");
					String title = rs.getString("TITLE");
					String text = rs.getString("TEXT");
					String time = rs.getString("TIME");
					Mutter mutter = new Mutter(uId, delete, num, userName, title, text, time);
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

	//入力されたつぶやきと件名、名前、IDをDBに入れ込むクラス
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
			String sql = "INSERT INTO MUTTERS (NAME, TITLE, TEXT, TIME, UID) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「？」に使用する値を設定してSQL文を完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getTitle());
			pStmt.setString(3, mutter.getText());
			pStmt.setString(4, mutter.getTime());
			pStmt.setInt(5, mutter.getUId());

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
