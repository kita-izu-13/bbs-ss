package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Form;
import model.FormLogic;

public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String userId = request.getParameter("userId");
				String pass =request.getParameter("pass");
				String name =request.getParameter("name");

				//フォームを追加して追加
				Form form = new Form(userId,pass,name);
				
				//フォーム追加処理の実行
				FormLogic bo = new FormLogic();
				boolean result = bo.execute(form);
				
				
				//ログイン処理の成否によって処理を分岐
				if(result) {	//ログイン成功時
					//セッションスコープに名前を保存
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
				
					//フォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/formResult.jsp");
					dispatcher.forward(request, response);
				}	else {	//ログイン失敗時
					//リダイレクト
					response.sendRedirect("FormServlet");
				}	
					
			}
		}
