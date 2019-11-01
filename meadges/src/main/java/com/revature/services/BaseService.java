package com.revature.services;

import com.revature.daos.BaseDao;
import com.revature.models.Credentials;

public class BaseService {
	
	BaseDao baseDao = new BaseDao();

	public boolean authenticate(Credentials credentials) {
		String password = baseDao.getPassword(credentials);
		
		if (credentials.getPassword() == password) {
			/* Run call to the baseDao to get the user information 
			 * and create a User object 
			 */
			return true;
		} else {
			return false;
		}
	}

}
