package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemoAdd;
import model.MemoAddLogic;

public class MeomFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("メモ追加前");
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String datetext = request.getParameter("date");
		String title =request.getParameter("title");
		String memo =request.getParameter("memo");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=(Date) dateFormat.parse(datetext, null);

		//メモフォームを追加して追加
		MemoAdd memoadd = new MemoAdd(date,title,memo);
		
		//フォーム追加処理の実行
		MemoAddLogic bo = new MemoAddLogic();
		boolean result = bo.execute(memoadd);
		
		//処理の成否によって処理を分岐
		if(result) {	//登録成功時
	
			//フォワード
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoResult.jsp");
			dispatcher.forward(request, response);
		}	else {	//失敗時
			//リダイレクト
			response.sendRedirect("MemoFormServlet");
		}

	}
}
