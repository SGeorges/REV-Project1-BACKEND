package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Credentials;
import com.revature.util.ConnectionUtil;

public class BaseDao {

	/* Take in credentials object from BaseService.
	 * If credentials.username is in database it'll return
	 * the password from the database to the server, which
	 * will then be returned to BankService.
	 */
	public String getPassword(Credentials credentials) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT ers_password FROM ers_users WHERE ers_username = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, credentials.getUsername());				statement.setString(2, credentials.getPassword());
				
			ResultSet resultSet = statement.executeQuery();
			String password = new String();
			
			while(resultSet.next()) {
				password = resultSet.getString("ers_password");
			}
			
			return password;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
