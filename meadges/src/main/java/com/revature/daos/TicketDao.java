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
	public static Ticket createTicket(double amount,int type,String description,
			String receipt,int userID) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Insert into ers_reimbursement(reimb_amount,reimb_type_id,reimb_description,reimb_author_id, reimb_receipt,reimb_status_id) values(?, ?, ?, ?, ?,1) returning reimb_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, type);
			ps.setString(3, description);
			ps.setInt(4, userID);
			ps.setString(5, receipt);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				return getTicketById(RS.getInt("reimb_id"));
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
		ans.setReimb_status_id(RS.getInt("reimb_status_id"));
		ans.setReimb_status(RS.getString("reimb_status"));
		ans.setReimb_type_id(RS.getInt("reimb_status_id"));
		ans.setReimb_type(RS.getString("reimb_type"));
		
		//System.out.println(ans.getReimb_submitted().toString());
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
	public static Ticket getTicketById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet RS =ps.executeQuery();
			if(RS.next()) {
				
				return extractTicket(RS);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<Ticket> getAllTickets(){
		List<Ticket> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type";
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public static boolean modifyTicket(int id,int newStatus,int resolver) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "update ers_reimbursement set reimb_status_id = ?, reimb_resolver_id = ?, reimb_resolved = current_date where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, newStatus);
			ps.setInt(2, resolver);
			ps.setInt(3, id);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static Object getAllTicketsByStatus(String status) {
		List<Ticket> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type where reimb_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
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

	public static Object getAllTicketsForUserByStatus(int ers_users_id, String status) {
		// TODO Auto-generated method stub
		List<Ticket> ans= new ArrayList();
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from ers_reimbursement natural join ers_reimbursement_status natural join ers_reimbursement_type where reimb_author_id = ? and reimb_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,ers_users_id);
			ps.setString(2, status);
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
}
