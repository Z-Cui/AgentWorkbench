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
package gasmas.clustering.behaviours;

import gasmas.clustering.analyse.ClusterIdentifier;
import gasmas.clustering.analyse.PathSearchBotDistributionMatrix;
import gasmas.clustering.analyse.PathSearchBotRunner;
import agentgui.envModel.graph.networkModel.NetworkComponent;
import agentgui.envModel.graph.networkModel.NetworkModel;
import agentgui.simulationService.environment.EnvironmentModel;

/**
 * The Class PathAnalyseClusteringBehaviour.
 */
public class BottelneckClusteringBehaviour extends ClusteringBehaviour {

	/** The Constant STEPS. */
	private static final int STEPS = 70;

	/**
	 * Instantiates a new path analyse clustering behaviour.
	 *
	 * @param environmentModel the environment model
	 * @param thisNetworkComponent the this network component
	 */
	public BottelneckClusteringBehaviour(EnvironmentModel environmentModel) {
		super(environmentModel);
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		ClusterIdentifier clusterIdentifier = new ClusterIdentifier();
		NetworkModel clusteredNM = getClusterNM();

		analyseClusters(clusteredNM, clusterIdentifier);
		correctAndSetCluster(clusteredNM, clusterIdentifier);
	}

	/**
	 * Analyse clusters.
	 *
	 * @param networkModel the network model
	 * @param clusterIdentifier the cluster identifier
	 */
	public void analyseClusters(NetworkModel networkModel, ClusterIdentifier clusterIdentifier) {
		NetworkModel newNetworkModel = networkModel.getCopy();
		while (newNetworkModel != null && newNetworkModel.getNetworkComponent(coalitionBehaviour.getThisNetworkComponent().getId()) != null) {
			newNetworkModel = startPathAnalysis(newNetworkModel);
			if (newNetworkModel != null) {
				newNetworkModel = clusterIdentifier.search(newNetworkModel, networkModel);
				// if (coalitionBehaviour.getThisNetworkComponent().getId().equals("n25")) {
				// changeDisplay(newNetworkModel);
				// }
			}
		}
	}

	/**
	 * Start path analysis.
	 *
	 * @param newNetworkModel the new network model
	 * @return the network model
	 */
	private NetworkModel startPathAnalysis(NetworkModel newNetworkModel) {
		PathSearchBotDistributionMatrix antDistributionMatrix = new PathSearchBotRunner().runBotsAndGetDistributionMatrix(newNetworkModel, coalitionBehaviour.getThisNetworkComponent().getId(),
				BottelneckClusteringBehaviour.STEPS);
		String frequentComponent = antDistributionMatrix.findFrequentPathComponent();
		if (frequentComponent == null) {
			return null;
		}
		NetworkComponent networkComponent = newNetworkModel.getNetworkComponent(frequentComponent);
		newNetworkModel.removeNetworkComponent(networkComponent);
		return newNetworkModel;
	}

}