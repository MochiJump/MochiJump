package com.mochijump;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class HairClipNoCollideAnimation implements AnimationInterface{
	DogLogic dogLogic;
	
	//need to finish making images for this class.
	Image ms = new ImageIcon(this.getClass().getResource("/hClipL2F1.png")).getImage();
	Image hcl2 = new ImageIcon(this.getClass().getResource("/hClipL2F2.png")).getImage();
	Image hcl3 = new ImageIcon(this.getClass().getResource("/hClipL2F3.png")).getImage();	
	
	Image msr = new ImageIcon(this.getClass().getResource("/hClipR2F1.png")).getImage();
	Image hcr2 =  new ImageIcon(this.getClass().getResource("/hClipR2F2.png")).getImage();
	Image hcr3 = new ImageIcon(this.getClass().getResource("/hClipR2F3.png")).getImage();
	
	Image currentSprite;
	
	
		
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
	
	

	public void aniVarUpdate (GameCharacter mochi) {
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
			currentSprite = ms;
			aniTime ++;
		}else if (aniTime <= 10) {
			currentSprite = hcl2;
			aniTime++;
		}else if (aniTime <= 15){
			currentSprite = hcl3;
		}
	}
	
	public void rightAni() {
		if (aniTime <= 5) {
			currentSprite = msr;
			aniTime ++;
		}else if (aniTime <= 10) {
			currentSprite = hcr2;
			aniTime++;
		}else if (aniTime <= 15){
			currentSprite = hcr3;
		}
	}

	public void setCurrentSprite (){
		if (mRestR == true) {
			rightAni();
		}
		if (mRestL == true) {
			leftAni();
		}
		if (mRunR == true) {
			rightAni();
		}
		if (mRunL== true) {
			leftAni();

		}
		if (mJumpR == true) {
			rightAni();

		}
		if (mJumpL == true) {
			leftAni();

				
			}
		
	}	
	
}

