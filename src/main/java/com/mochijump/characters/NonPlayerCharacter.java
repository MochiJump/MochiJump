package com.mochijump.characters;

import com.mochijump.framesandpanels.DogLogic;

public abstract class NonPlayerCharacter extends GameCharacter {
	
	/*
	 * no class extends this NPC abstract class yet
	 */
	
	public NonPlayerCharacter (DogLogic dl) {
		super(dl);
	}
	
	public abstract void aIInputs();

}
