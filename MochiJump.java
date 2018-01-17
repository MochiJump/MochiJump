import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


// I need a better understanding of how JFrame works. That is really holding me back right now.

public class MochiJump {

	 public static void main(String[] args) {

		 	// okay so this testPain was used to just setup the JFrame. I've added a label at the top which will
		 	// give me an indication as to where mochi is in the game even if he isn't animated. Next step is to get him 
		 	// into this JFrame.
		 DogLogic dogLogic = new DogLogic();
				 JFrame frame = new JFrame ("Mochi Jump");
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 // I need to use a drawPanel component here in place of testPain in the future
				 frame.getContentPane().add(new TestClass(), BorderLayout.CENTER);
				 // okay so to setup the size I want, I will just do that in the JFrame
				 frame.setPreferredSize(new Dimension(500,500));
				 frame.pack();
				 frame.setVisible(true);
			 
		}
	
}
