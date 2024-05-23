package model;

import dao.UsersDAO;

public class SignUpLogic {
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		System.out.println(dao.insert(user));
		boolean result = dao.insert(user);
		return result;
	}


}
