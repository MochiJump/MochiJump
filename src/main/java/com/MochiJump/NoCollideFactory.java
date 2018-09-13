package com.MochiJump;

public class NoCollideFactory {
	DogLogic dogLogic;
	
	public NoCollideFactory (DogLogic dl) {
		dogLogic = dl;
	}
	
	
	
	public NoCollideCharacter swapToNoCollide (GameCharacter original) {
		if (original instanceof HairClipNPC) {
			return new HairClipNoCollide (dogLogic);
		} else {
			//should be unreachable:
			return new HairClipNoCollide(dogLogic);
		}
		
		
	}

}
