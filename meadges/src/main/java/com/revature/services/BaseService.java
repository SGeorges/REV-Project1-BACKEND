package com.revature.services;

import com.revature.models.Credentials;

public class BaseService {
	
	BaseDao baseDao = new BaseDao();

	public Object authenticate(Credentials credentials) {
		// TODO Auto-generated method stub
		username = credentials.getUsername();
		password = password.getPassword();
		
		
	}

}
