package com.MochiJump;

public class YLimiter extends NonPlayerCharacter{

	
	// the only thing this class is here for is to mark the lowest point
	
	public YLimiter(DogLogic d) {
		super(d);
	}
	
	public void mJumpHandler(){
		// this class doesn't jump
	}
	
	public void boundaryRules() {
		// this needs to implement a special collision interface so anything that passes it's y axis get's destoryed 
	}
	
	public void aIInputs(){
		//Either this will contain nothing or we can determine lowest y point here, easiest way to do that is:
		y = dogLogic.plat.get(dogLogic.plat.size()-1).y + 100;
	}
}
