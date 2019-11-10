package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.ByteArrayInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.TicketDao;
import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.models.createTicketRequest;
import com.revature.services.BaseService;

public class ReceiptServlet extends HttpServlet {
	public void init() throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reimb_id =request.getHeader("reimb_id");
		Regions region =Regions.US_EAST_2;
		String bucketName = "revaturemeadows";
        
        try {
        	 AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                     .withRegion(region)
                     .withCredentials(new ProfileCredentialsProvider())
                     .build();

        	 InputStream inputStream = request.getInputStream();

        	 byte[] contents = IOUtils.toByteArray(inputStream);
        	 InputStream stream = new ByteArrayInputStream(contents);
        	 ObjectMetadata metadata = new ObjectMetadata();
        	 metadata.setContentLength(contents.length);
        	 metadata.setContentType("image/");
        	 PutObjectRequest s3Put = new PutObjectRequest(bucketName, reimb_id, stream, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
        	 s3Client.putObject(s3Put);
        	 //AccessControlList acl = s3Client.getObjectAcl(bucketName, reimb_id);
        	 
        	 URL url = s3Client.getUrl("ers-tickets", reimb_id);
        	 TicketDao.setReceipt(Integer.parseInt(reimb_id), url.toString());
        }
	 catch (AmazonServiceException e) {
        // The call was transmitted successfully, but Amazon S3 couldn't process 
        // it, so it returned an error response.
        e.printStackTrace();
    } catch (SdkClientException e) {
        // Amazon S3 couldn't be contacted for a response, or the client
        // couldn't parse the response from Amazon S3.
        e.printStackTrace();
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
