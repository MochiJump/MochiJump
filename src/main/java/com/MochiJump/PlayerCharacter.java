package com.MochiJump;

public abstract class PlayerCharacter extends GameCharacter {
	public PlayerCharacter (DogLogic dl){
		super(dl);
	}

	public abstract void keyInputs();
}
