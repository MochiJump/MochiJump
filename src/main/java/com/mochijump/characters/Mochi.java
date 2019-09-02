package com.mochijump.characters;


import com.mochijump.actions.JumpInterface;
import com.mochijump.actions.StandardJump;
import com.mochijump.collision.CollisionInterface;
import com.mochijump.collision.MochiCollision;
import com.mochijump.framesandpanels.DogLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class Mochi extends PlayerCharacter {
    private final static Logger LOG = LogManager.getLogger(Mochi.class);
    private JumpInterface jump = new StandardJump();
    private CollisionInterface collide = new MochiCollision();
    private boolean moveKeyDown = false;

    public Mochi(DogLogic dl) {
        super(dl);
    }

    @Override
    public void landing() {
        if (moveKeyDown == true) {
            if (mJumpR == true) {
                setActionToFalse();
                mRunR = true;
                moveKeyDown = true;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Mochi should be running currently");
                }
            } else if (mJumpL == true) {
                setActionToFalse();
                mRunL = true;
                moveKeyDown = true;
            }
        } else {
            if (mJumpR == true) {
                setActionToFalse();
                mRestR = true;
                LOG.debug("Mochi should NOT be running currently");
            } else if (mJumpL == true) {
                setActionToFalse();
                mRestL = true;
            }
        }
    }


    public void mJumpHandler() {
        jump.jump(this);
    }

    public void boundaryRules() {
        collide.collide(this);
    }

    public void keyInputs() {

        MoveRightAct MoveRightAct = new MoveRightAct();
        RestRight RestRight = new RestRight();
        MoveLeftAct MoveLeftAct = new MoveLeftAct();
        RestLeft RestLeft = new RestLeft();
        JumpAct JumpAct = new JumpAct();
        Escape Escape = new Escape();

        InputMap im = dogLogic.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = dogLogic.getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "MoveRightAct");
        am.put("MoveRightAct", MoveRightAct);
        im.put(KeyStroke.getKeyStroke("released RIGHT"), "RestRight");
        am.put("RestRight", RestRight);
        im.put(KeyStroke.getKeyStroke("LEFT"), "MoveLeftAct");
        am.put("MoveLeftAct", MoveLeftAct);
        im.put(KeyStroke.getKeyStroke("released LEFT"), "RestLeft");
        am.put("RestLeft", RestLeft);
        im.put(KeyStroke.getKeyStroke("UP"), "JumpAct");
        am.put("JumpAct", JumpAct);
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        am.put("Escape", Escape);

    }


    class MoveRightAct extends AbstractAction {
        public void actionPerformed(ActionEvent mr) {
            if (jumpChu == false) {
                setActionToFalse();
                mRunR = true;
                moveKeyDown = true;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("The movekey for Mochi is set to true");
                }
            }
            if (jumpChu == true) {
                setActionToFalse();
                mJumpR = true;
                moveKeyDown = true;
            }
            x += 2 * dogLogic.resizeValue;
            if (speedX >= -1) {
                speedX += 1 * dogLogic.resizeValue;
            }
            if (speedX >= 1.5 * dogLogic.resizeValue) {
                speedX = (float) 1.5 * dogLogic.resizeValue;
            }
        }
    }

    class MoveLeftAct extends AbstractAction {
        public void actionPerformed(ActionEvent ml) {
            mRunL = true;
            if (jumpChu == false) {
                setActionToFalse();
                mRunL = true;
                moveKeyDown = true;
            }
            if (jumpChu == true) {
                setActionToFalse();
                mJumpL = true;
                moveKeyDown = true;
            }
            x -= 2 * dogLogic.resizeValue;
            if (speedX <= 1) {
                speedX -= 1 * dogLogic.resizeValue;
            }
            if (speedX <= -1.5 * dogLogic.resizeValue) {
                speedX = (float) -1.5 * dogLogic.resizeValue;
            }


        }

    }

    class JumpAct extends AbstractAction {
        public void actionPerformed(ActionEvent jr) {
            if (jumpChu == false) {
                jumpChu = true;
                jump();

            }
        }
    }

    class RestRight extends AbstractAction {
        public void actionPerformed(ActionEvent rr) {
            if (speedX > 0) {
                speedX -= (float) 1.5 * dogLogic.resizeValue;
                if (speedX < 0) {
                    speedX = 0;
                }
                if (speedX == 0 && jumpChu == false) {
                    setActionToFalse();
                    mRestR = true;
                    moveKeyDown = false;

                }

            }
        }
    }

    class RestLeft extends AbstractAction {
        public void actionPerformed(ActionEvent rr) {
            if (speedX < 0) {
                speedX += (float) 1.5 * dogLogic.resizeValue;
                if (speedX > 0) {
                    speedX = 0;
                }
                if (speedX == 0 && jumpChu == false) {
                    setActionToFalse();
                    mRestL = true;
                    moveKeyDown = false;

                }

            }
        }
    }

    class Escape extends AbstractAction {
        public void actionPerformed(ActionEvent es) {
            dogLogic.switcher.leaveDogLogic();

        }
    }
}