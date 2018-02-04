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
	boolean uJump;
	boolean midJump;
// even as a class variable, JTime does not seem to update	
	int jTime = 0;
	
	// okay in order to move the keybindings I'll need to create setters for Mochi that can be called outside this class.
// looks like I never create a reference variable to LevelMap here, let's fix that:
	LevelMap levelMap = new LevelMap();
	
// This shouldn't be an array list it should be a method that sets all these to false
// this method will need to be public if I move the keybindings to doglogic class
	public void setActionToFalse () {
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
	public boolean getmRunR () {
		return this.mRunR;
	}
	public boolean getJumpChu() {
		return this.jumpChu;
	}
	public int getJTime() {
		return this.jTime;
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
	
	// weirdly changing from a speedY parameter to just changing the Y works just fine.
	// not sure if I like having the mochijs2 as part of the jump animation. but i got it in there.
	public void mJumpHandler () {	
		if (jumpChu == true && jTime > 0) {
			jTime++;
			getJTime();
			if (jTime <= 18) {
				y -= 6;
				uJump = true;
				if (mRunR == true || mRestR == true) {
					setActionToFalse();
					mJumpR = true;	
				}
				if (mRunL == true || mRestL == true) {
					setActionToFalse();
					mJumpL = true;
				}
			}
			if (jTime>18 && jTime<= 20) {
				y -= 6;
				uJump = false;
				midJump = true;
				if (mRunR == true || mRestR == true) {
					setActionToFalse();
					mJumpR = true;	
				}
				if (mRunL == true || mRestL == true) {
					setActionToFalse();
					mJumpL = true;
				}
			}
			if (jTime < 25) {
				midJump = true;
			}
			if (jTime > 25) {	
				jTime =0;
			midJump = false;
			}
		}
	}
	
	// Collision detection happens here
	public void boundaryRules () {
		// let's apply inertia here:
		getSpeedY();
		y = speedY+y;
		x = x+speedX;
		mBoundaries();
		mJumpHandler();
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
				if (mleft.intersects(p1)) {
					x = p1.x +p1.width +1;
				}
				if (mtop.intersects(p1)) {
					y = p1.y +p1.height;
				}
				if (mbottom.intersects(p1)) {
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

// from playing around with an example of KeyBindings I can tell that it is possible setup and call keybindings from one class into
// another. 

	public JLabel keyInputs () {
		// okay it looks like I've been forgetting to create reference variables for the move classes!
		JLabel MochiL = new JLabel("Test");
		
		MoveRightAct MoveRightAct = new MoveRightAct();
		RestRight RestRight = new RestRight();
		MoveLeftAct MoveLeftAct = new MoveLeftAct();
		RestLeft RestLeft = new RestLeft();
		JumpAct JumpAct = new JumpAct();

		InputMap im = MochiL.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = MochiL.getActionMap();	
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
		// I think I'll need this so that I can just call this method in the DogLogic Class
		return MochiL;
		
	}
	
	

	class MoveRightAct extends AbstractAction{
		public void actionPerformed (ActionEvent mr) {
			// I'm trying to turn off all boolean action indicators here
			// should I include a check to see if jump is false before continuing?
			if (jumpChu == false) {
				setActionToFalse();
				mRunR = true;
			}
			if (jumpChu == true) {
				setActionToFalse();
				mJumpR = true;
			}
			x +=2;
			//this speedX variable will likely need adjusting
			if (speedX >=-1) {
				speedX++;
			}
			if (speedX >= 1) {
				speedX = 1;
			}
		//Here I turn on the one that is true
		// shouldn't this be bMochiAction.set(2, true); ****
		}
		// I could add the jumpR true here if that is the case...
	}
	class MoveLeftAct extends AbstractAction{
		public void actionPerformed (ActionEvent ml) {
			if (jumpChu == false) {
				setActionToFalse();
				mRunL = true;
			}
			if (jumpChu == true) {
				setActionToFalse();
				mJumpL = true;
			} 
			x -= 2;
			if (speedX <=1) {
				speedX --;
			}
			if (speedX <= -1) {
				speedX = -1;
			}

			
		}
		
	}
	// the jump is a little more involved here, used to have two consolidated them into one:
	// remember Y axis is inverted!!!!
	class JumpAct extends AbstractAction{
		public void actionPerformed (ActionEvent jr) {
			getJumpChu();
			// okay I've deleted most of the instructions below I'm going to move a lot of the logic
			// into it's own independent method so it can finish running it's cycle outside of the action map
			if (jumpChu == false) {
				jumpChu = true;
				jTime = 1;
				mJumpHandler();
				
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
				if (speedX == 0 && jumpChu == false) {
					setActionToFalse();
					mRestR=true;
					
				}
				
			}
		}
	}
	class RestLeft extends AbstractAction{
		public void actionPerformed (ActionEvent rr) {
			if (speedX <0) {
				// getting closer to having normal controls here
				speedX ++;
				if (speedX >0) {
					speedX = 0;
				}
				if (speedX == 0 && jumpChu==false) {
					setActionToFalse();
					mRestL=true;
					
				}
				
			}
		}
	}

	


}
