package com.mochijump;

import java.awt.Graphics;

public interface AnimationInterface {
	
	abstract void aniVarUpdate (GameCharacter Implementer);
	abstract void setCurrentSprite();
	abstract void draw(Graphics g);

}
