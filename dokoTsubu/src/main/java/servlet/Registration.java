package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import dao.RegistrationDAO;
import model.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
		dispatcher.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//registration.jspからpostで来た物を取得、RegistrationDAOへ送る
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String address = request.getParameter("address");

		User user = new User(name, pass, address);
		RegistrationDAO registrationDao = new RegistrationDAO();
		boolean isRegistration = registrationDao.execute(user);

		if (isRegistration) {//登録成功の場合、LoginDAOにてUID取得
			User logUser = new User(name, pass, 0);
			LoginDAO loginDAO = new LoginDAO();
			boolean isLog = loginDAO.execute(logUser);
			if(isLog) {//UID取得成功したら、セッションスコープにloginUser保存
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", logUser);

			}
		}
		//新規登録結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrationResult.jsp");
		dispatcher.forward(request, response);

	}
}