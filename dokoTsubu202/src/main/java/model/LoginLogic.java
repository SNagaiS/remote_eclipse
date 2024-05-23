package model;

import dao.UsersDAO;

public class LoginLogic {

	public boolean execute(User user) {

		UsersDAO dao = new UsersDAO();
		User member = dao.find(user);

		if (user.getPass().equals(member.getPass())) {
			return true;
		}
		return false;

	}

}
