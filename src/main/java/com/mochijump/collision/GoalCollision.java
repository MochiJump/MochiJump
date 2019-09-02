package com.mochijump.collision;

import com.mochijump.characters.NoCollideCharacter;
import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.PlayerCharacter;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GoalCollision implements CollisionInterface {
	public void collide(GameCharacter Implementer) {
		Implementer.getSpeedY();
		Implementer.y = Implementer.speedY+Implementer.y;
		Implementer.x = Implementer.x+Implementer.speedX;
		Implementer.mBoundaries();
		Implementer.mJumpHandler();
		ArrayList<Rectangle> platlist = Implementer.levelMap.getPlat();
		ArrayList<GameCharacter> npcs = new ArrayList<GameCharacter>();
		
		for (int i= 0; i<Implementer.dogLogic.gameCharacters.size(); i++) {
			if(!(i==Implementer.posInGameCharacter)&&
					!(Implementer.dogLogic.gameCharacters.get(i) 
							instanceof NoCollideCharacter)) {
					npcs.add(Implementer.dogLogic.gameCharacters.get(i));
				}
		}
		
		for (GameCharacter next: npcs) {
			Rectangle p1 = next.mochi;
			if (next instanceof PlayerCharacter && Implementer.mochi.intersects(p1)) {
				JOptionPane.showMessageDialog(null, "Level Complete!");
				Implementer.dogLogic.switcher.restartDogLogic();
			}
			if (Implementer.mochi.intersects(p1)) {			
				if (Implementer.mright.intersects(p1)) {
					Implementer.x = p1.x-Implementer.sW;
				} 
				if (Implementer.mleft.intersects(p1)) {
					Implementer.x = p1.x +p1.width +1;
				}
				if (Implementer.mtop.intersects(p1)) {
					Implementer.y = p1.y +p1.height;
				}
				if (Implementer.mbottom.intersects(p1)) {
					Implementer.y = p1.y-Implementer.sH;
					Implementer.jumpChu = false;
					if (Implementer.jumpChu == false) {
						Implementer.landing();
					}
				}
							
			}
		}
		
		
		for (Rectangle next: platlist) {
			Rectangle p1 = next.getBounds();
			if (Implementer.mochi.intersects(p1)) {
				if (Implementer.mright.intersects(p1)) {
					Implementer.x = p1.x-Implementer.sW;
				} 
				if (Implementer.mleft.intersects(p1)) {
					Implementer.x = p1.x +p1.width +1;
				}
				if (Implementer.mtop.intersects(p1)) {
					Implementer.y = p1.y +p1.height;
				}
				if (Implementer.mbottom.intersects(p1)) {
					Implementer.y = p1.y-Implementer.sH;
					Implementer.jumpChu = false;
					if (Implementer.jumpChu == false) {
						Implementer.landing();
					}
				}
							
			}
		}
		
	}

}
