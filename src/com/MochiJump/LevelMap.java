package com.MochiJump;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LevelMap extends JPanel {

	// should just make this a method I can all (Interface?)
	double keepHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight()/786;
	double keepWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1336;
	double reSizer = 1;	

	int x, y, width, height;	
	
	
	private ArrayList<Rectangle> platlist = new ArrayList<>();
	
	private void addPlat (int x, int y, int width, int height){	
	
		
		
	platlist.add (new Rectangle (x,y,width,height));	
	}
    
	private void platSetup() {
	addPlat((int)(0*keepWidth*reSizer), (int) (keepHeight*reSizer*500), 
			(int) (keepWidth*reSizer*1000), (int) (keepHeight*reSizer*500));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	addPlat((int) (reSizer*keepWidth*120), (int) (keepHeight*reSizer*(500-70)), 
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer* 10));
	addPlat((int) (reSizer*keepWidth*250),(int) (keepHeight*reSizer*(500-35*3)),
			(int) (keepWidth*reSizer*100),(int) (keepHeight*reSizer*10));
	}

	public ArrayList<Rectangle> getPlat(){
		platSetup();
		return this.platlist;
	}
}


