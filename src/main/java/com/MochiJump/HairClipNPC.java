package com.mochijump;



public class HairClipNPC extends NonPlayerCharacter {
	
	private JumpInterface jump = new StandardJump();
	private CollisionInterface collide = new HairClipCollision();

	public HairClipNPC (DogLogic dl) {
		super (dl);
	}
	
	
	public void mJumpHandler () {	
		jump.jump(this);
	}
	

	public void boundaryRules () {
		collide.collide(this);
	}
	
	// simple AI:
	public void aIInputs() {
		if (!jumpChu) {
			if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x>(int)x) {
				setActionToFalse();
				mRunR = true;
				x= x+(1*dogLogic.resizeValue);
			} else if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x==(int)x) {
			} else if ((int)dogLogic.gameCharacters.get(dogLogic.gameCharacters.size()-1).x<(int)x) {
				setActionToFalse();
				mRunL = true;
				x = x-(1*dogLogic.resizeValue);
			}
		}
		
	}

}
