package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		/*		ServletContext application = this.getServletContext();
				List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		
				if (mutterList == null) {
					mutterList = new ArrayList<>();
					application.setAttribute("mutterList", mutterList);
				}
		*/
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("WEB-INF/jsp/login.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}

	}

	//	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String text = request.getParameter("text");

		if (text != null && text.strip().length() != 0) {
			/*	ServletContext application = this.getServletContext();
				List<Mutter> mutterList =
						(List<Mutter>)application.getAttribute("mutterList");
			*/
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//			Mutter mutter = new Mutter(loginUser.getName(),text);
			/*			Mutter mutter = new Mutter(loginUser.getName(),title,text);
						PostMutterLogic postMutterLogic = new PostMutterLogic();
						postMutterLogic.execute(mutter, mutterList);
//						application.setAttribute("mutterList", mutterList);
			*/
			//	
			Mutter mutter = new Mutter(loginUser.getName(), title, text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);

		} else {
			request.setAttribute("errorMsg", "つぶやきが未入力です");
		}

		//
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
}
