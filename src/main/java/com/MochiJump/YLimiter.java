package com.MochiJump;

public class YLimiter extends NonPlayerCharacter{
	
	CollisionInterface collide = new YLimiterCollision();

	
	// the only thing this class is here for is to mark the lowest point
	
	public YLimiter(DogLogic d) {
		super(d);
		y=10000;
		System.out.println(y);
	}
	
	public void mJumpHandler(){
		// this class doesn't jump
	}
	
	public void boundaryRules() {
		collide.collide(this); 
	}
	
	public void aIInputs(){
			y = (float)(dogLogic.plat.get(0).y+dogLogic.plat.get(0).getHeight() + 100);
	}
}
