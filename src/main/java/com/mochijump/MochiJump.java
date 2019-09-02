package com.mochijump;


import com.mochijump.framesandpanels.PanelSwitcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MochiJump {
	private static final Logger LOG = LogManager.getLogger(MochiJump.class);

	public static void main(String[] args) {
		if (LOG.isDebugEnabled()){
			LOG.debug("Application starting...");
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
			  new PanelSwitcher();
	         }
 		});
	}
}
