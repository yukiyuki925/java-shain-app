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
 * Servlet implementation class ShainUpdate
 */
@WebServlet("/ShainUpdate")
public class ShainUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShainUpdate() {
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
			
			//更新社員をセットする
			request.setAttribute("shainBean", shainBean);
			
			//update.jspへ転送
			request.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(request, response);
			
		} catch (NumberFormatException | SQLException | NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
