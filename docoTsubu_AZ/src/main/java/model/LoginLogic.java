package model;

import dao.LoginDAO;

public class LoginLogic {
	public Boolean execute(User user) {
		//ログイン判定のコードを記述
		LoginDAO dao = new LoginDAO();
		Boolean flag = dao.findAll(user);
		return flag;
	}
}
