package model;

import dao.LoginDAO;

public class EditAccountLogic {
	LoginDAO dao = new LoginDAO();
	
	public String acoountChangeCheck(User user) {
		String msg = dao.acoountChangeCheck(user);
		return msg;
		
	}
	
	public void upDate(User user) {
		dao.upDate(user);
	}
}
