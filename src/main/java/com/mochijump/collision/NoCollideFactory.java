package com.mochijump.collision;

import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.HairClipNPC;
import com.mochijump.characters.HairClipNoCollide;
import com.mochijump.characters.NoCollideCharacter;
import com.mochijump.framesandpanels.DogLogic;

public class NoCollideFactory {
	private DogLogic dogLogic;
	
	public NoCollideFactory (DogLogic dl) {
		dogLogic = dl;
	}
	
	
	
	public NoCollideCharacter swapToNoCollide (GameCharacter original) {
		if (original instanceof HairClipNPC) {
			return new HairClipNoCollide(dogLogic);
		} else {
			//should be unreachable:
			return new HairClipNoCollide(dogLogic);
		}
		
		
	}

}
