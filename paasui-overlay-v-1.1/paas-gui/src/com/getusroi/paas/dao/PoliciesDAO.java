package com.getusroi.paas.dao;

import static com.getusroi.paas.helper.PAASConstant.MYSQL_DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.getusroi.paas.db.helper.DataBaseConnectionFactory;
import com.getusroi.paas.db.helper.DataBaseHelper;
import com.getusroi.paas.helper.PAASConstant;
import com.getusroi.paas.helper.PAASErrorCodeExceptionHelper;
import com.getusroi.paas.vo.ContainerTypes;
import com.getusroi.paas.vo.HostScalingPolicy;
import com.getusroi.paas.vo.ResourceSelection;
import com.getusroi.paas.vo.ScalingAndRecovery;
import com.getusroi.paas.vo.ServiceAffinities;
import com.mysql.jdbc.PreparedStatement;

/**
 * this class contains all DAO operation of Policies page
 * @author bizruntime
 *
 */
public class PoliciesDAO {

	private static final Logger logger = LoggerFactory.getLogger(PoliciesDAO.class);
	public static final String INSERT_SACALING_AND_RECOVERY_QUERY = "INSERT INTO scaling_and_recovery VALUES(?,?,?,?,?)";
	public static final String SELECT_ALL_DATA_FROM_SCALING_AND_RECOVERY = "SELECT * FROM scaling_and_recovery";
	public static final String DELETE_SCALING_AND_RECOVERY_BY_APPLICATION = "DELETE FROM scaling_and_recovery WHERE application = ?";
	public static final String INSERT_HOST_SCALING_POLICY = "INSERT INTO host_scaling_policy VALUES(?,?)";
	public static final String SELECT_ALL_HOST_SCALING_POLICY_QUERY = "SELECT * FROM host_scaling_policy";
	public static final String REMOVE_HOST_SCALING_POLICY_BY_NAME = "DELETE FROM host_scaling_policy WHERE name = ?";
	public static final String INSERT_SERVICE_AFFINITIES_QUERY = "INSERT INTO service_affinities VALUES(?,?,?,?)";
	public static final String GET_ALL_SERVICE_AFFINITIES_QUERY = "SELECT * FROM service_affinities";
	public static final String REMOVE_SERVICE_AFFINITIES_BY_APPLICATION = "DELETE FROM service_affinities WHERE application = ?";
	public static final String INSERT_RESOURCE_SELECTION_QUERY  = "INSERT INTO resource_selection VALUES(?,?,?,?,?,?,?)";
	public static final String SELECT_ALL_RESOURCE_SELECTION = "SELECT * FROM resource_selection";
	public static final String DELETE_RESOURCE_SELECTION_BY_RANK = "DELETE from resource_selection WHERE rank = ?";
	public static final String INSERT_CONTAINER_TYPES_QUERY = "INSERT INTO container_types VALUES(?,?,?,?)";
	public static final String SELECT_ALL_CONTAINER_TYPE_QUERY = "SELECT * FROM container_types";
	public static final String REMOVE_CONTAINER_TYPES_BY_NAME = "DELETE FROM container_types WHERE name = ?";
	
