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
package agentgui.core.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import agentgui.core.application.Application;
import agentgui.core.application.Language;
import agentgui.core.common.ClassLoaderUtil;
import agentgui.core.common.PathHandling;
import agentgui.core.environment.EnvironmentController;
import agentgui.core.environment.EnvironmentPanel;
import agentgui.core.environment.EnvironmentType;
import agentgui.core.gui.ProjectWindow;
import agentgui.core.gui.components.JPanel4Visualisation;
import agentgui.core.gui.projectwindow.BaseAgents;
import agentgui.core.gui.projectwindow.Distribution;
import agentgui.core.gui.projectwindow.JadeSetup;
import agentgui.core.gui.projectwindow.OntologyTab;
import agentgui.core.gui.projectwindow.ProjectDesktop;
import agentgui.core.gui.projectwindow.ProjectInfo;
import agentgui.core.gui.projectwindow.ProjectResources;
import agentgui.core.gui.projectwindow.ProjectWindowTab;
import agentgui.core.gui.projectwindow.TabForSubPanels;
import agentgui.core.gui.projectwindow.simsetup.SimulationEnvironment;
import agentgui.core.gui.projectwindow.simsetup.StartSetup;
import agentgui.core.jade.ClassSearcher;
import agentgui.core.ontologies.OntologyVisualisationHelper;
import agentgui.core.plugin.PlugIn;
import agentgui.core.plugin.PlugInLoadException;
import agentgui.core.plugin.PlugInNotification;
import agentgui.core.plugin.PlugInsLoaded;
import agentgui.core.resources.VectorOfProjectResources;
import agentgui.core.sim.setup.SimulationSetups;
import agentgui.core.webserver.JarFileCreator;

