package test;

import model.SignUpLogic;
import model.User;

public class SignupLogicTest {

	public static void main(String[] args) {
		testExecuteOK();
		testExecuteNG();

	}
	private static void testExecuteOK() {
		User login = new User("asaka","1234");
		SignUpLogic bo = new SignUpLogic();
		boolean result = bo.execute(login);
		if (result) {
			System.out.println("testExecuteOK:成功しました");
		} else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}
	private static void testExecuteNG() {
		User login = new User("minato","1234");
		SignUpLogic bo = new SignUpLogic();
		boolean result = bo.execute(login);
		if (result) {
			System.out.println("testExecuteNG:成功しました");
		} else {
			System.out.println("testExecuteNG:失敗しました");
		}
		
	}



}
