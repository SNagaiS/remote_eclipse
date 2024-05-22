package model;

import dao.LoginDAO;

public class SignUpLogic {
	public void execute(User user) {
		LoginDAO dao = new LoginDAO();
		dao.post(user);
	}
	public boolean idCheck(User user) {
		LoginDAO dao = new LoginDAO();
		 return dao.idCheck(user);
	}
}
