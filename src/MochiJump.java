import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


// Okay at least now I've got my window, and I can call data to the JLabel to test certain things. This is a start for debugging.


public class MochiJump {

	
	
	 public static void main(String[] args) {

		 	// okay so this testPain was used to just setup the JFrame. I've added a label at the top which will
		 	// give me an indication as to where mochi is in the game even if he isn't animated. Next step is to get him 
		 	// into this JFrame.
		 		TestClass testPain = new TestClass();
				 JFrame frame = new JFrame ("Mochi Jump");
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 // I need to use a drawPanel component here in place of testPain in the future
				 frame.getContentPane().add(testPain, BorderLayout.CENTER);
				 frame.pack();
				 frame.setVisible(true);
			 
		}
	
}
