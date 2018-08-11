package com.MochiJump;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LevelMap extends JPanel {

	double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/786;
	double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	double reSizer = 1;
	DogLogic dogLogic;
	private ArrayList<Rectangle> platlist = new ArrayList<>();

	boolean webImport = false;
	
	public LevelMap (DogLogic dl) {
		dogLogic = dl;
	}
	
	
	private void addPlat (int x, int y, int width, int height){	
	
		
		
	platlist.add (new Rectangle (x,y,width,height));	
	}
    
	private void noWebPlatSetup() {
		if (!webImport) {
	addPlat((int)(0*keepWidth*reSizer), (int) (keepHeight*reSizer*500), 
			(int) (keepWidth*reSizer*1000), (int) (keepHeight*reSizer*500));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	addPlat((int) (reSizer*keepWidth*120), (int) (keepHeight*reSizer*(500-70)), 
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer* 10));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35*3)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	dogLogic.addGameCharacter (new HairClipNPC(dogLogic), 150, 50);
	dogLogic.addGameCharacter (new HairClipNPC(dogLogic), 300, 100);
	dogLogic.addGameCharacter(new HairClipNPC(dogLogic), 500, 300);
	dogLogic.addGameCharacter(new GoalNPC(dogLogic), 700, 400);
	dogLogic.addGameCharacter (new Mochi(dogLogic), 0, 0);
		}
	}
	
	int previousPlatY = 0;
	public void useWebImport(ArrayList<Rectangle> platforms) {
		ArrayList<Rectangle>OrderedPlat = new ArrayList<Rectangle>();
		for (int i = 0; i<platforms.size(); i++) {
			int nextPlatY = platforms.get(i).y;
			if (i==0) {
				OrderedPlat.add(platforms.get(i));
				previousPlatY = platforms.get(i).y;
			} else if (nextPlatY <= previousPlatY ) {
				OrderedPlat.add(platforms.get(i));
				previousPlatY = platforms.get(i).y;
			} else if (nextPlatY > previousPlatY) {
				boolean doLoop = true;
				for (int z = 0; z<OrderedPlat.size() && doLoop == true; z++) {
					if (nextPlatY >= OrderedPlat.get(z).y) {
						OrderedPlat.add(z, platforms.get(i));
						doLoop = false;
					}
				}
			}
			
		}
		
		platlist.clear();
		platlist.addAll (OrderedPlat);
		webImport = true;
	}
	
	public void addHairClipNPC (int x, int y) {
		dogLogic.addGameCharacter (new HairClipNPC(dogLogic), x, y);
	}
	
	//currently this always has to be called last
	public void addMochi(int x, int y) {
		dogLogic.addGameCharacter(new Mochi(dogLogic), x, y);
	}
	
	// cheap fix for concurrent thread error:
	boolean noWebSetupDone;
	public ArrayList<Rectangle> getPlat(){
		if (webImport == true) {
				return this.platlist;	
		}else {
			if(!noWebSetupDone) {
				noWebPlatSetup();
				noWebSetupDone = true;
				return this.platlist;

			}
			else {
				return this.platlist;
			}
		}
	}
}

