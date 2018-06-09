package com.MochiJump;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;




public class Mochi {
	

	private float x;
	private float y;
	double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/768;
	double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	double reSizer = .9;
	
	public void reSize() {
		x = (float) (x*reSizer*keepWidth);
		y = (float)(y*reSizer*keepHeight);
		sH = (float)(sH*reSizer*keepWidth);
		sW = (float)(sW*reSizer*keepHeight);
		// speed/gravity adjustment needs to be affected by size, otherwise making mochi larger he feels slower
	}


	private float speedX = 0;
	private float speedY = 3;
	private float sH = 14;
	private float sW = 21;
	boolean mRestR;
	boolean mRestL;
	boolean mRunR;
	boolean mRunL;
	boolean mJumpR = true;
	boolean mJumpL;
	boolean uJump;
	boolean midJump;
	int jTime = 0;
	
	LevelMap levelMap = new LevelMap();
	
	public void setActionToFalse () {
		mRestR = false;
		mRestL = false;
		mRunR = false;
		mRunL = false;
		mJumpR = false;
		mJumpL = false;
	}
//Chu means middle of in Japanese
	boolean jumpChu = true;		

// this is defined later in set boundaries so it is not necessary to have the arguments here.	
 	Rectangle mochi = new Rectangle((int)(x), (int)(y), (int)(sH), (int)(sW));
 	Line2D.Float mright = new Line2D.Float(x+sW, y, x+sW, y+sH);
	Line2D.Float mleft = new Line2D.Float(x, y, x, y+sH);
	Line2D.Float mtop = new Line2D.Float(x,y,x+sW,y);
	Line2D.Float mbottom = new Line2D.Float(x, y+sH, x+sW, y+sH);
	

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
	public float getsH() {
		return this.sH;
	}
	public float getsW() {
		return this.sW;
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
			// testing whether mochi does not intersect may work to turn on "falling animation" *******
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
		JLabel MochiL = new JLabel("Mochi Jump");
		
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
			if (jumpChu == false) {
				setActionToFalse();
				mRunR = true;
			}
			if (jumpChu == true) {
				setActionToFalse();
				mJumpR = true;
			}
			x +=2;
			if (speedX >=-1) {
				speedX++;
			}
			if (speedX >= 1) {
				speedX = 1;
			}
		}
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
	class JumpAct extends AbstractAction{
		public void actionPerformed (ActionEvent jr) {
			getJumpChu();
			if (jumpChu == false) {
				jumpChu = true;
				jTime = 1;
				mJumpHandler();
				
			}
		}
	}
		
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