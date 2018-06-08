package com.MochiJump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;

public class DogLogic extends JPanel {
	static int currentPanel;
	LevelMap levelMap = new LevelMap();
	ArrayList <Rectangle> plat = new ArrayList<Rectangle>();
	Animation animation = new Animation();
	Mochi mochi = new Mochi();
	int refreshRate = 30;
	Rectangle background = new Rectangle (0,0,10000,1000);
	Color skyblue = new Color (102, 204, 255);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	
	public DogLogic () {
		JPanel dogPain = new JPanel();
		dogPain.add(mochi.keyInputs());
		add(dogPain);
		gameStart();

	}
	
	public void setCurrentPanel (int option) {
		this.currentPanel = option;
	}

	public void gameStart() {
		Thread gameThread = new Thread() {
			public void run() {
				while (currentPanel==2) {
					gameUpdate();
					repaint();
					System.out.println("Bark");
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
			// 1st objects drawn will be in background so I can create a background by doing this:
			// an animated background could work here as well.
			g2.setColor(skyblue);
			g2.fill(background);
			g2.draw(background);
			for (Rectangle next: plat) {
				g2.setColor( new Color (130, 87, 27));
				g2.fill(next);
				g2.draw(next);
				}
			animation.draw(g2);
			
			}
		
	@Override
	public Dimension getPreferredSize() {
			return screenSize;
	}
	
}