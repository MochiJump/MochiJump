package com.mochijump;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LevelMap extends JPanel {

	double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/786;
	double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	double reSizer = 7;
	private DogLogic dogLogic;
	public ArrayList<Rectangle> platlist = new ArrayList<>();
	boolean webImport = false;
	int previousPlatY = 0;
	boolean noWebSetupDone;
	
	public LevelMap (DogLogic dl) {
		dogLogic = dl;
		reSizer = dogLogic.resizeValue;
	}
	
	
	private void addPlat (int x, int y, int width, int height){	
	
		
		
	platlist.add (new Rectangle ((int)(x*keepWidth*reSizer),(int)(keepHeight*reSizer*y),(int)(width*keepWidth*reSizer), 
			(int)(keepHeight*reSizer*height)));	
	}
    
	private void noWebPlatSetup() {
		if (!webImport) {
	addPlat((int)(0), (int) (500), 
			(int) (1000), (int) (500));
	addPlat((int) (250),(int) ((500-35)),
			(int) (100),(int) (10));
	addPlat((int) (120), (int) ((500-70)), 
			(int) (100),(int) (10));
	addPlat((int) (250),(int) ((500-35*3)),
			(int) (100),(int) (10));
	dogLogic.addGameCharacter (new HairClipNPC(dogLogic), 150, 50);
	dogLogic.addGameCharacter (new HairClipNPC(dogLogic), 300, 100);
	dogLogic.addGameCharacter(new HairClipNPC(dogLogic), 500, 300);
	dogLogic.addGameCharacter(new GoalNPC(dogLogic), 700, 400);
	dogLogic.addGameCharacter(new GooseNPC(dogLogic), 800, 400);
	addYLimiter();
	dogLogic.addGameCharacter (new Mochi(dogLogic), 0, 0);
		}
	}
	
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
		for (int i = 0; i<platlist.size(); i++) {
			platlist.get(i).width = (int)(platlist.get(i).width*reSizer*keepWidth);
			platlist.get(i).height= (int)(platlist.get(i).height*reSizer*keepHeight);
			platlist.get(i).x =  (int)(platlist.get(i).x*reSizer*keepWidth);
			platlist.get(i).y = (int) (platlist.get(i).y*reSizer*keepHeight);
		}
		
		System.out.println(platlist.get(0));
	}
	
	public void addHairClipNPC (int x, int y) {
		dogLogic.addGameCharacter (new HairClipNPC(dogLogic), x, y);
	}
	
	public void addGoalNPC (int x, int y) {
		dogLogic.addGameCharacter(new GoalNPC (dogLogic), x, y);
	}
	
	public void addGooseNPC (int x, int y) {
		dogLogic.addGameCharacter(new GooseNPC(dogLogic), x, y);
	}
	
	public void addYLimiter () {
		dogLogic.addGameCharacter(new YLimiter(dogLogic), 10000, 10000);
	}
	
	//currently this always has to be called last
	public void addMochi(int x, int y) {
		dogLogic.addGameCharacter(new Mochi(dogLogic), x, y);
	}
	
	// cheap fix for concurrent thread error:
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

