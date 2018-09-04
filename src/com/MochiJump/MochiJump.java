package com.MochiJump;



public class MochiJump {
	
	public static void main(String[] args) {
		
 		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
			  new Switcher();
	         }

 		});
	}
}
