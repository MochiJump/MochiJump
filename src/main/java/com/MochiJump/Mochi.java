package com.MochiJump;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;




public class Mochi extends PlayerCharacter{
	
	boolean moveKeyDown = false;
	
	
	public Mochi (DogLogic dl){
		super(dl);
	}
	
	@Override
	public void setActionToFalse () {
		mRestR = false;
		mRestL = false;
		mRunR = false;
		mRunL = false;
		mJumpR = false;
		mJumpL = false;
	}
	
	@Override
	public void landing (){
		if (moveKeyDown == true) {
			if (mJumpR == true) {
				setActionToFalse();
				mRunR=true;
				moveKeyDown = true;
				System.out.println("should be running");
			}
			else if (mJumpL == true) {
				setActionToFalse();
				mRunL=true;
				moveKeyDown = true;
			}
		}else {
			if (mJumpR == true) {
				setActionToFalse();
				mRestR=true;
				System.out.println("isn't running");
			}
			else if (mJumpL == true) {
				setActionToFalse();
				mRestL=true;
			}
		}
	}

	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new MochiCollision();


	
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}


	public void keyInputs() {
		
		MoveRightAct MoveRightAct = new MoveRightAct();
		RestRight RestRight = new RestRight();
		MoveLeftAct MoveLeftAct = new MoveLeftAct();
		RestLeft RestLeft = new RestLeft();
		JumpAct JumpAct = new JumpAct();
		Escape Escape = new Escape();

		InputMap im = dogLogic.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = dogLogic.getActionMap();	

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
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "Escape");
		am.put("Escape", Escape);
		
	}
	
	

	class MoveRightAct extends AbstractAction{
		public void actionPerformed (ActionEvent mr) {
			if (jumpChu == false) {
				setActionToFalse();
				mRunR = true;
				moveKeyDown = true;
				System.out.println("moveKeyDown set true");
			}
			if (jumpChu == true) {
				setActionToFalse();
				mJumpR = true;
				moveKeyDown = true;
			}
			x +=2 *dogLogic.resizeValue;
			if (speedX >=-1) {
				speedX += 1 *dogLogic.resizeValue;
			}
			if (speedX >= 1.5*dogLogic.resizeValue) {
				speedX = (float)1.5*dogLogic.resizeValue;
			}
		}
	}
	class MoveLeftAct extends AbstractAction{
		public void actionPerformed (ActionEvent ml) {
			mRunL = true; 
			if (jumpChu == false) {
				setActionToFalse();
				mRunL = true;
				moveKeyDown = true;
			}
			if (jumpChu == true) {
				setActionToFalse();
				mJumpL = true;
				moveKeyDown = true;
			} 
			x -= 2*dogLogic.resizeValue;
			if (speedX <=1) {
				speedX -= 1*dogLogic.resizeValue;
			}
			if (speedX <= -1.5*dogLogic.resizeValue) {
				speedX = (float)-1.5*dogLogic.resizeValue;
			}

			
		}
		
	}
	class JumpAct extends AbstractAction{
		public void actionPerformed (ActionEvent jr) {
			if (jumpChu == false) {
				jumpChu = true;
				jump();
				
			}
		}
	}
		
	class RestRight extends AbstractAction{
		public void actionPerformed (ActionEvent rr) {
			if (speedX >0) {
				speedX -= (float)1.5*dogLogic.resizeValue;
				if (speedX <0) {
					speedX = 0;
				}
				if (speedX == 0 && jumpChu == false) {
					setActionToFalse();
					mRestR=true;
					moveKeyDown = false;
					
				}
				
			}
		}
	}
	class RestLeft extends AbstractAction{
		public void actionPerformed (ActionEvent rr) {
			if (speedX <0) {
				speedX += (float)1.5* dogLogic.resizeValue;
				if (speedX >0) {
					speedX = 0;
				}
				if (speedX == 0 && jumpChu==false) {
					setActionToFalse();
					mRestL=true;
					moveKeyDown = false;
					
				}
				
			}
		}
	}
	class Escape extends AbstractAction{
		public void actionPerformed (ActionEvent es) {
			dogLogic.switcher.leaveDogLogic();
			
		}
	}


	


}