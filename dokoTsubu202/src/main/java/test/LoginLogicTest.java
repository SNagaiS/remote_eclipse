package test;

import model.LoginLogic;
import model.User;

public class LoginLogicTest {

	public static void main(String[] args) {
		testExecuteOK();
		testExecuteNG();

	}
	
	public static void testExecuteOK() {
		User login = new User("minato","1234");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if (result) {
			System.out.println("testExecuteOK:成功しました");
		} else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}
	public static void testExecuteNG() {
		User login = new User("minato","12345");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if (!result) {
			System.out.println("testExecuteNG:成功しました");
		} else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}

}
