package com.mochijump;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GooseAnimation implements AnimationInterface {
	
	int aniTime = 1;
	int sW = 21;
	int sH = 14;
	int x;
	int y;
	GameCharacter npc;
	int aniTimer=0;


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
	
	Image gooseStandRight =  new ImageIcon(this.getClass().getResource("/GooseStandRight.png")).getImage();
	Image gooseWalkRight =  new ImageIcon(this.getClass().getResource("/GooseWalkRight.png")).getImage();
	Image gooseStandLeft =  new ImageIcon(this.getClass().getResource("/GooseStandLeft.png")).getImage();
	Image gooseWalkLeft =  new ImageIcon(this.getClass().getResource("/GooseWalkLeft.png")).getImage();
	Image gooseFlyRight1 =  new ImageIcon(this.getClass().getResource("/GooseFlyRight1.png")).getImage();
	Image gooseFlyRight2 =  new ImageIcon(this.getClass().getResource("/GooseFlyRight2.png")).getImage();
	Image gooseFlyLeft1 =  new ImageIcon(this.getClass().getResource("/GooseFlyLeft1.png")).getImage();
	Image gooseFlyLeft2 =  new ImageIcon(this.getClass().getResource("/GooseFlyLeft2.png")).getImage();
	int counter = 0;
	
	
	Image currentSprite;
	
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
	}


	
	public void setCurrentSprite() {
		if(mRestR) {
		currentSprite = gooseStandRight;
		}
		if(mRunR) {
			if (aniTimer <3) {
				currentSprite = gooseWalkRight;
				aniTimer++;
			} else if (aniTimer <5) {
				currentSprite = gooseStandRight;
				aniTimer++;
			} else {
				currentSprite = gooseStandRight;
				aniTimer=0;
			}
				
		}
		if (mRestL) {
			currentSprite = gooseStandLeft;
		}
		if (mRunL) {
			if (aniTimer <3) {
				currentSprite = gooseWalkLeft;
				aniTimer++;
			} else if (aniTimer <5) {
				currentSprite = gooseStandLeft;
				aniTimer++;
			} else {
				currentSprite = gooseStandLeft;
				aniTimer=0;
			}
				
		}
		if (mJumpR) {
			if (aniTimer<5) {
				currentSprite = gooseFlyRight1;
				aniTimer++;
			} else if (aniTimer<9) {
				currentSprite = gooseFlyRight2;
				aniTimer++;
			} else {
				currentSprite = gooseFlyRight2;
				aniTimer = 0;
			}
		} 
		if (mJumpL) {
			if (aniTimer<5) {
				currentSprite = gooseFlyLeft1;
				aniTimer++;
			} else if (aniTimer<9) {
				currentSprite = gooseFlyLeft2;
				aniTimer++;
			} else {
				currentSprite = gooseFlyLeft2;
				aniTimer = 0;
			}
		}
		
	}
	
	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setClip(x, y, sW, sH);
		g2.drawImage(currentSprite, x, y, sW, sH, null);
	}

}
