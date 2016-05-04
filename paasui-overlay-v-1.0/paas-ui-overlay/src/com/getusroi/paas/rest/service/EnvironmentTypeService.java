package com.getusroi.paas.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.getusroi.paas.dao.DataBaseOperationFailedException;
import com.getusroi.paas.dao.EnvironmentDAO;
import com.getusroi.paas.dao.ImageRegistryDAO;
import com.getusroi.paas.helper.PAASConstant;
import com.getusroi.paas.helper.PAASErrorCodeExceptionHelper;
import com.getusroi.paas.rest.service.exception.EnvironmentTypeServiceException;
import com.getusroi.paas.vo.AddService;
import com.getusroi.paas.vo.ApplicantSummary;
import com.getusroi.paas.vo.EnvironmentType;
import com.getusroi.paas.vo.Environments;
import com.getusroi.paas.vo.ImageRegistry;
import com.google.gson.Gson;

/**
 * this class handle all rest call request of environment page
 * @author Bizruntime
 *
 */
@Path("/environmentTypeService")
public class EnvironmentTypeService {

	private static final Logger LOG = LoggerFactory.getLogger(EnvironmentTypeService.class);

	@POST
	@Path("/insertEnvironmentType")
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertEnvironmentType(String environmentTypeData)
			throws EnvironmentTypeServiceException, DataBaseOperationFailedException {

		LOG.debug(".insertEnvironmentType method of EnvironmentTypeService");
		ObjectMapper mapper = new ObjectMapper();
		EnvironmentDAO environmentDAO = new EnvironmentDAO();

		try {
			EnvironmentType environmentTypes = mapper.readValue(environmentTypeData, EnvironmentType.class);
			environmentDAO.insertEnvironmentType(environmentTypes);
		} catch (IOException e) {
			LOG.error("Error in reading data : " + environmentTypeData + " using object mapper in environmentType");
			throw new EnvironmentTypeServiceException(
					"Error in reading data : " + environmentTypeData + " using object mapper in addApplicationSummary");
		}

	} // end of insertEnvironmentType method

	
	@GET
	@Path("/getAllEnvironmentType")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEnvironmentType() throws EnvironmentTypeServiceException, DataBaseOperationFailedException {
		LOG.debug(".getEnvironmentType of EnvironmentTypeService");
		List<EnvironmentType> environmentTypeList = new ArrayList<>();
		EnvironmentDAO environmentDAO = new EnvironmentDAO();
		environmentTypeList = environmentDAO.getAllEnvironmentType();
		Gson gson = new Gson();
		String environmentList = gson.toJson(environmentTypeList);
		return environmentList;
	} // end of getEnvironmentType method

	@POST
	@Path("/deleteEnvironmentByName/{name}")
	public void deleteEnvironment(@PathParam("name") String name)
			throws DataBaseOperationFailedException, EnvironmentTypeServiceException {
		LOG.debug(".deleteEnvironment of EnvironmentTypeService");
		EnvironmentDAO environmentDAO = new EnvironmentDAO();
		environmentDAO.deleteEnvironmentTypeByName(name);
	} // end of deleteEnvironment method

	@GET
	@Path("/getAllEnvironamentList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEnvironmentList() throws DataBaseOperationFailedException {
		LOG.debug(".getEnvironmentList of EnvironmentTypeService");
		List<Environments> customers = new ArrayList<Environments>();
		EnvironmentDAO environmentDAO = new EnvironmentDAO();
		customers = environmentDAO.getAllEnvironmentsList();
		Gson gson = new Gson();
		String customersList = gson.toJson(customers);
		return customersList;
	} // end of getEnvironmentList

	@GET
	@Path("/getApplicationSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public String getApplicationSummary() throws DataBaseOperationFailedException {
		List<ApplicantSummary> appSummaryList = new ArrayList<ApplicantSummary>();
		EnvironmentDAO environmentDAO = new EnvironmentDAO();
		appSummaryList = environmentDAO.getApplicationSummaryByApplicationName();
		Gson gson = new Gson();
		String appList = gson.toJson(appSummaryList);
		return appList;
	} // end of getApplicationSummary

