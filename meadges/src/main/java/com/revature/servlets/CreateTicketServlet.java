package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.models.createTicketRequest;

public class CreateTicketServlet extends HttpServlet {
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
		createTicketRequest ctr = om.readValue(request.getReader(), createTicketRequest.class);
		User user=UserDao.getUserByUserName(ctr.getUsername());
		if(user==null) {
			response.setStatus(403);
			response.getWriter().write("403 #17");
		}
		else if(!(ctr.getPassword().equals(user.getPassword()))) {
			response.setStatus(403);
			response.getWriter().write("403 #18");
			
		}
		else {
			om.writeValue(response.getWriter(),TicketDao.createTicket(ctr.getAmount(), ctr.getType(), ctr.getDescription(), ctr.getUsername()));
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
