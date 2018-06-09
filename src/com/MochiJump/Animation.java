package com.MochiJump;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Animation {
	Image ms = new ImageIcon(this.getClass().getResource("/mochirs.png")).getImage();
	Image msr = new ImageIcon(this.getClass().getResource("/mochirsr.png")).getImage();
	Image mws = new ImageIcon(this.getClass().getResource("/mochiws.png")).getImage();
	Image mwsr = new ImageIcon (this.getClass().getResource("/mochiwsr.png")).getImage();
	Image mjc1 = new ImageIcon (this.getClass().getResource("/mochijs1.png")).getImage();
	Image mjc2 = new ImageIcon (this.getClass().getResource("/mochijs2.png")).getImage();
	Image mjc3 = new ImageIcon (this.getClass().getResource("/mochijs3.png")).getImage();
	Image mjc1r = new ImageIcon (this.getClass().getResource("/mochijs1r.png")).getImage();
	Image mjc2r = new ImageIcon (this.getClass().getResource("/mochijs2r.png")).getImage();
	Image mjc3r = new ImageIcon (this.getClass().getResource("/mochijs3r.png")).getImage();
	Image currentSprite;
		
	int aniTime = 1;
	int sW = 21;
	int sH = 14;
	Mochi mochi = new Mochi();
	int x = (int) mochi.getX();
	int y = (int) mochi.getY();



	int speedY = (int) mochi.getSpeedY();
	int speedX = (int) mochi.getSpeedX();
	boolean mRestR = mochi.mRestR;
	boolean mRestL = mochi.mRestL;
	boolean mRunR = mochi.mRunR;
	boolean mRunL = mochi.mRunL;
	boolean mJumpR = mochi.mJumpR;
	boolean mJumpL = mochi.mJumpL;
	boolean uJump = mochi.uJump;
	boolean midJump = mochi.midJump;
	
	
	
	public void inertia() {
		y = speedY+y;
		x = speedX+x;
	}
	
	// check to see if the method below needs the argument (Mochi mochi)... turns out you do. Research why!
	public void AniVarUpdate (Mochi mochi) {
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
	

	public void setCurrentSprite (){
		if (mRestR == true) {
			currentSprite = ms;
		}
		if (mRestL == true) {
			currentSprite = msr;
		}
		if (mRunR == true) {
			if (aniTime <= 5) {
				currentSprite = mws;
				aniTime ++;
			}else if (aniTime <= 10) {
				currentSprite = ms;
				aniTime++;
			}else  {
				aniTime = 1;
			}
		}
		if (mRunL== true) {
			if (aniTime <= 5) {  
				currentSprite = mwsr;
				aniTime ++;
			}
			else if (aniTime <= 10) {
				currentSprite = msr;
				aniTime ++;
			} 
			else{
				aniTime = 1;
			}
				
			}
			if (mJumpR == true) {
				 if (uJump == true) {
					 currentSprite = mjc1;
				 }
				 else if (midJump == true) {
					 currentSprite = mjc2;
				 }
				 else if (uJump==false && midJump == false){
					 currentSprite = mjc3;
				 }
				 else {
						System.out.println("something has gone wrong");
					}
			}
			if (mJumpL == true) {
				if (uJump == true ) {
					currentSprite = mjc1r;
				 }
				else if (midJump == true) {
					currentSprite = mjc2r;
				 }
				else if (uJump == false && midJump == false) {
					
					 currentSprite = mjc3r;
				 }
				else {
					System.out.println("something has gone wrong");
				}
				
			}
		
		}	
	
}