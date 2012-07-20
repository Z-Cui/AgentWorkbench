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
package gasmas.agents.components;

import gasmas.resourceallocation.AllocData;
import gasmas.resourceallocation.FindDirData;
import gasmas.resourceallocation.FindDirectionBehaviour;
import gasmas.resourceallocation.ResourceAllocationBehaviour;
import gasmas.resourceallocation.StatusData;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.TickerBehaviour;
import agentgui.envModel.graph.networkModel.NetworkModel;
import agentgui.simulationService.SimulationService;
import agentgui.simulationService.SimulationServiceHelper;
import agentgui.simulationService.agents.SimulationAgent;
import agentgui.simulationService.environment.EnvironmentModel;
import agentgui.simulationService.transaction.EnvironmentNotification;

public abstract class GenericNetworkAgent extends SimulationAgent {

	private static final long serialVersionUID = 1743261783247570185L;

	protected SimulationServiceHelper simHelper;
	protected EnvironmentModel envModel;
	protected NetworkModel myNetworkModel = null;
	
	/** Msgpool, which contains messages, which are not needed at the moment */
	protected ResourceAllocationBehaviour resourceAllocationBehaviour;
	protected FindDirectionBehaviour findDirectionBehaviour;

	
	/* (non-Javadoc)
	 * @see agentgui.simulationService.agents.SimulationAgent#setup()
	 */
	@Override
	protected void setup() {
		super.setup();
		this.addBehaviour(new WaitForEnvironmentModelBehaviour(this, 100));
	}
	/**
	 * Setup the agent here, in order to make sure 
	 * that the EnvironmentModel arrived already.
	 */
	private void setupAgent() {
		findDirectionBehaviour = new FindDirectionBehaviour(this, 20000, myEnvironmentModel);
		this.addBehaviour(findDirectionBehaviour);
	}
	
	/* (non-Javadoc)
	 * @see agentgui.simulationService.agents.SimulationAgent#onEnvironmentNotification(agentgui.simulationService.transaction.EnvironmentNotification)
	 */
	@Override
	protected EnvironmentNotification onEnvironmentNotification(EnvironmentNotification notification) {
		// System.out.println("Got Message " + this.getLocalName() + "...von..."
		// + notification.getSender().getLocalName());
		if (notification.getNotification() instanceof AllocData) {
			if (resourceAllocationBehaviour == null) {
				notification.moveLastOrBlock(100);
				System.out.println("=> Notification parked for 'AllocData' !");
			} else {
				resourceAllocationBehaviour.interpretMsg(notification);
			}
		} else if (notification.getNotification() instanceof FindDirData) {
			sendManagerNotification(new StatusData(1));
			if (findDirectionBehaviour == null) {
				notification.moveLastOrBlock(100);
				System.out.println("=> Notification parked for 'FindDirData' !");
			} else {
				findDirectionBehaviour.interpretMsg(notification);
			}
		} else {
			System.out.println("WOEPv " +notification.getNotification());
		}
		return notification;
	}
	
	/**
	 * The Behaviour class WaitForEnvironmentModelBehaviour.
	 */
	protected class WaitForEnvironmentModelBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 7962972851532229620L;

		/**
		 * Instantiates a new behaviour that waits for the initial EnvironmentModel.
		 * @param agent the agent
		 * @param period the ticker period
		 */
		public WaitForEnvironmentModelBehaviour(Agent agent, long period) {
			super(agent, period);
		}

		/* (non-Javadoc)
		 * @see jade.core.behaviours.TickerBehaviour#onTick()
		 */
		@Override
		protected void onTick() {
			try {
				simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
				envModel = simHelper.getEnvironmentModel();
				if (envModel != null) {
					myEnvironmentModel = envModel;
					myNetworkModel = (NetworkModel) myEnvironmentModel.getDisplayEnvironment();
					setupAgent();
					this.stop();
				}
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}
	
}
