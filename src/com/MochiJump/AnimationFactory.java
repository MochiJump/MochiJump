package com.MochiJump;

public class AnimationFactory {
	
	public AnimationInterface makeAnimation (GameCharacter type) {
		if (type instanceof Mochi) {
			return new MochiAnimation();
		} else if (type instanceof HairClipNPC){
			return new HairClipAnimation();
		} else if (type instanceof HairClipNoCollide) {
			return new HairClipNoCollideAnimation();
		} else if (type instanceof GoalNPC) {
			return new GoalNPCAnimation();
		}else {
			//this should be unreachable
			return new MochiAnimation();
		}
		
	}

}
