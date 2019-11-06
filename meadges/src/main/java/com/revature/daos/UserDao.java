package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
	public static User extractUser(ResultSet RS) throws SQLException {
		User ans = new User();
		ans.setErs_users_id(RS.getInt("ers_users_id"));
		ans.setErs_password(RS.getString("ers_password"));
		ans.setErs_username(RS.getString("ers_username"));
		ans.setUser_first_name(RS.getString("user_first_name"));
		ans.setUser_last_name(RS.getString("user_last_name"));
		ans.setUser_email(RS.getString("user_email"));
		ans.setUser_role_id(RS.getInt("user_role_id"));
		ans.setSalt(RS.getString("salt"));
		return ans;
	}
	public static User getUserByUserID(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_users natural join salts where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				//System.out.println(ans);
				return extractUser(RS);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static User getUserByUserName(String userName) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_users natural join salts where ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				return extractUser(RS);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
