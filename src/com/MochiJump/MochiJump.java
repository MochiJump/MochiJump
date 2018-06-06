package com.MochiJump;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MochiJump {

	public static void main(String[] args) {

 		DogLogic dogLogic = new DogLogic();
 		
 		
 // start pause screen still requires some work so it is not added to the main getContentPane below
 		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        
		 JFrame frame = new JFrame ("Mochi Jump");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().add(dogLogic, BorderLayout.CENTER);
		 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	
		 frame.pack();
		 frame.setVisible(true);
		 frame.repaint();
			 
	         }

 		});
	}
}