/**
 * This is the class, which holds all necessary informations about a project.<br> 
 * In order to allow multiple access to the instance of Project, we designed it <br>
 * in the common <b>MVC pattern</b> (Model-View-Controller)<br>
 * 
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
@XmlRootElement public class Project extends Observable {

	// --- public statics --------------------------------------
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String SAVED = "ProjectSaved";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectName = "ProjectName";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectDescription = "ProjectDescription";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectFolder= "ProjectFolder";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectView = "ProjectView";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_EnvironmentModel= "EnvironmentModel";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_StartArguments4BaseAgent = "StartArguments4BaseAgents";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_TimeModelClass = "TimeModelConfiguration";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectOntology = "ProjectOntology";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_ProjectResources = "ProjectResources";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_JadeConfiguration = "JadeConfiguration";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_DistributionSetup = "DistributionSetup";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_RemoteContainerConfiguration = "RemoteContainerConfiguration";
	/** Constant value in order to inform the Observer about changes of this kind */
	@XmlTransient public static final String CHANGED_UserRuntimeObject = "UserRuntimeObject";
	
	/** Constant value in order to set the project view */
	@XmlTransient public static final String VIEW_Developer = "Developer";
	@XmlTransient public static final String VIEW_User = "User";
	@XmlTransient public static final String VIEW_Maximize = "MaximizeView";
	@XmlTransient public static final String VIEW_Restore = "RestoreView";
	
	// --- private statics -------------------------------------
	@XmlTransient private static final String newLine = Application.getGlobalInfo().getNewLineSeparator();	
	
	// --- Constants -------------------------------------------
	@XmlTransient private String defaultSubFolder4Setups   = "setups";
	@XmlTransient private String defaultSubFolderEnvSetups = "setupsEnv";
	@XmlTransient private String[] defaultSubFolders	   = {defaultSubFolder4Setups,
															  defaultSubFolderEnvSetups, 
															  };
	
	
	/** This is the 'view' in the context of the mentioned MVC pattern */
	@XmlTransient public ProjectWindow projectWindow = null;
	/** This panel holds the instance of environment model display */
	@XmlTransient private JPanel4Visualisation visualisationTab4SetupExecution = null;
	/**
	 * This JDesktopPane can be used in order to allow further user interactions within the project
	 * by using individual JInternalFrames. If frames are added to this desktop, the focus will be 
	 * set to it.
	 */
	@XmlTransient public JDesktopPane projectDesktop = null;
	

	/** Indicates that the project is unsaved or not */
	@XmlTransient public boolean isUnsaved = false;
	

	@XmlTransient private String projectFolder;
	@XmlTransient private String projectFolderFullPath;
	
	// --- Variables saved within the project file -------------
	@XmlElement(name="projectName")			private String projectName;
	@XmlElement(name="projectDescription")	private String projectDescription;
	@XmlElement(name="projectView")			private String projectView;			// --- Developer / End-User ---

	@XmlElement(name="environmentModel")	private String environmentModelName;	
	
	/**
	 * This Vector holds the additional resources which are used for the current project 
	 * (external jar files or the binary folder of a development project)  
	 */
	@XmlElementWrapper(name = "projectResources")
	@XmlElement(name="projectResource")
	private VectorOfProjectResources projectResources = new VectorOfProjectResources();
	
	/**
	 * This Vector handles the list of resources which should be loadable in case of 
	 * distributed simulations. The idea is, that for example external jar-files can
	 * be distributed to a remote location, where such jar-files will be added automatically 
	 * to the ClassPath of the starting JVM.         
	 */
	@XmlTransient public Vector<String> downloadResources = new Vector<String>();
	
	/**
	 * This Vector will store the class names of the PlugIns which are used within the project
	 */
	@XmlElementWrapper(name="plugins")
	@XmlElement(name="className")
	public Vector<String> plugIns_Classes = new Vector<String>();
	/**
	 * This extended Vector will hold the concrete instances of the PLugIns loaded in this project 
	 */
	@XmlTransient public PlugInsLoaded plugInsLoaded = new PlugInsLoaded();
	@XmlTransient private boolean plugInVectorLoaded = false;
	
	/**
	 * This class is used for the management of the used Ontology's inside a project.
	 * It handles the concrete instances.
	 */
	@XmlTransient private OntologyVisualisationHelper ontologyVisualisationHelper;
	
	/**
	 * This Vector is used in order to store the class names of the used ontology's in the project file
	 */
	@XmlElementWrapper(name = "subOntologies")
	@XmlElement(name="subOntology")
	private Vector<String> subOntologies = new Vector<String>();
	
	/**
	 * This extended HashTable is used in order to save the relationship between an agent (agents class name)
	 * and the classes (also class names) which can be used as start-argument for the agents   
	 */
	@XmlElement(name="agentStartConfiguration")
	private AgentStartConfiguration agentStartConfiguration = null;
	
	/**
	 * Configuration settings for the TimeModel used in this Project  
	 */
	@XmlElement(name="timeModelClass")
	private String timeModelClass;
	
	/**
	 * This field manages the configuration of JADE (e. g. JADE-Port 1099 etc.)
	 */
	@XmlElement(name="jadeConfiguration")
	public PlatformJadeConfig JadeConfiguration = new PlatformJadeConfig();
	
	/** The distribution setup. */
	@XmlElement(name="distributionSetup")
	public DistributionSetup distributionSetup = new DistributionSetup();
	
	/**
	 * This field manages the configuration of remote container if needed
	 */
	@XmlElement(name="remoteContainerConfiguration")
	private RemoteContainerConfiguration remoteContainerConfiguration = new RemoteContainerConfiguration();
	
	/**
	 * This field can be used in order to provide customised objects during
	 * the runtime of a project. This will be not stored within the file 'agentgui.xml' 
	 */
	@XmlTransient 
	private Serializable userRuntimeObject = null;
	
	/**
	 * This attribute holds the instance of the currently selected SimulationSetup
	 */
	@XmlElement(name="simulationSetupCurrent")
	public String simulationSetupCurrent = null;
	/**
	 * This extended HashTable is used in order to store the SimulationsSetup's names 
	 * and their file names 
	 */
	@XmlElementWrapper(name = "simulationSetups")
	public SimulationSetups simulationSetups = new SimulationSetups(this, simulationSetupCurrent);

	/**
	 * The environment controllerGUI of the project. Usually a subclass of {@link EnvironmentPanel}.
	 */
	@XmlTransient
	private EnvironmentController environmentController = null;
	
	/**
	 * This model contains all known environment types of the application 
	 * and can be used for tailored environment models / types
	 */
	@XmlTransient
	private DefaultComboBoxModel environmentsComboBoxModel = null;
	
	
	/**
	 * Default constructor for Project
	 */
	public Project() {
	
		// ----------------------------------------------------------
		// --- Fill the projects ComboBoxModel for environments ----- 
		environmentsComboBoxModel = new DefaultComboBoxModel();
		
		Vector<EnvironmentType> appEnvTypes = Application.getGlobalInfo().getKnownEnvironmentTypes();
		for (int i = 0; i < appEnvTypes.size(); i++) {
			EnvironmentType envType = (EnvironmentType) appEnvTypes.get(i);
			environmentsComboBoxModel.addElement(envType);
		}
		// ----------------------------------------------------------
		
	};
	
	/**
	 * This methods adds all default Agent.GUI-tabs to the ProjectWindow 
	 */
	public void addDefaultTabs() {
		
		ProjectWindowTab pwt = null;
		// ------------------------------------------------
		// --- General Informations -----------------------
		pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER, 
								   Language.translate("Info"), null, null, 
								   new ProjectInfo(this), null);
		pwt.add();
		
		// ------------------------------------------------
		// --- Configuration ------------------------------
		pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
				   Language.translate("Konfiguration"), null, null, 
				   new TabForSubPanels(this), null);
		pwt.add();
		projectWindow.registerTabForSubPanels(ProjectWindowTab.TAB_4_SUB_PANES_Configuration, pwt);
		
		
			// --- External Resources -------------------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
					   Language.translate("Ressourcen"), null, null, 
					   new ProjectResources(this), Language.translate("Konfiguration"));
			pwt.add();
			// --- Used Ontologies ----------------------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
					   Language.translate("Ontologien"), null, null, 
					   new OntologyTab(this), Language.translate("Konfiguration"));
			pwt.add();
			// --- Project Agents -----------------------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
					   Language.translate("Agenten"), null, null, 
					   new BaseAgents(this), Language.translate("Konfiguration"));
			pwt.add();
			// --- JADE-Configuration -------------------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
					   Language.translate("JADE-Konfiguration"), null, null, 
					   new JadeSetup(this), Language.translate("Konfiguration"));
			pwt.add();
			// --- distribution + thresholds --------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_DEVELOPER, 
					   Language.translate("Verteilung + Grenzwerte"), null, null, 
					   new Distribution(this), Language.translate("Konfiguration"));
			pwt.add();

		// ------------------------------------------------
		// --- Simulations-Setup --------------------------
		pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER, 
				   Language.translate("Simulations-Setup"), null, null, 
				   new TabForSubPanels(this), null);
		pwt.add();
		projectWindow.registerTabForSubPanels(ProjectWindowTab.TAB_4_SUB_PANES_SimSetup, pwt);
		
			// --- start configuration for agents ---------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER, 
					   Language.translate("Agenten-Start"), null, null, 
					   new StartSetup(this), Language.translate("Simulations-Setup"));
			pwt.add();
			// --- simulation environment -----------------
			pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER_VISUALIZATION, 
					   Language.translate("Simulationsumgebung"), null, null, 
					   new SimulationEnvironment(this), Language.translate("Simulations-Setup"));
			pwt.add();
			

		// --- Visualisation ------------------------------
		this.visualisationTab4SetupExecution = new JPanel4Visualisation(this, Language.translate("Simulations-Visualisierung"));
		pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER_VISUALIZATION, 
				   Language.translate("Simulations-Visualisierung"), null, null, 
				   this.getVisualisationTab4SetupExecution(), null);
		pwt.add();
		// --- Project Desktop ----------------------------
		pwt = new ProjectWindowTab(this, ProjectWindowTab.DISPLAY_4_END_USER, 
				   Language.translate("Projekt-Desktop"), null, null, 
				   new ProjectDesktop(this), null);
		pwt.add();
		
		// --- Expand the tree view -----------------------
		this.projectWindow.projectTreeExpand2Level(3, true);
		
	}
	
	/**
	 * Saves the current MAS-Project to the file 'agentgui.xml'
	 */
	public boolean save() {
		// --- Save the current project -------------------
		Application.getMainWindow().setStatusBar( projectName + ": " + Language.translate("speichern") + " ... ");
		try {			
			// --- prepare Context and Marshaller ---------
			JAXBContext pc = JAXBContext.newInstance( this.getClass() ); 
			Marshaller pm = pc.createMarshaller(); 
			pm.setProperty( Marshaller.JAXB_ENCODING, "UTF-8" );
			pm.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE ); 
			//pm.marshal( this, System.out );
			// --- Write values to xml-File ---------------
			Writer pw = new FileWriter( projectFolderFullPath + Application.getGlobalInfo().getFileNameProject() );
			pm.marshal(this, pw);
			pw.close();
			
			// --- Save the userRuntimeObject in the Project into a different file as a serializable binary object.
			FileOutputStream fos = null;
			ObjectOutputStream out = null;
		    try {
		       fos = new FileOutputStream(projectFolderFullPath + Application.getGlobalInfo().getFilenameProjectUserObject());
		       out = new ObjectOutputStream(fos);
		       out.writeObject(this.userRuntimeObject);
		       out.close();
		       
		    } catch(IOException ex) {
		    	ex.printStackTrace();
		    }
		    
			// --- Save the current SimulationSetup -------
			this.simulationSetups.setupSave();
			
			this.isUnsaved = false;			

			// --- Notification ---------------------------
			this.setNotChangedButNotify(Project.SAVED);

		} 
		catch (Exception e) {
			System.out.println("XML-Error while saving Project-File!");
			e.printStackTrace();
		}
		Application.getMainWindow().setStatusBar("");
		return true;		
	}
	
	/**
	 * This method closes the current project. If necessary it will try to save the before.
	 * @return Returns true if saving was successful
	 */
	public boolean close() {
		
		// --- Close project? -----------------------------
		String msgHead = null;
		String msgText = null;
		Integer msgAnswer = 0;
		
		Application.getMainWindow().setStatusBar(Language.translate("Projekt schlie�en") + " ...");
		if ( isUnsaved == true ) {
			msgHead = Language.translate("Projekt '@' speichern?");
			msgHead = msgHead.replace( "'@'", "'" + projectName + "'");			
			msgText = Language.translate(
						"Das aktuelle Projekt '@' ist noch nicht gespeichert!" + newLine + 
						"M�chten Sie es nun speichern ?");
			msgText = msgText.replace( "'@'", "'" + projectName + "'");
			
			msgAnswer = JOptionPane.showInternalConfirmDialog (Application.getMainWindow().getContentPane(), msgText, msgHead, JOptionPane.YES_NO_CANCEL_OPTION );
			if (msgAnswer == JOptionPane.CANCEL_OPTION) {
				return false;
			} else if (msgAnswer == JOptionPane.YES_OPTION) {
				if ( save()== false ) 
					return false;
			}
		}
		// --- ggf. noch Jade beenden ---------------------
		if (Application.getJadePlatform().jadeStopAskUserBefore()==false) {
			return false;
		}

		// --- Clear/Dispose EnvironmentPanel -------------
		EnvironmentController envController = this.getEnvironmentController();
		if (envController!=null) {
			EnvironmentPanel envPanel = envController.getEnvironmentPanel();
			if (envPanel!=null) {
				envPanel.dispose();
			}
		}
		
		// ------------------------------------------------		
		// --- Projekt kann geschlossen werden ------------
		// ------------------------------------------------
		// --- Clear PlugIns ------------------------------
		this.plugInVectorRemove();
		// --- Clear ClassPath ----------------------------
		this.resourcesRemove();
		
		// --- Close Project ------------------------------
		ProjectsLoaded loadedProjects = Application.getProjectsLoaded();
		int Index = loadedProjects.getIndexByName(projectName); // --- Merker Index ---		
		projectWindow.dispose();
		loadedProjects.remove(this);
		
		int nProjects = loadedProjects.count();
		if (nProjects > 0) {
			if ( Index+1 > nProjects ) Index = nProjects-1;  
			Application.setProjectFocused(loadedProjects.get(Index));
			Application.getProjectFocused().setFocus(true);
			Application.setTitelAddition( Application.getProjectFocused().projectName );
		} else {
			Application.setProjectFocused(null);
			Application.getMainWindow().setCloseButtonPosition(false);
			Application.setTitelAddition("");
		}
		Application.setStatusBar("");
		return true;
	}

	// ----------------------------------------------------------------------------------
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++	
	// --- Here we come with methods for (un-) load ProjectPlugIns --- Start ------------ 
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ----------------------------------------------------------------------------------	
	/**
	 * This method will load the ProjectPlugIns, which are configured for the
	 * current project (plugins_Classes). It will be executed only one time during 
	 * the 'ProjectsLoaded.add()' execution. After this no further functionality 
	 * can be expected. 
	 */
	public void plugInVectorLoad() {
		if (this.plugInVectorLoaded==false) {
			// --- load all plugins configured in 'plugIns_Classes' -----------
			for (int i = 0; i < this.plugIns_Classes.size(); i++) {
				if (this.plugInLoad(this.plugIns_Classes.get(i), false)== false ) {
					return;
				}
			}
			this.plugInVectorLoaded = true;
		}
	}
	/**
	 * This method will remove/unload the plugins in 
	 * descending order of the Vector 'plugins_Loaded'.  
	 */
	private void plugInVectorRemove() {
		// --- unload/remove all plugins configured in 'plugIns_Loaded' -----------
		for (int i = this.plugInsLoaded.size(); i>0; i--) {
			this.plugInRemove(this.plugInsLoaded.get(i-1), false);
		}
		this.plugInVectorLoaded = false;
	}
	/**
	 * This method will reload the configured PlugIns
	 */
	public void plugInVectorReload() {
		// --- remove all loaded PlugIns ------------------
		this.plugInVectorRemove();
		// --- re-initialise the 'PlugInsLoaded'-Vector ---
		plugInsLoaded = new PlugInsLoaded();
		// --- load all configured PlugIns to the project -
		this.plugInVectorLoad();
		
	}
	/**
	 * This method loads a single PlugIn, given by its class reference
	 * @param pluginReference
	 */
	public boolean plugInLoad(String pluginReference, boolean add2PlugInReferenceVector) {
		
		String MsgHead = "";
		String MsgText = "";
			
		try {
			if (plugInsLoaded.isLoaded(pluginReference)==true) {
				// --- PlugIn can't be loaded because it's already there ------
				PlugIn ppi = plugInsLoaded.getPlugIn(pluginReference);
				
				MsgHead = Language.translate("Fehler - PlugIn: ") + ppi.getName() + " !" ;
				MsgText = Language.translate("Das PlugIn wurde bereits in das Projekt integriert " +
						"und kann deshalb nicht erneut hinzugef�gt werden!");
				JOptionPane.showInternalMessageDialog( this.projectWindow, MsgText, MsgHead, JOptionPane.ERROR_MESSAGE);
				
			} else {
				// --- PlugIn can be loaded -----------------------------------
				PlugIn ppi = plugInsLoaded.loadPlugin(this, pluginReference);
				this.setNotChangedButNotify(new PlugInNotification(PlugIn.ADDED, ppi));
				if (add2PlugInReferenceVector) {
					this.plugIns_Classes.add(pluginReference);	
				}
			}
			
		} catch (PlugInLoadException err) {
			err.printStackTrace();
			return false;
		}		
		return true;
	}
	/**
	 * This method will unload and remove a single PlugIn. If removeFromProjectReferenceVector is set
	 * to true, the PlugIn-reference will be also removed from the list of PlugIns', which has to be 
	 * loaded with the project.
	 *  
	 * @param plugIn The PlugIn instance to be removed
	 * @param removeFromProjectReferenceVector 
	 */
	public void plugInRemove(PlugIn plugIn, boolean removeFromProjectReferenceVector) {
		this.plugInsLoaded.removePlugIn(plugIn);
		this.setNotChangedButNotify(new PlugInNotification(PlugIn.REMOVED, plugIn));
		if (removeFromProjectReferenceVector) {
			this.plugIns_Classes.remove(plugIn.getClassReference());
		}
	}
	// ----------------------------------------------------------------------------------
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// --- Here we come with methods for (un-) load ProjectPlugIns --- End --------------
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// ----------------------------------------------------------------------------------
	
	/**
	 * This Procedure creates the default Project-Structure  for a new project. It creates the 
	 * default folders ('agents' 'ontology' 'envSetups' 'resources') and creates default files
	 * like the project ontology main class 'AgentGUIProjectOntology'
	 */
	public void createDefaultProjectStructure() {
		// --- create default folders --------------------- 
		this.checkCreateSubFolders();
	}
	/**
	 * Controls and/or creates whether the sub-folder-Structure exists 
	 * @return boolean true or false :-)
	 */	
	public boolean checkCreateSubFolders() {
		
		String NewDirName = null;
		File f = null;
		boolean Error = false;
		
		for (int i=0; i< this.defaultSubFolders.length; i++  ) {
			// --- ggf. Verzeichnis anlegen ---------------
			NewDirName = this.projectFolderFullPath + defaultSubFolders[i];
			f = new File(NewDirName);
			if ( f.isDirectory() == false) {
				// --- Verzeichnis anlegen ----------------
				if ( f.mkdir() == false ) {
					Error = true;	
				}				
			}
		};
		
		// --- Set Return-Value ---------------------------
		if ( Error == true ) {
			return false;
		}
		else {
			return true;	
		}		
	}
	
	/**
	 * Allow change notifications within the observer pattern without necessarily saving such changes
	 * @param reason
	 */
	public void setNotChangedButNotify(Object reason) {
		setChanged();
		notifyObservers(reason);		
	}
	/**
	 * To prevent the closing of the project without saving
	 * @param reason
	 */
	public void setChangedAndNotify(Object reason) {
		isUnsaved = true;
		setChanged();
		notifyObservers(reason);		
	}
	
	/**
	 * Moves the requested Project-Window to the front
	 */
	public void setFocus(boolean forceClassPathReload) {
		
		if (forceClassPathReload) {
			// --- stop Jade ------------------------------
			if (Application.getJadePlatform().jadeStopAskUserBefore()==false) {
				return;
			}
			// --- unload ClassPath -----------------------
			this.resourcesRemove();
		}
		
		this.projectWindow.moveToFront();
		Application.setTitelAddition(projectName);
		Application.setProjectFocused(this);
		Application.getProjectsLoaded().setProjectView();
		this.setMaximized();
		
		if (forceClassPathReload) {
			// --- ClassPath laden ------------------------
			this.resourcesLoad();	
		}
	}
	/**
	 * Maximise the Project-Window within the AgenGUI-Application
	 */
	public void setMaximized() {
		// --- Validate the main application window -----------------
		Application.getMainWindow().validate();
		// --- Be sure that everything is there as needed ----------- 
		if (projectWindow!=null && projectWindow.getParent()!=null) {
			// --- Maximise now -------------------------------------
			((BasicInternalFrameUI) projectWindow.getUI()).setNorthPane(null);
			DesktopManager dtm = Application.getMainWindow().getJDesktopPane4Projects().getDesktopManager();
			if (dtm!=null) {
				dtm.maximizeFrame(projectWindow);	
			}
		}
	}
	
	/**
	 * Set method for the project name
	 * @param newProjectName the projectName to set
	 */
	public void setProjectName(String newProjectName) {
		projectName = newProjectName;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_ProjectName);
	}
	/**
	 * @return the projectName
	 */
	@XmlTransient
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * @param newProjectDescription the projectDescription to set
	 */
	public void setProjectDescription(String newProjectDescription) {
		projectDescription = newProjectDescription;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_ProjectDescription);
	}
	/**
	 * @return the projectDescription
	 */
	@XmlTransient
	public String getProjectDescription() {
		return projectDescription;
	}
	/**
	 * @param newProjectFolder the projectFolder to set
	 */
	@XmlTransient
	public void setProjectFolder(String newProjectFolder) {
		projectFolder = newProjectFolder;
		projectFolderFullPath = Application.getGlobalInfo().PathProjects(true) + projectFolder + File.separator;
		setChanged();
		notifyObservers(CHANGED_ProjectFolder);
	}
	/**
	 * @return the projectFolder
	 */
	public String getProjectFolder() {
		return projectFolder;
	}
	/**
	 * @return the ProjectFolderFullPath
	 */
	public String getProjectFolderFullPath() {
		return projectFolderFullPath;
	}
	
	/**
	 * Gets the projects temporary folder.
	 *
	 * @return the projects temporary folder
	 */
	public String getProjectTempFolderFullPath(){
		String tmpFolder = this.getProjectFolderFullPath() + "~tmp" + File.separator;
		File tmpFile = new File(tmpFolder);
		if (tmpFile.exists()==false) {
			tmpFile.mkdir();
		}
		tmpFile.deleteOnExit();
		return tmpFolder;
	}
	/**
	 * @param projectView the projectView to set
	 */
	@XmlTransient
	public void setProjectView(String projectView) {
		this.projectView = projectView;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_ProjectView);
	}
	/**
	 * @return the projectView
	 */
	public String getProjectView() {
		if (this.projectView==null) {
			return "";
		} else {
			return this.projectView;	
		}		
	}

	/**
	 * @param environmentModel the environmentModel to set
	 */
	@XmlTransient
	public void setEnvironmentModelName(String environmentModel) {
		this.environmentModelName = environmentModel;
		this.isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_EnvironmentModel);
	}
	/**
	 * @return the environmentModel
	 */
	public String getEnvironmentModelName() {
		return environmentModelName;
	}
	/**
	 * @return the 'EnvironmentType' of the current EnvironmentModel
	 */
	public EnvironmentType getEnvironmentModelType() {
		return Application.getGlobalInfo().getKnownEnvironmentTypes().getEnvironmentTypeByKey(this.environmentModelName);
	}
	
	/**
	 * Gets the default environment setup folder
	 * @return The default environment setup folder
	 */
	public String getEnvSetupPath(){
		return projectFolderFullPath + defaultSubFolderEnvSetups;
	}		
	
	/**
	 * @param newSubFolder4Setups the defaultSubFolderOntology to set
	 */
	public void setSubFolder4Setups(String newSubFolder4Setups) {
		defaultSubFolder4Setups = newSubFolder4Setups;
	}
	/**
	 * @return the defaultSubFolderSetups
	 */
	public String getSubFolder4Setups(boolean fullPath) {
		if (fullPath==true) {
			return getProjectFolderFullPath() + defaultSubFolder4Setups + File.separator;
		} else {
			return defaultSubFolder4Setups;	
		}	
	}
	
	/**
	 * @param newSubFolderEnvSetups the defaultSubFolderEnvSetups to set
	 */
	public void setSubFolderEnvSetups(String newSubFolderEnvSetups) {
		defaultSubFolderEnvSetups = newSubFolderEnvSetups;
	}
	/**
	 * @return the defaultSubFolderEnvSetups
	 */
	public String getSubFolderEnvSetups() {
		return defaultSubFolderEnvSetups;
	}

	
	/**
	 * Sets the reference Vector for the sub ontologies.
	 * @param subOntologies the new sub ontologies
	 */
	public void setSubOntologies(Vector<String> subOntologies) {
		this.subOntologies = subOntologies;
	}
	/**
	 * Returns the Vector of references to the used sub ontologies.
	 * @return the sub ontologies
	 */
	@XmlTransient
	public Vector<String> getSubOntologies() {
		return subOntologies;
	}
	/**
	 * Adds a new sub ontology to the current project  
	 * @param newSubOntology
	 */
	public void subOntologyAdd(String newSubOntology) {
		if (this.getSubOntologies().contains(newSubOntology)==false) {
			this.getSubOntologies().add(newSubOntology);
			this.getOntologyVisualisationHelper().addSubOntology(newSubOntology);
			isUnsaved = true;
			setChanged();
			notifyObservers(CHANGED_ProjectOntology);
		} 
	}
	/**
	 * Removes a new sub ontology from the current project ontology 
	 * @param subOntology2Remove
	 */
	public void subOntologyRemove(String subOntology2Remove) {
		this.getSubOntologies().remove(subOntology2Remove);
		this.getOntologyVisualisationHelper().removeSubOntology(subOntology2Remove);
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_ProjectOntology);
	}
	
	/**
	 * Returns the ontology visualisation helper for this project.
	 * @return the OntologyVisualisationHelper
	 */
	public OntologyVisualisationHelper getOntologyVisualisationHelper() {
		if (this.ontologyVisualisationHelper==null) {
			this.ontologyVisualisationHelper = new OntologyVisualisationHelper(this.getSubOntologies());
			this.setSubOntologies(this.ontologyVisualisationHelper.getSubOntologies());
		}
		return ontologyVisualisationHelper;
	}

	/**
	 * Sets the agent configuration.
	 * @param agentStartConfiguration the new agent configuration
	 */
	public void setAgentStartConfiguration(AgentStartConfiguration agentStartConfiguration) {
		this.agentStartConfiguration = agentStartConfiguration;
		this.setAgentStartConfigurationUpdated();
	}
	/**
	 * Returns the agent configuration.
	 * @return the agent configuration
	 */
	@XmlTransient
	public AgentStartConfiguration getAgentStartConfiguration() {
		if (this.agentStartConfiguration==null) {
			this.agentStartConfiguration = new AgentStartConfiguration();
			this.setAgentStartConfigurationUpdated();
		}
		return agentStartConfiguration;
	}
	/**
	 * Informs all observers about changes at the AgentConfiguration 'AgentConfig'
	 */
	public void setAgentStartConfigurationUpdated() {
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_StartArguments4BaseAgent);
	}

	/**
	 * Sets the time model class and configuration for this project.
	 * @param timeModelConfiguration the new time model class
	 */
	public void setTimeModelClass(String timeModelClass) {
		this.timeModelClass = timeModelClass;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_TimeModelClass);
	}
	/**
	 * Returns the time model class and configuration for this Project.
	 * @return the time model class
	 */
	@XmlTransient
	public String getTimeModelClass() {
		return timeModelClass;
	}

	/**
	 * @param projectResources the projectResources to set
	 */
	public void setProjectResources(VectorOfProjectResources projectResources) {
		this.projectResources = projectResources;
	}
	/**
	 * @return the projectResources
	 */
	@XmlTransient
	public VectorOfProjectResources getProjectResources() {
		return projectResources;
	}
	
	/**
	 * This method adds external project resources (*.jar-files) to the CLATHPATH  
	 */
	public void resourcesLoad() {

		for(String jarFile4Display : this.getProjectResources()) {

			jarFile4Display = PathHandling.getPathName4LocalFileSystem(jarFile4Display);
			
			String prefixText = null;
			String suffixText = null;

			try {
				
				String jarFileCorrected = ClassLoaderUtil.adjustPathForLoadIn(jarFile4Display, this.getProjectFolderFullPath());
				File file = new File(jarFileCorrected);
				if (file.exists()==false) {
					prefixText = "ERROR";
					suffixText = Language.translate("Datei nicht gefunden!");
					
				} else if (file.isDirectory()) {
					prefixText = "";
					suffixText = "proceeding started";
					
					// --- Prepare the path variables -----
					String pathBin = file.getAbsolutePath();
					String pathBinHash = ((Integer)pathBin.hashCode()).toString();
					String jarArchiveFileName = "BIN_DUMP_" + pathBinHash + ".jar";
					String jarArchivePath = this.getProjectTempFolderFullPath() + jarArchiveFileName;
					
					// --- Create the jar-file ------------
					JarFileCreator jarCreator = new JarFileCreator(pathBin);
					File jarArchiveFile = new File(jarArchivePath);
					jarCreator.createJarArchive(jarArchiveFile);
					jarArchiveFile.deleteOnExit();
					
					// --- Add to the class loader --------
					ClassLoaderUtil.addFile(jarArchiveFile.getAbsoluteFile());
					ClassLoaderUtil.addJarToClassPath(jarArchivePath);
					
					// --- prepare the notification -------
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
					String dateText = dateFormat.format(new Date());
					
					prefixText = "OK";
					suffixText = Language.translate("Verzeichnis gepackt zu") + " '" + File.separator + "~tmp" + File.separator + jarArchiveFileName + "' " + Language.translate("um") + " " + dateText;
					
				} else {
					ClassLoaderUtil.addFile(file.getAbsoluteFile());
					ClassLoaderUtil.addJarToClassPath(jarFileCorrected);
					
					prefixText = "OK";
					suffixText = null;
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				prefixText = "ERROR";
				suffixText = e.getMessage();
			}
			
			// --- Set the additional text ----------------
			this.getProjectResources().setPrefixText(jarFile4Display, prefixText);
			this.getProjectResources().setSuffixText(jarFile4Display, suffixText);
			
		}
		
		// --- Search for Agent-, Ontology- and BaseService - Classes ----
		if (Application.getClassSearcher() != null) {
			Application.getClassSearcher().stopSearch(null);
			Application.setClassSearcher(null);
		}
		Application.setClassSearcher(new ClassSearcher(this));
		
		this.setChangedAndNotify(CHANGED_ProjectResources);
	}
	/**
	 * This Method reloads the project resources in the ClassPath
	 */
	public void resourcesReLoad() {
		this.resourcesRemove();
		this.resourcesLoad();
	}
	
	/**
	 * This method removes all external project resources (jars) from the ClassPath  
	 */
	public void resourcesRemove() {
		
		for(String jarFile : getProjectResources()) {
			
			try {
				String jarFileCorrected = ClassLoaderUtil.adjustPathForLoadIn(jarFile, this.getProjectFolderFullPath());
				File file = new File(jarFileCorrected);
				if (file.isDirectory()) {
					// --- Prepare the path variables -----
					String pathBin = file.getAbsolutePath();
					String pathBinHash = ((Integer)pathBin.hashCode()).toString();
					String jarArchiveFileName = "BIN_DUMP_" + pathBinHash + ".jar";
					String jarArchivePath = this.getProjectTempFolderFullPath() + jarArchiveFileName;
					
					ClassLoaderUtil.removeFile(jarArchivePath);

					File jarArchiveFile = new File(jarArchivePath);
					jarArchiveFile.delete();
					
				} else {
					ClassLoaderUtil.removeFile(jarFileCorrected);
					
				}
				
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		this.setChangedAndNotify(CHANGED_ProjectResources);
	}

	/**
	 * Gets the distribution setup.
	 * @return the distributionSetup
	 */
	@XmlTransient
	public DistributionSetup getDistributionSetup() {
		return distributionSetup;
	}
	/**
	 * Sets the distribution setup.
	 * @param distributionSetup the distributionSetup to set
	 */
	public void setDistributionSetup(DistributionSetup distributionSetup) {
		this.distributionSetup = distributionSetup;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_DistributionSetup);
	}
	
	/**
	 * @return the remoteContainerConfiguration
	 */
	@XmlTransient
	public RemoteContainerConfiguration getRemoteContainerConfiguration() {
		return remoteContainerConfiguration;
	}
	/**
	 * @param remoteContainerConfiguration the remoteContainerConfiguration to set
	 */
	public void setRemoteContainerConfiguration(RemoteContainerConfiguration remoteContainerConfiguration) {
		this.remoteContainerConfiguration = remoteContainerConfiguration;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_RemoteContainerConfiguration);
	}
	
	/**
	 * @return the userRuntimeObject
	 */
	@XmlTransient
	public Object getUserRuntimeObject() {
		return userRuntimeObject;
	}
	/**
	 * @param userRuntimeObject the userRuntimeObject to set
	 */
	public void setUserRuntimeObject(Serializable userRuntimeObject) {
		this.userRuntimeObject = userRuntimeObject;
		isUnsaved = true;
		setChanged();
		notifyObservers(CHANGED_UserRuntimeObject);
	}

	/**
	 * Returns the current environment controller
	 * @return the environment controller 
	 */
	@XmlTransient
	public EnvironmentController getEnvironmentController(){
		return this.environmentController;
	}
	/**
	 * Sets the environment controller 
	 * @param environmentController
	 */
	public void setEnvironmentController(EnvironmentController environmentController) {
		this.environmentController = environmentController;
	}

	/**
	 * Returns the current ComboBoxModel for environment types.
	 * @return the environmentsComboBoxModel
	 */
	@XmlTransient
	public DefaultComboBoxModel getEnvironmentsComboBoxModel() {
		return environmentsComboBoxModel;
	}
	/**
	 * Sets the ComboBoxModel for environment types.
	 * @param environmentsComboBoxModel the environmentsComboBoxModel to set
	 */
	public void setEnvironmentsComboBoxModel(DefaultComboBoxModel environmentsComboBoxModel) {
		this.environmentsComboBoxModel = environmentsComboBoxModel;
	}

	/**
	 * Sets the visualisation tab of the {@link ProjectWindow} for an executed setup.
	 * @param visualisationTab4SetupExecution the new visualisation tab4 setup execution
	 */
	public void setVisualisationTab4SetupExecution(JPanel4Visualisation visualisationTab4SetupExecution) {
		this.visualisationTab4SetupExecution = visualisationTab4SetupExecution;
	}
	/**
	 * Returns the visualisation tab of the {@link ProjectWindow} for an executed setup.
	 * @return the visualisation tab4 setup execution
	 */
	@XmlTransient
	public JPanel4Visualisation getVisualisationTab4SetupExecution() {
		if (this.visualisationTab4SetupExecution==null) {
			this.visualisationTab4SetupExecution = new JPanel4Visualisation(this, Language.translate("Simulations-Visualisierung")); 
		}
		return this.visualisationTab4SetupExecution;
	}

}
