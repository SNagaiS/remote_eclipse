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
import javax.servlet.http.HttpSession;

import model.GetAccountListLogic;
import model.SignUpLogic;
import model.User;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//新規登録画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String msg = "";
		
		//セッションスコープからログインしているかを取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		//ユーザーインスタンスの作成
		User user = new User(name,pass);
		
		//ユーザーIDが重複していないかチェック
		SignUpLogic signUpLogic = new SignUpLogic();
		if(signUpLogic.idCheck(user)) {
			//ユーザ情報をサーバに保存
			signUpLogic.execute(user);
			
			//管理者の場合
			if(loginUser != null) {
				msg ="新規登録完了しました";
				request.setAttribute("msg", msg);
				
				//アカウントリストを取得して、アプリケーションスコープに保存
				GetAccountListLogic getAccountListLogic = new GetAccountListLogic();
				List<User> userList = getAccountListLogic.execute();
				
				ServletContext application = this.getServletContext();
				application.setAttribute("userList", userList);
				
				//アカウント一覧画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAccount.jsp");
				dispatcher.forward(request, response);
			}
				
			//新規登録完了画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUpResult.jsp");
			dispatcher.forward(request, response);
		}else { //ユーザーIDが重複していた場合
			
			//リクエストスコープにエラーメッセージを保存
			msg = "このユーザーIDは既に登録されています";
			request.setAttribute("msg", msg);
			
			if(loginUser != null) {
				//アカウント一覧画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminAccount.jsp");
				dispatcher.forward(request, response);
			}else {
				//新規登録画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
