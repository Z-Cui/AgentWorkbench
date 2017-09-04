/**
 * ***************************************************************
 * Agent.GUI is a framework to develop Multi-agent based simulation 
 * applications based on the JADE - Framework in compliance with the 
 * FIPA specifications. 
 * Copyright (C) 2010 Christian Derksen and DAWIS
 * http://www.dawis.wiwi.uni-due.de
 * http://sourceforge.net/projects/agentgui/
 * http://www.agentgui.org 
 *
 * GNU Lesser General Public License
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */
package agentgui.simulationService.distribution;

import jade.core.Profile;
import jade.util.leap.ArrayList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import agentgui.core.application.Application;
import agentgui.simulationService.agents.ServerSlaveAgent;
import agentgui.simulationService.ontology.RemoteContainerConfig;
import de.enflexit.common.transfer.Download;

/**
 * This class is only used by the {@link ServerSlaveAgent} of the background
 * system. It enables the agent to start a new process in order to extend
 * a remote JADE platform and join that platform with a new container.
 * 
 * @see ServerSlaveAgent
 * 
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class JadeRemoteStart extends Thread {

	/** Constant for a memory of 16 MB. */
	public static final String jvmMemo16MB 	= "16m";
	/** Constant for a memory of 32 MB. */
	public static final String jvmMemo32MB 	= "32m";
	/** Constant for a memory of 64 MB. */
	public static final String jvmMemo64MB 	= "64m";
	/** Constant for a memory of 128 MB. */
	public static final String jvmMemo128MB = "128m";
	/** Constant for a memory of 256 MB. */
	public static final String jvmMemo256MB = "256m";
	/** Constant for a memory of 512 MB. */
	public static final String jvmMemo512MB = "512m";
	/** Constant for a memory of 1024 MB. */
	public static final String jvmMemo1GB 	= "1g";
	/** Constant for a memory of 2048 MB. */
	public static final String jvmMemo2GB 	= "2g";
	/** Constant for a memory of 4096 MB. */
	public static final String jvmMemo4GB 	= "4g";
	/** Constant for a memory of 8192 MB. */
	public static final String jvmMemo8GB 	= "8g";
	/** Constant for a memory of 16384 MB. */
	public static final String jvmMemo16GB 	= "16g";
	/** Constant for a memory of 32768 MB. */
	public static final String jvmMemo32GB 	= "32g";
	
	private boolean debug = false;
	
	private boolean jvmMemAllocUseDefaults = true;
	private String jvmMemAllocInitial = JadeRemoteStart.jvmMemo128MB;
	private String jvmMemAllocMaximum = JadeRemoteStart.jvmMemo512MB;
	
	private boolean jadeIsRemoteContainer = true; 
	private boolean jadeShowGUI = true; 
	private String jadeShowGUIAgentName = "rma."; 
	private String jadeServices = "jade.core.event.NotificationService;jade.core.mobility.AgentMobilityService;agentgui.simulationService.SimulationService;agentgui.simulationService.LoadService";
	private String jadeHost = "localhost";
	private String jadePort = Application.getGlobalInfo().getJadeLocalPort().toString();
	private String jadeContainerName = "remote";
	
	private ArrayList jadeJarInclude = null;
	private ArrayList jadeJarIncludeClassPath = new ArrayList();
	private File extJarFolder = null; 
	
	private final String pathBaseDir = Application.getGlobalInfo().getPathBaseDir();
	
	
	/**
	 * Default constructor.
	 *
	 * @param reCoCo the RemoteContainerConfig
	 */
	public JadeRemoteStart(RemoteContainerConfig reCoCo) {
		
		if (debug) System.out.println("Class '" + this.getClass().getName() + "' in debug modue ...");
		
		jadeIsRemoteContainer = reCoCo.getJadeIsRemoteContainer();
		if (reCoCo.getJvmMemAllocInitial()==null && reCoCo.getJvmMemAllocMaximum()==null) {
			this.jvmMemAllocUseDefaults = true;	
			this.jvmMemAllocInitial = JadeRemoteStart.jvmMemo32MB;
			this.jvmMemAllocMaximum = JadeRemoteStart.jvmMemo128MB;
		} else {
			this.jvmMemAllocUseDefaults = false;
			this.jvmMemAllocInitial = reCoCo.getJvmMemAllocInitial();
			this.jvmMemAllocMaximum = reCoCo.getJvmMemAllocMaximum();
		}
		this.jadeShowGUI = reCoCo.getJadeShowGUI();	
		
		if (reCoCo.getJadeServices()!=null) {
			this.jadeServices = reCoCo.getJadeServices();
		}
		if (reCoCo.getJadeHost()!=null) {
			this.jadeHost = reCoCo.getJadeHost();	
		}
		if (reCoCo.getJadePort()!=null) {
			this.jadePort = reCoCo.getJadePort();	
		}
		if (reCoCo.getJadeContainerName()!=null) {
			this.jadeContainerName = reCoCo.getJadeContainerName();	
		}
		if (reCoCo.getJadeJarIncludeList()!=null) {
			this.jadeJarInclude = (ArrayList) reCoCo.getJadeJarIncludeList();	
			this.handelExternalJars();
		}
	}	
	
	/**
	 * If the request of a remote container contains external jars, download
	 * them and include them in the current CLASSPATH.
	 */
	private void handelExternalJars() {
		
		String localDirectoryDownLoad = Application.getGlobalInfo().getPathDownloads();
		String subDirectoryDownloads = Application.getGlobalInfo().getSubFolder4Downloads();
		String projectSubFolder = null;
		String downloadProtocol = "";
		
		if (debug) {
			System.out.println("method handelExternalJars()");
			System.out.println("=> localDirectoryDownLoad=" + localDirectoryDownLoad);
			System.out.println("=> subDirectoryDownloads=" + subDirectoryDownloads);
		}
		
		for (int i = 0; i < jadeJarInclude.size(); i++) {
			
			String httpJarFile = (String) jadeJarInclude.get(i);
			if (debug) System.out.println("=> httpJarFile=" + httpJarFile);
			if (projectSubFolder==null) {
				
				// --- Find sub-folder -------------------------
				projectSubFolder = httpJarFile.replace("http://", "");
				if (debug) System.out.println("=> projectSubFolder(1)=" + projectSubFolder);

				int cut = projectSubFolder.indexOf("/")+1;
				projectSubFolder = projectSubFolder.substring(cut, projectSubFolder.length());
				if (debug) System.out.println("=> projectSubFolder(2)=" + projectSubFolder);
				
				cut = projectSubFolder.indexOf("/");
				projectSubFolder = projectSubFolder.substring(0, cut);
				projectSubFolder+= File.separator;
				if (debug) System.out.println("=> projectSubFolder(3)=" + projectSubFolder);
				
				// --- Correct the Path for the download -------
				localDirectoryDownLoad = localDirectoryDownLoad + projectSubFolder;
				if (debug) System.out.println("=> localDirectoryDownLoad=" + localDirectoryDownLoad);
				
				// --- Check if this Folder exists -------------
				extJarFolder = new File(localDirectoryDownLoad);
				if (extJarFolder.exists()) {
					if (debug) System.out.println("=> Try to delete download direcotory first ...");
					this.deleteFolder(extJarFolder);
					extJarFolder.delete();
				}
				if (debug) System.out.println("=> Try to create download direcotory .../n");
				extJarFolder.mkdir();
			}
			
			// --- Define Destination-File ---------------------
			File remoteFile = new File(httpJarFile);
			String destinFile = localDirectoryDownLoad + remoteFile.getAbsoluteFile().getName();
			if (debug) {
				System.out.println("=> httpJarFile=" + httpJarFile);
				System.out.println("=> destinFile=" + destinFile);
				System.out.println("=> Starting download !");
			}
			
			// --- Start the download --------------------------
			new Download(httpJarFile, destinFile).startDownload();

			// --- Set reminder for the ClassPath --------------
			String classPathEntry = destinFile.substring(destinFile.indexOf(subDirectoryDownloads));
			classPathEntry = "./" + classPathEntry.replace(File.separator, "/") + ";";
			jadeJarIncludeClassPath.add(classPathEntry);
			
			// --- Download-Protocol ---------------------------
			if (downloadProtocol.equals("")==false) {
				downloadProtocol += "|";
			}
			downloadProtocol += remoteFile.getAbsoluteFile().getName();
			
		} // --- end for
		
		if (downloadProtocol.equals("")==false) {
			downloadProtocol = "Download to '" + localDirectoryDownLoad + "': " + downloadProtocol + "";
			System.out.println(downloadProtocol);
		}
	}
	 
 	/**
 	 * Deletes a folder and all sub elements.
 	 *
 	 * @param directory the directory
 	 */
    private void deleteFolder(File directory) {
    	
    	for (File file : directory.listFiles()) {
    		if (file.isDirectory()) {
	    		deleteFolder(file);
	    	}
	    	file.delete();
		}
    }
    
	/**
	 * Action for the Thread-Start. Starts a new JVM with a new Jade-Instance
	 */
	@Override
	public void run() {
		this.startJade();
	}
	
	/**
	 * This Method starts a Jade-Platform within a new Java Virtual Machine.
	 */
	public void startJade() {
		
		String os = System.getProperty("os.name");
		
		String java = "";
		String javaVMArgs = "";
		String classPath = "";
		String jade = "";
		String jadeArgs = "";
		
		// --------------------------------------
		// --- Java-Config ----------------------
		java = "java";
		if (jvmMemAllocUseDefaults==false) {
			javaVMArgs = "-Xms" + jvmMemAllocInitial + " -Xmx" + jvmMemAllocMaximum;
		} 
		
		// --------------------------------------
		// --- Class-Path configuration ---------
		classPath = this.getClassPath();
		// +++ Check for operating system +++
		if (os.toLowerCase().contains("windows")==true) {
			// --- nothing to do here ---
		} else if (os.toLowerCase().contains("linux")==true) {
			classPath = classPath.replaceAll(";", ":");
		}

		// --------------------------------------
		// --- Jade configuration ---------------
		jade += "agentgui.core.application.Application -jade" + " ";
		if (jadeServices!=null) {
			jadeArgs += "-services  " + jadeServices + " ";
		}
		if (jadeIsRemoteContainer) {
			jadeArgs += "-container ";
			jadeArgs += "-container-name " + jadeContainerName + " ";
		} 
		jadeArgs += "-host " + jadeHost + " ";
		jadeArgs += "-port " + jadePort + " ";
		// -- Configure -local-host -------------
		String localHost = Application.getGlobalInfo().getJadeDefaultProfile().getProperties().getProperty(Profile.LOCAL_HOST);
		if (localHost!=null && localHost.equals("")==false) {
			jadeArgs += "-local-host " + localHost + " ";	
		}
		// --- Show GUI? ------------------------
		if (jadeShowGUI) {
			jadeArgs += jadeShowGUIAgentName + jadeContainerName + ":jade.tools.rma.rma ";
		}		
		jadeArgs = jadeArgs.trim();
		
		// --------------------------------------
		// --- merge execute statement ----------
		String execute = java + " " + javaVMArgs + " " + classPath + " " + jade  + " " + jadeArgs;
		execute = execute.replace("  ", " ");
		System.out.println( "Execute: " + execute);
		
		// --------------------------------------
		Scanner in = null;
		Scanner err = null;
		try {
			
			String[] arg = execute.split(" ");
			ProcessBuilder proBui = new ProcessBuilder(arg);
			proBui.redirectErrorStream(true);
			proBui.directory(new File(pathBaseDir));
			
			Process p = proBui.start();
			
			in = new Scanner(p.getInputStream());
			in.useDelimiter("\\Z");
			
			err = new Scanner(p.getErrorStream());
			err.useDelimiter("\\Z");
			
			while (in.hasNextLine() || err.hasNextLine() ) {
				if (in.hasNextLine()) {
					System.out.println("[" + jadeContainerName + "]: " + in.nextLine());	
				}
				if (err.hasNextLine()){
					System.err.println("[" + jadeContainerName + "]: " + err.nextLine());	
				}
			}
			System.out.println("Killed Container [" + jadeContainerName + "]");
		    
			// --- Remove external jars from the download-folder ----
			if (extJarFolder!=null ) {
        		if (extJarFolder.exists()==true) {
        			deleteFolder(extJarFolder);
            		extJarFolder.delete();	
        		}
        	}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			err.close();	
		}

	}

	/**
	 * This method configures the CLASSPATH for the remote-container.
	 * @return the new class path
	 */
	private String getClassPath() {
		
		String classPath = "";
		
		// -----------------------------------------------------
		// --- Basic - Configuration ---------------------------
		classPath += "-classpath ";
		classPath += ".;";
		
		// -----------------------------------------------------
		// --- Agent.GUI with its integrated libraries ---------
		String agentGuiJar = Application.getGlobalInfo().getFileRunnableJar(false);
		agentGuiJar = agentGuiJar.replace("\\", "/");
		classPath += "./" + agentGuiJar + ";";
		
		// -----------------------------------------------------
		// --- Configure external jar-files -------------------- 
		for (int i = 0; i < this.jadeJarIncludeClassPath.size(); i++) {
			String jar = (String) jadeJarIncludeClassPath.get(i);
			classPath += jar;
		}

		return classPath;
	}
	
	/**
	 * Checks if is memory allocation for the new JVM will be the default one.
	 *
	 * @return the jvmMemAllocUseDefaults
	 */
	public boolean isJVMMemAllocUseDefaults() {
		return jvmMemAllocUseDefaults;
	}
	/**
	 * Sets the memory allocation for the new JVM to be the default value or not.
	 *
	 * @param useDefaults the new jVM mem alloc use defaults
	 */
	public void setJVMMemAllocUseDefaults(boolean useDefaults) {
		this.jvmMemAllocUseDefaults = useDefaults;
	}

	/**
	 * @return the jvmMemAllocInitial
	 */
	public String getJVMMemAllocInitial() {
		return jvmMemAllocInitial;
	}
	/**
	 * @param jvmMemAllocInitial the jvmMemAllocInitial to set
	 */
	public void setJVMMemAllocInitial(String jvmMemAllocInitial) {
		this.jvmMemAllocInitial = jvmMemAllocInitial;
	}

	/**
	 * @return the jvmMemAllocMaximum
	 */
	public String getJVMMemAllocMaximum() {
		return jvmMemAllocMaximum;
	}
	/**
	 * @param jvmMemAllocMaximum the jvmMemAllocMaximum to set
	 */
	public void setJVMMemAllocMaximum(String jvmMemAllocMaximum) {
		this.jvmMemAllocMaximum = jvmMemAllocMaximum;
	}

	/**
	 * @return the jadeIsRemoteContainer
	 */
	public boolean isJADEIsRemoteContainer() {
		return jadeIsRemoteContainer;
	}
	/**
	 * @param jadeIsRemoteContainer the jadeIsRemoteContainer to set
	 */
	public void setJADEIsRemoteContainer(boolean jadeIsRemoteContainer) {
		this.jadeIsRemoteContainer = jadeIsRemoteContainer;
	}

	/**
	 * @return the jadeShowGUI
	 */
	public boolean isJADEShowGUI() {
		return jadeShowGUI;
	}
	/**
	 * @param jadeShowGUI the jadeShowGUI to set
	 */
	public void setJADEShowGUI(boolean jadeShowGUI) {
		this.jadeShowGUI = jadeShowGUI;
	}

	/**
	 * @return the jadeShowGUIAgentName
	 */
	public String getJadeShowGUIAgentName() {
		return jadeShowGUIAgentName;
	}
	/**
	 * @param jadeShowGUIAgentName the jadeShowGUIAgentName to set
	 */
	public void setJadeShowGUIAgentName(String jadeShowGUIAgentName) {
		this.jadeShowGUIAgentName = jadeShowGUIAgentName;
	}

	/**
	 * @return the jadeHost
	 */
	public String getJADEHost() {
		return jadeHost;
	}
	/**
	 * @param jadeHost the jadeHost to set
	 */
	public void setJADEHost(String jadeHost) {
		this.jadeHost = jadeHost;
	}

	/**
	 * @return the jadePort
	 */
	public String getJADEPort() {
		return jadePort;
	}
	/**
	 * @param jadePort the jadePort to set
	 */
	public void setJADEPort(String jadePort) {
		this.jadePort = jadePort;
	}

	/**
	 * @return the jadeContainerName
	 */
	public String getJADEContainerName() {
		return jadeContainerName;
	}
	/**
	 * @param jadeContainerName the jadeContainerName to set
	 */
	public void setJADEContainerName(String jadeContainerName) {
		this.jadeContainerName = jadeContainerName;
	}

	/**
	 * @param jadeServices the jadeServices to set
	 */
	public void setJADEServices(String jadeServices) {
		this.jadeServices = jadeServices;
	}
	/**
	 * @return the jadeServices
	 */
	public String getJADEServices() {
		return jadeServices;
	}
	
}