package com.mochijump.framesandpanels;

import javax.swing.*;

/**
 * @author andrew
 */
public class PanelSwitcher extends JFrame {
    public int currentPanel = 0;
    public DogLogic dogLogic = new DogLogic(this);
    public StartPause startPause;
    public LevelSelector levelSelector = new LevelSelector(this);
    private JFrame frame = new JFrame("Mochi Jump");
    public boolean escapeUsed;
    public boolean showContinue;

    public PanelSwitcher() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.repaint();
        startPause = new StartPause(this);
        changePanel(1);
    }

    public void setCurrentPanel(int option) {
        this.currentPanel = option;
    }

    public void setOutsideClassCurrentPanel(int option) {
        dogLogic.setCurrentPanel(option);
        startPause.setCurrentPanel(option);
        levelSelector.setCurrentPanel(option);
    }

    public void restartDogLogic() {
        escapeUsed = true;
        showContinue = false;
        changePanel(1);
        dogLogic = new DogLogic(this);
        levelSelector = new LevelSelector(this);
    }

    public void leaveDogLogic() {
        escapeUsed = true;
        showContinue = true;
        changePanel(1);
        levelSelector = new LevelSelector(this);
    }

    /**
     * (1) is startPause (2) is dogLogic (3) is levelSelector
     *
     * @param panelNumber
     */

    public void changePanel(int panelNumber) {
        currentPanel = panelNumber;
        setOutsideClassCurrentPanel(panelNumber);
        frame.getContentPane().removeAll();
        if (currentPanel == 1) {
            frame.getContentPane().add(startPause);
            if (escapeUsed) {
                startPause.startPauseActive();
            }
        } else if (currentPanel == 2) {
            frame.getContentPane().add(dogLogic);
            dogLogic.gameStart();
        } else if (currentPanel == 3) {
            frame.getContentPane().add(levelSelector);
            levelSelector.levelSelectorActive();
        }
        frame.validate();
        // below causes NPE
//			 frame.pack();
    }
}