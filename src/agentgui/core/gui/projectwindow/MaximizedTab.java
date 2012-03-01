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
package agentgui.core.gui.projectwindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import agentgui.core.application.Application;
import agentgui.core.application.Language;
import agentgui.core.gui.ProjectWindow;

/**
 * The Class MaximizedTab.
 */
public class MaximizedTab extends JInternalFrame {
	
	private static final long serialVersionUID = 6120229084857901290L;

	private final String pathImage = Application.RunInfo.PathImageIntern();
	private final ImageIcon iconRestore = new ImageIcon( this.getClass().getResource( pathImage + "RestoreView.png") );
	
	private ProjectWindow projectWindow = null;
	private JButton jButtonRestore4MainToolBar = null;
	
	
	/**
	 * Instantiates a new maximized tab.
	 * @param title the title
	 */
	public MaximizedTab(ProjectWindow projectWindow, String title) {
		super(title);
		this.projectWindow = projectWindow;
		this.initialize();	
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		
		this.setClosable(true);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setVisible(true);
		this.registerEscapeKeyStroke();
		this.addInternalFrameListener(new InternalFrameAdapter() {
			/* (non-Javadoc)
			 * @see javax.swing.event.InternalFrameAdapter#internalFrameClosing(javax.swing.event.InternalFrameEvent)
			 */
			@Override
			public void internalFrameClosing(InternalFrameEvent ife) {
				projectWindow.tabRestore();
				super.internalFrameClosing(ife);
			}
			/* (non-Javadoc)
			 * @see javax.swing.event.InternalFrameAdapter#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
			 */
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				projectWindow.tabRestore();
				super.internalFrameDeactivated(e);
			}
			
		});
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);
	
	}
	
	 /**
     * Registers the escape key stroke in order to close this dialog.
     */
    private void registerEscapeKeyStroke() {
    	final ActionListener listener = new ActionListener() {
            public final void actionPerformed(final ActionEvent e) {
            	projectWindow.tabRestore();
            }
        };
        final KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true);
        this.getRootPane().registerKeyboardAction(listener, keyStroke, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
	
    /**
     * Gets the JButton for restoring a maximzed view to a tab.
     * @return the JButton restore4 main tool bar
     */
    public JButton getJButtonRestore4MainToolBar() {
    	if (jButtonRestore4MainToolBar==null) {
    		jButtonRestore4MainToolBar = new JButton();
    		jButtonRestore4MainToolBar.setIcon(this.iconRestore);
    		jButtonRestore4MainToolBar.setText(" " + Language.translate("Ansicht wiederherstellen") + " !");
    		jButtonRestore4MainToolBar.setFont(new Font("Dialog", Font.BOLD, 12));
    		jButtonRestore4MainToolBar.setForeground(new Color(230, 0, 0));
    		jButtonRestore4MainToolBar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					projectWindow.tabRestore();
				}
			});
    	}
    	return jButtonRestore4MainToolBar;
    	
    }
}