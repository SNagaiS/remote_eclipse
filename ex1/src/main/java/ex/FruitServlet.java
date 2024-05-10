package ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FruitServlet
 */
@WebServlet("/FruitServlet")
public class FruitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FruitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*	各種スコープ用  Fruit fruit = new Fruit("いちご", 700);
		*/	
		Fruit fruitRequest = new Fruit("ぶどう", 500);
		Fruit fruitSession = new Fruit("バナナ", 150);
		Fruit fruitApplication = new Fruit("マンゴー", 3000);
		/*		リクエストスコープ
		 * 		request.setAttribute("Fruit", fruit);
		*/
		/*セッションスコープ
		 * HttpSession session = request.getSession();
		session.setAttribute("fruit", fruit);
		*/
		/*アプリケーションスコープ */
		request.setAttribute("fruit", fruitRequest);
		HttpSession session = request.getSession();
		session.setAttribute("fruit", fruitSession);
		  ServletContext application = this.getServletContext();
		application.setAttribute("fruit", fruitApplication);
				RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/ex/fruit.jsp");
		dispatcher.forward(request, response);
	}

}
