package com.mochijump;

public abstract class PlayerCharacter extends GameCharacter {
	public PlayerCharacter (DogLogic dl){
		super(dl);
	}

	public abstract void keyInputs();
}
