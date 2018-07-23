package com.MochiJump;

public abstract class NonPlayerCharacter extends GameCharacter {
	
	/*
	 * no class extends this NPC abstract class yet
	 */
	
	public NonPlayerCharacter (DogLogic dl) {
		super(dl);
	}
	
	public abstract void AIInputs();

}
