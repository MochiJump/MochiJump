package com.mochijump.characters;

import com.mochijump.framesandpanels.DogLogic;

public abstract class PlayerCharacter extends GameCharacter {
	public PlayerCharacter (DogLogic dl){
		super(dl);
	}

	public abstract void keyInputs();
}
