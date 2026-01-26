package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.MemosDeleteDAO;
import dao.MemosOrderDAO;
import dao.MemosUpdateDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMemoListLogic;
import model.Memo;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//メモリストを取得して、リクエストスコープに保存
		GetMemoListLogic getMemoListLogic=new GetMemoListLogic();
		List<Memo> memoList = getMemoListLogic.execute();
		request.setAttribute("memoList", memoList);
		
		//ログインしているか確認するため　セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("userId");

		if(loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("index.jsp");
		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
		// ボタンの選択された値を取得
		String select= request.getParameter("select");
		
		RequestDispatcher dispatcher;
		
		//メモリストを取得して、リクエストスコープに保存
		GetMemoListLogic getMemoListLogic=new GetMemoListLogic();
		List<Memo> memoList = getMemoListLogic.execute();
		request.setAttribute("memoList", memoList);
		
		//ログインしているか確認するため　セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("userId");
		
		//セレクトが選択された場合（追加・更新・削除）
		if(select != null) {
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
					//それぞれのフォワード先へ
				switch(select) {
				case "追加":
					//フォワード
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoform.jsp");
					dispatcher.forward(request, response);
					break;
				case "更新":
					//フォワード
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
					request.setAttribute("update", select);
					dispatcher.forward(request, response);
					break;
				//"削除"
				default:
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
					request.setAttribute("delete", select);
					dispatcher.forward(request, response);
					break;
				}
			}
		}
	
		// 更新ボタンの選択された値(id)を取得
		String update= request.getParameter("update");
		
		//更新ボタンが選択された場合
			if(update != null) {
		
			//メモフォームへフォワード（選択したメモを取得して表示）
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
					
					//update(id)を、int型へ
					int id=Integer.parseInt(update);
					
					//idをリクエストスコープに保存
					request.setAttribute("id", id);
					
					//update(id）により、該当メモ部分のみ取得して、リクエストスコープに保存
					MemosUpdateDAO memoUpdateDAO=new MemosUpdateDAO();
					List<Memo> memoUpdate = memoUpdateDAO.update(id);
					
					request.setAttribute("memoUpdate", memoUpdate);
					
					//フォワード
					dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoform.jsp");
					dispatcher.forward(request, response);
			}
		}
			
			
		// 削除ボタンの選択された値を取得
		String deletecheck=request.getParameter("delete");
		
		//削除ボタンが選択された場合
			if(deletecheck != null) {					
		
			//メモフォームへフォワード（選択したメモを取得して表示）
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
				
				//リストを準備　
				String[] deleteList=request.getParameterValues("delete");
				List<Integer> delete=new ArrayList<>();	
						
				for (String idtext : deleteList) {
					//update(id)を、int型へ
					int id=Integer.parseInt(idtext);
					delete.add(0,id);	//先頭に追加
				}
				
				//idの行を削除する（DAO）
				MemosDeleteDAO memodeleteDAO = new MemosDeleteDAO();
				List<Integer> memoDelete = memodeleteDAO.delete(delete);
				
				//リクエストスコープに保存
				request.setAttribute("delete", deletecheck);
				
				//フォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memoResult.jsp");
				dispatcher.forward(request, response);
			}
		}
		
			// 昇順または降順ボタンの選択された値を取得
			String order=request.getParameter("order");
			// ラジオボタンの選択された値を取得
			String radio=request.getParameter("radio");
			
			//削除ボタンが選択された場合
			if(order != null) {				
		
			//メモフォームへフォワード（選択したメモを取得して表示）
			if(loginUser == null) { //ログインしていない場合
				//リダイレクト
				response.sendRedirect("index.jsp");
			} else { //ログイン済みの場合
				
				//orderをもとに並べ替えたメモリスト取得して、セッションスコープに保存
				MemosOrderDAO memoOrderDAO=new MemosOrderDAO();
				List<Memo> memoOrder = memoOrderDAO.order(order,radio);
				session.setAttribute("memoOrder", memoOrder);
				
				//orderの値とradioの値をセッションスコープに保存
				session.setAttribute("order", order);
				session.setAttribute("radio", radio);							
				
				//フォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/memo.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}