import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


// Platform creation, animation, collision, and Keybindings all are operational. 
// I've fixed the jump and inertia issue, but changing the Y value directly instead of speedY and it works fine.
// Jump animation currently includes mochijsp2, not sure if I liked not having it there better or not.
// I finished what I setout to do when I first started the project at this state.

// The decision now is to start something new, or impliment features and polish this into a real playable game.
// for now I'll try to create a level editor. Seems like a reasonable challenge.

// MochiJump V 0.1 02/04/2018

public class MochiJump {

	
	
	 public static void main(String[] args) {

		 	// okay so this testPain was used to just setup the JFrame. I've added a label at the top which will
		 	// give me an indication as to where mochi is in the game even if he isn't animated. Next step is to get him 
		 	// into this JFrame.
		 		TestClass testPain = new TestClass();
		 // so far nothing in any of these test invokes runnable except for this:
		 		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			         public void run() {
			        
				 JFrame frame = new JFrame ("Mochi Jump");
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 // I need to use a drawPanel component here in place of testPain in the future
				 frame.getContentPane().add(testPain, BorderLayout.CENTER);
				 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	

					 // try adding the below into TestClass
					 // panel.setOpaque(true);
					 // frame.setBackground(new Color (127, 127, 127));
		
				// for some reason starting this program in anything but maximized screen will cause
				 // the rectangles imported to be drawn strangely. I will look into why, but in the
				 // mean time the above Extended State will cause the app to launch in full screen.
				 frame.pack();
				 frame.setVisible(true);
				 frame.repaint();
				//the above repaint shouldn't be here. In fact, it should be in the JPanel portion, inside of a thread
				// and the thread should have a sleep command with (1000 / RefreshRate). Then the animation will run.
			 
			         }
	
		 		});
	 }
}
