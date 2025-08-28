package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.ShainBean;

public class ShainLogic {
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
