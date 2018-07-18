package com.MochiJump;

import java.awt.Rectangle;
import java.util.ArrayList;

public class StandardCollision implements CollisionInterface {
	
	public void collide(Mochi Implementer) {
		Implementer.getSpeedY();
		Implementer.y = Implementer.speedY+Implementer.y;
		Implementer.x = Implementer.x+Implementer.speedX;
		Implementer.mBoundaries();
		Implementer.mJumpHandler();
		// review the below code
		ArrayList<Rectangle> platlist = Implementer.levelMap.getPlat();
		for (Rectangle next: platlist) {
			// I tried remove.getBounds below no change.
			Rectangle p1 = next.getBounds();
			// testing whether mochi does not intersect may work to turn on "falling animation" *******
			if (Implementer.mochi.intersects(p1)) {
			// so here is where we program what we want a result of a collision...
			// what I want is for mochi to return to the state just prior to his collision.
			// how do I program that?
			// okay so I have to write a few more if statements here:
			// I need to determine if the collision happened from above or below,
			// or from left or from the right. 
				if (Implementer.mright.intersects(p1)) {
					// because this is being called!
					Implementer.x = p1.x-Implementer.sW;
				// I like getting to use basic algebra in real life.
				} 
				if (Implementer.mleft.intersects(p1)) {
					Implementer.x = p1.x +p1.width +1;
				}
				if (Implementer.mtop.intersects(p1)) {
					Implementer.y = p1.y +p1.height;
				}
				if (Implementer.mbottom.intersects(p1)) {
					// I'm thinking this should be an independant method that is called after this if statement
					// heard that many nested if statements is bad coding practice.
					// hmm looks like changing these parameters does nothing
					Implementer.y = p1.y-Implementer.sH;
					// going to always set JumpChu to false whenever this intersection happens
					Implementer.jumpChu = false;
					// while statement below didn't work trying if statement to stop jittering, the below isn't working either
					// perhaps I need an outside method that turns gravity off.
					if (Implementer.jumpChu == false) {
						Implementer.landing();
					}
				}
							
			}
		}
		
	}
}
