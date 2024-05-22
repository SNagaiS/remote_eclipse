package model;

import java.util.List;

import dao.LoginDAO;

public class GetAccountListLogic {
	public List<User> execute(){
		LoginDAO dao = new LoginDAO();
		List<User>userList = dao.findAllList();
		return userList;
	}
}
