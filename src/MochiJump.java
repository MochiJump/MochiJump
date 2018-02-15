import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


// Platform creation, animation, collision, and Keybindings all are operational. 
// I've fixed the jump and inertia issue, by changing the Y value directly instead of speedY and it works fine.
// Jump animation currently includes mochijsp2, not sure if I liked not having it there better or not.
// I finished what I set out to do when I first started the project at this state.

//Milestone Project Started V0.0 12/31/2017

//Milestone v0.1 02/04/2018

//Current Build MochiJump V 0.103 02/10/2018

// This current version may not be stable as I am working on implimenting a Start and Pause screen.***
// code in Mochi, Animation, LevelMap Classes will not be affected while implimenting start/pause screen

// once Start/Pause screen is complete learn seralization and implement save states and restarts



public class MochiJump {

	
	
	 public static void main(String[] args) {
		 
		 		DogLogic dogLogic = new DogLogic();
		 /**		StartPause sP = new StartPause();
		 * I think the switch to add a new thread and pain for pause start maybe should be in DogLogic only
		 * and not here in the main class
		 * this may require some testing. Big question is would this class update if it has a boolean switch
		 * to change panels
		 *		boolean isStart = sP.getIsStart();
		 *		boolean isPause = sP.getIsPause();
		 */
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
