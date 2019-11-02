package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.models.TicketRequest;

public class ApproveTicketServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper om = new ObjectMapper();
		TicketRequest tr = om.readValue(request.getReader(), TicketRequest.class);
		User user=UserDao.getUserByUserID(tr.getUserID());
		Ticket ticket= TicketDao.getTicketById(tr.getTicketID());
		if(user==null) {
			response.setStatus(403);
			response.getWriter().write("403 #9");
		}
		else if(!(tr.getPassword().equals(user.getErs_password()))) {
			response.setStatus(403);
			response.getWriter().write("403 #10");
			
		}
		else if(ticket==null) {
			response.setStatus(403);
			response.getWriter().write("403 #11");
		}
		else if(!(user.getUser_role_id()==1)){
			response.setStatus(403);
			response.getWriter().write("403 #12");
		}
		else {
			om.writeValue(response.getWriter(),TicketDao.modifyTicket(tr.getTicketID(), 2, user.getErs_users_id()));
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
