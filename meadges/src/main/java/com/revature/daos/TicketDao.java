package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.util.ConnectionUtil;

public class TicketDao {
	public static Integer createTicket(double amount,String type,String description
			,String userName) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Insert into expenses(amount,type,description,username) values(?, ?, ?, ?) returning id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, type);
			ps.setString(3, description);
			ps.setString(4, userName);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				Integer ans =RS.getInt("id");
				return ans;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Ticket getTicketById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from expenses where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				double amount=RS.getDouble("amount");
				String type=RS.getString("type");
				String description=RS.getString("description");
				String username=RS.getString("username");
				String status=RS.getString("status");
				Ticket ans=new Ticket(id,amount,type,description,username,status);
				return ans;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<Integer> getAllTicketIDs(){
		List<Integer> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select id from expenses";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet RS =ps.executeQuery();
			while(RS.next()) {
				ans.add(RS.getInt("id"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;
	}
	public static List<Integer> getAllTicketIDsForUserName(String userName){
		List<Integer> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select id from expenses where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet RS =ps.executeQuery();
			while(RS.next()) {
				ans.add(RS.getInt("id"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;
	}
	
	public static void modifyTicket(int id,String newStatus) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "update expenses set status = ? where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newStatus);
			ps.setInt(2, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
