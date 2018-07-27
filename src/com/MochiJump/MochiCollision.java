package com.MochiJump;

import java.awt.Rectangle;
import java.util.ArrayList;

public class MochiCollision implements CollisionInterface {
	
	public void collide(GameCharacter Implementer) {
		Implementer.getSpeedY();
		Implementer.y = Implementer.speedY+Implementer.y;
		Implementer.x = Implementer.x+Implementer.speedX;
		Implementer.mBoundaries();
		Implementer.mJumpHandler();
		ArrayList<Rectangle> platlist = Implementer.levelMap.getPlat();
		ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();
		
		for (int i= 0; i<Implementer.dogLogic.gameCharacters.size(); i++) {
			if (Implementer.dogLogic.gameCharacters.get(i) instanceof NonPlayerCharacter) {
				npcs.add((NonPlayerCharacter)Implementer.dogLogic.gameCharacters.get(i));
			}
		}
		
		
		// this controls collision with npcs (currently the same as platform objects):
		for (NonPlayerCharacter next: npcs) {
			Rectangle p1 = next.mochi;
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
		
		// this controls collision with platform objects:
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