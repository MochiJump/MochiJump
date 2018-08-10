package com.MochiJump;

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
	
	
	Image mws = new ImageIcon(this.getClass().getResource("/hClipL2F1.png")).getImage();
	Image mwsr = new ImageIcon (this.getClass().getResource("/hClipR2F3.png")).getImage();
	
	
	Image mjc1 = new ImageIcon (this.getClass().getResource("/hClipL2F1.png")).getImage();
	Image mjc2 = new ImageIcon (this.getClass().getResource("/hClipL2F2.png")).getImage();
	Image mjc3 = new ImageIcon (this.getClass().getResource("/hClipL2F3.png")).getImage();
	Image mjc1r = new ImageIcon (this.getClass().getResource("/hClipR2F1.png")).getImage();
	Image mjc2r = new ImageIcon (this.getClass().getResource("/hClipR2F2.png")).getImage();
	Image mjc3r = new ImageIcon (this.getClass().getResource("/hClipR2F3.png")).getImage();
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
	
	

	public void AniVarUpdate (GameCharacter mochi) {
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
			System.out.println("RestR");
		}
		if (mRestL == true) {
			leftAni();
			System.out.println("RestL");
		}
		if (mRunR == true) {
			rightAni();
			System.out.println("RunR");
		}
		if (mRunL== true) {
			leftAni();
			System.out.println("RunL");

		}
		if (mJumpR == true) {
			rightAni();
			System.out.println("JumpR");

		}
		if (mJumpL == true) {
			leftAni();
			System.out.println("JumpL");

				
			}
		
	}	
	
}

