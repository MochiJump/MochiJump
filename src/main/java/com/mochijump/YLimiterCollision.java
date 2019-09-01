package com.mochijump;

import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.PlayerCharacter;

import java.awt.Rectangle;
import java.util.ArrayList;

public class YLimiterCollision implements CollisionInterface{
	private int counter = 0;
	public void collide(GameCharacter implementer) {
		
		ArrayList<GameCharacter> npcs = new ArrayList<GameCharacter>();
		
		for (int i= 0; i<implementer.dogLogic.gameCharacters.size(); i++) {
			if(i!=implementer.posInGameCharacter&&
					!(implementer.dogLogic.gameCharacters.get(i) 
							instanceof NoCollideCharacter)) {
					npcs.add(implementer.dogLogic.gameCharacters.get(i));
				}
		}
		
		for (GameCharacter next: npcs) {
			Rectangle p1 = next.mochi;
			if (next instanceof PlayerCharacter && p1.y> implementer.y) {
				// this fires early
				if (counter<10) {
					counter++;
				}else {
					next.dogLogic.runAway = true;
				}
			}
		
		}
	}

}
