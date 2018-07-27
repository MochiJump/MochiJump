package com.MochiJump;

public class AnimationFactory {
	
	public AnimationInterface makeAnimation (GameCharacter type) {
		if (type instanceof Mochi) {
			return new MochiAnimation();
		} else if (type instanceof GenericNPC){
			// need to create new Animation class for GenericNPC:
			return new MochiAnimation();
		} else {
			//this should be unreachable
			return new MochiAnimation();
		}
		
	}

}
