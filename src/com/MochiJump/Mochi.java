package com.MochiJump;

import java.awt.Rectangle;
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
	
 	Rectangle mochi = new Rectangle((int)(x), (int)(y), (int)(sH), (int)(sW));
// so the above mochi is a rectangle, now I'm going to create lines around the rectangle which will act as the boundary
// interface
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
	public boolean getmRunR () {
		return this.mRunR;
	}
	public boolean getJumpChu() {
		return this.jumpChu;
	}
	public int getJTime() {
		return this.jTime;
	}
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
			Rectangle p1 = next.getBounds();
			if (mochi.intersects(p1)) {
				if (mright.intersects(p1)) {
					x = p1.x-sW;
				} 
				if (mleft.intersects(p1)) {
					x = p1.x +p1.width +1;
				}
				if (mtop.intersects(p1)) {
					y = p1.y +p1.height;
				}
				if (mbottom.intersects(p1)) {
					y = p1.y-sH;
					jumpChu = false;
					if (jumpChu == false) {
						landing();
					}
				}
							
			}
		}
		
	}



	public JLabel keyInputs () {
		
		JLabel MochiL = new JLabel("Mochi Jump");
		
		MoveRightAct MoveRightAct = new MoveRightAct();
		RestRight RestRight = new RestRight();
		MoveLeftAct MoveLeftAct = new MoveLeftAct();
		RestLeft RestLeft = new RestLeft();
		JumpAct JumpAct = new JumpAct();

		InputMap im = MochiL.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = MochiL.getActionMap();	
		im.put(KeyStroke.getKeyStroke("RIGHT"), "MoveRightAct");
		am.put("MoveRightAct", MoveRightAct);
		im.put(KeyStroke.getKeyStroke("released RIGHT"), "RestRight");
		am.put("RestRight", RestRight);
		im.put(KeyStroke.getKeyStroke("LEFT"), "MoveLeftAct");
		am.put("MoveLeftAct", MoveLeftAct);
		im.put(KeyStroke.getKeyStroke("released LEFT"), "RestLeft");
		am.put("RestLeft", RestLeft);
		im.put(KeyStroke.getKeyStroke("UP"), "JumpAct" );
		am.put("JumpAct", JumpAct);
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
