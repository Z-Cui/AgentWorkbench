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
package agentgui.core.benchmark;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.SwingUtilities;

import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.kernel;
import agentgui.core.application.Application;
import agentgui.simulationService.load.LoadMeasureThread;

/**
 * This thread is doing the actual benchmark measurements, which 
 * results to the capability of a computer system to crunch numbers.<br> 
 * This will be comparable through a benchmark value in 
 * Mflops (Millions of floating point operations per second).<br><br>
 * 
 * The benchmark value can be obtained by using the method<br>  
 * 'Application.RunInfo.getBenchValue();'
 * 
 * @author Christian Derksen - DAWIS - ICB - University of Duisburg - Essen
 */
public class BenchmarkMeasurement extends Thread {

	private boolean forceBench = false;
	private boolean isHeadlessOperation = Application.isOperatingHeadless();

	private float benchValueOld = Application.getGlobalInfo().getBenchValue();
	private boolean benchAllwaysSkip = Application.getGlobalInfo().isBenchAllwaysSkip();
	private String benchExecOn = Application.getGlobalInfo().getBenchExecOn();
	private String nowExecOn;
	
	private double min_time = Constants.RESOLUTION_DEFAULT;
	private int FFT_size = Constants.FFT_SIZE;
	private int SOR_size =  Constants.SOR_SIZE;
	private int Sparse_size_M = Constants.SPARSE_SIZE_M;
	private int Sparse_size_nz = Constants.SPARSE_SIZE_nz;
	private int LU_size = Constants.LU_SIZE;

	private BenchmarkMonitor benchGUI;
	
	/**
	 * The constructor of this class.<br>
	 * If <i>forceBenchmark</i> is set to true the benchmark will be executed regardless of
	 * the settings in the property file or if the system was executed in the same network
	 * already or not.
	 *
	 * @param forceBenchmark the force benchmark
	 */
	public BenchmarkMeasurement(boolean forceBenchmark) {
		this.forceBench = forceBenchmark;	
	}
	/**
	 * Depending on the FileProperties this will measure the ability> of this computer to crunch numbers.<br>
	 * The results will be given in Mflops (Millions of floating point operations per second)  
	 */
	@Override
	public void run() {
		super.run();
		this.setName("SciMark2-Benchmark");

		// --- Criteria to not execute the benchmark ------
		if (this.benchValueOld>0 && this.getLocalSystemIdentifier().equalsIgnoreCase(this.benchExecOn) && this.benchAllwaysSkip==true && forceBench==false) {
			// --- Start search for Agents, Ontologies and BaseServices -----------
			Application.setBenchmarkRunning(false);
			if (this.isHeadlessOperation==false) {
				Application.getClassSearcher();
			}
			return;
		}  
		
		// --- Initialise Benchmark-Monitor  --------------
		if (this.isHeadlessOperation==false) {
			// --- Initialise BenchmarkMonitor ------------ 
			if (Application.getMainWindow()==null) {
				benchGUI = new BenchmarkMonitor(null);	
			} else {
				benchGUI = new BenchmarkMonitor(Application.getMainWindow());	
			}		
			
			// --- Set user buttons -----------------------
			if (this.benchValueOld>0 && this.getLocalSystemIdentifier().equalsIgnoreCase(benchExecOn)) {
				benchGUI.jButtonSkip.setEnabled(true);
				if (forceBench==true) {
					benchGUI.jButtonSkipAllways.setEnabled(false);
				} else {
					benchGUI.jButtonSkipAllways.setEnabled(true);
				}
			} else {
				benchGUI.jButtonSkip.setEnabled(false);
				benchGUI.jButtonSkipAllways.setEnabled(false);
			}
			
			// --- Progress Display --- ON ----------------
			benchGUI.setBenchmarkValue(this.benchValueOld);
			benchGUI.jProgressBarBenchmark.setMinimum(0);
			benchGUI.jProgressBarBenchmark.setMaximum(6);
			benchGUI.jProgressBarBenchmark.setValue(0);
			benchGUI.validate();
			benchGUI.setVisible(true);
			
		} else {
			System.out.println("Executing Benchmark, please wait ... ");
		}
		// ------------------------------------------------
		
		
		// --- Start benchmark tests ----------------------
		double res[] = new double[6];
		Random R = new Random(Constants.RANDOM_SEED);

		this.setBenchmarkProgress(1);
		res[1] = kernel.measureFFT(FFT_size, min_time, R);
		if (this.isSkipAction()) return;
		
		this.setBenchmarkProgress(2);
		res[2] = kernel.measureSOR(SOR_size, min_time, R);
		if (this.isSkipAction()) return;
		
		this.setBenchmarkProgress(3);
		res[3] = kernel.measureMonteCarlo(min_time, R);
		if (this.isSkipAction()) return;
		
		this.setBenchmarkProgress(4);
		res[4] = kernel.measureSparseMatmult(Sparse_size_M, Sparse_size_nz, min_time, R);
		if (this.isSkipAction()) return;
		
		this.setBenchmarkProgress(5);
		res[5] = kernel.measureLU(LU_size, min_time, R);
		if (this.isSkipAction()) return;

		this.setBenchmarkProgress(6);
		res[0] = (res[1] + res[2] + res[3] + res[4] + res[5]) / 5;
		
		System.out.println("=> Average Benchmark Result: " + Math.round(res[0]) + " Mflops");
//		System.out.println("FFT:           " + Math.round(res[1]) + " Mflops");
//		System.out.println("SOR:           " + Math.round(res[2]) + " Mflops");
//		System.out.println("MonteCarlo:    " + Math.round(res[3]) + " Mflops");
//		System.out.println("SparseMatmult: " + Math.round(res[4]) + " Mflops");
//		System.out.println("LU:            " + Math.round(res[5]) + " Mflops");
//		System.out.println("A V E R A G E: " + Math.round(res[0]) + " Mflops");

		float result  = (float)Math.round((float)res[0]*100)/100;
		if (this.isSkipAction()) return;

		// --- Store result in LoadMeasurThread -----------
		LoadMeasureThread.setCompositeBenchmarkValue(result);
		Application.getGlobalInfo().setBenchValue(result);
		Application.getGlobalInfo().setBenchExecOn(this.getLocalSystemIdentifier());
		Application.getGlobalInfo().setBenchAllwaysSkip(benchAllwaysSkip);
		Application.getGlobalInfo().getFileProperties().save();
		
		// --- Progress Display --- OFF -------------------
		if (this.isHeadlessOperation==false) {
			benchGUI.setBenchmarkValue(result);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException sleepE) {
				sleepE.printStackTrace();
			}
			this.closeGUI();
		}
		Application.setBenchmarkRunning(false);
		
