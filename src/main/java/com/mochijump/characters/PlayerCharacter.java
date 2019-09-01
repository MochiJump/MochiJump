package com.mochijump.characters;

import com.mochijump.DogLogic;
import com.mochijump.characters.GameCharacter;

public abstract class PlayerCharacter extends GameCharacter {
	public PlayerCharacter (DogLogic dl){
		super(dl);
	}

	public abstract void keyInputs();
}
