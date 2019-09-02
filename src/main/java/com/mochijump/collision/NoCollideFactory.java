package com.mochijump.collision;

import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.HairClipNPC;
import com.mochijump.characters.HairClipNoCollide;
import com.mochijump.characters.NoCollideCharacter;
import com.mochijump.framesandpanels.DogLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoCollideFactory {
	private final static Logger LOG = LogManager.getLogger(NoCollideFactory.class);
    private DogLogic dogLogic;

    public NoCollideFactory(DogLogic dl) {
        dogLogic = dl;
    }

    public NoCollideCharacter swapToNoCollide(GameCharacter original) {
        if (original instanceof HairClipNPC) {
			NoCollideCharacter ncc =  new HairClipNoCollide(dogLogic);
			return ncc;
        }
        //This is happening with the Goose NPC... which is okay it's behaving as the default
		NoCollideCharacter ncc =  new HairClipNoCollide(dogLogic);
		return ncc;
    }
}
