package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getでアクセスされた場合、ログイン画面へリダイレクトする
		response.sendRedirect("/docoTsubu_AZ/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name1 = request.getParameter("name");
		String pass1 = request.getParameter("pass");
		
		//Userインスタンス（ユーザー情報）の生成
		User user = new User(name1,pass1);
		
		//サーバに保存されている情報と一致するか判定
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);
		
		//ログイン成功時の処理
		if(isLogin) {
			//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user); 
		}
		
		//ログイン成功画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}
