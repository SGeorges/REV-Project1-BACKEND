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
	public void init() throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CreateTicketServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		createTicketRequest ctr = om.readValue(request.getReader(), createTicketRequest.class);
		User user=UserDao.getUserByUserID(ctr.getUserID());
		if(user==null) {
			response.setStatus(403);
			response.getWriter().write("403 #17");
		}
		else if(!(ctr.getPassword().equals(user.getErs_password()))) {
			response.setStatus(403);
			response.getWriter().write("403 #18");
			
		}
		else {
			om.writeValue(response.getWriter(),TicketDao.createTicket(ctr.getAmount(), ctr.getType(), ctr.getDescription(), ctr.getReceipt(),ctr.getUserID()));
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
