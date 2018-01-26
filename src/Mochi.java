// Project started at 0830 AM 12/31/17 

// This class is going contain variables, boundary rules, and the Actions for the Mochi Sprite
// outside of this class we will need a main class, a map class, and an animation class

// read this: https://tips4java.wordpress.com/2013/06/09/motion-using-the-keyboard/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.*;
import java.util.*;
import java.util.Timer;


public class Mochi {
	
// we need the variables for mochi
	private float x;
	private float y;
// may want to include a cap to speeds here.
	private float speedX;
	private float speedY;
// might as well make a gravity value, i'll set it to -5, find that -3 is actually a better speed with a refresh rate of 30.
	private float gravity = -3;	
// since he isn't a ball, we will need the sprite's height and width as well (right now the sprite is 14 px tall and 21 px wide)
	private float sH;
	private float sW;
// I'm thinking I'll include boolan variables to indicate which animation to run!
	boolean mRestR;
	boolean mRestL;
	boolean mRunR;
	boolean mRunL;
	boolean mJumpR;
	boolean mJumpL;
// looks like I never create a reference variable to LevelMap here, let's fix that:
	LevelMap levelMap = new LevelMap();
	
// This shouldn't be an array list it should be a method that sets all these to false
	private void setActionToFalse () {
		mRestR = false;
		mRestL = false;
		mRunR = false;
		mRunL = false;
		mJumpR = false;
		mJump = false;
	}
	
//Should I use this to indicate whether a jump is taking place? Chu means middle of in Japanese
	boolean jumpChu = false;		
// looks like I'll need to add actions here
	private Action MoveRightAct;
	private Action MoveLeftAct;
// I've got to change the two Jump actions into one
	private Action JumpAct;
	private Action RestRight;
	private Action RestLeft;

// Well I'll either need two panels or I'll need to make the main panel available to call here... KeyBinding is a pain to setup.
	// I think the solution here is to either have the below set to component.(etc) or to have this in the class with JPanel
	// and then just call back to here.
	InputMap im = component.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	ActionMap am = component.getActionMap();
	
 	Rectangle mochi = new Rectangle ((int)(x), (int)(y), (int)(sH), (int)(sW));
// so the above mochi is a rectangle, now I'm going to create lines around the rectangle which will act as the boundary
// interface
	Line2D.Float mright = new Line2D.Float(x, y+sW, x+sH, y+sW);
	Line2D.Float mleft = new Line2D.Float(x, y, x+sH, y);
	Line2D.Float mtop = new Line2D.Float(x,y,x,y+sW);
	Line2D.Float mbottom = new Line2D.Float(x+sH, y, x+sH, y+sW);
// ***** Remember to assign a value to sW (spriteWidth) and sH (spriteHeight)
	

// all of the below can go. This is a constructor that I don't need for anything in this program.
	
	public Mochi (float x, float y, float speedX, float speedY, float sH, float sW) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.sH = sH;
		this.sW = sW;
	}
// same here. I just didn't understand how setters and getters worked.
	public Mochi (boolean mRestR, boolean mRestL, boolean mRunR, boolean mRunL, boolean mJumpR, boolean mJumpL) {
		this.mRestR=mRestR;
		this.mRestL=mRestL;
		this.mRunR=mRunR;
		this.mRestL=mRunL;
		this.mJumpR=mJumpR;
		this.mJumpL=mJumpL;
	}
// This is a good example of a getter, and it is neccessary for the animation class to work
	public float getSpeedY(){
			return this.speedY;
	}
	 // I don't think I need this any more
		public float getX() {
			return this.x;
		}
	// creating this empty Mochi constructor for the purpose of just getting everything if I need it
	public Mochi () {
		// and it totally works. I could probably just delete everything above in the other two keep them all together etc and it would work.
	}

// okay what do we still need in this class?
// we need boundry detection
	// okay, I've created objects in the levelMap class with values for boundary detection. If I can pull these values
	// in this class I can set rules here for boundaries.
	// this https://stackoverflow.com/questions/2126714/java-get-all-variable-names-in-a-class may help!
	// If I can get this to work I'll level up.

