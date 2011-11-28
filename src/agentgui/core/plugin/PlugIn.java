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
package agentgui.core.plugin;

import jade.core.Profile;

import java.awt.Container;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JMenu;

import agentgui.core.application.Application;
import agentgui.core.environment.EnvironmentType;
import agentgui.core.gui.projectwindow.ProjectWindowTab;
import agentgui.core.project.Project;
import agentgui.core.sim.setup.SimulationSetup;
import agentgui.core.sim.setup.SimulationSetups;
import agentgui.core.sim.setup.SimulationSetupsChangeNotification;

/**
 * This abstract class is the root for customized plug-in's, which can
 * be loaded to extend an individual agent project.<br>
 * Classes which inherit from this class can be registered to an
 * agent project by using the "Resources"-Tab in the project
 * configuration. 
 *   
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public abstract class PlugIn implements Observer {

	public static final String CHANGED = "PlugIns";
	public static final int ADDED = 1;
	public static final int REMOVED = 2;
	
	protected Project project = null;
	protected String classReference = null;

	private Vector<JComponent> customJComponent = new Vector<JComponent>();
	private Vector<ProjectWindowTab> customProjectWindowTab = new Vector<ProjectWindowTab>();
	private Vector<EnvironmentType> customEnvironmentTypes = new Vector<EnvironmentType>();
	
	/**
	 * Default constructor for this class.
	 *
	 * @param currProject the current project
	 */
	public PlugIn(Project currProject) {
		this.project = currProject;
		this.project.addObserver(this);		
	}
	
	/**
	 * Sets the class reference.
	 *
	 * @param classReference the classReference to set
	 */
	public void setClassReference(String classReference) {
		this.classReference = classReference;
	}
	
	/**
	 * Gets the class reference.
	 * @return the classReference
	 */
	public String getClassReference() {
		return classReference;
	}

	/**
	 * Gets the name.
	 *
	 * @return the pluginName
	 */
	public abstract String getName();

	/**
	 * This method be called if the plug-in is loaded into the project.
	 * This happens immediately after the project was opened. 
	 */
	public void onPlugIn() {
		System.out.println( "+ Plug-In loaded [" + this.getName() + "]" );
	}
	/**
	 * This method will be called if the plug-in will be removed from the 
	 * project, means immediately before the project will be closed.
	 */
	public void onPlugOut() {
		System.out.println( "- Plug-In removed [" + this.getName() + "]" );
	}
	
	/**
	 * This method will be invoked just after the onPlugOut() method 
	 * was executed.
	 * DO NOT OVERRIDE !!!
	 */
	public void afterPlugOut() {
		this.project.deleteObserver(this);
		this.removeCustomElements();
	}
	
	// --------------------------------------------------------------
	// --- Handling of custom elements for the GUI -------- START ---
	// --------------------------------------------------------------
	// --- Start of adding functions ------------
	/**
	 * This method can be used in order to add an individual menu.
	 * @param myMenu the my menu
	 */
	protected void addJMenu(JMenu myMenu) {
		Application.MainWindow.addJMenu(myMenu);
		customJComponent.add(myMenu);
	}
	
	/**
	 * This method can be used in order to add an individual menu
	 * at a specified index position of the menu bar.
	 *
	 * @param myMenu the my menu
	 * @param indexPosition the index position
	 */
	protected void addJMenu(JMenu myMenu, int indexPosition) {
		Application.MainWindow.addJMenu(myMenu, indexPosition);
		customJComponent.add(myMenu);
	}
	
	/**
	 * This method can be used in order to add an
	 * individual JMmenuItem to the given menu.
	 *
	 * @param menu2add the menu2add
	 * @param myMenuItemComponent the my menu item component
	 */
	protected void addJMenuItemComponent(JMenu menu2add, JComponent myMenuItemComponent) {
		Application.MainWindow.addJMenuItemComponent(menu2add, myMenuItemComponent);
		customJComponent.add(myMenuItemComponent);
	}
	
	/**
	 * This method can be used in order to add an individual JMmenuItem
	 * at a specified index position of the given menu.
	 *
	 * @param menu2add the menu2add
	 * @param myMenuItemComponent the my menu item component
	 * @param indexPosition the index position
	 */
	protected void addJMenuItemComponent(JMenu menu2add, JComponent myMenuItemComponent, int indexPosition) {
		Application.MainWindow.addJMenuItemComponent(menu2add, myMenuItemComponent, indexPosition);
		customJComponent.add(myMenuItemComponent);
	}
	
	/**
	 * This method can be used in order to add an
	 * individual menu button to the toolbar.
	 *
	 * @param myComponent the my component
	 */
	protected void addJToolbarComponent(JComponent myComponent) {
		Application.MainWindow.addJToolbarComponent(myComponent);
		customJComponent.add(myComponent);
	}
	
	/**
	 * This method can be used in order to add an individual menu button
	 * a specified index position of the toolbar.
	 *
	 * @param myComponent the my component
	 * @param indexPosition the index position
	 */
	protected void addJToolbarComponent(JComponent myComponent, int indexPosition) {
		Application.MainWindow.addJToolbarComponent(myComponent,indexPosition);
		customJComponent.add(myComponent);
	}
	
	/**
	 * This method can be used in order to add a customized Tab to the project window.
	 *
	 * @param projectWindowTab the project window tab
	 */
	protected void addProjectWindowTab(ProjectWindowTab projectWindowTab) {
		projectWindowTab.add();
		customProjectWindowTab.add(projectWindowTab);
	}
	
	/**
	 * This method can be used in order to add a customized Tab to the project window at the specified index position.
	 * The index position has to be greater than 1, in order to keep the 'Info'-Tab and the 'Configuration'-Tab at
	 * its provided position!
	 *
	 * @param projectWindowTab the project window tab
	 * @param indexPositionGreaterOne the index position greater one
	 */
	protected void addProjectWindowTab(ProjectWindowTab projectWindowTab, int indexPositionGreaterOne) {
		projectWindowTab.add(indexPositionGreaterOne);
		customProjectWindowTab.add(projectWindowTab);
	}
	
	/**
	 * Adds an environment type to the list of available environment types. It can be used 
	 * in order to register an individual, taylored EnvironmentType to Agent.GUI.<br>
	 * After this method was invoked the EnvironmentType can be found for selection
	 * in the Resources-Tab of the project configuration.
	 *  
	 * @param envType the new EnvironmentType
	 */
	protected void addEnvironmentType(EnvironmentType envType) {
		if (envType!=null) {
			Application.RunInfo.addEnvironmentType(envType);	
			project.getEnvironmentsComboBoxModel().addElement(envType);
			customEnvironmentTypes.add(envType);
		}
	}
	// --- End of adding functions --------------
	
	/**
	 * This method can be used to remove all custom components
	 * for menus and for the toolbar.
	 */
	private void removeCustomElements() {
		
		// --- remove custom toolbar/menu elements --------
		for (int i = 0; i < customJComponent.size(); i++) {
			JComponent component = customJComponent.get(i);
			Container container = component.getParent();
			container.remove(component);
		}
		customJComponent = new Vector<JComponent>();
		// --- remove custom Tab-elements -----------------
		for (int i = customProjectWindowTab.size()-1; i>-1; i--) {
			ProjectWindowTab pwt = customProjectWindowTab.get(i);
			pwt.remove();
		}
		customProjectWindowTab = new Vector<ProjectWindowTab>();
		// --- remove custom environment types ------------
		for (int i = customEnvironmentTypes.size()-1; i>-1; i--) {
			EnvironmentType envType = customEnvironmentTypes.get(i);
			if (envType.equals(this.project.getEnvironmentModelType())) {
				this.project.setEnvironmentModelName("none");
			}
			project.getEnvironmentsComboBoxModel().removeElement(envType);
			Application.RunInfo.removeEnvironmentType(envType);
		}
		// --- validate/repaint the CorwWindow ------------
		Application.MainWindow.validate();
	}
	// --------------------------------------------------------------
	// --- Handling of custom elements for the GUI -------- END -----
	// --------------------------------------------------------------
	
	
	
	/**
	 * AgentGUI uses the observer pattern to inform about changes
	 * within the project. They can consist on the following kinds
	 * of notifications<br>
	 * <br>
	 * - String Project.SAVED<br>
	 * - String Project.CHANGED_ProjectName<br>
	 * - String Project.CHANGED_ProjectDescription<br>
	 * - String Project.CHANGED_ProjectFolder<br>
	 * - String Project.CHANGED_ProjectView<br>
	 * - String Project.CHANGED_EnvironmentModel<br>
	 * - String Project.CHANGED_AgentReferences<br>
	 * - String Project.CHANGED_ProjectOntology<br>
	 * - String Project.CHANGED_ProjectResources<br>
	 * <br>
	 * - String SimulationSetups.CHANGED<br>
	 * Here in Detail, while using the
	 * 'SimulationSetupsChangeNotification.getUpdateReason' - method:<br>
	 * => int SimulationSetups.SIMULATION_SETUP_LOAD<br>
	 * => int SimulationSetups.SIMULATION_SETUP_ADD_NEW<br>
	 * => int SimulationSetups.SIMULATION_SETUP_COPY<br>
	 * => int SimulationSetups.SIMULATION_SETUP_REMOVE<br>
	 * => int SimulationSetups.SIMULATION_SETUP_RENAME<br>
	 * => int SimulationSetups.SIMULATION_SETUP_SAVED<br>
	 * <br>
	 * - String PlugIn.CHANGED<br>
	 * Here in Detail, while using the
	 * 'PlugInNotification.getUpdateReason' - method:<br>
	 * => int PlugIn.ADDED<br>
	 * => int PlugIn.REMOVED<br>
	 * Furthermore the instance of the PlugIn can be get
	 * by using 'PlugInNotification.getPlugIn()'<br>
	 * <br>
	 * <br>
	 * Do NOT override this method directly. Use the updateFromObserver()-method
	 * instead, in order to get your individual Observer-Changes.
	 * 
	 * @see Project
	 * @see SimulationSetup
	 * 
	 * @see PlugIn#CHANGED
	 * @see PlugIn#ADDED
	 * @see PlugIn#REMOVED
	 * @see PlugInNotification
	 *
	 * @param observable the observable
	 * @param updateObject the update object
	 */
	@Override
	public void update(Observable observable, Object updateObject) {
		
		// ----------------------------------------------------------
		this.updateFromObserver(observable, updateObject);
		// ----------------------------------------------------------
		
		// ----------------------------------------------------------
		// --- Changes in the Project-Configuration -----------------
		// ----------------------------------------------------------
		if (updateObject==null) {
			return;			
		} else if (updateObject.equals(Project.SAVED)) {
			this.onProjectSaved();
		} else if (updateObject.equals(Project.CHANGED_ProjectName)) {
			this.onProjectChangedProjectName();
		} else if (updateObject.equals(Project.CHANGED_ProjectDescription)) {
			this.onProjectChangedProjectDescription();
		} else if (updateObject.equals(Project.CHANGED_ProjectFolder)) {
			// --- Do Nothing here !!! ----------
			// this.onProjectChangedProjectFolder();
		} else if (updateObject.equals(Project.CHANGED_ProjectView)) {
			this.onProjectChangedProjectView();
		} else if (updateObject.equals(Project.CHANGED_EnvironmentModel)) {
			this.onProjectChangedEnvironmentModel();
		} else if (updateObject.equals(Project.CHANGED_StartArguments4BaseAgent)) {
			this.onProjectChangedAgentStartConfiguration();
		} else if (updateObject.equals(Project.CHANGED_ProjectOntology)) {
			this.onProjectChangedProjectOntology();
		} else if (updateObject.equals(Project.CHANGED_ProjectResources)) {
			this.onProjectChangedProjectResources();
		} else if (updateObject.equals(Project.CHANGED_JadeConfiguration)) {
			this.onProjectChangedJadeConfiguration();
			
		} else if (updateObject.equals(Project.CHANGED_DistributionSetup)) {
			this.onProjectChangedDistributionSetup();
		} else if (updateObject.equals(Project.CHANGED_RemoteContainerConfiguration)) {
			this.onProjectChangedRemoteContainerConfiguration();
		} else if (updateObject.equals(Project.CHANGED_UserRuntimeObject)) {
			this.onProjectChangedUserRuntimeObject();
			
		// ----------------------------------------------------------
		// --- Changes with the SimulationSetups --------------------			
		// ----------------------------------------------------------
		} else if (updateObject.toString().equals(SimulationSetups.CHANGED)) {
			
			SimulationSetupsChangeNotification sscn = (SimulationSetupsChangeNotification) updateObject;
			int sscnUpdate = sscn.getUpdateReason();
			SimulationSetup simSetup = project.simulationSetups.getCurrSimSetup();
			if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_ADD_NEW) {
				this.onSimSetupChangedAddNew(simSetup);
			} else if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_COPY) {
				this.onSimSetupChangedCopy(simSetup);
			} else if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_LOAD) {
				this.onSimSetupChangedLoad(simSetup);
			} else if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_REMOVE) {
				this.onSimSetupChangedRemove(simSetup);
			} else if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_RENAME) {
				this.onSimSetupChangedRename(simSetup);
			} else if (sscnUpdate==SimulationSetups.SIMULATION_SETUP_SAVED) {
				this.onSimSetupChangedSaved(simSetup);				
			}
		
		// ----------------------------------------------------------
		// --- Changes with the Project-PlugIns ---------------------			
		// ----------------------------------------------------------
		} else if (updateObject.toString().equals(PlugIn.CHANGED)) {

			PlugInNotification pin = (PlugInNotification) updateObject;
			int pinUpdate = pin.getUpdateReason();
			PlugIn plugIn = pin.getPlugIn();
			if (pinUpdate == PlugIn.ADDED) {
				this.onPlugInAdded(plugIn);
			} else if (pinUpdate == PlugIn.REMOVED) {
				this.onPlugInRemoved(plugIn);
			}			
			
		} else {
			//System.out.println("Unknown Notification from Observer " + observable.toString() + ": " + updateObject.toString() );
		}
		
		
	}

	/**
	 * In order to perceive individual informations from the project Observer
	 * (observer pattern), this method should be used in the extended class.
	 * !!! Do NOT override the update method directly !!
	 *
	 * @param observable the observable
	 * @param updateObject the update object
	 */
	protected void updateFromObserver(Observable observable, Object updateObject) {
	}

	// ------------------------------------------------------------------------
	// --- Protected methods for the PlugIn-development --- START ------------- 
	// ------------------------------------------------------------------------
	
	// --- Changes in the project configuration --------------------------
	/**On project saved. */
	protected void onProjectSaved() { }
	
	/** On project changed project name. */
	protected void onProjectChangedProjectName() { }
	
	/** On project changed project description. */
	protected void onProjectChangedProjectDescription() { }
	
	/** On project changed environment model. */
	protected void onProjectChangedEnvironmentModel() {	}
	
	/** On project changed agent start configuration. */
	protected void onProjectChangedAgentStartConfiguration() { }
	
	/** On project changed project resources. */
	protected void onProjectChangedProjectResources() {	}
	
	/** On project changed jade configuration. */
	protected void onProjectChangedJadeConfiguration() { }
	
	/** On project changed project ontology. */
	protected void onProjectChangedProjectOntology() { }
	
	/** On project changed project view. */
	protected void onProjectChangedProjectView() {	}
	
	/** On project changed user runtime object. */
	private void onProjectChangedUserRuntimeObject() { }

	/** On project changed remote container configuration. */
	private void onProjectChangedRemoteContainerConfiguration() { }

	/** On project changed distribution setup. */
	private void onProjectChangedDistributionSetup() { }
	
	
	// --- Changes in the SimulationSetup --------------------------------
	/**
	 * On sim setup changed add new.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedAddNew(SimulationSetup currSimSetup) {
	}
	
	/**
	 * On sim setup changed copy.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedCopy(SimulationSetup currSimSetup) {
	}
	
	/**
	 * On sim setup changed load.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedLoad(SimulationSetup currSimSetup) {
	}
	
	/**
	 * On sim setup changed remove.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedRemove(SimulationSetup currSimSetup) {
	}
	
	/**
	 * On sim setup changed rename.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedRename(SimulationSetup currSimSetup) {
	}
	
	/**
	 * On sim setup changed saved.
	 *
	 * @param currSimSetup the curr sim setup
	 */
	private void onSimSetupChangedSaved(SimulationSetup currSimSetup) {
	}

	// --- Changes with the PlugIns --------------------------------------
	/**
	 * On plug in removed.
	 *
	 * @param plugIn the plug in
	 */
	private void onPlugInRemoved(PlugIn plugIn) {
	}
	
	/**
	 * On plug in added.
	 *
	 * @param plugIn the plug in
	 */
	private void onPlugInAdded(PlugIn plugIn) {
	}

	/**
	 * Overriding his method allows to extend/change the currently 
	 * used Profile JADE container.
	 * 
	 * @param jadeContainerProfile The profile to CHANGE
	 * @return the CHANGED (!) configuration of the JADE Profile
	 */
	public Profile getJadeProfile(Profile jadeContainerProfile) {
		return jadeContainerProfile;
	}

	// ------------------------------------------------------------------------
	// --- Protected methods for the PlugIn-development --- END --------------- 
	// ------------------------------------------------------------------------
	
}
