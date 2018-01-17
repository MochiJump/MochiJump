import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class TestClass extends JPanel {

	private Mochi mochi;
	private LevelMap levelMap;
	private Animation animation;
	
	private DrawCanvas canvas;
	
	public TestClass () {
//	    canvas = new DrawCanvas();
//	    this.setLayout(new BorderLayout());
//	    this.add(canvas, BorderLayout.CENTER);
		
//		animation = new Animation();
		
//		int x = 50;
//		int y = 50;
//		mochi = new Mochi(x,y,0,-5,21,14);
		
		
		
		JPanel testPain = new JPanel();
//		testPain.add(canvas, BorderLayout.CENTER);
		JLabel testLabel = new JLabel("Test");
		testPain.add(testLabel, BorderLayout.NORTH);
		testPain.setPreferredSize(new Dimension (500,500));
	}
	class DrawCanvas extends JPanel{
		// why do I need this override here?
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			levelMap.draw(g);
			animation.draw(g);
		}
		
	}
	
}
