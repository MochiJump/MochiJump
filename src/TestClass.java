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
	Draw draw;
	
	public TestClass () {
		draw = new Draw();
		
		JPanel testPain = new JPanel();
		JLabel testLabel = new JLabel(Integer.toString(plat.size()));
		testPain.add(testLabel, BorderLayout.NORTH);
		testPain.setPreferredSize(new Dimension (500,500));
		testPain.add(draw);
		add (testPain);
	}
	class Draw extends JPanel{
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			plat = levelMap.getPlat();
			Graphics2D g2 = (Graphics2D) g.create();
			for (Rectangle next: plat) {
				// added fillRect and set color 
				g2.setColor(Color.MAGENTA);
				g2.fill(next);
				g2.draw(next);
			
		//	levelMap.draw(g);
		//	animation.draw(g);
				}
		
			}
	
	}
}
