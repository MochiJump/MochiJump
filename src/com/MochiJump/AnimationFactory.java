package com.MochiJump;

public class AnimationFactory {
	
	public AnimationInterface makeAnimation (GameCharacter type) {
		if (type instanceof Mochi) {
			return new MochiAnimation();
		} else if (type instanceof HairClipNPC){
			// need to create new Animation class for GenericNPC:
			return new HairClipAnimation();
		} else {
			//this should be unreachable
			return new MochiAnimation();
		}
		
	}

}
