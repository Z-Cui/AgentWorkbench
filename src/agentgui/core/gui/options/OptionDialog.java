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
package agentgui.core.gui.options;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import agentgui.core.application.Application;
import agentgui.core.application.Language;
import agentgui.core.config.GlobalInfo.ExecutionMode;

/**
 * This JDialog represents the option dialog, where the 
 * background system can be set up. 
 * 
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class OptionDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final String pathImage = Application.getGlobalInfo().getPathImageIntern(); 
	
	private ImageIcon imageIcon = new ImageIcon( this.getClass().getResource(pathImage + "AgentGUI.png"));
	private Image image = imageIcon.getImage();  
	
	private Frame owner = null;
	
	private JSplitPane jSplitPaneMain = null;
	private JTabbedPane jTabbedPaneRight = null;
	private JScrollPane jScrollPaneLeft = null;
	private JTree jTreeOptions = null;
	private JPanel jPanelBase = null;
	private JButton jButtonClose = null;
	
	private DefaultTreeModel optionTreeModel;
	private DefaultMutableTreeNode rootNode;
	
	private TreeMap<Integer, String[]> additionalNodes = new TreeMap<Integer, String[]>();  //  @jve:decl-index=0:
	
	private StartOptions startOptions ;
	private UpdateOptions updateOptions ;

	
	/**
	 * Instantiates a new option dialog.
	 * @param owner the owner
	 */
	public OptionDialog(Frame owner) {
		super(owner);
		this.owner = owner;
		
		// --- Prepare OptionTree -----------------------------------
		rootNode = new DefaultMutableTreeNode(Language.translate("Optionen"));
		optionTreeModel = new DefaultTreeModel(rootNode);	
		
		// --- Set the Look and Feel of the Dialog ------------------
		ExecutionMode execMode = Application.getGlobalInfo().getExecutionMode(); 
		if (Application.isRunningAsServer()==true ||  execMode==ExecutionMode.DEVICE_SYSTEM) {
			if (Application.getGlobalInfo().getAppLnF()!=null) {
				setLookAndFeel(Application.getGlobalInfo().getAppLnF());
			}
		}
		
		this.initialize();

		// --- Translate --------------------------------------------
	    this.setTitle(Application.getGlobalInfo().getApplicationTitle() + ": " + Language.translate("Optionen"));
	    this.jButtonClose.setText(Language.translate("Schließen"));
	    
	    // --- Integrate sub panels ---------------------------------
	    String tabTitle = Language.translate("Programmstart");
	    this.addOptionTab(this.getStartOptions(), null);
	    
	    tabTitle = Language.translate("Agent.GUI - Update");
	    this.addOptionTab(this.getUpdateOptions(), null);
	    
	    if (Application.isRunningAsServer()==true || execMode==ExecutionMode.DEVICE_SYSTEM) {
	    	tabTitle = Language.translate("Konsole");
	    	this.addOptionTab(tabTitle, null, Application.getConsole(), tabTitle);	
	    }
	    
		 // --- Expand tree -----------------------------------------
	    this.optionTreeExpand2Level(3, true);
	}
	
	/**
	 * This method initialises this.
	 */
	private void initialize() {
		
		this.setModal(true);
		if (this.owner==null) {
			this.setAlwaysOnTop(true);
		}

		this.setTitle("Agent.GUI: Optionen");
		this.setIconImage(image);
		
		this.setContentPane(this.getJPanelBase());
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.registerEscapeKeyStroke();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
			}
		});

		// --- Size and centre dialog -------------------------------
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		
		this.setSize((int) (screenSize.getWidth()*0.7), (int)(screenSize.getHeight()*0.8));
		
		int top = (screenSize.height - this.getHeight()) / 2; 
	    int left = (screenSize.width - this.getWidth()) / 2; 
	    this.setLocation(left, top);	

	}

    /**
     * Registers the escape key stroke in order to close this dialog.
     */
    private void registerEscapeKeyStroke() {
    	final ActionListener listener = new ActionListener() {
            public final void actionPerformed(final ActionEvent e) {
            	setVisible(false);
            }
        };
        final KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        this.getRootPane().registerKeyboardAction(listener, keyStroke, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

	
	/**
	 * This method set the Look and Feel of this Dialog.
	 *
	 * @param NewLnF the new look and feel
	 */
	private void setLookAndFeel( String NewLnF ) {
		// --- Look and fell einstellen --------------- 
		if ( NewLnF == null ) return;		
		Application.getGlobalInfo().setAppLnf( NewLnF );
		try {
			String lnfClassname = Application.getGlobalInfo().getAppLnF();
			if (lnfClassname == null) {
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			}
			UIManager.setLookAndFeel(lnfClassname);
			SwingUtilities.updateComponentTreeUI(this);
			
		} catch (Exception e) {
				System.err.println("Cannot install " + Application.getGlobalInfo().getAppLnF()
					+ " on this platform:" + e.getMessage());
		}
	}		

	/**
	 * Gets the start options.
	 * @return the start options
	 */
	public StartOptions getStartOptions() {
		if (startOptions==null) {
			startOptions = new StartOptions(this);
		}
		return startOptions;
	}
	/**
	 * Gets the update options.
	 * @return the update options
	 */
	public UpdateOptions getUpdateOptions() {
		if (updateOptions==null) {
			updateOptions = new UpdateOptions();
		}
		return updateOptions;
	}
	
	/**
	 * This method initializes jSplitPaneMain.
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getJSplitPaneMain() {
		if (jSplitPaneMain == null) {
			jSplitPaneMain = new JSplitPane();
			jSplitPaneMain.setDividerSize(10);
			jSplitPaneMain.setResizeWeight(0.2D);
			jSplitPaneMain.setOneTouchExpandable(true);
			jSplitPaneMain.setRightComponent(getJTabbedPaneRight());
			jSplitPaneMain.setLeftComponent(getJScrollPaneLeft());
			jSplitPaneMain.setDividerLocation(200);
		}
		return jSplitPaneMain;
	}

	/**
	 * This method initializes jTabbedPaneRight.
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPaneRight() {
		if (jTabbedPaneRight == null) {
			jTabbedPaneRight = new JTabbedPane();
			jTabbedPaneRight.setTabPlacement(JTabbedPane.TOP);
			jTabbedPaneRight.setFont(new Font("Dialog", Font.BOLD, 12));
		}
		return jTabbedPaneRight;
	}

	/**
	 * This method initializes jScrollPaneLeft.
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneLeft() {
		if (jScrollPaneLeft == null) {
			jScrollPaneLeft = new JScrollPane();
			jScrollPaneLeft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jScrollPaneLeft.setViewportView(getJTreeOptions());
		}
		return jScrollPaneLeft;
	}

	/**
	 * This method initializes jTreeOptionGroup.
	 *
	 * @return javax.swing.JTree
	 */
	private JTree getJTreeOptions() {
		if (jTreeOptions == null) {
			jTreeOptions = new JTree(optionTreeModel);
			jTreeOptions.setName("OptionTree");
			jTreeOptions.setShowsRootHandles(false);
			jTreeOptions.setRootVisible(true);
			jTreeOptions.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
			jTreeOptions.addTreeSelectionListener( new TreeSelectionListener() {
				@Override
				public void valueChanged(TreeSelectionEvent ts) {
					// ----------------------------------------------------------
					// --- Tree-Selection abfangen --- S T A R T ----------------
					// ----------------------------------------------------------
					TreePath PathSelected = ts.getPath();
					Integer PathLevel = PathSelected.getPathCount();

					// ----------------------------------------------------------
					if ( PathLevel >= 2 ) {
						// ------------------------------------------------------
						// --- Fokus auf die entsprechende Karteikarte setzen ---
						// ------------------------------------------------------
						Component currComp = null;
						JPanel subJPanel = null;
						JTabbedPane subJTabs = null;
						String FocusNodeName = PathSelected.getPathComponent(1).toString();
						
						// --- Nach entsprechender Karteikarte suchen -----------
						for (int i=0; i<jTabbedPaneRight.getComponentCount();  i++ ) {
							currComp = jTabbedPaneRight.getComponent(i);
							if ( currComp.getName() == FocusNodeName ) {
								jTabbedPaneRight.setSelectedIndex(i);
								if ( currComp instanceof JPanel ) {
									subJPanel = (JPanel) jTabbedPaneRight.getComponent(i);	
								}
							}							
						}	
						// ------------------------------------------------------
						// --- Falls ein Aufruf aus einer tieferen Ebene kam ----
						// ------------------------------------------------------
						if (PathLevel>2 && subJPanel!=null) {
							// --- Suche nach einer JTabbedPane -----------------
							for (int i=0; i<subJPanel.getComponentCount();  i++ ) {
								currComp = subJPanel.getComponent(i);
								if ( currComp instanceof JTabbedPane ) {
									subJTabs = (JTabbedPane) currComp;
									break;									
								}							
							}	
							FocusNodeName = PathSelected.getPathComponent(2).toString();
							if (subJTabs!=null) {
								// --- Fokus auf Karteikarte setzen -------------
								for (int i=0; i<subJTabs.getComponentCount();  i++ ) {
									if ( subJTabs.getComponent(i).getName() == FocusNodeName ) {
										subJTabs.setSelectedIndex(i);
									}							
								}	
							}
						}
						// ------------------------------------------------------
					} 
					// ----------------------------------------------------------
					// --- Tree-Selection abfangen --- S T O P ------------------
					// ----------------------------------------------------------
				}// End - valueChanged
			});
		}
		return jTreeOptions;
	}

	/**
	 * This method initializes jPanelBase.
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelBase() {
		if (jPanelBase == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(0, 20, 25, 20);
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.anchor = GridBagConstraints.CENTER;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(20, 20, 20, 20);
			gridBagConstraints.weightx = 1.0;
			jPanelBase = new JPanel();
			jPanelBase.setLayout(new GridBagLayout());
			jPanelBase.add(getJSplitPaneMain(), gridBagConstraints);
			jPanelBase.add(getJButtonCancel(), gridBagConstraints11);
		}
		return jPanelBase;
	}

	/**
	 * This method initializes jButtonCancel.
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCancel() {
		if (jButtonClose == null) {
			jButtonClose = new JButton();
			jButtonClose.setText("Schließen");
			jButtonClose.setForeground(new Color(0, 0, 153));
			jButtonClose.setPreferredSize(new Dimension(100, 26));
			jButtonClose.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonClose.setActionCommand("Close");
			jButtonClose.addActionListener(this);
		}
		return jButtonClose;
	}
	
	/**
	 * Adds a Project-Tab and a new Base Folder (child of root!) to the OptionDialog.
	 *
	 * @param title the title
	 * @param icon the icon
	 * @param component the component
	 * @param toolTipText the tool tip text
	 */
	public void addOptionTab(String title, Icon icon, JComponent component, String toolTipText) {
		component.setName(title); 							
		this.jTabbedPaneRight.addTab(title, icon, component, toolTipText);
		this.addOptionTabNode(title);
	}
	
	/**
	 * Adds a Project-Tab and a new Base Folder (child of root!) to the OptionDialog.
	 *
	 * @param optionTab the option tab
	 * @param icon the icon
	 */	
	public void addOptionTab(AbstractOptionTab optionTab, Icon icon) {
		
		String title = optionTab.getTitle();
		String toolTip = optionTab.getTabToolTipText();
		optionTab.setName(title); 							
		jTabbedPaneRight.addTab(title, icon, optionTab, toolTip);

		addOptionTabNode(title);
	}

	/**
	 * Adds a new node to the left Project-Tree.
	 * @param newNode the new node
	 */
	public void addOptionTabNode(String newNode) {
		this.rootNode.add(new DefaultMutableTreeNode(newNode));
	}
	
	/**
	 * Adds a child-node to a given parent node of the left project tree.
	 * If the node can not be found, the method adds the textual node-definition
	 * to the local TreeMap 'additionalNodes', for a later addition to the tree
	 * 
	 * @param parentNodeName name of the parent node
	 * @param newNodeName name of the new node
	 */
	public void addOptionTabNode(String parentNodeName, String newNodeName) {
		DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode( newNodeName );
		DefaultMutableTreeNode parentNode  = getTreeNode(parentNodeName); 
		if (parentNode!=null) {
			parentNode.add( currentNode );			
		} else {
			String[] newNodeDef = new String[2];
			newNodeDef[0] = parentNodeName;
			newNodeDef[1] = newNodeName;
			additionalNodes.put(additionalNodes.size()+1, newNodeDef);
		}
	}
	
	/**
	 * Returns the tree node requested by the node name.
	 *
	 * @param nodeName the node name
	 * @return the DefaultMutableTreeNode, if found
	 */
	public DefaultMutableTreeNode getTreeNode(String nodeName) {
		
		DefaultMutableTreeNode nodeFound = null;
		DefaultMutableTreeNode currNode = null;
		String currNodeText;
		
		for (int i = 0; i < rootNode.getChildCount(); i++) {
			currNode = (DefaultMutableTreeNode) rootNode.getChildAt(i);
			currNodeText = currNode.getUserObject().toString(); 
			if (currNodeText.equals(nodeName)) {				
				nodeFound = currNode;
				break;
			} 
			
		}
		return nodeFound;
	}
	
	/**
	 * Sets the focus to a given tab.
	 *
	 * @param titleOfTab the new focus on tab
	 */
	public void setFocusOnTab (String titleOfTab) {
		for (int i=0; i<jTabbedPaneRight.getComponentCount();  i++ ) {
			Component Comp = jTabbedPaneRight.getComponent(i);
			if ( Comp.getName().equalsIgnoreCase( Language.translate(titleOfTab) ) ) {
				jTabbedPaneRight.setSelectedIndex(i);		
			}
		}	
	}

	
	// If expand is true, expands all nodes in the tree.
    // Otherwise, collapses all nodes in the tree.
    /**
	 * Option tree expand2 level.
	 *
	 * @param Up2TreeLevel the up2 tree level
	 * @param expand the expand
	 */
	public void optionTreeExpand2Level(Integer Up2TreeLevel, boolean expand ) {
    	
    	Integer CurrNodeLevel = 1;
    	if ( Up2TreeLevel == null ) 
    		Up2TreeLevel = 1000;

    	optionTreeExpand( new TreePath(rootNode), expand, CurrNodeLevel, Up2TreeLevel);
    }
	
	/**
	 * Option tree expand.
	 *
	 * @param parent the parent
	 * @param expand the expand
	 * @param currNodeLevel the curr node level
	 * @param up2TreeLevel the up2 tree level
	 */
	private void optionTreeExpand(TreePath parent, boolean expand, Integer currNodeLevel, Integer up2TreeLevel) {
    
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        if (currNodeLevel >= up2TreeLevel) {
        	return;
        }
        if (node.getChildCount() >= 0) {
            for ( @SuppressWarnings("rawtypes") Enumeration e=node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                optionTreeExpand(path, expand, currNodeLevel+1, up2TreeLevel);
            }
        }    
        // Expansion or collapse must be done bottom-up
        if (expand) {
        	jTreeOptions.expandPath(parent);
        } else {
        	jTreeOptions.collapsePath(parent);
        }
    }
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String actCMD = ae.getActionCommand();
		if (actCMD.equalsIgnoreCase("Close")) {
			this.setVisible(false);
		} else {
			System.err.println(Language.translate("Unbekannt: ") + "ActionCommand => " + actCMD);
		}
		
	}
		
	
}  //  @jve:decl-index=0:visual-constraint="33,9"
