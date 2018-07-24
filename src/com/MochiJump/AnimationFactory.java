package com.MochiJump;

public class AnimationFactory {
	
	public AnimationInterface makeAnimation (GameCharacter type) {
		if (type instanceof Mochi) {
			return new MochiAnimation();
		} else {
			// new animation types go here:
			return new MochiAnimation();
		}
		
	}

}
