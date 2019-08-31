package com.mochijump;

public class HairClipNoCollide extends NoCollideCharacter {
	
	private JumpInterface jump = new StandardJump();
	private CollisionInterface collide = new HairClipNoCollideCollision();

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