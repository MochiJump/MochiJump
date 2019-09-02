package com.mochijump.collision;

import com.mochijump.characters.GameCharacter;
import com.mochijump.characters.GoalNPC;
import com.mochijump.characters.NonPlayerCharacter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MochiCollision implements CollisionInterface {
	private static final Logger LOG = LogManager.getLogger(MochiCollision.class);

	public void collide(GameCharacter Implementer) {
		Implementer.getSpeedY();
		Implementer.y = Implementer.speedY+Implementer.y;
		Implementer.x = Implementer.x+Implementer.speedX;
		Implementer.mBoundaries();
		Implementer.mJumpHandler();
		ArrayList<NonPlayerCharacter> npcs = new ArrayList<NonPlayerCharacter>();
		
		// adds npcs to list while skipping mochi
		for (int i= 0; i<Implementer.dogLogic.gameCharacters.size(); i++) {
			if (Implementer.dogLogic.gameCharacters.get(i) instanceof NonPlayerCharacter) {
				npcs.add((NonPlayerCharacter)Implementer.dogLogic.gameCharacters.get(i));
			}
		}
		
		if (Implementer.mochi.getWidth() <1) {
			JOptionPane.showMessageDialog(null, "Game Over");
			Implementer.dogLogic.switcher.restartDogLogic();
		}
		// this controls collision with npcs (could change format to GameCharacter

		for (NonPlayerCharacter next: npcs) {
			Rectangle p1 = next.mochi;
			//controls collision with HairClipNPC:
			if (!(next instanceof GoalNPC)) {
				if (Implementer.mochi.intersects(p1)) {
					if (LOG.isDebugEnabled()){
						LOG.debug("Mochi has collided with an npc");
					}
					if (Implementer.mright.intersects(p1)) {
						Implementer.dogLogic.runAway = true;
					}
					if (Implementer.mleft.intersects(p1)) {
						Implementer.dogLogic.runAway = true;
					}
					if (Implementer.mtop.intersects(p1)) {
						Implementer.dogLogic.runAway = true;
					}
					if (Implementer.mbottom.intersects(p1)) {
						Implementer.y = p1.y-Implementer.sH;
						Implementer.jumpChu = false;
						Implementer.landing();
					}
				}
			}
		}
		// Rearranging import of level works for fixing this!
		for (Rectangle next: Implementer.dogLogic.plat) {
			Rectangle p1 = next.getBounds();
			Line2D.Float platTop = new Line2D.Float(p1.x,p1.y,p1.x+p1.width,p1.y);
			if (Implementer.mochi.intersectsLine(platTop)) {
					Implementer.y = p1.y-Implementer.sH;
					Implementer.jumpChu = false;
						Implementer.landing();
			}
			if (Implementer.mochi.intersects(p1)) {
				if (Implementer.mright.intersects(p1)) {
					Implementer.speedX=0;
					Implementer.x = p1.x-Implementer.sW;
				} 
				if (Implementer.mleft.intersects(p1)) {
					Implementer.speedX=0;
					Implementer.x = p1.x +p1.width +1;
				}
				if (Implementer.mtop.intersects(p1)) {
					if (Implementer.jTime<18) {
						Implementer.jTime= 18;
					}
					Implementer.y = p1.y +p1.height;
				}
			}
		}
	}
}
