package agentgui.simulationService;

import jade.core.AID;
import jade.core.GenericCommand;
import jade.core.IMTPException;
import jade.core.Location;
import jade.core.Node;
import jade.core.ServiceException;
import jade.core.SliceProxy;

import java.util.Vector;

import agentgui.simulationService.load.LoadThresholdLevels;
import agentgui.simulationService.load.LoadAgentMap.AID_Container;
import agentgui.simulationService.load.LoadInformation.Container2Wait4;
import agentgui.simulationService.ontology.ClientRemoteContainerReply;
import agentgui.simulationService.ontology.PlatformLoad;
import agentgui.simulationService.ontology.RemoteContainerConfig;


public class LoadServiceProxy extends SliceProxy implements LoadServiceSlice {

	private static final long serialVersionUID = -7016240061703852319L;

	// ----------------------------------------------------------
	// --- Method to get the Load-Informations of all ----------- 
	// --- containers ----------------------------- S T A R T ---
	// ----------------------------------------------------------
	@Override
	public boolean startAgent(String nickName, String agentClassName, Object[] args) throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_START_AGENT, LoadService.NAME, null);
			cmd.addParam(nickName);
			cmd.addParam(agentClassName);
			cmd.addParam(args);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (Boolean) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public String startNewRemoteContainer(RemoteContainerConfig remoteConfig, boolean preventUsageOfAlreadyUsedComputers ) throws IMTPException {
		
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_START_NEW_REMOTE_CONTAINER, LoadService.NAME, null);
			cmd.addParam(remoteConfig);
			cmd.addParam(preventUsageOfAlreadyUsedComputers);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (String) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public RemoteContainerConfig getDefaultRemoteContainerConfig(boolean preventUsageOfAlreadyUsedComputers) throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_DEFAULT_REMOTE_CONTAINER_CONFIG, LoadService.NAME, null);
			cmd.addParam(preventUsageOfAlreadyUsedComputers);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (RemoteContainerConfig) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public Container2Wait4 getNewContainer2Wait4Status(String containerName2Wait4) throws IMTPException {
		
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_NEW_CONTAINER_2_WAIT_4_STATUS, LoadService.NAME, null);
			cmd.addParam(containerName2Wait4);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (Container2Wait4) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public Location getLocation() throws IMTPException {
		
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_LOCATION, LoadService.NAME, null);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			Location loc = (Location) result;
			return loc;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public void setThresholdLevels(LoadThresholdLevels thresholdLevels) throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_SET_THRESHOLD_LEVEL, LoadService.NAME, null);
			cmd.addParam(thresholdLevels);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}		
	}
	@Override
	public PlatformLoad measureLoad() throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_MEASURE_LOAD, LoadService.NAME, null);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (PlatformLoad) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public AID[] getAIDList() throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_AID_LIST, SimulationService.NAME, null);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (AID[]) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public AID[] getAIDListSensorAgents() throws IMTPException {

		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_AID_LIST_SENSOR, SimulationService.NAME, null);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (AID[]) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	@Override
	public void setAgentMigration(Vector<AID_Container> transferAgents) throws IMTPException {
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_SET_AGENT_MIGRATION, LoadService.NAME, null);
			cmd.addParam(transferAgents);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}			
	}
	// ----------------------------------------------------------
	// --- Method to get the Load-Informations of all ----------- 
	// --- containers ----------------------------- E N D -------
	// ----------------------------------------------------------

	@Override
	public void putContainerDescription(ClientRemoteContainerReply crcReply) throws IMTPException {
		
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_PUT_CONTAINER_DESCRIPTION, LoadService.NAME, null);
			cmd.addParam(crcReply);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}			
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
		
	}
	
	@Override
	public ClientRemoteContainerReply getCRCReply() throws IMTPException {
	
		try {
			GenericCommand cmd = new GenericCommand(SERVICE_GET_CONTAINER_DESCRIPTION, LoadService.NAME, null);
			
			Node n = getNode();
			Object result = n.accept(cmd);
			if((result != null) && (result instanceof Throwable)) {
				if(result instanceof IMTPException) {
					throw (IMTPException)result;
				} else {
					throw new IMTPException("An undeclared exception was thrown", (Throwable)result);
				}
			}
			return (ClientRemoteContainerReply) result;
		}
		catch(ServiceException se) {
			throw new IMTPException("Unable to access remote node", se);
		}
	}
	
}
