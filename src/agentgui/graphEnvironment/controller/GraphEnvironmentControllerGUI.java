package agentgui.graphEnvironment.controller;

import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import agentgui.core.application.Language;
import agentgui.core.application.Project;
import agentgui.physical2Denvironment.display.BasicSVGGUI;
import javax.swing.JLabel;

public class GraphEnvironmentControllerGUI extends JSplitPane implements Observer, ActionListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;
	private JPanel pnlControlls = null;
	private JScrollPane scpComponentTable = null;
	private JTable tblComponents = null;
	private JButton btnLoadGraph = null;
	private JButton btnSetClasses = null;
	
	private ComponentSettingsDialog componentSettingsDialog = null;
	private ClassSelectorDialog classSelectorDialog = null;
	private BasicSVGGUI svgGUI = null;
	private GraphEnvironmentController controller = null;
	/**
	 * The currently selected SVG element
	 */
	private Element selectedElement = null;
	/**
	 * The selectedElement's style attribute before it was selected
	 */
	private String originalStyle;
	/**
	 * The style attribute used for highlighting selected elements
	 */
	private String selectionStyle = "fill:white;stroke:orange;stroke-width:3;stroke-miterlimit:4;stroke-dasharray:none";
	private JLabel lblTable = null;
	
	/**
	 * This is the default constructor
	 */
	public GraphEnvironmentControllerGUI(Project project) {
		super();
		initialize(project);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(Project project) {
		controller = new GraphEnvironmentController(project);
		controller.addObserver(this);
		
		this.setDividerLocation(200);
		this.setLeftComponent(getPnlControlls());
		this.setRightComponent(getSVGGUI());
		
		if(controller.getSvgDoc() != null){
			this.setSVGDoc(controller.getSvgDoc());
		}
	}

	/**
	 * This method initializes pnlControlls	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlControlls() {
		if (pnlControlls == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridy = 2;
			lblTable = new JLabel();
			lblTable.setText(Language.translate("Netz-Komponenten"));
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridheight = 1;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.gridwidth = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 3;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 0;
			gridBagConstraints.gridx = 0;
			pnlControlls = new JPanel();
			pnlControlls.setLayout(new GridBagLayout());
			pnlControlls.add(getScpComponentTable(), gridBagConstraints);
			pnlControlls.add(getBtnLoadGraph(), gridBagConstraints1);
			pnlControlls.add(getBtnSetClasses(), gridBagConstraints7);
			pnlControlls.add(lblTable, gridBagConstraints6);
		}
		return pnlControlls;
	}

	/**
	 * This method initializes scpComponentTable	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpComponentTable() {
		if (scpComponentTable == null) {
			scpComponentTable = new JScrollPane();
			scpComponentTable.setViewportView(getTblComponents());
		}
		return scpComponentTable;
	}

	/**
	 * This method initializes tblComponents	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getTblComponents() {
		if (tblComponents == null) {
			// Column titles
			Vector<String> titles = new Vector<String>();
			titles.add(Language.translate("Komponente"));
			titles.add(Language.translate("Typ"));
			
			tblComponents = new JTable(getComponentTableContents(), titles);
			tblComponents.getSelectionModel().addListSelectionListener(this);
			tblComponents.setShowGrid(true);
			tblComponents.setFillsViewportHeight(true);
		}
		return tblComponents;
	}
	
	/**
	 * Creates a JTable containing the IDs and types of all Components from the GridModel 
	 */
	private void rebuildTblComponents(){
		// Column titles
		Vector<String> titles = new Vector<String>();
		titles.add(Language.translate("Komponente"));
		titles.add(Language.translate("Typ"));
		
		getTblComponents().setModel(new DefaultTableModel(getComponentTableContents(), titles));
	}
	
	/**
	 * This method builds the tblComponents' contents based on the controllers GridModel 
	 * @return The grid components' IDs and class names
	 */
	private Vector<Vector<String>> getComponentTableContents(){
		Vector<Vector<String>> componentVector = new Vector<Vector<String>>();
		
		if(controller.getGridModel() != null){
			
			// Get the components from the controllers GridModel
			@SuppressWarnings("unchecked")
			Iterator<GraphNode> components = controller.getGridModel().getComponents().iterator();
			
			// Add component ID and class name to the data vector
			while(components.hasNext()){
				GraphNode comp = components.next();
				
				Vector<String> compData = new Vector<String>();
				compData.add(comp.getId());
				compData.add(comp.getOntologyObject().getClass().getSimpleName());
				compData.add("Test");
				componentVector.add(compData);
			}
		}
		
		return componentVector;
	}

	/**
	 * This method initializes btnLoadGraph	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnLoadGraph() {
		if (btnLoadGraph == null) {
			btnLoadGraph = new JButton();
			btnLoadGraph.setText(Language.translate("Graph Laden"));
			btnLoadGraph.addActionListener(this);
			if(controller.getProject().getOntoClassHash() == null){
				btnLoadGraph.setEnabled(false);
			}
		}
		return btnLoadGraph;
	}

	/**
	 * This method initializes btnSetClasses	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSetClasses() {
		if (btnSetClasses == null) {
			btnSetClasses = new JButton();
			btnSetClasses.setText(Language.translate("Klassenzuordnung"));
			btnSetClasses.addActionListener(this);
		}
		return btnSetClasses;
	}
	
	private ComponentSettingsDialog getComponentSettingsDialog(){
		if(componentSettingsDialog == null){
			componentSettingsDialog = new ComponentSettingsDialog(this);
			componentSettingsDialog.setOntologyTreeModel(controller.getProject().ontologies4Project.getOntologyTree());
		}
		return componentSettingsDialog;
	}
	
	private ClassSelectorDialog getClassSelectorDialog(){
		if(classSelectorDialog == null){
			classSelectorDialog = new ClassSelectorDialog(this, controller.getOntologyClasses(), controller.getAgentClasses());
		}
		return classSelectorDialog;
	}
	
	private BasicSVGGUI getSVGGUI(){
		if(svgGUI == null){
			svgGUI = new BasicSVGGUI();
		}
		return svgGUI;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.equals(controller) && arg.equals(GraphEnvironmentController.EVENT_SVG_LOADED)){
			this.setSVGDoc(controller.getSvgDoc());
		}else if(o.equals(controller) && arg.equals(GraphEnvironmentController.EVENT_GRAPH_LOADED)){
			rebuildTblComponents();
		}else if(o.equals(controller) && arg == GraphEnvironmentController.EVENT_ONTOLOGY_CLASSES_SET){
			if(controller.getOntologyClasses() != null){
				getBtnLoadGraph().setEnabled(true);
			}
		}
	}
	
	/**
	 * This method adds onClick handler to a SVG element if it represents a graph node.
	 * An element represents a graph node if it is of type g and has an ID like "y.node.{number}"
	 * @param node A XML node 
	 */
	private void addOnclickHandler(Node node){
		String regex = "^y\\.node\\.\\d+$";		// Matches y.node.<nr>
		if(node instanceof Element 
				&& ((Element)node).getTagName().equals("g")
				&& ((Element)node).getAttributeNS(null, "id").matches(regex)
		){
			((EventTarget)node).addEventListener("click", new EventListener() {
				
				@Override
				public void handleEvent(Event evt) {
					String svgID = ((Element)evt.getCurrentTarget()).getAttributeNS(null, "id");
					String componentID = "n"+svgID.substring(svgID.lastIndexOf('.')+1);
					GraphNode selectedComponent = controller.getGridModel().getComponent(componentID);
					getComponentSettingsDialog().setVisible(true);
					getComponentSettingsDialog().setSelectedComponent(selectedComponent);
					setSelectedElement((Element)evt.getTarget());
				}
			}, false);
		}
		if(node.hasChildNodes()){
			for(int i=0;i<node.getChildNodes().getLength();i++){
				addOnclickHandler(node.getChildNodes().item(i));
			}
		}
	}
	
	/**
	 *  This method sets the GUIs SVG document.
	 * @param doc The SVG document 
	 */
	private void setSVGDoc(Document doc){
		addOnclickHandler(doc.getDocumentElement());
		this.svgGUI.setSVGDoc(doc);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(getBtnLoadGraph())){
			JFileChooser graphFC = new JFileChooser();
			graphFC.setFileFilter(new FileNameExtensionFilter(Language.translate("GraphML-Dateien"), "graphml"));
			if(graphFC.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				File graphMLFile = graphFC.getSelectedFile();
				this.controller.loadGridModel(graphMLFile);
			}
		}else if(event.getSource().equals(getBtnSetClasses())){
			getClassSelectorDialog().setVisible(true);
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String compID = (String) tblComponents.getModel().getValueAt(e.getFirstIndex(), 0);
		setSelectedElementByComponentID(compID);
		getComponentSettingsDialog().setVisible(true);
		getComponentSettingsDialog().setSelectedComponent(controller.getGridModel().getComponent(compID));
	}
	
	void setComponentType(){
		System.out.println("Die Knotentyp-�nderung ist noch nicht Implementiert.");
		setSelectedElement(null);
	}
	
	GraphEnvironmentController getController(){
		return controller;
	}
	
	/**
	 * Sets the currently selected SVG element
	 * @param element The SVG element
	 */
	void setSelectedElement(Element element){
		svgGUI.getCanvas().getUpdateManager().getUpdateRunnableQueue().invokeLater(new ElementSelector(element));
	}

	/**
	 * This method finds the SVG element corresponding to the graph element with the given ID and selects it
	 * @param componentID The graph element's ID
	 */
	private void setSelectedElementByComponentID(String componentID){
		int number = Integer.parseInt(componentID.substring(1));
		String svgElementID = "svg"+(number+1)+".NodeBackground";
		Element svgElement = svgGUI.getSVGDoc().getElementById(svgElementID);
		setSelectedElement(svgElement);
	}
	
	private class ElementSelector implements Runnable{
		
		private Element element;
		
		public ElementSelector(Element element){
			this.element = element;
		}

		@Override
		public void run() {
			if(selectedElement != null){
				selectedElement.setAttributeNS(null, "style", originalStyle);
			}
			if(element != null){
				originalStyle = element.getAttributeNS(null, "style");
				element.setAttributeNS(null, "style", selectionStyle);
				selectedElement = element;
			}
		}
		
	}

}