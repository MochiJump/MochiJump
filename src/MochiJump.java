import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;


//The resting, walking, falling, and jumping animations now work. Now I need to figure out how to link keybinding to actionmap to
// a swing component without moving a bunch of code from Mochi class to (ultimately) the DogLogic class where the JPanel code
// is housed.

// for simplicties sake I'm going to try to get key bindings working in GuiTest first


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
