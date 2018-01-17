import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


// I need a better understanding of how JFrame works. That is really holding me back right now.


public class MochiJump {

	
	
	 public static void main(String[] args) {

		 	//It seems as though the JPanel is not actually going into or being visible in the JFrame and I do not know why...
		 
		 		TestClass testPain = new TestClass();
				 JFrame frame = new JFrame ("Mochi Jump");
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 // I need to use a drawPanel component here in place of testPain in the future
				 frame.getContentPane().add(testPain, BorderLayout.CENTER);
				 // okay so to setup the size I want, I will just do that in the JFrame
				 frame.setPreferredSize(new Dimension(500,500));
				 frame.pack();
				 frame.setVisible(true);
			 
		}
	
}
