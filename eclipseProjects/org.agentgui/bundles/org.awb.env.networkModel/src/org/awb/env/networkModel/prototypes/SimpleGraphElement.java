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
package org.awb.env.networkModel.prototypes;

import java.util.HashSet;

import org.awb.env.networkModel.GraphEdge;
import org.awb.env.networkModel.GraphElement;
import org.awb.env.networkModel.GraphNode;
import org.awb.env.networkModel.NetworkModel;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * A simple graph / network element with two connection points, represented by two nodes and an edge.
 * 
 * @author Nils Loose - DAWIS - ICB University of Duisburg - Essen
 */
public class SimpleGraphElement extends AbstractGraphElementPrototype {

    /* (non-Javadoc)
     * @see org.awb.env.networkModel.prototypes.AbstractGraphElementPrototype#addToGraph(org.awb.env.networkModel.NetworkModel)
     */
    @Override
    public HashSet<GraphElement> addToGraph(NetworkModel networkModel) {
    	
    	Graph<GraphNode, GraphEdge> graph = networkModel.getGraph();
	
		// Create nodes and edge
		GraphNode entry = new GraphNode();
		entry.setId(networkModel.nextNodeID());
		graph.addVertex(entry);
		
		GraphNode exit = new GraphNode();
		exit.setId(networkModel.nextNodeID());
		graph.addVertex(exit);
		
		GraphEdge graphEdge = new GraphEdge(getId(), getType());
		graph.addEdge(graphEdge, entry, exit, EdgeType.UNDIRECTED);
	
		// Create a HashSet containing the nodes and edge ant return it
		HashSet<GraphElement> elements = new HashSet<GraphElement>();
		elements.add(graphEdge);
		elements.add(entry);
		elements.add(exit);
		return elements;
    }

}
