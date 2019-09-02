package com.mochijump.characters;

import com.mochijump.actions.JumpInterface;
import com.mochijump.collision.CollisionInterface;
import com.mochijump.collision.HairClipNoCollideCollision;
import com.mochijump.actions.StandardJump;
import com.mochijump.framesandpanels.DogLogic;

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