	@POST
	@Path("/getImageRepositoryFromSummary")    
	@Produces(MediaType.APPLICATION_JSON)
	public String getImageRepository(String appName) throws DataBaseOperationFailedException, EnvironmentTypeServiceException {		
		LOG.info(".getImageRepository of EnvironmentsTypeService: ");		
		String response=null;
		EnvironmentDAO environmentDAO=new EnvironmentDAO();
		ImageRegistryDAO imageRegistryDAO=new ImageRegistryDAO();
		ApplicantSummary applicantSummary =environmentDAO.selectImageRepositoryFromRepositoryName(appName);
		if(applicantSummary !=null){
		ImageRegistry imageRegistry=imageRegistryDAO.getImageRegistryByName(applicantSummary.getImageRegistry().trim());
		
		if(imageRegistry != null){
			String baseURL = PAASConstant.HTTPS_PROTOCOL_KEY+imageRegistry.getLocation() +PAASConstant.ALL_REPOSTORY_KEY+imageRegistry.getName()+PAASConstant.ALL_TAGS_KEY;
			String authentication=imageRegistry.getUser_name() + ":" + imageRegistry.getPassword();
			try {
				 response=getHttpResponse(baseURL, authentication, "GET");
				LOG.debug("http response  : "+response);				
			} catch (IOException e) {
				LOG.error("Unable to get the http response using base url :"+baseURL+", authentication : "+authentication);
				throw new EnvironmentTypeServiceException("Unable to get the http response using base url :"+baseURL+", authentication : "+authentication);
			}
		}else{
			LOG.debug("No image repository availabel with name : "+applicantSummary.getImageRegistry().trim());
		}	
		}//end of if(applicantSummary !=null)
		return response;
	} // end of getImageRepository
	
	@POST
	@Path("/insertEnvironmentsData")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertEnvironmentsData(String environmentData) throws DataBaseOperationFailedException {
		
		LOG.debug(".insertEnvironmentsData of EnvironmentsTypeService: " + environmentData);
		List<Environments> environmentsList = new ArrayList<>();
		JSONObject jsonObject =new JSONObject(environmentData);
		jsonObject.put("containername", "dev.fuse");
		jsonObject.put("host", "192.168.1.219");
		jsonObject.put("ipadress", "w");
		jsonObject.put("state", "running");
		jsonObject.toString();
		
		Environments environments = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			environments = mapper.readValue(jsonObject.toString(), Environments.class);
			EnvironmentDAO environmentDAO = new EnvironmentDAO();
			environmentDAO.insertAllEnvironmentsData(environments);
		} catch (IOException e) {
			LOG.debug("Unable to read value");
		}
		Gson gson = new Gson();
		String environmentsLists = gson.toJson(environmentsList);
		return environmentsLists;
		
	} // end of insertEnvironmentsData method
	
	
	/**
	 * This method is used to get response from http client
	 * @param baseURL : base url in String
	 * @param authentication : authentication in String
	 * @param httpRequestMethod : http request method
	 * @return String : response data in String
	 * @throws IOException : Unable to connect to url using http client
	 */
	private String getHttpResponse(String baseURL,String authentication,String httpRequestMethod) throws IOException{
		LOG.debug(".getHttpResponse method of EnvironmentTypeService");	
		StringBuffer result = new StringBuffer();
		URL url = new URL(baseURL);       
		//String authStr = summary.getUser_name() + ":" + summary.getPassword();
        String encodedAuthStr = Base64.encodeBase64String(authentication.getBytes());
		// Create Http connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if(connection!=null){
        // Set connection properties
        connection.setRequestMethod(httpRequestMethod);
        connection.setRequestProperty("Authorization", "Basic "
                + encodedAuthStr);
        connection.setRequestProperty("Accept", "application/json");        
        LOG.debug("Response  Code"+connection.getResponseCode());        
        InputStream content = (InputStream) connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                content));
        String line = "";
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        }
        return result.toString();
	}//end of method getHttpResponse
	
}
