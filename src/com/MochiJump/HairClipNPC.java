package com.MochiJump;


/*
 * this is a place holder class for now to test out NPCs
 * use AnimationFactory class to choose animation for the npc (currently using Mochi's as
 * a placeholder)
 */
public class HairClipNPC extends NonPlayerCharacter {
	
	public HairClipNPC (DogLogic dl) {
		super (dl);
	}
	
	JumpInterface jump = new StandardJump();
	CollisionInterface collide = new HairClipCollision();
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	
	// simple placeholder:
	public void aIInputs() {
		if (!jumpChu) {
			if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x>(int)x) {
				setActionToFalse();
				mRunR = true;
				x++;
			} else if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x==(int)x) {
			} else if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x<(int)x) {
				setActionToFalse();
				mRunL = true;
				x--;
			}
		}
		
	}

}
