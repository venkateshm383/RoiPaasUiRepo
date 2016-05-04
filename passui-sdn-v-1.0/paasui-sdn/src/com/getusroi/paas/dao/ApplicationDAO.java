package com.getusroi.paas.dao;

import static com.getusroi.paas.helper.PAASConstant.MYSQL_DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.getusroi.paas.db.helper.DataBaseConnectionFactory;
import com.getusroi.paas.db.helper.DataBaseHelper;
import com.getusroi.paas.helper.PAASConstant;
import com.getusroi.paas.helper.PAASErrorCodeExceptionHelper;
import com.getusroi.paas.vo.AddService;
import com.getusroi.paas.vo.ApplicantSummary;
import com.getusroi.paas.vo.EnvironmentVariable;
import com.getusroi.paas.vo.Route;
import com.getusroi.paas.vo.Scale;
import com.mysql.jdbc.PreparedStatement;

public class ApplicationDAO {
	 static final Logger logger = LoggerFactory.getLogger(ApplicationDAO.class);
	 
	 public static final String INSERT_APPLICATION_SUMMARY_QUERY="insert into appsummary values(?,?,?,?,?)";
	 public static final String GET_ALL_APPLICATION_SUMMARY_QUERY="select * from appsummary";
	 public static final String INSERT_APPLICATION_SERVICE_QUERY="insert into addService values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 public static final String GET_ALL_APPLICATION_SERVICE_QUERY="select * from addService";
	 public static final String DELET_SERVICE_BY_SERVICENAME_QUERY="delete from addService where serviceName=?";
	 public static final String GET_ENVIRONMENT_VARIABLE_BY_SERVICENAME="select * from environment_variable where serviceName =?";
	 public static final String GET_ROUTE_BY_SERVICENAME="select * from route where serviceName =?";
	 public static final String GET_NETWORK_POLICY_BY_SERVICENAME="select * from network_policy where serviceName =?";
	 
