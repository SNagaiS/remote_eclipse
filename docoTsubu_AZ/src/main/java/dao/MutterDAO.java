package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/docoTsubuLogin";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT * FROM MUTTER WHERE IS_EXIST = 0 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			
			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int no = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String date = rs.getString("DATE");
				int good = rs.getInt("GOOD");
				int bad = rs.getInt("BAD");
				String edit = rs.getString("EDIT");
				Mutter mutter = new Mutter(no, userName, text, title, date, good, bad, edit);
				mutterList.add(mutter);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	public boolean create(Mutter mutter) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//INSERT文の準備
			String sql = "INSERT INTO MUTTER(NAME,TITLE,TEXT,DATE) VALUES(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1,mutter.getUserName());
			pStmt.setString(2,mutter.getTitle());
			pStmt.setString(3,mutter.getText());
			pStmt.setString(4,mutter.getDate());
			
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
	
	//削除処理
	public void deleteMutter(int no) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "UPDATE MUTTER SET IS_EXIST = 1 WHERE ID =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,no);
			
			//INSERT文を実行
			int result = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//全削除処理
	public void allDeleteMutter() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "UPDATE MUTTER SET IS_EXIST = 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文を実行
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//更新前重複処理
	public boolean editCheck(Mutter mutter) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "SELECT * FROM MUTTER WHERE ID=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,mutter.getNo());
			ResultSet rs = pStmt.executeQuery();
			
			
			String title = "";
			String text = "";
			//SELECT文の結果からtitleとtextを取得
			while(rs.next()) {
				title = rs.getString("TITLE");
				text = rs.getString("TEXT");
			}
			
			//titleとtextが変更されているか評価
			if(title.equals(mutter.getTitle()) && text.equals(mutter.getText())) {
				return false;
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//更新
	public boolean upDate(Mutter mutter) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "UPDATE MUTTER SET TITLE = ?, TEXT = ?, DATE = ?,EDIT = ? WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,mutter.getTitle());
			pStmt.setString(2,mutter.getText());
			pStmt.setString(3,mutter.getDate());
			pStmt.setString(4,"編集済み");
			pStmt.setInt(5,mutter.getNo());
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
	
	//goodカウントを増加
	public void goodCountUp(int no) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "UPDATE Mutter SET GOOD= GOOD + 1 WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,no);
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//badカウントを追加
	public void badCountUp(int no) {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"JDBCドライバを読み込めませんでした");
		}
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "UPDATE Mutter SET BAD= BAD + 1 WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,no);
			int result = pStmt.executeUpdate();
			
			if(result != 1) {
				System.out.println(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//特定のつぶやきを取得
	//更新前重複処理
		public Mutter postMutter(int no) {
			Mutter mutter = null;
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException(
						"JDBCドライバを読み込めませんでした");
			}
			try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
				String sql = "SELECT * FROM MUTTER WHERE ID=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1,no);
				ResultSet rs = pStmt.executeQuery();
				
				if(!rs.next()){
					System.out.println("失敗");
				}else {
						String userName = rs.getString("NAME");
						String title = rs.getString("TITLE");
						String text = rs.getString("TEXT");
						String date = rs.getString("DATE");
						int good = rs.getInt("GOOD");
						int bad = rs.getInt("BAD");
						String edit = rs.getString("EDIT");
						mutter = new Mutter(no, userName, text, title, date, good, bad, edit);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return mutter;
		}
}
