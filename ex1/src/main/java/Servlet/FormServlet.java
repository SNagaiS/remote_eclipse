package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String help = request.getParameter("help");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String hoge = request.getParameter("site");
		
		//リクエストパラメータをチェック
		String errorMsg = "";
		if(name==null||name.length()==0) {
			errorMsg += "名前が入力されていません<br>";
		}
		if(gender==null||gender.length()==0) {
			errorMsg += "性別が選択されていません";
		}else {
			if(gender.equals("0")){
				gender = "男性";
			}else if(gender.equals("1")){
				gender = "女性";
			}
		}
		
		//errorMsgがあれば表示、無ければ「登録しました」表示
		String msg = name + "さん(" + gender + ")を登録しました。";
		if(errorMsg.length()!=0) {
			msg = errorMsg;
		}
		
		
		//html表示
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<neta charset=\"UTF-8\">");
		out.println("<title>ユーザー登録結果</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>" + msg  + "</p>");
		out.println("</body>");
		out.println("</html>");
	
	}

}
