package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.Credentials;
import com.revature.models.User;

public class ViewTicketsServlet extends HttpServlet {
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		Credentials credentials = om.readValue(request.getReader(), Credentials.class);
		User user=UserDao.getUserByUserName(credentials.getUsername());
		if(user==null) {
			response.setStatus(403);
			response.getWriter().write("403 #3");
		}
		else if(!(credentials.getPassword().equals(user.getPassword()))) {
			response.setStatus(403);
			response.getWriter().write("403 #4");
			
		}
		else if(user.isAdmin()){
			om.writeValue(response.getWriter(), TicketDao.getAllTicketIDs());
		}
		else {
			om.writeValue(response.getWriter(),TicketDao.getAllTicketIDsForUserName(user.getUserName()));
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