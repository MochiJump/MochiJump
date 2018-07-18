package com.MochiJump;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LevelMap extends JPanel {

	//
	double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/786;
	double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	double reSizer = 1;	

	int x, y, width, height;
	static boolean webImport = false;
	
	
	private static ArrayList<Rectangle> platlist = new ArrayList<>();
	
	private void addPlat (int x, int y, int width, int height){	
	
		
		
	platlist.add (new Rectangle (x,y,width,height));	
	}
    
	private void noWebPlatSetup() {
	addPlat((int)(0*keepWidth*reSizer), (int) (keepHeight*reSizer*500), 
			(int) (keepWidth*reSizer*1000), (int) (keepHeight*reSizer*500));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	addPlat((int) (reSizer*keepWidth*120), (int) (keepHeight*reSizer*(500-70)), 
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer* 10));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35*3)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	}
	
	public void useWebImport(ArrayList<Rectangle> platforms) {
		platlist.addAll (platforms);
		webImport = true;
	}

	public ArrayList<Rectangle> getPlat(){
		if (webImport == true) {
		return this.platlist;	
		}else {
		noWebPlatSetup();
		return this.platlist;
		}
	}
}

