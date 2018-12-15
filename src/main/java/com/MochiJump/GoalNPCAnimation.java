package com.mochijump;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GoalNPCAnimation implements AnimationInterface {
	Image goalAni =  new ImageIcon(this.getClass().getResource("/ball.png")).getImage();
	Image currentSprite;
	GameCharacter npc;
	
	
		
	int aniTime = 1;
	int sW = 21;
	int sH = 14;
	int x;
	int y;



	int speedY;
	int speedX;
	boolean mRestR;
	boolean mRestL;
	boolean mRunR;
	boolean mRunL;
	boolean mJumpR;
	boolean mJumpL;
	boolean uJump;
	boolean midJump;
	
	
// right now I want to change the height of the sprite for the purposes of animating the bouncing ball (below sH).
// I think the easiest way to to do this is to have a class variable for GameCharacter, set it below, and then change the sH
// directly in the animation methods below (e.g. goalNPC.sH = changed)
	public void aniVarUpdate (GameCharacter mochi) {
		npc = mochi;
		x = (int) mochi.getX();
		y = (int) mochi.getY();
		sH = (int)mochi.getsH();
		sW = (int)mochi.getsW();
		speedY = (int) mochi.getSpeedY();
		speedX = (int) mochi.getSpeedX();
		mRestR = mochi.mRestR;
		mRestL = mochi.mRestL;
		mRunR = mochi.mRunR;
		mRunL = mochi.mRunL;
		mJumpR = mochi.mJumpR;
		mJumpL = mochi.mJumpL;
		uJump = mochi.uJump;
		midJump = mochi.midJump;
	}
	

	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setClip(x, y, sW, sH);
		g2.drawImage(currentSprite, x, y, sW, sH, null);
	}
	
	public void leftAni() {
		if (aniTime <= 5) {
			currentSprite = goalAni;
			aniTime ++;
		}else if (aniTime <= 10) {
			currentSprite = goalAni;
			aniTime++;
		}else if (aniTime <= 15){
			currentSprite = goalAni;
			aniTime++;
		}else {
			aniTime = 1;
		}
	}
	
	public void rightAni() {
		if (aniTime <= 5) {
			currentSprite = goalAni;
			aniTime ++;
		}else if (aniTime <= 10) {
			currentSprite = goalAni;
			aniTime++;
		}else if (aniTime <= 15){
			currentSprite = goalAni;
			aniTime++;
		}else {
			aniTime = 1;
		}
	}
// implement this:
	public void setCurrentSprite (){
		if (mRestR == true) {
			rightAni();
			if (npc.sH > 14*npc.dogLogic.resizeValue){
				npc.sH = npc.sH-(2*npc.dogLogic.resizeValue);
			}
		}
		if (mRestL == true) {
			leftAni();
			if (npc.sH > 14*npc.dogLogic.resizeValue){
				npc.sH = npc.sH-(2*npc.dogLogic.resizeValue);
			}
		}
		if (mRunR == true) {
			rightAni();
			if (npc.sH > (14*npc.dogLogic.resizeValue)){
				npc.sH = npc.sH-(2*npc.dogLogic.resizeValue);
			}
		}
		if (mRunL== true) {
			leftAni();
			if (npc.sH > (14*npc.dogLogic.resizeValue)){
				npc.sH = npc.sH-(2*npc.dogLogic.resizeValue);
			}
		}
		if (mJumpR == true) {
			npc.sH = (21*npc.dogLogic.resizeValue);
			rightAni();
		}
		if (mJumpL == true) {
			npc.sH = (21*npc.dogLogic.resizeValue);
			leftAni();
				
			}
		
	}	
	
}

