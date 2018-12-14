package com.mochijump;

public class HairClipNoCollide extends NoCollideCharacter {
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new HairClipNoCollideCollision();

	public HairClipNoCollide (DogLogic dl) {
		super (dl);
	}
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	

}