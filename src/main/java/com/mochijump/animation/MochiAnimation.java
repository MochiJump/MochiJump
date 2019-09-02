package com.mochijump.animation;

import com.mochijump.framesandpanels.DogLogic;
import com.mochijump.characters.GameCharacter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MochiAnimation implements AnimationInterface{
	DogLogic dogLogic;
	private Image ms = new ImageIcon(this.getClass().getResource("/mochirs.png")).getImage();
	private Image msr = new ImageIcon(this.getClass().getResource("/mochirsr.png")).getImage();
	private Image mws = new ImageIcon(this.getClass().getResource("/mochiws.png")).getImage();
	private Image mwsr = new ImageIcon (this.getClass().getResource("/mochiwsr.png")).getImage();
	private Image mjc1 = new ImageIcon (this.getClass().getResource("/mochijs1.png")).getImage();
	private Image mjc2 = new ImageIcon (this.getClass().getResource("/mochijs2.png")).getImage();
	private Image mjc3 = new ImageIcon (this.getClass().getResource("/mochijs3.png")).getImage();
	private Image mjc1r = new ImageIcon (this.getClass().getResource("/mochijs1r.png")).getImage();
	private Image mjc2r = new ImageIcon (this.getClass().getResource("/mochijs2r.png")).getImage();
	private Image mjc3r = new ImageIcon (this.getClass().getResource("/mochijs3r.png")).getImage();
	private Image currentSprite;
	
	
		
	private int aniTime = 1;
	private int sW = 21;
	private int sH = 14;
	private int x;
	private int y;



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