package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class LoginDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/docoTsubuLogin";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	//新規登録
	public boolean post(User user) {
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			//名前とパスワードを保存
			String sql = "INSERT INTO LOGIN(NAME,PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//ログインリストを取得
	public List<User> findAllList(){
		List<User> userList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT * FROM LOGIN";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int no = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				int flag = rs.getInt("IS_EXIST");
				User user = new User(no,name,pass,flag);
				userList.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}
	
	//ログイン情報を取得(ユーザ名・パスワードチェック）
	public boolean findAll(User user) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			
			String sql = "SELECT NAME,PASS FROM LOGIN WHERE IS_EXIST = 0 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				if(user.getName().equals(name)) {
					if(user.getPass().equals(pass)) {
						return true;
					}
				}
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//ユーザ名重複チェック
	public boolean idCheck(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT * FROM LOGIN WHERE NAME=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user.getName());
			
			ResultSet rs = pStmt.executeQuery();
			
			
			int i = 0;
			while(rs.next()) {
				i++;
			}
			if(i != 0) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//登録しようとしている内容と変更があるかどうかをチェック
	public String acoountChangeCheck(User user) {
		String msg ="";
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT * FROM LOGIN WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,user.getNo());
			ResultSet rs = pStmt.executeQuery();
			
			String name = "";
			String pass = "";
			int flag = 0;
			
			while(rs.next()) {
				name = rs.getString("NAME");
				pass = rs.getString("PASS");
				flag = rs.getInt("IS_EXIST");
			}
			
			//アカウント情報が変更されているかを確認
			if(name.equals(user.getName()) && pass.equals(user.getPass()) && flag == user.getFlag()) {
				return msg = "内容が変更されていません";
			}else if(!name.equals(user.getName())) {
				//IDが重複くしていないかチェック
				if(!idCheck(user)) {
					return msg = "このユーザー名は既に登録されています";
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return msg = "処理エラーです";
		}
		return msg = "アカウント情報を変更しました";
	}
	
	//登録内容を更新
	public boolean upDate(User user) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "UPDATE LOGIN SET NAME = ?, PASS = ?, IS_EXIST = ? WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,user.getName());
			pStmt.setString(2,user.getPass());
			pStmt.setInt(3,user.getFlag());
			pStmt.setInt(4,user.getNo());
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				System.out.println(result);
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
