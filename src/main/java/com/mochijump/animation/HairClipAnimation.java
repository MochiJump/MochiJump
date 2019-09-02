package com.mochijump.animation;

import com.mochijump.framesandpanels.DogLogic;
import com.mochijump.characters.GameCharacter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class HairClipAnimation implements AnimationInterface{
	DogLogic dogLogic;
	
	private Image ms = new ImageIcon(this.getClass().getResource("/hClipL1.png")).getImage();
	private Image hcl2 = new ImageIcon(this.getClass().getResource("/hClipL2.png")).getImage();
	private Image hcl3 = new ImageIcon(this.getClass().getResource("/hClipL3.png")).getImage();
	
	
	private Image msr = new ImageIcon(this.getClass().getResource("/hClipR1.png")).getImage();
	private Image hcr2 =  new ImageIcon(this.getClass().getResource("/hClipR2.png")).getImage();
	private Image hcr3 = new ImageIcon(this.getClass().getResource("/hClipR3.png")).getImage();
	
	private Image currentSprite;
	
	
		
	private int aniTime = 1;
	private int sW = 21;
	private int sH = 14;
	private int x;
	private int y;



	private int speedY;
	private int speedX;
	private boolean mRestR;
	private boolean mRestL;
	private boolean mRunR;
	private boolean mRunL;
	private boolean mJumpR;
	private boolean mJumpL;
	private boolean uJump;
	private boolean midJump;
	
	

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
			aniTime++;
		}else {
			aniTime = 1;
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
			aniTime++;
		}else {
			aniTime = 1;
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