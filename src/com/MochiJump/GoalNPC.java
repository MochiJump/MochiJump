package com.MochiJump;

public class GoalNPC extends NonPlayerCharacter {
	
	public GoalNPC (DogLogic dl) {
		super(dl);
	}
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new GoalCollision();
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	// implement this:
	public void aIInputs() {
		if (jumpChu == false && this.sH <18*dogLogic.resizeValue) {
			jumpChu = true;
			jTime = 1;
			mJumpHandler();
			
		}
	}

}
