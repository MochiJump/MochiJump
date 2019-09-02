package com.mochijump.collision;

import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.NoCollideCharacter;
import com.mochijump.characters.PlayerCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;

public class HairClipCollision implements CollisionInterface {
    private final static Logger LOG = LogManager.getLogger(HairClipCollision.class);

    public void collide(GameCharacter implementer) {
        implementer.getSpeedY();
        implementer.y = implementer.speedY + implementer.y;
        implementer.x = implementer.x + implementer.speedX;
        implementer.mBoundaries();
        implementer.mJumpHandler();
        ArrayList<Rectangle> platlist = implementer.levelMap.getPlat();
        ArrayList<GameCharacter> npcs = new ArrayList<GameCharacter>();

        for (int i = 0; i < implementer.dogLogic.gameCharacters.size(); i++) {
            if (i != implementer.posInGameCharacter &&
                    !(implementer.dogLogic.gameCharacters.get(i)
                            instanceof NoCollideCharacter)) {
                npcs.add(implementer.dogLogic.gameCharacters.get(i));
            }
        }
        for (GameCharacter next : npcs) {
            Rectangle p1 = next.mochi;
            if (implementer.mochi.intersects(p1)) {
                if (implementer.mright.intersects(p1)) {
                    implementer.x = p1.x - implementer.sW;
                }
                if (implementer.mleft.intersects(p1)) {
                    implementer.x = p1.x + p1.width + 1;
                }
                if (implementer.mtop.intersects(p1)) {
                    if (next instanceof PlayerCharacter) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("HairClip is being destroyed by Mochi, current cooridnates x: {}, y: {}",
                                    implementer.x, implementer.y);
                        }
                        next.jump();
                        implementer.dogLogic.turnToNoCollide(implementer,
                                (int) implementer.x-(10*implementer.dogLogic.resizeValue), (int) implementer.y);
                    } else {
                        implementer.y = p1.y + p1.height;
                    }
                }
                if (implementer.mbottom.intersects(p1)) {
                    implementer.y = p1.y - implementer.sH;
                    implementer.jumpChu = false;
                    if (implementer.jumpChu == false) {
                        implementer.landing();
                    }
                }

            }
        }


        for (Rectangle next : platlist) {
            Rectangle p1 = next.getBounds();
            if (implementer.mochi.intersects(p1)) {
                if (implementer.mright.intersects(p1)) {
                    implementer.x = p1.x - implementer.sW;
                }
                if (implementer.mleft.intersects(p1)) {
                    implementer.x = p1.x + p1.width + 1;
                }
                if (implementer.mtop.intersects(p1)) {
                    implementer.y = p1.y + p1.height;
                }
                if (implementer.mbottom.intersects(p1)) {
                    implementer.y = p1.y - implementer.sH;
                    implementer.jumpChu = false;
                    implementer.landing();
                }
            }
        }
    }
}
