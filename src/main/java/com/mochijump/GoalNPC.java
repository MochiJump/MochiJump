package com.mochijump;

public class GoalNPC extends NonPlayerCharacter {
	
	private JumpInterface jump = new StandardJump();
	private CollisionInterface collide = new GoalCollision();
	
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
