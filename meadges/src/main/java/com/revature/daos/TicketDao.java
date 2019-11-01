package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.models.User;
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
	
	public static Ticket extractTicket(ResultSet RS) throws SQLException {
		Ticket ans=new Ticket();
		ans.setReimb_id(RS.getInt("reimb_id"));
		ans.setReimb_amount(RS.getDouble("reimb_amount"));
		ans.setReimb_author_id(RS.getInt("reimb_author_id"));
		ans.setReimb_resolver_id(RS.getInt("reimb_resolver_id"));
		ans.setReimb_description(RS.getString("reimb_description"));
		ans.setReimb_receipt(RS.getString("reimb_receipt"));
		ans.setReimb_resolved(RS.getTimestamp("reimb_resolved"));
		ans.setReimb_submitted(RS.getTimestamp("reimb_submitted"));
		User author=UserDao.getUserByUserID(ans.getReimb_author_id());
		User resolver=UserDao.getUserByUserID(ans.getReimb_resolver_id());
		if(resolver==null) {
			ans.setReimb_author_first_name(null);
			ans.setReimb_resolver_last_name(null);
		}
		else{
			ans.setReimb_resolver_first_name(resolver.getUser_first_name());
			ans.setReimb_resolver_last_name(resolver.getUser_last_name());
		}
		ans.setReimb_author_first_name(author.getUser_first_name());
		ans.setReimb_author_last_name(author.getUser_last_name());
		return ans;
	}
	/*public static Ticket getTicketById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				double amount=RS.getDouble("amount");
				String type=RS.getString("type");
				String description=RS.getString("description");
				String username=RS.getString("username");
				String status=RS.getString("status");
				
				return ans;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	public static List<Ticket> getAllTickets(){
		List<Ticket> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet RS =ps.executeQuery();
			while(RS.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;
	}
	public static List<Ticket> getAllTicketsForUser(int id){
		List<Ticket> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type where reimb_author_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet RS =ps.executeQuery();
			while(RS.next()) {
				ans.add(extractTicket(RS));
				
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
