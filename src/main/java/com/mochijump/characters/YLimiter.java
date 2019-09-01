package com.mochijump.characters;


import com.mochijump.CollisionInterface;
import com.mochijump.DogLogic;
import com.mochijump.YLimiterCollision;
import com.mochijump.characters.NonPlayerCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is techinically a GameCharacter, however, it is just used to hold a position specifically the y value that
 * other characters cannot pass until they fall out of the world.
 * @author andrew
 */
public class YLimiter extends NonPlayerCharacter {
    private final Logger LOG = LogManager.getLogger(YLimiter.class);
    private CollisionInterface collide = new YLimiterCollision();
    private final float DEFAULT_Y_LIMITER_HEIGHT = 10000;


    // the only thing this class is here for is to mark the lowest point

    public YLimiter(DogLogic d) {
        super(d);
        try {
            final String imported = this.getClass().getClassLoader().getResource("/ImportUrl").getFile();
            this.y = Integer.valueOf(imported);
            if (LOG.isDebugEnabled()){
                LOG.debug("Imported YLimiter config file {} and assigned to y value", imported);
            }
        }catch (NullPointerException npe){
            this.y = DEFAULT_Y_LIMITER_HEIGHT;
        } catch (Exception e ){
            LOG.warn("Unexpected exception in YLimiter during config import stack trace: {}", e.getStackTrace());
        }
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
