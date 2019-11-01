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
import com.revature.models.viewTicketRequest;

public class DenyTicketServlet extends HttpServlet {
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
		viewTicketRequest vtr = om.readValue(request.getReader(), viewTicketRequest.class);
		User user=UserDao.getUserByUserName(vtr.getUsername());
		Ticket ticket= TicketDao.getTicketById(vtr.getTicketID());
		if(user==null) {
			response.setStatus(403);
			response.getWriter().write("403 #13");
		}
		else if(!(vtr.getPassword().equals(user.getPassword()))) {
			response.setStatus(403);
			response.getWriter().write("403 #14");
			
		}
		else if(ticket==null) {
			response.setStatus(403);
			response.getWriter().write("403 #15");
		}
		else if(!(user.isAdmin())){
			response.setStatus(403);
			response.getWriter().write("403 #16");
		}
		else {
			TicketDao.modifyTicket(vtr.getTicketID(), "Denied");
			om.writeValue(response.getWriter(),true);
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
