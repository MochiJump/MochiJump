package com.mochijump.characters;

import com.mochijump.DogLogic;
import com.mochijump.characters.GameCharacter;

public abstract class NonPlayerCharacter extends GameCharacter {
	
	/*
	 * no class extends this NPC abstract class yet
	 */
	
	public NonPlayerCharacter (DogLogic dl) {
		super(dl);
	}
	
	public abstract void aIInputs();

}
