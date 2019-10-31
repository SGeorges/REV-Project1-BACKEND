package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Credentials;
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
	
	BaseService baseService = new BaseService();
	
	/* Authenticating User w/ login credentials */
	/* login to user */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		
		Credentials credentials = om.readValue(req.getReader(), Credentials.class);
		
		om.writeValue(resp.getWriter(), baseService.authenticate(credentials));
		
		
		
	}
}
