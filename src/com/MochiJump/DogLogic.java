package com.MochiJump;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.*;

public class DogLogic extends JPanel {
	StartPause startPause = new StartPause();
	boolean isStartOn = false;
	LevelMap levelMap = new LevelMap();
	ArrayList <Rectangle> plat = new ArrayList<Rectangle>();
	Animation animation = new Animation();
	Mochi mochi = new Mochi();
	int refreshRate = 30;

	
	public DogLogic () {
		JPanel dogPain = new JPanel();
		dogPain.add(mochi.keyInputs());
		add(dogPain);
		gameStart();

	}

	public void gameStart() {
		Thread gameThread = new Thread() {
			public void run() {
				while (!isStartOn) {
					gameUpdate();
					repaint();
					try {
						Thread.sleep(1000/refreshRate);
					}catch (InterruptedException e) {
						System.out.println("An error in gameThread has occured");
					}
				}
			
			}
		}; 
		gameThread.start(); 
	}
	
	public void gameUpdate () {
		animation.setCurrentSprite();
		mochi.boundaryRules();
		animation.AniVarUpdate(this.mochi);
	}
	

		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			plat = levelMap.getPlat();
			Graphics2D g2 = (Graphics2D) g.create();
			for (Rectangle next: plat) {
				g2.setColor( new Color (130, 87, 27));
				g2.fill(next);
				g2.draw(next);
				}
			animation.draw(g2);
			
			}
	
}