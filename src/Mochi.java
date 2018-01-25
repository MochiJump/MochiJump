// Project started at 0830 AM 12/31/17 taking 1st break at 10300. Taking 2nd real break at 1237.
// This is making my head hurt... slow and steady wins the race I guess...
// This class is going contain both the sprite and the animation for Mochi when he moves (runs and jumps) and rests
// outside of this class we will need a main class, a map class
// best to use key binding api for this
// I'm going to call it quits today at 1651 (12/31). I've got to figure out a more efficient way of inputting and testing
// parameters for boundary detection. The solution is to use .getBounds (part of the geom API). Implement it and we can get
// this guy up and running.

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

import java.lang.*;

public class Mochi {
	
// we need the variables for mochi
	private float x;
	private float y;
// may want to include a cap to speeds here.
	private float speedX;
	private float speedY;
// might as well make a gravity value, i'll set it to -5
	private float gravity = -5;
// let's make inertia a thing:
	
// since he isn't a ball, we will need the sprite's height and width as well;
	private float sH;
	private float sW;
// I'm thinking I'll include boolan variables to indicate which animation to run!
	boolean mRestR;
	boolean mRestL;
	boolean mRunR;
	boolean mRunL;
	boolean mJumpR;
	boolean mJumpL;
// I should probably include setters and getters for each of these.
// looks like I never create a reference variable to LevelMap here, let's fix that:
	LevelMap levelMap = new LevelMap();
	

	// This arraylist is messy I'm going to create a method for setting up the arraylist.
	ArrayList<Boolean> bMochiAction = new ArrayList <>();
	private void setupMochiAction () {
		bMochiAction.add(mRestR);
		bMochiAction.add(mRestL);
		bMochiAction.add(mRunR);
		bMochiAction.add(mRunL);
		bMochiAction.add(mJumpR);
		bMochiAction.add(mJumpL);
	}
	// now I need to be sure that I call this at somepoint!
	
	// okay I've got an arraylist getter here, will that work?
	//public ArrayList <Boolean> getMochiState() {
	//	return bMochiAction;
	//}
	// not sure this will work. so I'm turning it into a comment for now
	// Yeah I didn't need that because the booleans aren't private.
	
//Should I use this to indicate whether a jump is taking place? Chu means middle of in Japanese
	boolean jumpChu = false;	
// I'll need a timer here for mochi's jump input.
	
// looks like I'll need to add actions here
	private Action MoveRightAct;
	private Action MoveLeftAct;
// I've got to change the two Jump actions into one, the above two boolean actions can stay, which one will be accessed must be written into the new single Action
// class
	private Action JumpAct;
	private Action RestRight;
	private Action RestLeft;

// Does this need to extend JPanel? I have everything running in a single Jpanel in another class.
//	JPanel panel = new JPanel();
	InputMap im = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
	ActionMap am = panel.getActionMap();
// I'll put the keybindings at the bottom
	
 	Rectangle mochi = new Rectangle ((int)(x), (int)(y), (int)(sH), (int)(sW));
// so the above mochi is a rectangle, now I'm going to create lines around the rectangle which will act as the boundary
// interface
	Line2D.Float mright = new Line2D.Float(x, y+sW, x+sH, y+sW);
	Line2D.Float mleft = new Line2D.Float(x, y, x+sH, y);
	Line2D.Float mtop = new Line2D.Float(x,y,x,y+sW);
	Line2D.Float mbottom = new Line2D.Float(x+sH, y, x+sH, y+sW);
// ***** Remember to assign a value to sW (spriteWidth) and sH (spriteHeight)
	

// so this is going to set variables for the mochi's positions and speed
	
