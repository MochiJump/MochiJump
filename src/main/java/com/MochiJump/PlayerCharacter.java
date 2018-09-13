package com.MochiJump;

import javax.swing.JLabel;

public abstract class PlayerCharacter extends GameCharacter {
	public PlayerCharacter (DogLogic dl){
		super(dl);
	}

	public abstract JLabel keyInputs();
}
