package gasmas.adapter;

import gasmas.ontology.Compressor;
import gasmas.ontology.GasGridOntology;

import java.util.Vector;

import agentgui.envModel.graph.networkModel.NetworkComponentAdapter4Ontology;
import agentgui.ontology.AgentGUI_BaseOntology;

public class CompressorDataModelAdapter extends NetworkComponentAdapter4Ontology {

	private Vector<String> ontologyBaseClasses = null;
	private String[] ontologyClassReferences = null;
	
	@Override
	public Vector<String> getOntologyBaseClasses() {
		if (this.ontologyBaseClasses==null) {
			this.ontologyBaseClasses = new Vector<String>();
			this.ontologyBaseClasses.add(GasGridOntology.class.getName());
			this.ontologyBaseClasses.add(AgentGUI_BaseOntology.class.getName());
		}
		return this.ontologyBaseClasses;
	}
	
	@Override
	public String[] getOntologyClassReferences() {
		if (ontologyClassReferences==null) {
			this.ontologyClassReferences = new String[1];
			this.ontologyClassReferences[0] = Compressor.class.getName();
		}
		return this.ontologyClassReferences;
	}

}
