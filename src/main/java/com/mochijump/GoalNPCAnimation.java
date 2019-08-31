package com.mochijump;

import javax.swing.*;
import java.awt.*;

public class GoalNPCAnimation implements AnimationInterface {
    private Image goalAni = new ImageIcon(this.getClass().getResource("/ball.png")).getImage();
    private Image currentSprite;
    private GameCharacter npc;


    private int aniTime = 1;
    private int sW = 21;
    private int sH = 14;
    private int x;
    private int y;
    private int spriteHeight;


    private boolean mRestR;
    private boolean mRestL;
    private boolean mRunR;
    private boolean mRunL;
    private boolean mJumpR;
    private boolean mJumpL;


    // right now I want to change the height of the sprite for the purposes of animating the bouncing ball (below sH).
// I think the easiest way to to do this is to have a class variable for GameCharacter, set it below, and then change the sH
// directly in the animation methods below (e.g. goalNPC.sH = changed)
    public void aniVarUpdate(GameCharacter mochi) {
        npc = mochi;
        x = (int) mochi.getX();
        y = (int) mochi.getY();
        sH = (int) mochi.getsH();
        sW = (int) mochi.getsW();
        mRestR = mochi.mRestR;
        mRestL = mochi.mRestL;
        mRunR = mochi.mRunR;
        mRunL = mochi.mRunL;
        mJumpR = mochi.mJumpR;
        mJumpL = mochi.mJumpL;
        spriteHeight = 14 * npc.dogLogic.resizeValue;
    }


    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setClip(x, y, sW, sH);
        g2.drawImage(currentSprite, x, y, sW, sH, null);
    }

    public void leftAni() {
        if (aniTime <= 5) {
            currentSprite = goalAni;
            aniTime++;
        } else if (aniTime <= 10) {
            currentSprite = goalAni;
            aniTime++;
        } else if (aniTime <= 15) {
            currentSprite = goalAni;
            aniTime++;
        } else {
            aniTime = 1;
        }
    }

    public void rightAni() {
        if (aniTime <= 5) {
            currentSprite = goalAni;
            aniTime++;
        } else if (aniTime <= 10) {
            currentSprite = goalAni;
            aniTime++;
        } else if (aniTime <= 15) {
            currentSprite = goalAni;
            aniTime++;
        } else {
            aniTime = 1;
        }
    }

    // implement this:
    public void setCurrentSprite() {
        if (mRestR) {
            rightAni();
            setSpriteHeight();
        }
        if (mRestL) {
            leftAni();
            setSpriteHeight();
        }
        if (mRunR) {
            rightAni();
            setSpriteHeight();
        }
        if (mRunL) {
            leftAni();
			setSpriteHeight();
        }
        if (mJumpR) {
            npc.sH = (21 * npc.dogLogic.resizeValue);
            rightAni();
        }
        if (mJumpL) {
            npc.sH = (21 * npc.dogLogic.resizeValue);
            leftAni();
        }
    }

    private void setSpriteHeight (){
		if (npc.sH > (spriteHeight)) {
			npc.sH = npc.sH - (2 * npc.dogLogic.resizeValue);
		}
	}
}

