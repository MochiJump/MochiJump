package com.MochiJump;



public class MochiJump {
	
	public static void main(String[] args) {
		
 // start pause screen still requires some work so it is not added to the main getContentPane below
 		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
			  new Switcher();
	         }

 		});
	}
}
