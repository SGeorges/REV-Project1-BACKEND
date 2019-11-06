package com.revature.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.revature.models.User;

public class BaseService {
	public static boolean authenticate(String password, User user) {
		if(user==null)return false;
		String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            byte salt[]=DatatypeConverter.parseHexBinary(user.getSalt());
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(password.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
    
        }
        return generatedPassword.equals(user.getErs_password());
	}
}