	public Mochi (float x, float y, float speedX, float speedY, float sH, float sW) {
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.sH = sH;
		this.sW = sW;
	}
// this is going to set variables variables for mochi's state
	public Mochi (boolean mRestR, boolean mRestL, boolean mRunR, boolean mRunL, boolean mJumpR, boolean mJumpL) {
		this.mRestR=mRestR;
		this.mRestL=mRestL;
		this.mRunR=mRunR;
		this.mRestL=mRunL;
		this.mJumpR=mJumpR;
		this.mJumpL=mJumpL;
	}
// okay now we're going to add the getter's here and since 
		public float getSpeedY(){
			return this.speedY;
	}
	 // I'm going to add another one for testing purposes
		public float getX() {
			return this.x;
		}
	// creating this empty Mochi constructor for the purpose of just getting everything if I need it
	public Mochi () {
		// and it totally works. I could probably just delete everything above in the other two keep them all together etc and it would work.
	}
	
// attempt to add mochi sprite 
	// I'm going to create a new class for animation, I'll combine keybinding and calling animation just below
	// current code just below is placeholder
	// turning it into a comment for now. I think it's okay to delete it.
//	public void draw (Graphics g) {
		// rectangles start at the top left corner, and everything in java is inverted i.e. o is top left and any integer moves
		// beneath or to the right depending on the axis. 
//		g.setClip(new Rectangle2D.Double((int)(x), (int)(y), (int)(sH), (int)(sW)));
//		g.drawImage(ms, (int) (sH) , (int) (sW),null);
//	}

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
		// ***** BELLOW THIS IS WHAT IS CAUSING PROBLEMS!!!!!
		ArrayList<Rectangle> platlist = levelMap.getPlat();
		for (Rectangle next: platlist) {
			Rectangle p1 = next.getBounds();
			// in order to use intersection we need to create mochi as a rectangle at the top
			// I made a mistake, trying to create a new variable as part of the mochi constructor. Try not to repeat that.
			if (mochi.intersects(p1)) {
			// so here is where we program what we want a result of a collision...
			// what I want is for mochi to return to the state just prior to his collision.
			// how do I program that?
			// okay so I have to write a few more if statements here:
			// I need to determine if the collision happened from above or below,
			// or from left or from the right. I think I'll take a break here 1/3 1839;
			// picking back up time to write the rest of the bounds for mochi;
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
// let's put them in a callable method
	public void keyInputs () {
		// for the right key? I hope
		im.put(KeyStroke.getKeyStroke("RIGHT"), "MoveRightAct");
		am.put("MoveRightAct", MoveRightAct);
		// For keybinding I'm going to have to add an event for when a key is released for < & >.
		im.put(KeyStroke.getKeyStroke("released RIGHT"), "RestRight");
		am.put("RestRight", RestRight);
		im.put(KeyStroke.getKeyStroke("LEFT"), "MoveLeftAct");
		am.put("MoveLeftAct", MoveLeftAct);
		im.put(KeyStroke.getKeyStroke("released LEFT"), "RestLeft");
		am.put("RestLeft", RestLeft);
		//well I was going to use the keybinding to indicate whether mochi jumped right or left, however, as I'm looking at it there is no way to do this,
		//I'll have to write it into the action class above! *******************
		im.put(KeyStroke.getKeyStroke("UP"), "JumpAct" );
		am.put("JumpAct", JumpAct);
		//If I did all of this correctly then I just need to make a main class and put all of this in there. I will most likely need to come back here
		// and determine what to make runnable for threading purposes.
		
	}
	
	

	class MoveRightAct extends AbstractAction{
		public void actionPerformed (ActionEvent mr) {
			// I'm trying to turn off all boolean indicators here
			// should I include a check to see if jump is false before continuing?
			for (int counter = 0; counter < bMochiAction.size(); counter++) {
				// this is okay
				bMochiAction.set(counter, false);
			}
			x ++;
			//this speed thing could cause problems here...
			speedX ++;
		//Here I turn on the one that is true
		// shouldn't this be bMochiAction.set(2, true); ****
			mRunR = true;
		}
		// I could add the jumpR true here if that is the case...
	}
	class MoveLeftAct extends AbstractAction{
		public void actionPerformed (ActionEvent ml) {
			for (int counter = 0; counter < bMochiAction.size(); counter++) {
				bMochiAction.set(counter, false);
			}
			x --;
			speedX --;
			// bMochiAction.set(3, true); ****
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
