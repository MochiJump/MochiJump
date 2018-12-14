package com.mochijump;

public class GoalNPC extends NonPlayerCharacter {
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new GoalCollision();
	
	public GoalNPC (DogLogic dl) {
		super(dl);
	}
	
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	// implement this:
	public void aIInputs() {
		if (jumpChu == false && this.sH <18*dogLogic.resizeValue) {
			jump();
			
		}
	}

}
