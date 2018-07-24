package com.MochiJump;

import java.awt.Graphics;

public interface AnimationInterface {
	
	abstract void AniVarUpdate (GameCharacter Implementer);
	abstract void setCurrentSprite();
	abstract void draw(Graphics g);

}
