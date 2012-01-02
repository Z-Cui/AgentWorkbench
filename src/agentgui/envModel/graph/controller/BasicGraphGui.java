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

package agentgui.envModel.graph.controller;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import agentgui.core.agents.AgentClassElement4SimStart;
import agentgui.core.application.Language;
import agentgui.envModel.graph.networkModel.ComponentTypeSettings;
import agentgui.envModel.graph.networkModel.GraphEdge;
import agentgui.envModel.graph.networkModel.GraphElement;
import agentgui.envModel.graph.networkModel.GraphNode;
import agentgui.envModel.graph.networkModel.NetworkComponent;
import agentgui.envModel.graph.networkModel.NetworkModel;
import agentgui.envModel.graph.prototypes.GraphElementPrototype;
import agentgui.envModel.graph.prototypes.Star3GraphElement;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.ConstantDirectionalEdgeValueTransformer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;

/**
 * This class implements a GUI component for displaying visualizations for JUNG
 * graphs. <br>
 * This class also has a toolbar component which provides various features for
 * editing, importing and interacting with the graph.
 * 
 * @see GraphEnvironmentControllerGUI
 * @see GraphEnvironmentMousePlugin
 * 
 * @author Nils Loose - DAWIS - ICB University of Duisburg - Essen
 * @author Satyadeep Karnati - CSE - Indian Institute of Technology, Guwahati
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class BasicGraphGui extends JPanel {

	private static final long serialVersionUID = 5764679914667183305L;

	/** Default color to be used for Vertices in the graph */
	public static Color DEFAULT_VERTEX_COLOR = Color.RED;  //  @jve:decl-index=0:
	/** Default color to be used for Vertices in the graph when highlighted/picked.	 */
	public static Color DEFAULT_VERTEX_PICKED_COLOR = Color.YELLOW;  //  @jve:decl-index=0:
	/** Default color to be used for edges in the graph	 */
	public static Color DEFAULT_EDGE_COLOR = Color.BLACK;  //  @jve:decl-index=0:
	/** Default color to be used for edges in the graph when highlighted/picked. */
	public static Color DEFAULT_EDGE_PICKED_COLOR = Color.CYAN;  //  @jve:decl-index=0:
	/** Default vertex size of the nodes. */
	public static Integer DEFAULT_VERTEX_SIZE = 10;  //  @jve:decl-index=0:
	/** Default raster size for guide grid. */
	public static Integer DEFAULT_RASTER_SIZE = 20;  //  @jve:decl-index=0:
	/** Default width for edges. */
	public static float DEFAULT_EDGE_WIDTH = 2;

	
	/** Environment model controller, to be passed by the parent GUI. */
	private GraphEnvironmentController controller = null;  //  @jve:decl-index=0:
	
	/** The GUI's main component, either the graph visualization, or an empty JPanel if no graph is loaded */
	private Component centerComponent = null;
	/** The ToolBar for this component */
	private BasicGraphGuiTools graphGuiTools = null;
	
	/** Graph visualization component */
	private VisualizationViewer<GraphNode, GraphEdge> visView = null;
	/** JUNG object handling zooming */
	private ScalingControl scalingControl = new CrossoverScalingControl();  //  @jve:decl-index=0:
	/** the margin of the graph for the visualization */
	private double graphMargin = 25;
	/** Indicates that the initial scaling is allowed */
	private boolean allowInitialScaling = true;
	
	/**
	 * The DefaultModalGraphMouse which can be added to the visualization
	 * viewer. Used here for the transforming mode
	 */
	private DefaultModalGraphMouse<GraphNode, GraphEdge> defaultModalGraphMouse = null; 		// @jve:decl-index=0:
	/**
	 * The pluggable graph mouse which can be added to the visualization viewer.
	 * Used here for customized Picking mode
	 */
	private PluggableGraphMouse pluggableGraphMouse = null; 								// @jve:decl-index=0:


	
	/**
	 * This is the default constructor
	 * @param controller The Graph Environment controller
	 */
	public BasicGraphGui(GraphEnvironmentController controller) {
		super();
		this.controller = controller; 
		this.graphGuiTools = new BasicGraphGuiTools(this.controller);
		
		initialize();
	}

	/**
	 * This method initializes this
	 * @return void
	 */
	private void initialize() {
		
		this.setSize(300, 300);
		this.setLayout(new BorderLayout());
		this.add(this.graphGuiTools.getJToolBar(), BorderLayout.WEST);
		
		// --- React on component changes ------------------
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent ce) { 
				setInitialScaling();
			}
		});
		
		// --- Initializing JUNG mouse modes --------------
		this.initMouseModes();
		
	}

	/**
	 * This method initializes the two mouse modes - Transforming mode and
	 * picking mode by using PluggableGraphMouse and DefaultModalGraphMouse
	 * respectively
	 */
	private void initMouseModes() {
		
		pluggableGraphMouse = new PluggableGraphMouse();
		
		// Adding the picking plugin
		pluggableGraphMouse.add(new GraphEnvironmentMousePlugin(this));
		// Adding the context menu plugin
		GraphEnvironmentPopupPlugin<GraphNode, GraphEdge> popupPlugin = new GraphEnvironmentPopupPlugin<GraphNode, GraphEdge>(this);
		popupPlugin.setEdgePopup(this.graphGuiTools.getEdgePopup());
		popupPlugin.setVertexPopup(this.graphGuiTools.getVertexPopup());
		
		pluggableGraphMouse.add(popupPlugin);

		defaultModalGraphMouse = new DefaultModalGraphMouse<GraphNode, GraphEdge>();
		defaultModalGraphMouse.setMode(DefaultModalGraphMouse.Mode.TRANSFORMING);
	}

	/**
	 * @return the pluggableGraphMouse
	 */
	public PluggableGraphMouse getPluggableGraphMouse() {
		return pluggableGraphMouse;
	}
	/**
	 * @return the pluggableGraphMouse
	 */
	public DefaultModalGraphMouse<GraphNode, GraphEdge> getDefaultModalGraphMouse() {
		return defaultModalGraphMouse;
	}

	/**
     * Controls the shape, size, and aspect ratio for each vertex.
     * @author Satyadeep
     */
    private final class VertexShapeSizeAspect<V,E> extends AbstractVertexShapeTransformer <V> implements Transformer<V,Shape>  {
    	
        protected boolean scale = false;
        
        public VertexShapeSizeAspect() {
        
        	this.setSizeTransformer(new Transformer<V,Integer>() {
        		
				public Integer transform(V v) {
		            
					Integer size = BasicGraphGui.DEFAULT_VERTEX_SIZE; // default
					if (scale) {   
		            	try{
		            		// --- Get the vertex size from the component type settings -
			            	ComponentTypeSettings cts = controller.getComponentTypeSettings().get("node");
			            	Integer vertexSize = cts.getVertexSize();
			    			if(vertexSize!=null){
			    				size = vertexSize;
			    			} else{
			    				size = BasicGraphGui.DEFAULT_VERTEX_SIZE;
			    			}			
		            	
		            	} catch(NullPointerException ex){
							ex.printStackTrace();
							size = BasicGraphGui.DEFAULT_VERTEX_SIZE;				
						}
		            } 
		            return size;
				}});
        
        }//end constructor
        
		/**
		 * Sets the scaling.
		 * @param scale the new scaling
		 */
		public void setScaling(boolean scale) {
            this.scale = scale;
        }
                
        /* (non-Javadoc)
         * @see org.apache.commons.collections15.Transformer#transform(java.lang.Object)
         */
        public Shape transform(V v) {
        	return factory.getEllipse(v);
        }
    }
    
    /**
	 * Gets the VisualizationViewer
	 * @return The VisualizationViewer
	 */
	public VisualizationViewer<GraphNode, GraphEdge> getVisView() {
		return visView;
	}
	/**
	 * Gets the scaling control.
	 * @return the scalingControl
	 */
	public ScalingControl getScalingControl() {
		return scalingControl;
	}
	/**
	 * Sets the scaling control.
	 * @param scalingControl the scalingControl to set
	 */
	public void setScalingControl(ScalingControl scalingControl) {
		this.scalingControl = scalingControl;
	}
	
	/**
	 * Checks if the initial scaling is allowed.
	 * @return true, if is allow initial scaling
	 */
	public boolean isAllowInitialScaling() {
		return allowInitialScaling;
	}
	/**
	 * Sets to allow the initial scaling.
	 * @param allowInitialScaling the new allow initial scaling
	 */
	public void setAllowInitialScaling(boolean allowInitialScaling) {
		this.allowInitialScaling = allowInitialScaling;
	}
	
	/**
	 * Sets the initial scaling for the graph on the VisualizationViewer.
	 */
	public void setInitialScaling() {
		
		if (this.visView==null) return;
		if (this.allowInitialScaling==false) return;
		
		Graph<GraphNode, GraphEdge> currGraph = this.visView.getGraphLayout().getGraph();
		Rectangle2D rectGraph = this.getGraphSpreadDimension(currGraph);
		Rectangle2D rectVis   = this.visView.getVisibleRect();
		if (rectVis.isEmpty()) return;
		
		double graphWidth  = rectGraph.getWidth() + this.graphMargin;
		double graphHeight = rectGraph.getHeight() + this.graphMargin;
		double VisibWidth  = rectVis.getWidth();
		double VisibHeight = rectVis.getHeight();

		float scaleX = (float) (graphWidth  / VisibWidth);
		float scaleY = (float) (graphHeight / VisibHeight);
		if (scaleX < 1) scaleX = 1;
		if (scaleY < 1) scaleY = 1;
		
		float scale = scaleX;
		if (scaleX < scaleY) scale = scaleY;	
				
		if (scale!=0 && scale!=1) {
			this.scalingControl.scale(this.visView, 1/scale,  new Point2D.Double(0, 0));	
		}
		this.allowInitialScaling=false;
		
	}
	
	/**
	 * Gets the graph spread as Rectangle.
	 *
	 * @param graph the graph
	 * @return the graph spread
	 */
	private Rectangle2D getGraphSpreadDimension(Graph<GraphNode, GraphEdge> graph) {
		
		Double x_min = null; 
		Double y_min = null; 
		double x_max = 0; 
		double y_max = 0;
		
		if (graph==null) {
			return new Rectangle2D.Double(0, 0, 0, 0);
		}
		
		Collection<GraphNode> nodeCollection = graph.getVertices();
		GraphNode[] nodes = nodeCollection.toArray(new GraphNode[nodeCollection.size()]);
		for (int i = 0; i < nodes.length; i++) {
			double x = nodes[i].getPosition().getX();
			double y = nodes[i].getPosition().getY();
			
			if (x_min==null) x_min = x;
			if (y_min==null) y_min = y;
			
			if (x < x_min) x_min = x;
			if (x > x_max) x_max = x;
			if (y < y_min) y_min = y;
			if (y > y_max) y_max = y;
		}

		if (x_min==null) x_min = 0.0;
		if (y_min==null) y_min = 0.0;
		
		Rectangle2D rect = new Rectangle2D.Double(x_min, y_min, x_max, y_max);
		return rect;
	}
	
	/**
	 * Sets the graph coordinates to values bigger than 0 for the x and the y axis.
	 *
	 * @param graph the graph
	 * @return the graph
	 */
	private Graph<GraphNode, GraphEdge> correctGraphCoordinates(Graph<GraphNode, GraphEdge> graph2Correct, double xCorrect, double yCorrect) {
		
		if (xCorrect==0 && yCorrect==0) {
			// --- Nothing to correct ---------------------
			return graph2Correct;
		}
		
		// --- Correct the positions of the graph nodes ---
		Graph<GraphNode, GraphEdge> graph = graph2Correct; 
		Collection<GraphNode> nodeCollection = graph.getVertices();
		GraphNode[] nodes = nodeCollection.toArray(new GraphNode[nodeCollection.size()]);
		for (int i = 0; i < nodes.length; i++) {
			Point2D pos = nodes[i].getPosition();
			pos.setLocation(pos.getX()+xCorrect, pos.getY()+yCorrect);
			nodes[i].setPosition(pos);
		}
		return graph;
	}
	
	
	/**
	 * This method assigns a graph to a new VisualizationViewer and adds 
	 * it to the GUI This is used for creating graphs for the first time.
	 * 
	 * @param graph The graph
	 */
	public void setGraph(Graph<GraphNode, GraphEdge> graph) {

		// --- Remove the old component -------------------
		if (centerComponent!=null) {
			this.remove(centerComponent);
			this.centerComponent = null;
			this.visView = null;
		}
		
		// --- Set the display ---------------------------- 
		if (graph==null) {
			// --- NO graph to display ----------
			this.visView = null;
			this.centerComponent = new JPanel();
			this.add(centerComponent, BorderLayout.CENTER);
		
		} else {
			// --- Graph to display -------------
			this.visView = this.getNewVisualizationViewer(graph);
			this.centerComponent = new GraphZoomScrollPane(this.visView);
			this.add(this.centerComponent, BorderLayout.CENTER);
		
			this.allowInitialScaling=true;
			this.validate();
			this.setInitialScaling();
		}
		
		
	}
	
	/**
	 * Gets the new visualization viewer for a given graph.
	 *
	 * @param graph the graph
	 * @return the new VisualizationViewer
	 */
	public VisualizationViewer<GraphNode, GraphEdge> getNewVisualizationViewer(Graph<GraphNode, GraphEdge> graph){
		
		// ----------------------------------------------------------------
		// --- Get the ComponentTypeSettings for nodes --------------------
		// ----------------------------------------------------------------
		final ComponentTypeSettings cts = controller.getComponentTypeSettings().get("node");
		
		// ----------------------------------------------------------------
		// --- Get the spread of the graph and correct the positions ------
		// ----------------------------------------------------------------
		double moveX = 0;
		double moveY = 0;
		
		Rectangle2D rect = this.getGraphSpreadDimension(graph);
		if (rect.getX()!=graphMargin) moveX = (rect.getX()*(-1)) + graphMargin;  
		if (rect.getY()!=graphMargin) moveY = (rect.getY()*(-1)) + graphMargin;
		graph = this.correctGraphCoordinates(graph, moveX, moveY);
		
		// ----------------------------------------------------------------
		// --- Define graph layout ----------------------------------------
		// ----------------------------------------------------------------
		Layout<GraphNode, GraphEdge> layout = new StaticLayout<GraphNode, GraphEdge>(graph);
		layout.setSize(new Dimension((int) (rect.getWidth()+graphMargin), (int) (rect.getHeight()+graphMargin)));
		layout.setInitializer(new Transformer<GraphNode, Point2D>() {
			@Override
			public Point2D transform(GraphNode node) {
				return node.getPosition(); // The position is specified in the GraphNode instance	
			}
		});


		// ----------------------------------------------------------------
		// --- Create a new VisualizationViewer instance ------------------
		// ----------------------------------------------------------------
		final VisualizationViewer<GraphNode, GraphEdge> vViewer = new VisualizationViewer<GraphNode, GraphEdge>(layout);
		vViewer.setBackground(Color.WHITE);
		
		// --- Configure vertex shape and size --						
		VertexShapeSizeAspect<GraphNode, GraphEdge> vssa = new VertexShapeSizeAspect<GraphNode, GraphEdge>();
		vssa.setScaling(true);
		vViewer.getRenderContext().setVertexShapeTransformer(vssa); 
		
		// --- Configure mouse interaction --------------------------------
		vViewer.setGraphMouse(pluggableGraphMouse);

		// --- Set tool tip for nodes -------------------------------------
		vViewer.setVertexToolTipTransformer(new Transformer<GraphNode,String>() {
			@Override
			public String transform(GraphNode edge) {
				return edge.getId();
			}
		});

		// --- Configure to show node labels ------------------------------
		boolean showLable = false;
		if (cts==null) {
			showLable = true;
		} else {
			if (cts.isShowLabel()==true){
				showLable = true;
			}
		}
		// --- Configure node labels --------------------------------------
		if (showLable==true) {
			vViewer.getRenderContext().setVertexLabelTransformer(
					new Transformer<GraphNode, String>() {
						@Override
						public String transform(GraphNode node) {
							return node.getId();
						}
					});
		}
		// --- Configure vertex colors ------------------------------------
		vViewer.getRenderContext().setVertexFillPaintTransformer(
				new Transformer<GraphNode, Paint>() {
					public Paint transform(GraphNode node) {
						if(vViewer.getPickedVertexState().isPicked(node))
						{//Highlight color when picked	
							return BasicGraphGui.DEFAULT_VERTEX_PICKED_COLOR;
						}
						else
						{	//Get the color from the component type settings
							try{
								String colorString= cts.getColor();
								if(colorString!=null){
									Color color = new Color(Integer.parseInt(colorString));							
									return color;
								}
								else
									return BasicGraphGui.DEFAULT_VERTEX_COLOR;
							}
							catch(NullPointerException ex){
								ex.printStackTrace();
								return BasicGraphGui.DEFAULT_VERTEX_COLOR;					
							}
						}
					}
				}
		);
		// --- Configure edge colors --------------------------------------
		vViewer.getRenderContext().setEdgeDrawPaintTransformer(
		new Transformer<GraphEdge, Paint>() {
			public Paint transform(GraphEdge edge) {
				if(vViewer.getPickedEdgeState().isPicked(edge)) {
					//Highlight color when picked	
					return BasicGraphGui.DEFAULT_EDGE_PICKED_COLOR;
					
				} else {	//Get the color from the component type settings
					try{
						ComponentTypeSettings cts = controller.getComponentTypeSettings().get(edge.getComponentType());
						String colorString = cts.getColor();
						if(colorString!=null){
							Color color = new Color(Integer.parseInt(colorString));							
							return color;
						}
						else
							return BasicGraphGui.DEFAULT_EDGE_COLOR;
					}
					catch(NullPointerException ex){
						ex.printStackTrace();
						return BasicGraphGui.DEFAULT_EDGE_COLOR;							
					}
						
				}
			}
		}
		);
		// --- Configure Edge Image Labels --------------------------------			
		vViewer.getRenderContext().setEdgeLabelTransformer(new Transformer<GraphEdge,String>() {	        	
			public String transform(GraphEdge edge) {
				//Get the path of the Image from the component type settings
				String textDisplay = "";
				try{
					ComponentTypeSettings cts = controller.getComponentTypeSettings().get(edge.getComponentType());
					String edgeImage = cts.getEdgeImage();
					boolean showLabel = cts.isShowLabel();
					
					if (showLabel) {
						textDisplay = edge.getId();
					}
					
					if(edgeImage!=null){
						URL url = getClass().getResource(edgeImage);
						if(url!=null){
							if (showLabel) {
								textDisplay = "<html><center>"+textDisplay+"<br><img src='"+url+"'></center></html>";	
							} else {
								textDisplay = "<html><center><img src='"+url+"'></center></html>";
							}
							return textDisplay;
						} else {
							return textDisplay;
						}
					} else {
						return textDisplay;
					}
				
				} catch(NullPointerException ex) {
					ex.printStackTrace();
					return edge.getId();						
				}
			}
			});
		// --- Configure edge label position ------------------------------
		vViewer.getRenderContext().setLabelOffset(0);
		vViewer.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer<GraphNode, GraphEdge>(.5, .5));
		
		// --- Use straight lines as edges --------------------------------
		vViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<GraphNode, GraphEdge>());
		
		// --- Set edge width ---------------------------------------------
		vViewer.getRenderContext().setEdgeStrokeTransformer(new Transformer<GraphEdge, Stroke>(){
			@Override
			public Stroke transform(GraphEdge edge) {
				float edgeWidth = BasicGraphGui.DEFAULT_EDGE_WIDTH;
				try {
					ComponentTypeSettings cts = controller.getComponentTypeSettings().get(edge.getComponentType());
					edgeWidth = cts.getEdgeWidth();
					if(edgeWidth==0) {
						edgeWidth = BasicGraphGui.DEFAULT_EDGE_WIDTH;
					}  
					
				} catch (Exception e) {
					e.printStackTrace();
					edgeWidth = BasicGraphGui.DEFAULT_EDGE_WIDTH;
				}
				return new BasicStroke(edgeWidth);
			}
		}); 
		return vViewer;
	}
	
	/**
	 * Repaints the visualisation viewer, with the given graph
	 * @param graph The new graph to be painted with.
	 */
	public void repaintGraph(Graph<GraphNode, GraphEdge> graph) {
		visView.getGraphLayout().setGraph(graph);
		visView.repaint();
	}

	/**
	 * This method notifies the observers about a graph object selection
	 * @param pickedObject The selected object
	 */
	public void handleObjectLeftClick(Object pickedObject) {
		this.clearPickedObjects();
		selectObject(pickedObject);
	}

	/**
	 * Notifies the observers that this object is right clicked
	 * @param pickedObject the selected object
	 */
	public void handleObjectRightClick(Object pickedObject) {
		this.clearPickedObjects();
		selectObject(pickedObject);
	}

	/**
	 * Invoked when a graph node or edge is double clicked (left or right)
	 * @param pickedObject
	 */
	public void handleObjectDoubleClick(Object pickedObject) {
		this.clearPickedObjects();
		selectObject(pickedObject, true);
	}

	/**
	 * Clears the picked nodes and edges
	 */
	public void clearPickedObjects() {
		visView.getPickedVertexState().clear();
		visView.getPickedEdgeState().clear();
	}

	/**
	 * Sets a node or edge as picked
	 * @param object The GraphNode or GraphEdge to pick
	 */
	public void setPickedObject(GraphElement object) {
		if (object instanceof GraphEdge) {
			visView.getPickedEdgeState().pick((GraphEdge) object, true);
		} else if (object instanceof GraphNode) {
			visView.getPickedVertexState().pick((GraphNode) object, true);
		}
	}

	/**
	 * Marks a group of objects as picked
	 * @param objects The objects
	 */
	public void setPickedObjects(Vector<GraphElement> objects) {
		Iterator<GraphElement> objIter = objects.iterator();
		while (objIter.hasNext()) {
			setPickedObject(objIter.next());
		}
	}

	/**
	 * Returns the node which is picked. If multiple nodes are picked, returns null.
	 * @return GraphNode - the GraphNode which is picked. 
	 */
	public GraphNode getPickedVertex(){
		Set<GraphNode> nodeSet = this.getVisView().getPickedVertexState().getPicked();
		if(nodeSet.size()==1)
			return nodeSet.iterator().next();
		return null;
	}
	/**
	 * Returns the edge which is picked. If multiple nodes are picked, returns null.
	 * @return GraphEdge - the GraphNode which is picked. 
	 */
	public GraphEdge getPickedEdge(){
		Set<GraphEdge> edgeSet = this.getVisView().getPickedEdgeState().getPicked();
		if(edgeSet.size()==1)
			return edgeSet.iterator().next();
		return null;
	}
	
	/**
	 * This method handles the selection of an object, i.e. highlights the related graph elements
	 * @param object The object to select
	 */
	public void selectObject(Object object){
		this.selectObject(object, false);
	}
	/**
	 * Same as selectObject but optionally shows component settings dialog 
	 * @param object 
	 * @param showComponentSettingsDialog - shows the dialog if true
	 */
	public void selectObject(Object object, boolean showComponentSettingsDialog){
		
		if (object instanceof GraphNode) {
			this.setPickedObject((GraphElement) object);
			
		} else if (object instanceof GraphEdge) {
			NetworkComponent netComp = getNetworkComponentFromEdge((GraphEdge)object);
			this.setPickedObjects(getNetworkComponentElements(netComp));
			GraphEnvironmentControllerGUI graphEnvGui = (GraphEnvironmentControllerGUI) this.controller.getEnvironmentPanel();
			graphEnvGui.selectComponentInTable(netComp);
			
		} else if (object instanceof NetworkComponent) {
			this.setPickedObjects(getNetworkComponentElements((NetworkComponent)object));
			
		}
		
		if (showComponentSettingsDialog==true) {
			OntologySettingsDialog osd = null;
			if (object instanceof GraphNode) {
				osd = new OntologySettingsDialog(this.controller.getProject(), this.controller, object);		
				
			} else if (object instanceof GraphEdge) {
				NetworkComponent netComp = getNetworkComponentFromEdge((GraphEdge)object);
				osd = new OntologySettingsDialog(this.controller.getProject(), this.controller, netComp);
				
			} else if (object instanceof NetworkComponent) {
				osd = new OntologySettingsDialog(this.controller.getProject(), this.controller, (NetworkComponent)object);
				
			}		
			osd.setVisible(true);
		}
		
	}
	
	/**
	 * Checks the constraint for a given node in the network model whether it is free to add a component.
	 * Constraint :- a node can be a member of maximum two network components.
	 * @param object - selected node
	 * @return true if the node is a member of 0 or 1 components, false otherwise
	 */
	public boolean isFreeToAddComponent(Object object) {
		if(object instanceof GraphNode){
			GraphNode node = (GraphNode) object;	
			//The number of network components containing this node
			int compCount = getNetworkComponentCount(node);
			if(compCount == 1)
			{ //Node is present in only one component
				
				NetworkComponent comp = getNetworkComponentsFromNode(node).iterator().next();
				if(isStarGraphElement(comp)){
				//If it is a star component
					//Check whether the given node is the center of the star.
					if(isCenterNodeOfStar(node,comp))
						return false;						
				}
				return true;
			}					
		}	
		return false;
	}
	
	/**
	 * Returns the number of network components which have the given node
	 * @param node Vertex in the Graph
	 * @return count No of network components containing the given node
	 */
	public int getNetworkComponentCount(GraphNode node){
		if(this.controller.getNetworkModel() != null){				
			// Get the components from the controllers GridModel
			Iterator<NetworkComponent> components = this.controller.getNetworkModel().getNetworkComponents().values().iterator();						
			int count = 0;
			while(components.hasNext()){ // iterating through all network components
				NetworkComponent comp = components.next();
				// check if the component contains the current node
				if(comp.getGraphElementIDs().contains(node.getId())){
					count++;
				}
			}
			return count;
		}
		return 0;
	}
	
	/**
	 * Gives the set of network components containing the given node.
	 * @param node - A GraphNode
	 * @return HashSet<NetworkComponent> - The set of components which contain the node
	 */
	public HashSet<NetworkComponent> getNetworkComponentsFromNode(GraphNode node){						
		// Get the components from the controllers GridModel
		HashSet<NetworkComponent>  compSet = new HashSet<NetworkComponent>();
		Iterator<NetworkComponent> components = this.controller.getNetworkModel().getNetworkComponents().values().iterator();						
		while(components.hasNext()){ // iterating through all network components
			NetworkComponent comp = components.next();
			// check if the component contains the given node
			if(comp.getGraphElementIDs().contains(node.getId())){
				compSet.add(comp);
			}
		}
		return compSet;		
	}
	/**
	 * Checks whether a network component is in the star graph element
	 * @param comp the network component
	 * @return true if the component is a star graph element
	 */
	private boolean isStarGraphElement(NetworkComponent comp) {
		
		GraphElementPrototype graphElement = null;
		try {
			Class<?> theClass;
			theClass = Class.forName(comp.getPrototypeClassName());
			graphElement = (GraphElementPrototype)theClass.newInstance();
		
		} catch (ClassNotFoundException ex ) {
			System.err.println( ex + " GraphElementPrototype class must be in class path.");
		} catch(InstantiationException ex ) {
			System.err.println( ex + " GraphElementPrototype class must be concrete.");
	    } catch(IllegalAccessException ex ) {
	    	System.err.println( ex + " GraphElementPrototype class must have a no-arg constructor.");
	    }
	    
	    // --- Star3GraphElement is the super class of all star graph elements ---
	    if(graphElement instanceof Star3GraphElement){
	    	return true;
	    } else {
	    	return false;
	    }
	    	
	}
	/**
	 * Given  a node and a graph component of star prototype, 
	 * checks whether the node is the center of the star or not.
	 * @param node The node to be checked 
	 * @param comp The network component containing the node having the star prototype
	 */
	private boolean isCenterNodeOfStar(GraphNode node, NetworkComponent comp) {
		//Get the network model
		NetworkModel networkModel = this.controller.getNetworkModel();
		Iterator<String> iter  = comp.getGraphElementIDs().iterator();
		//For each graph element of the component
		while(iter.hasNext()){
			GraphElement elem =  networkModel.getGraphElement(iter.next());
			if(elem instanceof GraphEdge){		
				//The center node should be incident on all the edges of the component
				if( ! networkModel.getGraph().isIncident(node, (GraphEdge)elem))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * This method gets the NetworkComponent the GraphEdge with the given ID belongs to
	 * @param edge The GraphEdge's ID
	 * @return The NetworkComponent
	 */
	public NetworkComponent getNetworkComponentFromEdge(GraphEdge edge){
		// Get the components from the controllers GridModel
		Iterator<NetworkComponent> components = controller.getNetworkModel().getNetworkComponents().values().iterator();						
		while(components.hasNext()){ // iterating through all network components
			NetworkComponent comp = components.next();
			// check if the component contains the given node
			if(comp.getGraphElementIDs().contains(edge.getId())){
				return comp;
			}
		}
		return null;
	}
	
	/**
	 * This method gets the GraphElements that are part of the given NetworkComponent
	 * @param netComp The NetworkComponent
	 * @return The GraphElements
	 */
	private Vector<GraphElement> getNetworkComponentElements(NetworkComponent netComp){
		Vector<GraphElement> elements = new Vector<GraphElement>();
		
		Iterator<String> elementIDs = netComp.getGraphElementIDs().iterator();
		while(elementIDs.hasNext()){
			elements.add(this.controller.getNetworkModel().getGraphElement(elementIDs.next()));
		}
		
		return elements;
	}
	
	/**
	 * Removes the given network component from the network model,
	 * updates the graph accordingly and the HashMap of graphElements 
	 * 
	 * @param selectedComponent The network component to be removed
	 */
	public void removeNetworkComponent(NetworkComponent selectedComponent) {
		
		NetworkModel networkModel = this.controller.getNetworkModel();
		
		//The IDs of the elements present in the given component 
		HashSet<String> graphElementIDs = selectedComponent.getGraphElementIDs();
		Iterator<String> idIter = graphElementIDs.iterator();
		
		//For each graph element of the network component
		while(idIter.hasNext()){ 
			String elementID = idIter.next();
			GraphElement element = networkModel.getGraphElement(elementID);
			//For an edge		
			if(element instanceof GraphEdge){ 
				//Remove from the Graph
				networkModel.getGraph().removeEdge((GraphEdge)element);
				//Remove from the HashMap of GraphElements
				networkModel.getGraphElements().remove(element.getId());
			}
			//For a node
			else if (element instanceof GraphNode){
				//Check whether the GraphNode is present in any other NetworkComponent
				
				// The vertex is only present in the selected component
				if(getNetworkComponentCount((GraphNode)element)==1){
					//Removing the vertex from the Graph
					networkModel.getGraph().removeVertex((GraphNode)element);
					//Remove from the HashMap of GraphElements
					networkModel.getGraphElements().remove(element.getId());
				}
			}
		}
		//Finally, remove the network component from the HashMap of components in the network model
		networkModel.removeNetworkComponent(selectedComponent);
		
		//Removing the new agent from the agent start list of the simulation setup
		int i=0;
		for( i=0; i < this.controller.getAgents2Start().size(); i++) {
			AgentClassElement4SimStart ac4s = (AgentClassElement4SimStart) this.controller.getAgents2Start().get(i);
			if(ac4s.getStartAsName().equals(selectedComponent.getId())){
				this.controller.getAgents2Start().remove(i);
				break;
			}
		}
		
		//Shifting the positions of the later components by 1
		for(int j=i; j<this.controller.getAgents2Start().size();j++) {
			AgentClassElement4SimStart ac4s = (AgentClassElement4SimStart) this.controller.getAgents2Start().get(j);
			ac4s.setPostionNo(ac4s.getPostionNo()-1);
		}
	}
	
	/**
	 * Merges the two nodes as a single node 
	 * and updates the graph and network model
	 * @param node1
	 * @param node2
	 */
	public void mergeNodes(GraphNode node1, GraphNode node2) {
		//Environment Network Model
		NetworkModel networkModel = this.controller.getNetworkModel();
		Graph<GraphNode,GraphEdge> graph = networkModel.getGraph();
		
		//Get the Network components from the nodes
		
		NetworkComponent comp1 = getNetworkComponentsFromNode(node1).iterator().next();
		NetworkComponent comp2 = getNetworkComponentsFromNode(node2).iterator().next();
		
		//Finding the intersection set of the Graph elements of the two network components
		HashSet<String> intersection = new HashSet<String>(comp1.getGraphElementIDs());
		intersection.retainAll(comp2.getGraphElementIDs());
		//Checking the constraint - Two network components can have maximum one node in common
		if(intersection.size()==0){
			// No common node
			
			//Incident Edges on node2
			Collection<GraphEdge> incidentEdges = graph.getIncidentEdges(node2);		
			Iterator<GraphEdge> edgeIter = incidentEdges.iterator();
			while(edgeIter.hasNext()){ // for each incident edge
				GraphEdge edge = edgeIter.next();
				//Find the node on the other side of the edge
				GraphNode otherNode = graph.getOpposite(node2,edge);
				//Create a new edge with the same ID and type
				GraphEdge newEdge = new GraphEdge(edge.getId(), edge.getComponentType());
				
				if(graph.getSource(edge)!=null) {
					//if the edge is directed
					if(graph.getSource(edge) == node2)
						graph.addEdge(newEdge,node1, otherNode, EdgeType.DIRECTED);
					else if(graph.getDest(edge) == node2)
						graph.addEdge(newEdge,otherNode, node1, EdgeType.DIRECTED);
					
				} else {
					// if the edge is undirected
					graph.addEdge(newEdge,node1, otherNode, EdgeType.UNDIRECTED);
				}
				
				//Removing the old edge from the graph and network model
				graph.removeEdge(edge);
				networkModel.getGraphElements().remove(edge.getId());
				networkModel.getGraphElements().put(newEdge.getId(),newEdge);
			}
			
			//Updating the graph element IDs of the component
			comp2.getGraphElementIDs().remove(node2.getId());
			comp2.getGraphElementIDs().add(node1.getId());
			
			//Removing node2 from the graph and network model
			graph.removeVertex(node2);
			networkModel.getGraphElements().remove(node2.getId());
			
			this.controller.refreshNetworkModel();
			
		} else {
			//Have a common node
			JOptionPane.showMessageDialog(this,Language.translate("The two network components selected already have a vertex in common", Language.EN),
					Language.translate("Constraint: Two components can have atmost one common vertex", Language.EN),JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Splits the node into two nodes, separating the two network components
	 * and updates the graph and the network model
	 * @param node
	 */
	public void splitNode(GraphNode node) {		
		//Environment Network Model
		NetworkModel networkModel = this.controller.getNetworkModel();
		Graph<GraphNode,GraphEdge> graph = networkModel.getGraph();
		
		//Get the components containing the node
		Iterator<NetworkComponent> compIter = getNetworkComponentsFromNode(node).iterator();		
//		NetworkComponent comp1 = compIter.next();
		NetworkComponent comp2 = compIter.next();
		
		//Creating the new Graph node
		GraphNode newNode = new GraphNode();
		newNode.setId(networkModel.nextNodeID());
			//Shifting position a bit
		newNode.setPosition(new Point((int)node.getPosition().getX()-20, (int)node.getPosition().getY()-20));
		node.setPosition(new Point((int)node.getPosition().getX()+20, (int)node.getPosition().getY()+20));
		
		//Incident Edges on the node
		Collection<GraphEdge> incidentEdges = graph.getIncidentEdges(node);		
		Iterator<GraphEdge> edgeIter = incidentEdges.iterator();
		while(edgeIter.hasNext()){ // for each incident edge
			GraphEdge edge = edgeIter.next();
			//If the edge is in comp2
			if(comp2.getGraphElementIDs().contains(edge.getId())){						
				//Find the node on the other side of the edge
				GraphNode otherNode = graph.getOpposite(node,edge);
				//Create a new edge with the same ID and type
				GraphEdge newEdge = new GraphEdge(edge.getId(), edge.getComponentType());				
				//if the edge is directed
				if(graph.getSource(edge)!=null) 
				{
					if(graph.getSource(edge) == node)
						graph.addEdge(newEdge,newNode, otherNode, EdgeType.DIRECTED);
					else if(graph.getDest(edge) == node)
						graph.addEdge(newEdge,otherNode, newNode, EdgeType.DIRECTED);
				}
				// if the edge is undirected
				else 
					graph.addEdge(newEdge,newNode, otherNode, EdgeType.UNDIRECTED);
				
				//Removing the old edge from the graph and network model
				graph.removeEdge(edge);
				networkModel.getGraphElements().remove(edge.getId());
				networkModel.getGraphElements().put(newEdge.getId(),newEdge);
			}
		}
		
		//Updating the graph element IDs of the component
		comp2.getGraphElementIDs().remove(node.getId());
		comp2.getGraphElementIDs().add(newNode.getId());
		
		//Adding new node to the network model
		networkModel.getGraphElements().put(newNode.getId(),newNode);
		
		this.controller.refreshNetworkModel();
	}

	/**
	 * Gets the graph environment controller.
	 * @return the controller
	 */
	public GraphEnvironmentController getGraphEnvironmentController() {
		return controller;
	}
	/**
	 * Sets the graph environment controller.
	 * @param controller the controller to set
	 */
	public void setGraphEnvironmentController(GraphEnvironmentController controller) {
		this.controller = controller;
	}

}
