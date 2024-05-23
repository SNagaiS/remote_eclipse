package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UsersDAO {

	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/dokoTsubu202";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public User find(User user) {
		User member = new User();

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}

		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT * FROM USERS WHERE NAME = ? AND PASS= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());

			ResultSet rs = pStmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				member = new User(name,pass);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return member;
	}

	public boolean insert(User user) {

		try (Connection conn = DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO USERS(NAME,PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());

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
