package com.MochiJump;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class DogLogic extends JPanel {
	int currentPanel;
	JPanel dogPain;
	LevelMap levelMap;
	AnimationFactory animationFactory = new AnimationFactory();
	ArrayList <Rectangle> plat = new ArrayList<Rectangle>();
	ArrayList <AnimationInterface> animation = new ArrayList <AnimationInterface>();
	ArrayList <GameCharacter> gameCharacters = new ArrayList <GameCharacter>();
	int refreshRate = 30;
	Rectangle background = new Rectangle (0,0,10000,10000);
	Color skyblue = new Color (102, 204, 255);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Switcher switcher;
	NoCollideFactory noCollideFactory = new NoCollideFactory (this);

	
	public DogLogic (Switcher s) {
		levelMap = new LevelMap(this);
		dogPain = new JPanel();
		add(dogPain);
		gameStart();
		for (int i=0; i<gameCharacters.size(); i++) {
			gameCharacters.get(i).reSize();
		}
		switcher = s;

	}
	
	public void addGameCharacter (GameCharacter character, int startingX, int startingY) {
		character.posInGameCharacter = gameCharacters.size();
		gameCharacters.add(character);
		character.x = startingX;
		character.y = startingY;
		if (character instanceof PlayerCharacter) {
			PlayerCharacter playerCharacter = (PlayerCharacter) character;
			dogPain.add(playerCharacter.keyInputs());
		}
		animation.add(animationFactory.makeAnimation(character));
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

boolean runAway = false;

	public void gameUpdate () {
		plat = levelMap.getPlat();
		for (int i=0; i<animation.size(); i++) {
			animation.get(i).setCurrentSprite();
		}
		for (int i=0; i<gameCharacters.size(); i++) {
			if (gameCharacters.get(i) instanceof NonPlayerCharacter) {
				NonPlayerCharacter npc = (NonPlayerCharacter)gameCharacters.get(i);
				npc.aIInputs();
			}
			gameCharacters.get(i).boundaryRules();
		}
		for (int i=0; i<animation.size(); i++) {
			animation.get(i).AniVarUpdate(this.gameCharacters.get(i));
		}
		
		if (runAway == true) {
			gameCharacters.get(gameCharacters.size()-1).runOffScreen(.9);
		}
	}
	
	public void turnToNoCollide (GameCharacter toBeChanged, int currentX, int currentY) {
		for (int i=0; i<gameCharacters.size(); i++) {
			if (gameCharacters.get(i) == toBeChanged) {
				gameCharacters.set(i, noCollideFactory.swapToNoCollide(toBeChanged));
				gameCharacters.get(i).x= currentX;
				gameCharacters.get(i).y= currentY;
				animation.set(i, animationFactory.makeAnimation(gameCharacters.get(i)));
			}
		}
		
	}
	

		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
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
			for (int i=0; i<animation.size(); i++) {
				animation.get(i).draw(g2);
			}
		}
		
	@Override
	public Dimension getPreferredSize() {
			return screenSize;
	}
	
}