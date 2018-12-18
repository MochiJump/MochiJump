package com.mochijump;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Line2D;

public abstract class GameCharacter {
	private JumpInterface jump;
	private CollisionInterface collide;
	public DogLogic dogLogic;
	public LevelMap levelMap;
	private AnimationInterface animation;
	
	
	
	public int posInGameCharacter;
	public float x;
	public float y;
	public double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/768;
	public double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	public double resizer = 1;
	public float speedX = 0;
	public float speedY = 3;
	public float sH = 14;
	public float sW = 21;
	public boolean mRestR;
	public boolean mRestL;
	public boolean mRunR;
	public boolean mRunL;
	public boolean mJumpR = true;
	public boolean mJumpL;
	public boolean uJump;
	public boolean midJump;
	public int jTime = 0;
	
	public boolean jumpChu = true;			
	public Rectangle mochi = new Rectangle((int)(x), (int)(y), (int)(sW), (int)(sH));
	public Line2D.Float mright = new Line2D.Float(x+sW, y-5, x+sW, y+sH-5);
	public Line2D.Float mleft = new Line2D.Float(x, y-5, x, y+sH-5);
	public Line2D.Float mtop = new Line2D.Float(x,y,x+sW,y);
	public Line2D.Float mbottom = new Line2D.Float(x, y+sH, x+sW, y+sH);
	

	
	public GameCharacter (DogLogic dl) {
		dogLogic = dl;
		levelMap = dogLogic.levelMap;
	}
	
	public void reSize(double reSizer) {
		resizer = reSizer;
		x = (float) (x*reSizer*keepWidth);
		y = (float)(y*reSizer*keepHeight);
		sH = (float)(sH*reSizer*keepWidth);
		sW = (float)(sW*reSizer*keepHeight);
		speedY = (float) (speedY * reSizer);
	}
	
	public void runOffScreen(double reSizer) {
		x = (float) (x*reSizer);
		y = (float)(y*reSizer);
		sH = (float)(sH*reSizer);
		sW = (float)(sW*reSizer);
	}


	

	
	public void setActionToFalse () {
		mRestR = false;
		mRestL = false;
		mRunR = false;
		mRunL = false;
		mJumpR = false;
		mJumpL = false;
	}
	
	


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
	public void setY(float y) {
		this.y = y;
	}
	public float getsH() {
		return this.sH;
	}
	public float getsW() {
		return this.sW;
	}
	
	public boolean getmRunR() {
		return this.mRunR;
	}
	
	public void setRunR(boolean r) {
		mRunR = r;
	}
	
	
	public boolean getJumpChu() {
		return this.jumpChu;
	}
	public void setJumpChu(boolean jumpChu) {
		this.jumpChu = jumpChu;
	}
	
	public void setUJump(boolean uJump) {
		this.uJump = uJump;
	}
	
	public void setMidJump(boolean midJump) {
		this.midJump = midJump;
	}
	
	public void setJumpR(boolean mJumpR) {
		this.mJumpR = mJumpR;
	}
	
	public void setJumpL(boolean mJumpL) {
		this.mJumpL = mJumpL;
	}
	public void setJTime (int jTime) {
		this.jTime = jTime;
	}
	
	public void setRunL (boolean r) {
		mRunL = r;
	}
	
	public void setRestR (boolean r) {
		mRestR = r;
	}
	
	public void setRestL (boolean r) {
		mRestL = r;
	}
	
	public void mBoundaries () {
		
			mright.setLine(x+sW, y+5*resizer, x+sW, y+sH-7*resizer);
			mleft.setLine(x, y+5*resizer, x, y+sH-7*resizer);
			// trim the x axis here for the same effect
			mtop.setLine(x+5*resizer,y,x+sW-5*resizer,y);
			mbottom.setLine(x+5*resizer, y+sH, x+sW-5*resizer, y+sH);
			mochi.setRect((int)(x), (int)(y), (int)(sW), (int)(sH));
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
	
	public void jump() {
		jumpChu = true;
		jTime = 1;
		mJumpHandler();
	}
	
	public abstract void mJumpHandler();
	
	public abstract void boundaryRules();


}