	/**
	 * this method is used to insert all values of scaling and recovery
	 * @param scalingAndRecovery : contains all data
	 * @return : return true if data successfully inserted
	 * @throws DataBaseOperationFailedException : Unable to store data into db
	 */
	public void insertScalingAndRecovery(ScalingAndRecovery scalingAndRecovery)
			throws DataBaseOperationFailedException {
		logger.debug(".insertAllScalingAndRecovery method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_SACALING_AND_RECOVERY_QUERY);
			preparedStatement.setString(1, scalingAndRecovery.getApplication());
			preparedStatement.setString(2, scalingAndRecovery.getServices());
			preparedStatement.setString(3, scalingAndRecovery.getEnvironmentTypes());
			preparedStatement.setString(4, scalingAndRecovery.getDesiredCount());
			preparedStatement.setString(5, scalingAndRecovery.getAutoRecovery());
			preparedStatement.executeUpdate();
			logger.debug("scaling and recovery inserted successfully: " + scalingAndRecovery);

		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to add scaling and recovery into db with data : " + scalingAndRecovery);
			throw new DataBaseOperationFailedException(
					"Unable to add scaling and recovery into db with data : " + scalingAndRecovery, e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to add scaling and recovery into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to add scaling and recovery into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else 
				throw new DataBaseOperationFailedException("Unable to add scaling and recovery into db", e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}

		
	}//end of method 
	
	/**
	 * this method is used to get all scaling and recovery from db
	 * @return : return list of scaling and recovery data
	 * @throws DataBaseOperationFailedException : Unable to get data from db
	 */
	public List<ScalingAndRecovery> getAllScalingAndRecovery() throws DataBaseOperationFailedException {
		logger.debug(".getAllScalingAndRecovery method of PoliciesDAO");
		List<ScalingAndRecovery> scalingAndRecoveryList = new ArrayList<>();
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_DATA_FROM_SCALING_AND_RECOVERY);
			resultSet = preparedStatement.executeQuery();
			ScalingAndRecovery scalingAndRecovery = null;
			while(resultSet.next()) {
				scalingAndRecovery = new ScalingAndRecovery();
				scalingAndRecovery.setApplication(resultSet.getString("application"));
				scalingAndRecovery.setServices(resultSet.getString("services"));
				scalingAndRecovery.setEnvironmentTypes(resultSet.getString("environment_types"));
				scalingAndRecovery.setDesiredCount(resultSet.getString("desired_count"));
				scalingAndRecovery.setAutoRecovery(resultSet.getString("auto_recovery"));

				scalingAndRecoveryList.add(scalingAndRecovery);
			}
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to fetch scaling and recovery from db ");
			throw new DataBaseOperationFailedException("Unable to fetch scaling and recovery from ",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to fetch scaling and recovery from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to fetch scaling and recovery from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to fetch scaling and recovery from db" , e);
		} finally {
			DataBaseHelper.dbCleanup(connection, preparedStatement, resultSet);
		}
		
		return scalingAndRecoveryList;
		
	} // end of getAllScalingAndRecovery
	
	/**
	 * this method is used to remove scaling and recovery by application
	 * @param application : application data
	 * @throws DataBaseOperationFailedException : Unable to remove scaling and recovery
	 */
	public void removeScalingAndRecoveryByApplication(String application) throws DataBaseOperationFailedException {
		logger.debug(".removeScalingAndRecoveryByApplication method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		try{
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_SCALING_AND_RECOVERY_BY_APPLICATION);
			preparedStatement.setString(1, application);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to remove scaling and recovery from db ");
			throw new DataBaseOperationFailedException("Unable to remove scaling and recovery",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to remove scaling and recovery from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to remove scaling and recovery from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to remove scaling and recovery from db" , e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
	} // end of removeScalingAndRecoveryByApplication
	
	/**
	 * this method is used to insert host scaling policy into db
	 * @param hostScalingPolicy : it contains host scaling policy data
	 * @return : return true if successfully added into db
	 * @throws DataBaseOperationFailedException : Unable to store data into db
	 */
	public boolean insertAllHostScalingPolicy(HostScalingPolicy hostScalingPolicy) throws DataBaseOperationFailedException {
		
		logger.debug(".insertAllHostScaling method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_HOST_SCALING_POLICY);
			preparedStatement.setString(1, hostScalingPolicy.getName());
			preparedStatement.setString(2, hostScalingPolicy.getHostGroups());

			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to insert host and scaling into db ");
			throw new DataBaseOperationFailedException("Unable to insert host and scaling into db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to insert host and scaling into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to insert host and scaling into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else 
				throw new DataBaseOperationFailedException("Unable to insert host and scaling into db", e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
		return true;
		
	} // end of insertAllHostScalingPolicy
	
	/**
	 * this method is used to select all host and scaling policy
	 * @return : return host and scaling policy list
	 * @throws DataBaseOperationFailedException : Unable to store host and scaling into db
	 */
	public List<HostScalingPolicy> selectAllHostScalingPolicy() throws DataBaseOperationFailedException {
		logger.debug(".selectAllHostScalingPolicy method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<HostScalingPolicy> hostScalingPolicyList = null;
		
		try {
			hostScalingPolicyList = new ArrayList<>();
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_HOST_SCALING_POLICY_QUERY);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				HostScalingPolicy hostScalingPolicy = new HostScalingPolicy();
				hostScalingPolicy.setName(resultSet.getString("name"));
				hostScalingPolicy.setHostGroups("host_groups");
				hostScalingPolicyList.add(hostScalingPolicy);
			}
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to select host and scaling from db ");
			throw new DataBaseOperationFailedException("Unable to select host and scaling from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to select host and scaling from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to select host and scaling from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to select host and scaling from db", e);
		} finally {
			DataBaseHelper.dbCleanup(connection, preparedStatement, resultSet);
		}
		
		return hostScalingPolicyList;
	} // end of selectAllHostScalingPolicy method
	
	
	/**
	 * this method is used to remove host scaling policy by name from db
	 * @param name : this is used to remove data 
	 * @throws DataBaseOperationFailedException : Unable to remove host scaling policy
	 */
	public void removeHostScalingPolicyByName(String name) throws DataBaseOperationFailedException {		
		logger.debug(".removeHostScalingPolicyByName method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(REMOVE_HOST_SCALING_POLICY_BY_NAME);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to remove host and scaling from db ");
			throw new DataBaseOperationFailedException("Unable to remove host and scaling from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to remove host and scaling from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to remove host and scaling from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to remove host and scaling from db", e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
	} // end of removeHostScalingPolicyByName
	
	/**
	 * this method is used to insert all services affinities into db
	 * @param affinities : it contains object of affinities
	 * @return : return true if data inserted successfully
	 * @throws DataBaseOperationFailedException : Unable to insert data into data base
	 */
	public boolean insertAllServiceAffinities(ServiceAffinities affinities) throws DataBaseOperationFailedException {
		
		logger.debug(".insertAllServiceAffinities method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_SERVICE_AFFINITIES_QUERY);
			preparedStatement.setString(1, affinities.getApplication());
			preparedStatement.setString(2, affinities.getServices());
			preparedStatement.setString(3, affinities.getEnvironmentTypes());
			preparedStatement.setString(4, affinities.getAffinity());
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to insert service affinities into db ");
			throw new DataBaseOperationFailedException("Unable to insert service affinities into db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to insert service affinities into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to insert service affinities into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to insert service affinities into db", e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		return true;
	} // end of insertAllServiceAffinities
	
	/**
	 * this method is used to get all service affinities from db
	 * @return : return the list of service affinities data
	 * @throws DataBaseOperationFailedException : Unable to fetch data
	 */
	public List<ServiceAffinities> getAllServiceAffinities() throws DataBaseOperationFailedException {
		
		logger.debug(".getAllServiceAffinities method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		List<ServiceAffinities> serviceAffinitiesList = new ArrayList<ServiceAffinities>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(GET_ALL_SERVICE_AFFINITIES_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ServiceAffinities serviceAffinities = new ServiceAffinities();
				serviceAffinities.setApplication(resultSet.getString("application"));
				serviceAffinities.setServices(resultSet.getString("services"));
				serviceAffinities.setEnvironmentTypes(resultSet.getString("environment_types"));
				serviceAffinities.setAffinity(resultSet.getString("affinity"));
				serviceAffinitiesList.add(serviceAffinities);
			}

		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to get service affinities from db ");
			throw new DataBaseOperationFailedException("Unable to get service affinities from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to get service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to get service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else
				throw new DataBaseOperationFailedException("Unable to get service affinities from db", e);
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
		return serviceAffinitiesList;
	} // end of getAllServiceAffinities
	
	
	/**
	 * this method is used to remove service affinities data by application
	 * @param application : it is used to remove data with where clouse
	 * @throws DataBaseOperationFailedException : Unable to remove data from db
	 */
	public void removeServiceAffinitiesByApplication(String application) throws DataBaseOperationFailedException {
		
		logger.debug(".removeServiceAffinitiesByApplication method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(REMOVE_SERVICE_AFFINITIES_BY_APPLICATION);
			preparedStatement.setString(1, application);
			preparedStatement.execute();
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to remove service affinities from db ");
			throw new DataBaseOperationFailedException("Unable to remove service affinities from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to remove service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to remove service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to remove service affinities from db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
	} // end of removeServiceAffinitiesByApplication
	
	
	/**
	 * this method is used to insert resource selection into db
	 * @param resourceSelection : it contains ResourceSelection data
	 * @return return true if successful inserted data into db
	 * @throws DataBaseOperationFailedException : Unable to insert data
	 */
	public boolean insertResourceSelection(ResourceSelection resourceSelection) throws DataBaseOperationFailedException {
		logger.debug(".insertResourceSelection method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_RESOURCE_SELECTION_QUERY);
			preparedStatement.setString(1, resourceSelection.getRank());
			preparedStatement.setString(2, resourceSelection.getName());
			preparedStatement.setString(3, resourceSelection.getContainerTypes());
			preparedStatement.setString(4, resourceSelection.getEnvironmentTypes());
			preparedStatement.setString(5, resourceSelection.getHostGroups());
			preparedStatement.setString(6, resourceSelection.getPlacement());
			preparedStatement.setString(7, resourceSelection.getMinimum());

			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to insert resource selection into db ");
			throw new DataBaseOperationFailedException("Unable to insert resource selection into db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to insert resource selection into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to insert resource selection into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to insert resource selection into db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		return true;
	} // end of insertResourceSelection
	
	/**
	 * this method is used to get all resource selection from db
	 * @return : return the list of resource selection
	 * @throws DataBaseOperationFailedException : Unable to fetch data from db
	 */
	public List<ResourceSelection> getAllResourceSelection() throws DataBaseOperationFailedException {
		logger.debug(".getAllServiceAffinities method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		List<ResourceSelection> resourceSelectionList = new ArrayList<ResourceSelection>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_RESOURCE_SELECTION);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceSelection resourceSelection = new ResourceSelection();
				resourceSelection.setRank(resultSet.getString("rank"));
				resourceSelection.setName(resultSet.getString("name"));
				resourceSelection.setContainerTypes(resultSet.getString("container_types"));
				resourceSelection.setEnvironmentTypes(resultSet.getString("environment_types"));
				resourceSelection.setHostGroups(resultSet.getString("host_groups"));
				resourceSelection.setPlacement(resultSet.getString("placement"));
				resourceSelection.setMinimum(resultSet.getString("minimum"));

				resourceSelectionList.add(resourceSelection);
			}

		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to get service affinities from db ");
			throw new DataBaseOperationFailedException("Unable to get service affinities from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to get service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to get service affinities from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to get service affinities from db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}

		return resourceSelectionList;
	} //end of getAllServiceAffinities
	
	/**
	 * this method is used to remove resource selection by rank
	 * @param rank : based on rank data will be removed
	 * @throws DataBaseOperationFailedException : Unable to remove resource selection
	 */
	public void removeResourceSelectionByRank(String rank) throws DataBaseOperationFailedException {
		logger.debug(".removeResourceSelectionByRank method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_RESOURCE_SELECTION_BY_RANK);
			preparedStatement.setString(1, rank);
			preparedStatement.execute();
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to remove resource selection from db ");
			throw new DataBaseOperationFailedException("Unable to remove resource selection from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to remove resource selection from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to remove resource selection from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to remove resource selection from db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
	} // END OF removeResourceSelectionByRank

	/**
	 * this is used to insert all values of container type into db
	 * @param containerTypes : list of values which is going to store into db
	 * @return : true if data inserted successfully
	 * @throws DataBaseOperationFailedException : Unable to store data into db
	 */
	public boolean insertContainerType(ContainerTypes containerTypes) throws DataBaseOperationFailedException {
		logger.debug(".insertContainerType method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_CONTAINER_TYPES_QUERY);
			preparedStatement.setString(1, containerTypes.getName());
			preparedStatement.setInt(2, containerTypes.getCpuShares());
			preparedStatement.setInt(3, containerTypes.getMemory());
			preparedStatement.setString(4, containerTypes.getDescription());

			preparedStatement.executeUpdate();
						
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to insert container type into db ");
			throw new DataBaseOperationFailedException("Unable to insert container type into db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to insert container type into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to insert container type into db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to insert container type into db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		return true;
	} //end of insertContainerType
	
	/**
	 * this method is used to get all data from container_type table
	 * @return : it return list of data from container_table
	 * @throws DataBaseOperationFailedException : Unable to fetch data from db
	 */
	public List<ContainerTypes> getAllContainerTypesData() throws DataBaseOperationFailedException {
		logger.debug(".getAllContainerTypesData method of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		List<ContainerTypes> containerTypesList = new ArrayList<ContainerTypes>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_CONTAINER_TYPE_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ContainerTypes containerTypes = new ContainerTypes();
				containerTypes.setName(resultSet.getString("name"));
				containerTypes.setCpuShares(resultSet.getInt("cpu_shares"));
				containerTypes.setMemory(resultSet.getInt("memory"));
				containerTypes.setDescription(resultSet.getString("description"));

				containerTypesList.add(containerTypes);
			}

		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to get container types from db ");
			throw new DataBaseOperationFailedException("Unable to get container types from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to get container types from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to get container types from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to get container types from db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanup(connection, preparedStatement, resultSet);
		}

		return containerTypesList;
	} // end of getAllContainerTypesData
	
	public void removeContainerTypesByName(String name) throws DataBaseOperationFailedException {		
		logger.debug(".removeContainerTypesByName of PoliciesDAO");
		DataBaseConnectionFactory dataBaseConnectionFactory = new DataBaseConnectionFactory();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataBaseConnectionFactory.getConnection(MYSQL_DB);
			preparedStatement = (PreparedStatement) connection.prepareStatement(REMOVE_CONTAINER_TYPES_BY_NAME);
			preparedStatement.setString(1, name);
			preparedStatement.execute();
			
		} catch (ClassNotFoundException | IOException e) {
			logger.error("Unable to remove container type from db ");
			throw new DataBaseOperationFailedException("Unable to remove container type from db",e);
		} catch(SQLException e) {
			if(e.getErrorCode() == 1064) {
				String message = "Unable to remove container type from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.ERROR_IN_SQL_SYNTAX);
				throw new DataBaseOperationFailedException(message, e);
			} else if(e.getErrorCode() == 1146) {
				String message = "Unable to remove container type from db because: " + PAASErrorCodeExceptionHelper.exceptionFormat(PAASConstant.TABLE_NOT_EXIST);
				throw new DataBaseOperationFailedException(message, e);
			} else {
				throw new DataBaseOperationFailedException("Unable to remove container type from db ", e);
			}
		} finally {
			DataBaseHelper.dbCleanUp(connection, preparedStatement);
		}
		
	} // end of removeContainerTypesByName
	
}

























