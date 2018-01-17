import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DogLogic extends JPanel {
// perhaps the key bindings should go here?

	private Mochi mochi;
	private LevelMap levelMap;
	private Animation animation;
	
	private DrawCanvas canvas;
	ArrayList<Rectangle> platlist = LevelMap.platlist;
	
	private static final int Update_Rate = 30;
	
	
	
	// removed parameters for constructor
	public DogLogic () {
		// Very important to import this, otherwise you get nothing:
	    canvas = new DrawCanvas();
	    this.setLayout(new BorderLayout());
	    this.add(canvas, BorderLayout.CENTER);
		levelMap = new LevelMap(platlist);
		animation = new Animation();
		
		int x = 50;
		int y = 50;
		mochi = new Mochi(x,y,0,-5,21,14);
		gameStart();
	}
	
	public void gameStart() {
		// this sets up the game logic
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
			//call on an update method
					gameUpdate();
			//repaint everything
					repaint();
					try {
						Thread.sleep(1000 / Update_Rate);
					}catch (InterruptedException ex) {}
				}
			}
		};
		gameThread.start();
	}
// All of the errors I get when I try to run this center on right here
	public void gameUpdate() {

		mochi.boundaryRules(levelMap);
	
	}
	// I need to actually put things together into a JPanel
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
