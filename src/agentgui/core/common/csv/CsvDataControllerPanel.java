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
package agentgui.core.common.csv;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import agentgui.core.application.Application;
import agentgui.core.application.Language;

import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

/**
 * The CSV Controller Panel that can be used with a customised CSV-Importer 
 * 
 * @author Nils Loose - DAWIS - ICB - University of Duisburg-Essen
 */
public class CsvDataControllerPanel extends JPanel implements ActionListener{


	private static final long serialVersionUID = -8553767098312965499L;
	
	private JToolBar jToolBarCsvHandling;
	
	private JScrollPane jScrollPaneTable;
	private JTable jTableData;
	private JButton jButtonImport;
	private JButton jButtonExport;
	
	private JLabel jLabelSeparator;
	private JCheckBox jCheckBoxHasHeadlines;
	private JComboBox<String> jComboBoxSeparator;
	
	/**
	 * The CSV data controller instance
	 */
	private CsvDataController importer;
	/**
	 * List of CSV data separators to choose from
	 */
	private String[] seperators = {";",",",":","."};
	
	
	/**
	 * Create the panel.
	 */
	public CsvDataControllerPanel() {
		this.initialize();
	}
	/**
	 * Initialize.
	 */
	private void initialize() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		this.add(getJToolBarCsvHandling(), gbc_toolBar);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		this.add(this.getJScrollPaneTable(), gbc_table);
	}
	
	private JToolBar getJToolBarCsvHandling() {
		if (jToolBarCsvHandling == null) {
			jToolBarCsvHandling = new JToolBar();
			jToolBarCsvHandling.setFloatable(false);
			jToolBarCsvHandling.add(getJButtonImport());
			jToolBarCsvHandling.add(getJButtonExport());
			jToolBarCsvHandling.addSeparator();
			jToolBarCsvHandling.add(getJComboBoxSeparator());
			jToolBarCsvHandling.add(getJLabelSeparator());
			jToolBarCsvHandling.addSeparator();
			jToolBarCsvHandling.add(getJCheckBoxHasHeadlines());
		}
		return jToolBarCsvHandling;
	}
	
	private JScrollPane getJScrollPaneTable() {
		if (jScrollPaneTable==null) {
			jScrollPaneTable = new JScrollPane();
			jScrollPaneTable.setViewportView(this.getJTableData());
		}
		return jScrollPaneTable;
	}
	private JTable getJTableData() {
		if (jTableData == null) {
			jTableData = new JTable();
			jTableData.setFillsViewportHeight(true);
		}
		return jTableData;
	}
	private JButton getJButtonImport() {
		if (jButtonImport == null) {
			jButtonImport = new JButton("Import");
			jButtonImport.setIcon(new ImageIcon(CsvDataControllerPanel.class.getResource("/agentgui/core/gui/img/import.png")));
			jButtonImport.addActionListener(this);
		}
		return jButtonImport;
	}
	private JButton getJButtonExport() {
		if (jButtonExport == null) {
			jButtonExport = new JButton("Export");
			jButtonExport.setIcon(new ImageIcon(CsvDataControllerPanel.class.getResource("/agentgui/core/gui/img/export.png")));
			jButtonExport.addActionListener(this);
		}
		return jButtonExport;
	}
	private JComboBox<String> getJComboBoxSeparator() {
		if (jComboBoxSeparator == null) {
			jComboBoxSeparator = new JComboBox<String>();
			jComboBoxSeparator.setModel(new DefaultComboBoxModel<String>(this.seperators));
		}
		return jComboBoxSeparator;
	}

	private JLabel getJLabelSeparator() {
		if (jLabelSeparator == null) {
			jLabelSeparator = new JLabel(Language.translate("Trennzeichen"));
		}
		return jLabelSeparator;
	}

	private JCheckBox getJCheckBoxHasHeadlines() {
		if (jCheckBoxHasHeadlines == null) {
			jCheckBoxHasHeadlines = new JCheckBox(Language.translate("Spaltenüberschriften"));
			jCheckBoxHasHeadlines.setSelected(true);
		}
		return jCheckBoxHasHeadlines;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == this.getJButtonImport()){
			
			// --- Import data from CSV
			JFileChooser jFileChooserImportCSV = new JFileChooser(Application.getGlobalInfo().getLastSelectedFolder());
			jFileChooserImportCSV.setFileFilter(new FileNameExtensionFilter(Language.translate("CSV-Dateien"), "csv"));
			jFileChooserImportCSV.setDialogTitle(Language.translate("CSV-Datei importieren"));
			
			if(jFileChooserImportCSV.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				Application.getGlobalInfo().setLastSelectedFolder(jFileChooserImportCSV.getCurrentDirectory());
				File csvFile = jFileChooserImportCSV.getSelectedFile();
				
				this.importer = new CsvDataController();
				this.importer.setHeadlines(this.getJCheckBoxHasHeadlines().isSelected());
				this.importer.setSeparator((String) this.getJComboBoxSeparator().getSelectedItem());
				
				this.importer.doImport(csvFile);
				DefaultTableModel dtm = this.importer.getDataModel();
				if(dtm != null){
					this.getJTableData().setModel(dtm);
				}
			}
			
		} else {
			
			// --- Export data to CSV
			JFileChooser jFileChooserExportCSV = new JFileChooser(Application.getGlobalInfo().getLastSelectedFolder());
			jFileChooserExportCSV.setFileFilter(new FileNameExtensionFilter(Language.translate("CSV-Dateien"), "csv"));
			jFileChooserExportCSV.setDialogTitle(Language.translate("CSV-Datei exportieren"));
			
			if(jFileChooserExportCSV.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
				Application.getGlobalInfo().setLastSelectedFolder(jFileChooserExportCSV.getCurrentDirectory());
				File csvFile = jFileChooserExportCSV.getSelectedFile();
				if(csvFile.getPath().endsWith(".csv") == false){
					csvFile = new File(csvFile.getPath().concat(".csv"));
				}
				
				importer.doExport(csvFile);
			}
		}
	}
	
	/**
	 * Just for development, remove later
	 * @param args
	 */
	public static void main(String[] args){
		JFrame frame = new JFrame("CSV Importer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new CsvDataControllerPanel());
		frame.setSize(600, 450);
		frame.setVisible(true);
	}
}