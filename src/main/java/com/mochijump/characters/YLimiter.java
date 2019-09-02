package com.mochijump.characters;


import com.mochijump.collision.CollisionInterface;
import com.mochijump.collision.YLimiterCollision;
import com.mochijump.framesandpanels.DogLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is technically a GameCharacter, however, it is just used to hold a position specifically the y value that
 * other characters cannot pass until they fall out of the world.
 *
 * @author andrew
 */
public class YLimiter extends NonPlayerCharacter {
    private final Logger LOG = LogManager.getLogger(YLimiter.class);
    private CollisionInterface collide = new YLimiterCollision();
    private final float DEFAULT_Y_LIMITER_HEIGHT = 10000;


    // the only thing this class is here for is to mark the lowest point

    public YLimiter(DogLogic d) {
        super(d);
        this.y = DEFAULT_Y_LIMITER_HEIGHT;
        // TODO: determine if I want the Y limit to be configurable, and how
        //this.y = Integer.valueOf(imported);
//        if (LOG.isDebugEnabled()) {
//            LOG.debug("Imported YLimiter config file {} and assigned to y value", imported);
//        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("value set for y (height) for the \"Y Limiter\" is {}", y);
        }
    }

    public void mJumpHandler() {
        // this class doesn't jump
    }

    public void boundaryRules() {
        collide.collide(this);
    }

    public void aIInputs() {
        y = (float) (dogLogic.plat.get(0).y + dogLogic.plat.get(0).getHeight() + 100);
    }
}
