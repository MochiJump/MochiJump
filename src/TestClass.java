import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.*;

	

public class TestClass extends JPanel {

	LevelMap levelMap = new LevelMap();
	ArrayList <Rectangle> plat = new ArrayList<Rectangle>();

	
	public TestClass () {
		plat = levelMap.getPlat();
		Rectangle r1 = plat.get(1);
		JPanel testPain = new JPanel();
		JLabel testLabel = new JLabel(Integer.toString(plat.size()));
		JLabel testLabel2= new JLabel (r1.toString());
		testPain.add(testLabel, BorderLayout.NORTH);
		testPain.add(testLabel2, BorderLayout.SOUTH);
		testPain.setPreferredSize(new Dimension (600,600));
		add (testPain);
	
	

	}

//Well it seems all of my problems stemmed from having the override in another class....
// It seems that the paint Component only works correctly in full screen, otherwise it draws strangely.
	
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			plat = levelMap.getPlat();

			Graphics2D g2 = (Graphics2D) g.create();
			
			for (Rectangle next: plat) {
				// added fillRect and set color 
				g2.setColor( Color.MAGENTA);
				g2.fill(next);
				g2.draw(next);
			
		//	levelMap.draw(g);
		//	animation.draw(g);
				}
		
			}
	
}
