package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDao;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.services.BaseService;

public class BaseServlet extends HttpServlet{

	/**
	 * Not needed but added anyway b/c whatever. 
	 */
	private static final long serialVersionUID = -8398581717265016106L;

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
//	BaseService baseService = new BaseService();
	
	/* Authenticating User w/ login credentials */
	/* login to user */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		
		Credentials credentials = om.readValue(request.getReader(), Credentials.class);
		User user=UserDao.getUserByUserName(credentials.getUsername());
		if(!BaseService.authenticate(credentials.getPassword(), user)) {
			response.setStatus(403);
			response.getWriter().write("403");
		}
		else {
			om.writeValue(response.getWriter(), user);
		}
		//om.writeValue(resp.getWriter(), baseService.authenticate(credentials));
		
		
		
	}
}
