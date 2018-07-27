package com.MochiJump;


/*
 * this is a place holder class for now to test out NPCs
 * use AnimationFactory class to choose animation for the npc (currently using Mochi's as
 * a placeholder)
 */
public class GenericNPC extends NonPlayerCharacter {
	
	public GenericNPC (DogLogic dl) {
		super (dl);
	}
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new StandardCollision();
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	
	public void aIInputs() {
		
	}

}
