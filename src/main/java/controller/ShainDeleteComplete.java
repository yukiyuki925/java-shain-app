package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ShainLogic;

/**
 * Servlet implementation class ShainDeleteComplete
 */
@WebServlet("/ShainDeleteComplete")
public class ShainDeleteComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShainDeleteComplete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//社員ロジックの作成
		ShainLogic shainLogic = new ShainLogic();
		
		try {
			//社員の削除
			shainLogic.deleteShain(Integer.parseInt(request.getParameter("id")));
			
			//ShainIndexにリダイレクト
			response.sendRedirect("ShainIndex");
			
		} catch (NumberFormatException | SQLException | NamingException e) {
			e.printStackTrace();
			request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
