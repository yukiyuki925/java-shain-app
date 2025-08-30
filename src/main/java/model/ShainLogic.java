package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jakarta.servlet.http.HttpServletRequest;

import beans.ShainBean;

public class ShainLogic {
	
	//社員情報を更新
	public void updateShain(ShainBean shainBean) throws SQLException, NamingException {
		
		//社員を更新するSQL
		String sql = "update shain set name=?, sei=?, nen=?, address=? where id=?";
		try(Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			//パラメータをSQLにセット
			pstmt.setString(1, shainBean.getName());
			pstmt.setString(2, shainBean.getSei());
			pstmt.setInt(3, shainBean.getNen());
			pstmt.setString(4, shainBean.getAddress());
			pstmt.setInt(5, shainBean.getId());
			
			//SQL文をコンソールに表示
			System.out.println(pstmt.toString());
			//SQLを実行
			pstmt.executeUpdate();
		}
	}

	public ShainBean getShainBean(int id) throws SQLException, NamingException {
		ShainBean shainBean = new ShainBean();

		//社員を取得するSQL
		String sql = "select id, name, sei, nen, address from shain where id=?";

		//SQL実行の準備
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			System.out.println(pstmt.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				shainBean.setId(id);
				shainBean.setName(rs.getString("name"));
				shainBean.setSei(rs.getString("sei"));
				shainBean.setNen(rs.getInt("nen"));
				shainBean.setAddress(rs.getString("address"));
			}
		}
		return shainBean;
	}

	//リクエストから社員Beanの作成
	public ShainBean getShainBean(HttpServletRequest request) {
		//社員Beanの初期化
		ShainBean shainBean = new ShainBean();
		//リクエストから社員Beanの作成
		shainBean.setId(Integer.parseInt(request.getParameter("id")));
		shainBean.setName(request.getParameter("name"));
		shainBean.setSei(request.getParameter("sei"));
		shainBean.setNen(Integer.parseInt(request.getParameter("nen")));
		shainBean.setAddress(request.getParameter("address"));
		//作成した社員Beanを戻す
		return shainBean;
	}

	//社員を登録
	public void insertShain(ShainBean shainBean) throws SQLException, NamingException {
		//社員を登録するSQL
		String sql = "insert into shain(id, name, sei, nen, address) values(?,?,?,?,?);";
		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			//パラメータをSQLにセット
			pstmt.setInt(1, shainBean.getId());
			pstmt.setString(2, shainBean.getName());
			pstmt.setString(3, shainBean.getSei());
			pstmt.setInt(4, shainBean.getNen());
			pstmt.setString(5, shainBean.getAddress());
			//SQL文を表示
			System.out.println(pstmt.toString());
			//SQL実行
			pstmt.executeUpdate();
		}
	}

	//社員を取得
	public ArrayList<ShainBean> getAllShain() throws SQLException, NamingException {

		//ArrayListの初期化
		ArrayList<ShainBean> shainList = new ArrayList<ShainBean>();

		String sql = "select id, name, sei, nen, address from shain";

		try (Connection con = ConnectionBase.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			System.out.println(pstmt.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				//社員Beanの初期化
				ShainBean shainBean = new ShainBean();
				//取得した値を社員Beanにセット
				shainBean.setId(rs.getInt("id"));
				shainBean.setName(rs.getString("name"));
				shainBean.setSei(rs.getString("sei"));
				shainBean.setNen(rs.getInt("nen"));
				shainBean.setAddress(rs.getString("address"));
				//リストに社員Beanの追加
				shainList.add(shainBean);
			}
		}
		return shainList;
	}
}
