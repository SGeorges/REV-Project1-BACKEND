package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
 
	public static User getUserByUserName(String userName) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				String pass=RS.getString("pass");
				boolean isAdmin=RS.getBoolean("admin");
				User ans=new User(userName,pass,isAdmin);
				//System.out.println(ans);
				return ans;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