// Let's get rid of the arguments for this method. This should work better.
	public void boundaryRules () {
		// let's apply inertia here:
		y = y+speedY;
		x = x+speedX;
		// review the below code
		ArrayList<Rectangle> platlist = levelMap.getPlat();
		for (Rectangle next: platlist) {
			Rectangle p1 = next.getBounds();
			// in order to use intersection we need to create mochi as a rectangle at the top
			// 
			if (mochi.intersects(p1)) {
			// so here is where we program what we want a result of a collision...
			// what I want is for mochi to return to the state just prior to his collision.
			// how do I program that?
			// okay so I have to write a few more if statements here:
			// I need to determine if the collision happened from above or below,
			// or from left or from the right. 
				if (mright.intersects(p1)) {
					y = p1.y - sW;
				// I like getting to use basic algebra in real life.
				} 
				else if (mleft.intersects(p1)) {
					y = p1.y +p1.width;
				}
				else if (mtop.intersects(p1)) {
					x = p1.x+ p1.height;
				}
				else if (mbottom.intersects(p1)) {
					x = p1.x - sH;
					// going to always set JumpChu to false whenever this intersection happens
					jumpChu = false;
				}
				// and that should do it for the bounds! Mochi should not jump around at rest
				// or move through the platforms. easy day today done 01/04/2018
// *note* you can use this method for enemies, powerups, etc!			
			}
		}
		
	}
// okay here goes the keybinding:
// let's put them in a public method
	public void keyInputs () {
		// How would this work if I moved "im" to another class?
		im.put(KeyStroke.getKeyStroke("RIGHT"), "MoveRightAct");
		am.put("MoveRightAct", MoveRightAct);
		// For keybinding I'm going to have to add an event for when a key is released for < & >.
		im.put(KeyStroke.getKeyStroke("released RIGHT"), "RestRight");
		am.put("RestRight", RestRight);
		im.put(KeyStroke.getKeyStroke("LEFT"), "MoveLeftAct");
		am.put("MoveLeftAct", MoveLeftAct);
		im.put(KeyStroke.getKeyStroke("released LEFT"), "RestLeft");
		am.put("RestLeft", RestLeft);
		im.put(KeyStroke.getKeyStroke("UP"), "JumpAct" );
		am.put("JumpAct", JumpAct);
		
	}
	
	

	class MoveRightAct extends AbstractAction{
		public void actionPerformed (ActionEvent mr) {
			// I'm trying to turn off all boolean action indicators here
			// should I include a check to see if jump is false before continuing?
				setActionToFalse();
			x ++;
			//this speedX variable will likely need adjusting
			speedX ++;
		//Here I turn on the one that is true
		// shouldn't this be bMochiAction.set(2, true); ****
			mRunR = true;
		}
		// I could add the jumpR true here if that is the case...
	}
	class MoveLeftAct extends AbstractAction{
		public void actionPerformed (ActionEvent ml) {
			setActionToFalse();
			x --;
			speedX --;
			mRunL = true;
			
		}
		
	}
	// the jump is a little more involved here, used to have two consolidated them into one:
	// remember Y axis is inverted!!!!
	class JumpRightAct extends AbstractAction{
		public void actionPerformed (ActionEvent jr) {
			if (jumpChu = false) {
				// when working with boolean this seems to be the best way to do things.
				// do I want to use a timer here? or a counter?
				// can always go back to a counter if this is difficult
				long start_jump_time = System.currentTimeMillis();
				long up_time = 500;
				long up_time_end = start_jump_time + up_time;
					// the below should be start_jump_time instead of .currentTime (?)****
				while (System.currentTimeMillis() < up_time_end) {
					speedY = gravity;
					y--;
					if (mRunR == true || mRestR == true) {
						for (int counter = 0; counter < bMochiAction.size(); counter++) {
							bMochiAction.set(counter, false);
						}
						// bMochiAction.set(4, true);
						mJumpR = true;	
					}
					if (mRunL == true || mRestL == true) {
						for (int counter = 0; counter < bMochiAction.size(); counter++) {
							bMochiAction.set(counter, false);
						}
						// bMochiAction.set(5, true);
						mJumpL = true;
					}
				}
				// the below should be start_jump_time instead of .currentTime (?)****
				while (System.currentTimeMillis()> up_time_end) {
					speedY++;
					if (speedY > gravity) {
						speedY = gravity;
					}
					// I think that should work!
				}
				
			}
			if (jumpChu = true) {
			// do nothing
			}
		}
	}
//this should be called when < or > is released.
	class RestRight extends AbstractAction{
		public void actionPerformed (ActionEvent rr) {
			if (speedX >0) {
				speedX --;
				if (speedX <0) {
					speedX = 0;
				}
				if (speedX == 0) {
					for (int counter = 0; counter < bMochiAction.size(); counter++) {
						bMochiAction.set(counter, false);
					}
					mRestR=true;
					
				}
				
			}
		}
	}
	class RestLeft extends AbstractAction{
		public void actionPerformed (ActionEvent rr) {
			if (speedX >0) {
				speedX --;
				if (speedX <0) {
					speedX = 0;
				}
				if (speedX == 0) {
					for (int counter = 0; counter < bMochiAction.size(); counter++) {
						bMochiAction.set(counter, false);
					}
					mRestL=true;
					
				}
				
			}
		}
	}

	


}
