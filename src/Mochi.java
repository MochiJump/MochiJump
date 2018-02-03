// Project started at 0830 AM 12/31/17 

// This class is going contain variables, boundary rules, and the Actions for the Mochi Sprite
// outside of this class we will need a main class, a map class, and an animation class

// read this: http://www.camick.com/java/source/MotionWithKeyBindings.java

// interesting. I could've just made Mochi a JComponent instead of doing all this with the rectangle. It looks like
// I can do xxx.getInputMap etc where xxx = rectangles, classes etc. I've been hung up on trying to make it a JPanel or JComponent

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
	private float speedX = 0;
	private float speedY = 3;
// might as well make a gravity value, i'll set it to -5, find that -3 is actually a better speed with a refresh rate of 30.
	private float gravity = 3;	
// since he isn't a ball, we will need the sprite's height and width as well (right now the sprite is 14 px tall and 21 px wide)
	private float sH = 14;
	private float sW = 21;
// I'm thinking I'll include boolan variables to indicate which animation to run!
	boolean mRestR;
	boolean mRestL;
	boolean mRunR;
	boolean mRunL;
	boolean mJumpR = true;
	boolean mJumpL;
	
	// okay in order to move the keybindings I'll need to create setters for Mochi that can be called outside this class.
	public void setMochiX(float x){
		this.x = x;
	}
	public void setMochiY (float y){
		this.y = y;
	}
	public void setMochiSpeedX(float speedX){
		this.speedX = speedX;
	}
	public void setMochiSpeedY(float speedY){
		this.speedY = speedY;
	} // that should do it for now
// looks like I never create a reference variable to LevelMap here, let's fix that:
	LevelMap levelMap = new LevelMap();
	
// This shouldn't be an array list it should be a method that sets all these to false
	private void setActionToFalse () {
		mRestR = false;
		mRestL = false;
		mRunR = false;
		mRunL = false;
		mJumpR = false;
		mJumpL = false;
	}

//Should I use this to indicate whether a jump is taking place? Chu means middle of in Japanese
// if he starts by falling, then maybe I should set this to true
	boolean jumpChu = true;		
// looks like I'll need to add actions here
	private Action MoveRightAct;
	private Action MoveLeftAct;
// I've got to change the two Jump actions into one
	private Action JumpAct;
	private Action RestRight;
	private Action RestLeft;
	
 	Rectangle mochi = new Rectangle((int)(x), (int)(y), (int)(sH), (int)(sW));
// 	JComponent mochiC;
// so the above mochi is a rectangle, now I'm going to create lines around the rectangle which will act as the boundary
// interface
 	
 	// or perhaps this needs to be in a method in order for it to be called more than once? It does work but I got a
 	// nullpoint error when I removed the value i.e. just had it Line mright;
	Line2D.Float mright = new Line2D.Float(x+sW, y, x+sW, y+sH);
	Line2D.Float mleft = new Line2D.Float(x, y, x, y+sH);
	Line2D.Float mtop = new Line2D.Float(x,y,x+sW,y);
	Line2D.Float mbottom = new Line2D.Float(x, y+sH, x+sW, y+sH);
// ***** Remember to assign a value to sW (spriteWidth) and sH (spriteHeight)

	

// Okay that is working, now I'll need to link the Rectangle to the Component

// This is a good example of a getter, and it is necessary for the animation class to work
	public float getSpeedY(){
			return this.speedY;
	}
	
	public float getSpeedX() {
		return this.speedX;
	}
	
	public float getX() {
			return this.x;
		}
	public float getY() {
			return this.y;
		}
	public void setSpeedY(float sY) {
		speedY = sY;
	}
// let see if putting this in a method and then calling that method inside boundaryRules updates it.
// okay that didn't fix it. wait maybe we need to add the rectangle mochi in here:
// I think I'm on the right track but I've got a nullPointerException when i try to run this now.
	public void mBoundaries () {
		// the y axis here needs to be trimmed for the right and left or the intersection will always call this first!
			mright.setLine(x+sW, y+5, x+sW, y+sH-5);
			mleft.setLine(x, y+5, x, y+sH-5);
			// trim the x axis here for the same effect
			mtop.setLine(x+5,y,x+sW-5,y);
			mbottom.setLine(x+5, y+sH, x+sW-5, y+sH);
			mochi.setRect((int)(x), (int)(y), (int)(sH), (int)(sW));
		}
	//let's try something like this
	public void landing (){
		if (mJumpR == true) {
			setActionToFalse();
			mRestR=true;
		}
		else if (mJumpL == true) {
			setActionToFalse();
			mRestL=true;
		}
	}
	
	// Collision detection happens here
	public void boundaryRules () {
		// let's apply inertia here:
		getSpeedY();
		y = speedY+y;
		x = x+speedX;
		mBoundaries();
		// review the below code
		ArrayList<Rectangle> platlist = levelMap.getPlat();
		for (Rectangle next: platlist) {
			// I tried remove.getBounds below no change.
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
					// because this is being called!
					x = p1.x-sW;
				// I like getting to use basic algebra in real life.
				} 
				else if (mleft.intersects(p1)) {
					x = p1.x +p1.width +1;
				}
				else if (mtop.intersects(p1)) {
					y = p1.y +p1.height+y+1;
				}
				else if (mbottom.intersects(p1)) {
					// I'm thinking this should be an independant method that is called after this if statement
					// heard that many nested if statements is bad coding practice.
					// hmm looks like changing these parameters does nothing
					y = p1.y-sH;
					// going to always set JumpChu to false whenever this intersection happens
					jumpChu = false;
					// while statement below didn't work trying if statement to stop jittering, the below isn't working either
					// perhaps I need an outside method that turns gravity off.
					if (jumpChu == false) {
						landing();
					}
				}
							
			}
		}
		
	}

// from here below is key binding, the idea of just adding a JLabel did not work.

		JLabel MochiL = new JLabel("Test");

		InputMap im = MochiL.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = MochiL.getActionMap();	

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
						setActionToFalse();
						mJumpR = true;	
					}
					if (mRunL == true || mRestL == true) {
						setActionToFalse();
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
					setActionToFalse();
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
					setActionToFalse();
					mRestL=true;
					
				}
				
			}
		}
	}

	


}