		// --- Start search for Agents, Ontologies and BaseServices -----------
		if (this.isHeadlessOperation==false) {
			Application.getClassSearcher();			
		}
		
	}
	
	/**
	 * Sets the benchmark progress.
	 * @param n the new benchmark progress
	 */
	private void setBenchmarkProgress(final int n) {
		if (this.benchGUI!=null) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					benchGUI.jProgressBarBenchmark.setValue(n);
				}
			});	
		}
	}
	
	/**
	 * This Method checks if a skip-button was pressed on the Monitor-GUI.
	 *
	 * @return true, if is skip action
	 */
	private boolean isSkipAction() {
		if (this.benchGUI!=null) {
			if (this.benchGUI.actionSkipAllways==true) {
				Application.getGlobalInfo().setBenchAllwaysSkip(true);
				this.closeGUI();
				Application.setBenchmarkRunning(false);
				return true;
			}
			if (this.benchGUI.actionSkip==true) {
				this.closeGUI();
				Application.setBenchmarkRunning(false);
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * This method closes the benchmark monitor GUI.
	 */
	private void closeGUI() {
		if (this.benchGUI!=null) {
			this.benchGUI.setVisible(false);
			this.benchGUI.dispose();
			this.benchGUI = null;	
		}
	}
	
	/**
	 * Returns the MAC-Address local 'CanonicalHostName' to identify
	 * on which computer this measurement were executed.
	 * 
	 * @return an identifier for the local system
	 */
	private String getLocalSystemIdentifier() {

		if (nowExecOn==null) {
			// ----------------------------------------------------------------
			// --- Try to get the MAC address first ---------------------------
			// ----------------------------------------------------------------
			try {
				Vector<String> macAddresses = new Vector<String>();
				Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
				while (interfaces.hasMoreElements()) {
					NetworkInterface netInterface = interfaces.nextElement();
					byte[] mac = netInterface.getHardwareAddress();
					if(mac != null) {
				        StringBuilder sb = new StringBuilder();
				        for (int i = 0; i < mac.length; i++) {
				          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				        }
				        // --- Add to the list of MAC addresses, if not empty ----- 
				        String macAddress = sb.toString();
				        if (macAddress!=null && macAddress.equals("")==false) {
				        	if (macAddresses.contains(macAddress)==false) {
				        		macAddresses.add(macAddress);
				        	}
				        }
					}
				}
				// --- Found one or more MAC-Addresses ----------------------------
				if (macAddresses.size()>0) {
					Collections.sort(macAddresses);
					nowExecOn = macAddresses.get(macAddresses.size()-1);
				}
				
			} catch (SocketException se) {
				//se.printStackTrace();
			}
			
			// --- In case that no MAC address was found --------------------------
			if (nowExecOn==null) {
				try {
					nowExecOn = InetAddress.getLocalHost().getCanonicalHostName();
				} catch (UnknownHostException inetE) {
					inetE.printStackTrace();
				}
			}
			
		}
		return nowExecOn;
	}
	
}
