import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;

// okay unless I have to do something special for time I think I'm done here
// yeah it looks like I will need a timer. maybe I can use java.util timer
// https://stackoverflow.com/questions/23095690/how-to-use-java-util-timer

// animation class
public class Animation {
	// this is the resting mochi sprite:
		Image ms = new ImageIcon("mochirs.png").getImage();
	// now let's do the reverse for when he faces the other way;
		Image msr = new ImageIcon("mochirsr.png").getImage();

	// okay let's import the walking image to splice in the middle so he looks like he's walking:
		Image mws = new ImageIcon("mochiws.png").getImage();
		
	// and the reverse
		Image mwsr = new ImageIcon ("mochiwsr.png").getImage();

	// and lastly the three images for the jump cycle
		Image mjc1 = new ImageIcon("mochijs1.png").getImage();
		Image mjc2 = new ImageIcon ("mochijs2.png").getImage();
		Image mjc3 = new ImageIcon ("mochijs3.pgn").getImage();

	// and the reverse
		Image mjc1r = new ImageIcon("mochijs1r.png").getImage();
		Image mjc2r = new ImageIcon ("mochijs2r.png").getImage();
		Image mjc3r = new ImageIcon ("mochijs3r.pgn").getImage();
	

		// do I need to do something special for a time variable? Nope we're using speedY here
		// darn, it works for the jump but I still need a variable that switches back and forth for the run animation
		int aniTime;
		int x;
		int y;
		int sW;
		int sH;


// I can fix the problem below by setting and getting rectangle mochi and then using the getBounds to set the variables here.
// however if I do that I'm not sure that the state booleans will be updated.
		private void setMochiState(Mochi Mochi){
// the fact that it says that the above method is not used is concerning to me
			Mochi.mochi.x = x;
			Mochi.mochi.y = y;
			Mochi.mochi.height = sH;
			Mochi.mochi.width=sW;
			Mochi.mRestR = mRestR;
			Mochi.mRestL = mRestL;
			Mochi.mRunR = mRunR;
			Mochi.mRunL= mRunL;
			Mochi.mJumpR = mJumpR;
			Mochi.mJumpL = mJumpL;
		}
		
// hmm this won't get me access the variable speedY from class Mochi
// created a getter specifically for it and also setting the boolean states for mochi here.
		Mochi mochi = new Mochi ();
		float speedY = mochi.getSpeedY();
		boolean mRestR = mochi.mRestR;
		boolean mRestL = mochi.mRestL;
		boolean mRunR = mochi.mRunR;
		boolean mRunL = mochi.mRunL;
		boolean mJumpR = mochi.mJumpR;
		boolean mJumpL = mochi.mJumpL;
// boom it worked. Now remember how to do it so you stop pulling your hair out!
		
// changing this from paint to draw and then calling it in the DogLogic class works!
		public void draw (Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setClip(x, y, sW, sW);
			if (mRestR = true) {
				g2.drawImage(ms, x, y, sW, sH,null);
			}
			if (mRestL = true) {
				g2.drawImage(msr,x, y, sW, sH,null);
			}
			if (mRunR=true) {
				aniTime = 0;
				// change the aniTime value below to control speed of animation
				while (aniTime == 0) {
					g2.drawImage(mws,x, y, sW, sH,null);
					aniTime ++;
				}while (aniTime == 1) {
					g2.drawImage(ms, x, y, sW, sH,null);
					aniTime = 0;
					// ok I think I'm on the right path today was a long day I'm going to call it here 1/5  20:10
				}
			}
			if (mRunL= true) {
				aniTime =0;
				while (aniTime %2 == 0) {
					g2.drawImage(mws,x, y, sW, sH,null);
					aniTime ++;
				}while (aniTime %2 != 0) {
					g2.drawImage(ms, x, y, sW, sH,null);
					aniTime ++;
				}
			
			}	
			// considering adding multiple conditions to allow the sprite to flip mid jump
			// i.e. if (mJumpR = true && mRunL = false) etc.
			if (mJumpR = true) {
				// this should be done via y speed instead of a timer
				 while (speedY > 0) {
					 g2.drawImage(mjc1,x,y,sW,sH,null);
					 aniTime ++;
				 }
				 while (speedY == 0) {
					 g2.drawImage(mjc2,x,y,sW,sH, null);
				 // maybe could add if else statement here to allow mochi to switch directions during jump
				 }
				 while (speedY < 0) {
					 g2.drawImage(mjc3,x,y,sW,sH, null);
				 }
			}
			if (mJumpL = true) {
				// I'm going to code this for a half second loop
				while (speedY > 0) {
					 g2.drawImage(mjc1r,x,y,sW,sH,null);
					 aniTime ++;
				 }
				while (speedY == 0) {
					 g2.drawImage(mjc2r,x,y,sW,sH, null);
				 // maybe could add if else statement here to allow mochi to switch directions during jump
				 }
				 while (speedY < 0) {
					 g2.drawImage(mjc3r,x,y,sW,sH, null);
				 }
			}
		
		}


}
