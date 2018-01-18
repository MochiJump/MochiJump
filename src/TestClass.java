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
	    canvas = new DrawCanvas();
		
//		animation = new Animation();
// using the getX method causes this to break
//		float mochiX = mochi.getX();
		int x = 50;
		int y = 50;
		mochi = new Mochi(x,y,0,-5,21,14);
		JPanel testPain = new JPanel();
//		testPain.add(canvas, BorderLayout.CENTER);
		JLabel testLabel = new JLabel("Float.toString(mochiX)");
		testPain.add(testLabel, BorderLayout.NORTH);
		testPain.setPreferredSize(new Dimension (500,500));
		add (testPain);
		// okay none of this is showing up. I think I'll create a separate project just to play with this without distractions.
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
