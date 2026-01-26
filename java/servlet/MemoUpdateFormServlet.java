package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemoUpdate;
import model.MemoUpdateLogic;


public class MemoUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String idtext =request.getParameter("id");
		//idをint型へ
		int id=Integer.parseInt(idtext);
		
		String title =request.getParameter("title");
		String memo =request.getParameter("memo");
		String datetext = request.getParameter("date");
		
		//datetext(String型)をDate型に変換　「date」へ	
		Date date = Date.valueOf(datetext);
		
		//メモフォームより更新
		MemoUpdate memoupdate = new MemoUpdate(id,date,title,memo);
		
		//フォーム更新処理の実行
		MemoUpdateLogic bo = new MemoUpdateLogic();
		boolean result = bo.update(memoupdate);
		
		
		//処理の成否によって処理を分岐
		if(result) {	//登録成功時
	
			//リクエストパラメータの取得
			String submit=request.getParameter("submit");
			
			//リクエストスコープに保存
			request.setAttribute("submit", submit);
			
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
