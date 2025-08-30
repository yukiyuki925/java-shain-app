package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.ShainBean;
import model.ShainLogic;

/**
 * Servlet implementation class ShainDelete
 */
@WebServlet("/ShainDelete")
public class ShainDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShainDelete() {
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
			//社員Beanの作成
			ShainBean shainBean = shainLogic.getShainBean(Integer.parseInt(request.getParameter("id")));
			
			//削除社員をセットする
			request.setAttribute("shainBean", shainBean);
			
			//delete.jspへ転送
			request.getRequestDispatcher("/WEB-INF/view/delete.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
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
