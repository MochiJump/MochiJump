import java.awt.*;

import javax.swing.ImageIcon;

// I'm adding a second test class "TC2" in here so I can safely copy everything from the Animation Class I've already written
// and delete and add stuff without losing my original work.

public class TC2 {
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

// setting this manually too:
	int aniTime = 1;
	int x = 5;
	int y = 5;
	int sW = 21;
	int sH = 14;


// This is a setter for Mochi's state. I don't think anything in the class needs this. I also don't have the appropriate arguments
// in the method to do anything with it. This was made before I actually understood how setters work.
//	private void setMochiState(Mochi Mochi){
//		Mochi.mochi.x = x;
//		Mochi.mochi.y = y;
//		Mochi.mochi.height = sH;
//		Mochi.mochi.width=sW;
//		Mochi.mRestR = mRestR;
//		Mochi.mRestL = mRestL;
//		Mochi.mRunR = mRunR;
//		Mochi.mRunL= mRunL;
//		Mochi.mJumpR = mJumpR;
//		Mochi.mJumpL = mJumpL;
//	}
	
// For now I'm just going to manually set all of the variables I need to see if I can get this to work.
// Currently set for only run condition to be true.	
//	Mochi mochi = new Mochi ();
	float speedY = 0;
	boolean mRestR = false;
	boolean mRestL = false;
	boolean mRunR = true;
	boolean mRunL = false;
	boolean mJumpR = false;
	boolean mJumpL = false;
	

	public void draw (Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setClip(x, y, sW, sH);
		if (mRestR == true) {
			g2.drawImage(ms, x, y, sW, sH,null);
		}
		if (mRestL == true) {
			g2.drawImage(msr,x, y, sW, sH,null);
		}
		if (mRunR == true) {
			// running this causes everything to break. running mRunL, however, does not?
			// getting rid of these while loops work find as well. keeping them in causes everything to break.
			// creating a new variable just for this has no effect
			// changing this to if statements makes it behave more like I would expect, however, it only runs a single iteration.
			if (aniTime <= 100) {
				g2.drawImage(mws,x, y, sW, sH,null);
				aniTime ++;
			}else if (aniTime <= 200) {
				g2.drawImage(ms, x, y, sW, sH,null);
			}else  {
				aniTime = 0;
			}
		}
		// why does this at least run when mRunL is set to true, but mRunR causes everything to lock up?
		// why is it only the
		// unless all three while statements are present nothing is painted, and then only the msr image is called... so strange
		// adding a new local variable here also does nothing to change the situation.
		// the crazy part is the wrong the sprite is what is shown when I try to run this, its the msr that is drawn not the mwsr...
		
		//As I know exactly how long these loops are supposed to be I should actually be using for loops instead of while loops,
		//while loops should work though and I have no idea why they aren't working here.
		if (mRunL== true) {
			if (aniTime <= 100) {  
				g2.drawImage(mwsr,x, y, sW, sH,null);
				aniTime ++;
			}
			else if (aniTime <= 200) {
				g2.drawImage(msr, x, y, sW, sH,null);
				aniTime ++;
			} 
			else{
				aniTime = 1;
			}
				
			}
		
		}	
	
}


// forgot to use == instead of = in if statements. The boolean statements seem to be doing something, however, the animations
// are not showing. Also int AniTime is not changing, and the imagine being displayed does not represent what should be displayed
// based off of the corresponding aniTime... This is behaving weird... The image does flip depending on whether it is right or left
// however, which is also strange if nothing else is working..
