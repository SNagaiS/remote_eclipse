package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EditAccountLogic;
import model.GetAccountListLogic;
import model.User;


@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//アカウントリストを取得して、アプリケーションスコープに保存
		GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
		List<User> userList = getAccountListLogic.execute();
		
		ServletContext application = this.getServletContext();
		application.setAttribute("userList", userList);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAccount.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		int flag = Integer.parseInt(request.getParameter("flag"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		//Userインスタンス（ユーザー情報）の生成
		User user = new User(no,name,pass,flag);
		
		//アカウントの内容が変更されているかを確認
		EditAccountLogic editAccountLogic = new EditAccountLogic();
		String msg = editAccountLogic.acoountChangeCheck(user);
		
		if(msg.equals("アカウント情報を変更しました")) {
			//アカウント情報を更新
			editAccountLogic.upDate(user);
			//アプリケーションスコープにアカウントリストを保存
			
			//アカウントリストを取得して、アプリケーションスコープに保存
			GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
			List<User> userList = getAccountListLogic.execute();
			
			ServletContext application = this.getServletContext();
			application.setAttribute("userList", userList);
			
			//リクエストスコープにメッセージを保存
			request.setAttribute("msg", msg);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAccount.jsp");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("msg", msg);
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAccount.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
