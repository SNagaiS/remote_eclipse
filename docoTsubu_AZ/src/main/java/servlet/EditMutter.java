package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CounterEvaluation;
import model.DeleteMutterLogic;
import model.EditMutterLogic;
import model.GetMutterListLogic;
import model.Mutter;
import model.User;


@WebServlet("/EditMutter")
public class EditMutter extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String hoge = request.getParameter("hoge");
		int no = Integer.parseInt(request.getParameter("no"));
		
		//インスタンスの準備
		DeleteMutterLogic deleteMutterLogic = new DeleteMutterLogic();
		CounterEvaluation counterevalution = new CounterEvaluation();
		
		//削除するボタンが押された場合
		switch(hoge) {
		case "delete":
			//削除処理
			deleteMutterLogic.execute(no);
			break;
		case "alldelete":
			//全削除
			deleteMutterLogic.allDelete();
			break;
		case "good":
			//良いねカウントを追加
			counterevalution.goodCountUp(no);
			break;
		case "bad":
			//悪いねカウントを追加
			counterevalution.badCountUp(no);
			break;
		}
		
		//つぶやきりすとを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
			
		//セッションスコープからログイン中のユーザーを取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
			
		if(loginUser.getName().equals("admin")) {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminMain.jsp");
			dispatcher.forward(request, response);
		}else {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		String title = request.getParameter("title");
		String edit = request.getParameter("edit");
		int no = Integer.parseInt(request.getParameter("no"));
		//編集された時間を取得
		Date date1 = new Date();
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		date = sdf.format(date1);
		
		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		//変更内容が記述されているかチェック
		Mutter mutter = new Mutter(no,loginUser.getName(), text, title, date,edit);
		EditMutterLogic editMutterLogic = new EditMutterLogic();
		
		if (editMutterLogic.editCheck(mutter)) {
			//つぶやきを更新
			editMutterLogic.upDate(mutter);
			//メッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "変更が完了しました");
		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "タイトル・本文ともに同じ内容でした。もう一度やり直してください");
		}
		
		//つぶやきりすとを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		if(loginUser.getName().equals("admin")) {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminMain.jsp");
			dispatcher.forward(request, response);
		}else {
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}	
	}

}