	 /**
	  * This method is used to add application summary
	  * @param appSummary : ApplicantSummary Object contains data need for inserting data
	  * @throws DataBaseOperationFailedException : Unable to insert ApplicantSummary
	  */
	 public void insertApplicationSummary(ApplicantSummary appSummary) throws DataBaseOperationFailedException{
		 logger.debug(".insertApplicationSummary method of ApplicationDAO");
		 DataBaseConnectionFactory connectionFactory=new DataBaseConnectionFactory();
		 Connection connection=null;
		 PreparedStatement pstmt=null;
		 try {
			 connection=connectionFactory.getConnection(MYSQL_DB);
			pstmt = (PreparedStatement) connection
					.prepareStatement(INSERT_APPLICATION_SUMMARY_QUERY);
			pstmt.setString(1, appSummary.getApplicantionName());
			pstmt.setString(2, appSummary.getDescription());
			pstmt.setString(3, appSummary.getImageRegistry());
			pstmt.setString(4, appSummary.getImageRepository());
			pstmt.setString(5, appSummary.getTag());
			pstmt.executeUpdate();
			logger.debug("apps summary data : "+appSummary+" inserted successfully");
			} catch (ClassNotFoundException | IOException e) {
				logger.error("Unable to add applicant summary into db with data : "+appSummary);
				throw new DataBaseOperationFailedException("Unable to add applicant summary into db with data : "+appSummary,e);
			} catch(SQLException e) {
				if(e.getErrorCode() == 1064) {
					String message = "Unable to add applicant summary into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
					throw new DataBaseOperationFailedException(message, e);
				} else if(e.getErrorCode() == 1146) {
					String message = "Unable to add applicant summary into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
					throw new DataBaseOperationFailedException(message, e);
				} else
					throw new DataBaseOperationFailedException("Unable to add applicant summary into db with data : "+appSummary,e);
			} finally{
				DataBaseHelper.dbCleanUp(connection, pstmt);
			}
		 
	 }//end of method insertApplicationSummary
	 
	 
	 /**
	  * This method is used to get All applicant summary from db
	  * @return List<ApplicantSummary> : List of ApplicantSummary Object
	  * @throws DataBaseOperationFailedException : Unable to get all applicant summary
	  */
	 public List<ApplicantSummary> getAllApplicantSummary() throws DataBaseOperationFailedException{
		 logger.debug(".getAllApplicantSummary method of ApplicationDAO");
		 DataBaseConnectionFactory connectionFactory=new DataBaseConnectionFactory();
		 List<ApplicantSummary> applicantSummaryList = new LinkedList<ApplicantSummary>();
		 Connection connection=null;
		 Statement stmt=null;
		 ResultSet result=null;
		 try {
			connection=connectionFactory.getConnection(MYSQL_DB);
			stmt=connection.createStatement();
			result=stmt.executeQuery(GET_ALL_APPLICATION_SUMMARY_QUERY);
			if(result != null){
				while(result.next()){
				ApplicantSummary applicantSummary = new ApplicantSummary();
				applicantSummary = new ApplicantSummary();
				applicantSummary.setApplicantionName(result.getString("applicantName"));
				applicantSummary.setDescription(result.getString("description"));
				applicantSummary.setImageRegistry(result.getString("imageRegistry"));
				applicantSummary.setImageRepository(result.getString("imageRepository"));
				applicantSummary.setTag(result.getString("tag"));
				applicantSummaryList.add(applicantSummary);
				}
			}else{
				logger.debug("No data avilable in apps summary table");
			}
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to fetch applicant summary into db ");
			throw new DataBaseOperationFailedException("Unable to fetch applicant summary ",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to fetch applicant summary into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to fetch applicant summary into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to fetch applicant summary ",e);
		} finally {
			DataBaseHelper.dbCleanup(connection, stmt, result);
		}
		return applicantSummaryList;
	 }//end of method getAllApplicantSummary
	 
	 /**
	  * This method is used to add service to db
	  * @param addService : addService Object
	  * @throws DataBaseOperationFailedException : Error in adding service to db
	  */
	 public void addService(AddService addService) throws DataBaseOperationFailedException{
		 logger.debug(".addService method of ApplicationDAO");
		 DataBaseConnectionFactory connectionFactory=new DataBaseConnectionFactory();
		 Connection connection=null;
		 PreparedStatement pstmt=null;
		 try {
			 connection=connectionFactory.getConnection(MYSQL_DB);
			 pstmt = (PreparedStatement) connection.prepareStatement(INSERT_APPLICATION_SERVICE_QUERY);
			pstmt.setString(1, addService.getServiceName());
			pstmt.setString(2, addService.getType());
			pstmt.setString(3, addService.getApplicantionName());
			pstmt.setString(4, addService.getImageRegistry());
			pstmt.setString(5, addService.getImageRepository());
			pstmt.setString(6, addService.getTag());
			pstmt.setString(7, addService.getRun());
			pstmt.setString(8, addService.getHostname());
			pstmt.setString(9, addService.getTypename());
			pstmt.setString(10, addService.getEnvirnament());
			pstmt.setString(11, addService.getEnvpath());
			pstmt.setString(12, addService.getEnvinterval());
			pstmt.setString(13, addService.getEnvtimeout());
			pstmt.setString(14, addService.getEnvthresold());
			pstmt.setString(15, addService.getEnvignore());
			pstmt.setString(16, addService.getVolume());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to insert data for service into db with data : "+addService);
			throw new DataBaseOperationFailedException("Unable to insert data for service into db with data :  "+addService,e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to insert data for service into db with because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to insert data for service into db with because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to insert data for service into db with data :  "+addService,e);
		} finally{
			DataBaseHelper.dbCleanUp(connection, pstmt);
		}
	 }//end of method addService
	 
	 /**
	  * This method is used to get all data available in addservice
	  * @return List<AddService> : List of addService
	  * @throws DataBaseOperationFailedException : Unable to get the all service from db
	  */
	 public List<AddService> getAllService() throws DataBaseOperationFailedException{
		 logger.debug(".getAllService method of ApplicationDAO"); 
		 DataBaseConnectionFactory connectionFactory=new DataBaseConnectionFactory();
		 List<AddService> addServiceList = new LinkedList<AddService>(); 
		 Connection connection=null;
		 PreparedStatement pstmt=null;
		 ResultSet result=null;
		 try {
			connection=connectionFactory.getConnection(MYSQL_DB);
			pstmt = (PreparedStatement)connection.prepareStatement(GET_ALL_APPLICATION_SERVICE_QUERY); 
			result = pstmt.executeQuery();    
			if(result!=null){
				while(result.next()){
					AddService addService=new AddService();
					addService.setServiceName(result.getString("serviceName"));  
					addService.setType(result.getString("type"));  
					addService.setApplicantionName(result.getString("applicantionName")); 
					addService.setImageRegistry(result.getString("imageRegistry"));  
					addService.setImageRegistry(result.getString("imageRepository")); 
					addService.setTag(result.getString("tag"));  
					addService.setRun(result.getString("run"));  
					addService.setHostname(result.getString("hostname"));   
					addService.setTypename(result.getString("typename"));  
					addService.setEnvirnament(result.getString("envirnament"));  
					addService.setEnvpath(result.getString("envpath"));    
					addService.setEnvinterval(result.getString("envinterval"));  
					addService.setEnvtimeout(result.getString("envtimeout"));   
					addService.setEnvthresold(result.getString("envthresold")); 
					addService.setEnvignore(result.getString("envignore"));  
					List<EnvironmentVariable> listOfEnvs=getAllEnvironment(connection,result.getString("serviceName"));
					List<Route> listOfRoute=getRouteByServiceName(connection, result.getString("serviceName"));
					List<Scale> listOfscale=getNetworkScale(connection,  result.getString("serviceName"));
					addService.setEnv(listOfEnvs); 
					addService.setRoute(listOfRoute);
					addService.setScales(listOfscale);
					addServiceList.add(addService);
				}
			}else{
				logger.debug("No data avilable in add service table");
			}
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to fetch  data for service from db");
			throw new DataBaseOperationFailedException("Unable to fetch  data for service from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to fetch  data for service from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to fetch  data for service from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to fetch  data for service from db",e);
		} finally{
			DataBaseHelper.dbCleanup(connection, pstmt, result);
		}
		 return addServiceList;
	 }//end of method getAllService
	 
	 /**
	  * This method is used to delete service from db by service name
	  * @param serviceName : service name in String
	  * @throws DataBaseOperationFailedException : Unable to delete service from db using service name
	  */
	 public void deleteServiceByServiceName(String serviceName) throws DataBaseOperationFailedException{
		 logger.debug(".deleteServiceByServiceName method of ApplicationDAO"); 
		 DataBaseConnectionFactory connectionFactory=new DataBaseConnectionFactory();
		 Connection connection=null;
		 PreparedStatement pstmt=null;
		 try {
			connection=connectionFactory.getConnection(MYSQL_DB);
			pstmt=(PreparedStatement) connection.prepareStatement(DELET_SERVICE_BY_SERVICENAME_QUERY);
			pstmt.setString(1, serviceName);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to delete   data for service from db : "+serviceName);
			throw new DataBaseOperationFailedException("Unable to delete  data for service from db "+serviceName,e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to delete   data for service from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to delete   data for service from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to delete  data for service from db "+serviceName,e);
		}
	 }//end of method deleteServiceByServiceName
	 
	 /**
	  * This method is used to get environment varibale based on service name
	  * @param connection : COnnection Object
	  * @param serviceName : service name in String
	  * @return List<EnvironmentVariable> : List of EnvironmentVariable
	  * @throws DataBaseOperationFailedException : Unable to fetch environment variable by service name
	 * @throws SQLException : Unable to close the resources
	  */
	 private List<EnvironmentVariable> getAllEnvironment(Connection connection,String serviceName) throws DataBaseOperationFailedException, SQLException{
		 logger.debug(".getAllEnvironment method of ApplicationDAO");
		 List<EnvironmentVariable> listOfEnvs=new ArrayList<>(); 
		 ResultSet envResultSet =null;
		 PreparedStatement envPreparedStatement = null;
		 try {
			 envPreparedStatement=(PreparedStatement) connection.prepareStatement(GET_ENVIRONMENT_VARIABLE_BY_SERVICENAME);
			envPreparedStatement.setString(1,serviceName);
			envResultSet = envPreparedStatement.executeQuery();
			if(envResultSet !=null){
				 while(envResultSet.next()) { 
					 EnvironmentVariable envVar = new EnvironmentVariable();  
					   envVar.setEnvkey(envResultSet.getString(1)); 
					   envVar.setEnvvalue(envResultSet.getString(2));
					   listOfEnvs.add(envVar);  
					   }  
			}else{
				logger.debug("No data available for environment varible for service name : "+serviceName);
			}
		} catch (SQLException e) {
			logger.error("Unable to fetch  EnvironmentVariable for service"+serviceName+" from db");
			throw new DataBaseOperationFailedException("Unable to fetch  EnvironmentVariable for service"+serviceName+" from db",e);
		} finally{
			envResultSet.close();
			envPreparedStatement.close();
		}
		 return listOfEnvs;
	 }//end of method getAllEnvironment
	 
	 /**
	  * This method is used to get route based on service name
	  * @param connection : Connection Object
	  * @param serviceName : service name in String
	  * @return List<Route> : List of Route
	  * @throws DataBaseOperationFailedException : Unable to get route by service name
	  * @throws SQLException : error in closing resources
	  */
	 private List<Route> getRouteByServiceName(Connection connection,String serviceName) throws DataBaseOperationFailedException, SQLException{
		 logger.debug(".getRouteByServiceName method of ApplicationDAO");
		 List<Route> listOfRoute=new ArrayList<>();
		 ResultSet routeResultSet =null;
		 PreparedStatement routeReparedStatement =null;
		 try {
			routeReparedStatement = (PreparedStatement) connection.prepareStatement(GET_ROUTE_BY_SERVICENAME);
			routeReparedStatement.setString(1,serviceName); 
			routeResultSet = routeReparedStatement.executeQuery();
			if(routeResultSet!=null){
				while(routeResultSet.next()) {
					Route routeVar=new Route();
					routeVar.setType(routeResultSet.getString("typename"));
					routeVar.setPort(routeResultSet.getString("portname"));
					routeVar.setRoutetype(routeResultSet.getString("routetype"));
					routeVar.setTarget(routeResultSet.getString("target"));
					listOfRoute.add(routeVar);
				}				
			}else{
				logger.debug("No route data availble for service name : "+serviceName);
			}
		} catch (SQLException e) {
			logger.error("Unable to fetch  Route for service"+serviceName+" from db");
			throw new DataBaseOperationFailedException("Unable to fetch  Route for service"+serviceName+" from db",e);
		}finally{
			routeResultSet.close();
			routeReparedStatement.close();
		}
		 return listOfRoute;
	 }//end of method getRouteByServiceName
	 
	 /**
	  * This method is used to get network scale by service name
	  * @param connection : Connection Object
	  * @param serviceName : service name in String
	  * @return List<Scale> : List of scale Object
	  * @throws DataBaseOperationFailedException : Unable to fetch Scale from db by service name
	 * @throws SQLException : Unable to close the resources
	  */
	 private List<Scale> getNetworkScale(Connection connection,String serviceName) throws DataBaseOperationFailedException, SQLException{
		 logger.debug(".getNetworkScale method of ApplicationDAO");
		 List<Scale> listOfscale=new ArrayList<>();
		 PreparedStatement scalePreparedStatement=null;
		 ResultSet scaleResultSet =null;
		 try {
			scalePreparedStatement = (PreparedStatement) connection.prepareStatement(GET_NETWORK_POLICY_BY_SERVICENAME);
			scalePreparedStatement.setString(1,serviceName);
			scaleResultSet = scalePreparedStatement.executeQuery();
			if(scaleResultSet !=null){
				while(scaleResultSet.next()) {  
					   Scale scale=new Scale();     
					   scale.setPortname(scaleResultSet.getString(1));  
					   scale.setPorttype(scaleResultSet.getString(2));  
					   scale.setHostport(scaleResultSet.getString(3)); 
					   scale.setContainerport(scaleResultSet.getString(4));   
					   listOfscale.add(scale); 
					   }           
			}else{
				logger.debug("No scale data availble for service name : "+serviceName);
			}
		} catch (SQLException e) {
			logger.error("Unable to fetch  Scale for service"+serviceName+" from db");
			throw new DataBaseOperationFailedException("Unable to fetch  Scale for service"+serviceName+" from db",e);
		}finally{
			scaleResultSet.close();
			scalePreparedStatement.close();
		}
		 return listOfscale;
	 }//end of method getNetworkScale

}
