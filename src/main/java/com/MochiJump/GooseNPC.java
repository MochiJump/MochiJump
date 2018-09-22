package com.MochiJump;

public class GooseNPC extends NonPlayerCharacter {
	
	public GooseNPC (DogLogic dl) {
		super(dl);
	}
	
	//use standard jump for now
	JumpInterface jump =  new GooseJump();
	CollisionInterface collide = new HairClipCollision();
	
	public void mJumpHandler() {
		jump.jump(this);
	}
	
	public void boundaryRules() {
		collide.collide(this);
	}
	
	
	public void aIInputs() {
		checkX();
		checkY();
		
		
			
		
	}
	
	
	public void checkX() {
		if (dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x<x) {
			walkLeft();
		} else {
			walkRight();
		}
	}
	
	public void checkY() {
		if (dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).y < y) {
			moveUp();
		} else {
			moveDown();
		}
		
	}
	
	public void walkLeft() {
		speedY=3;
		setActionToFalse();
		mRunL = true;
		x = x-(1*dogLogic.resizeValue);
	}
	
	
	public void walkRight() {
		speedY=3;
		setActionToFalse();
		mRunR = true;
		x = x+(1*dogLogic.resizeValue);
	}
	
	public void moveUp() {
		jump();
		jumpChu= true;
		y = y -(int)(1.5*dogLogic.resizeValue);
	}
	
	public void moveDown() {
		y  = y+(int)(1.5*dogLogic.resizeValue);
	}
	
	
	

}
