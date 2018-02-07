import java.awt.BorderLayout;
import java.awt.Color;
// import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.*;

// Keybinding does not have to be in this class, however, it does need to be called here just like every other
// working part of this program.

public class DogLogic extends JPanel {

	LevelMap levelMap = new LevelMap();
	ArrayList <Rectangle> plat = new ArrayList<Rectangle>();
	//Animation animtion = new Animation(); <-- changing this to TC2:
	Animation animation = new Animation();
	Mochi mochi = new Mochi();
	int refreshRate = 30;

	
	public DogLogic () {
		plat = levelMap.getPlat();
		JPanel testPain = new JPanel();
		JLabel testLabel = new JLabel(Integer.toString(plat.size()));
		JLabel testLabel2= new JLabel (Integer.toString((int)mochi.getX()));
		JLabel tl3 = new JLabel (Integer.toString((int) mochi.getY()));
		JLabel tl4 = new JLabel (Integer.toString((int) mochi.getSpeedY()));
		testPain.add(testLabel, BorderLayout.NORTH);
		testPain.add(testLabel2, BorderLayout.SOUTH);
		testPain.add(mochi.keyInputs());
		testPain.add(tl3);
		testPain.add(tl4);
		//testPain.setPreferredSize(new Dimension(600,600));
		add (testPain);
		testPain.add(mochi.keyInputs());
		
	// okay I need to create a new class here gameStart() and inside gameStart() is where
	// the refresh rate will lay as well as it ending with it invoking itself i.e. "gameStart();"
	// this will create the loop where everything will run, without it, I can only run one frame
		gameStart();

	}
	// The start/pause screen might require it's own thread that interupts this one. Changing the below to 
	// while (isStart == false && isPause == false) might work
	// new thread would run while (isStart == true :: isPause == false)

	public void gameStart() {
		// this needs to be a thread:
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					// I'll create a separate method to house everything here
					// now I just need to fill in the gameUpdate method
					gameUpdate();
					// I can call repaint here:
					repaint();
					try {
						Thread.sleep(1000/refreshRate);
					}catch (InterruptedException e) {
						System.out.println("An error in gameThread has occured");
					}
				}
			
			}
		}; // interesting syntax here to work properly.
		gameThread.start(); // it's not gameStart(), but rather gameThread.start();
	}
	
	public void gameUpdate () {
		// theoretically I should just have to add the setCurrentSprite method here and I should get animation:
		animation.setCurrentSprite();
		// okay i think it's time to try out boundary rules
		// mochi.inertia();
		mochi.boundaryRules();
		
		// Java is pass by value I need to update the variables in animation via their own method
		animation.AniVarUpdate(this.mochi);
	}
	
//Well it seems all of my problems stemmed from having the override in another class....
// It seems that the paint Component only works correctly in full screen, otherwise it draws strangely.

//Right now I'm thinking that adding a thread for Start/Pause would require placing a below for which would draw
// the game or the Start/Pause screen
	
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			plat = levelMap.getPlat();
			Graphics2D g2 = (Graphics2D) g.create();
			for (Rectangle next: plat) {
				// added fillRect and set color 
				g2.setColor( new Color (130, 87, 27));
				g2.fill(next);
				g2.draw(next);
				}
			animation.draw(g2);
			
			}
	
}
