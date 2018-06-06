package com.MochiJump;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LevelMap extends JPanel {

	

	int x, y, width, height;	
	
	
	private ArrayList<Rectangle> platlist = new ArrayList<>();
	
	private void addPlat (int x, int y, int width, int height){
	platlist.add (new Rectangle (x,y,width,height));	
	}
    
	private void platSetup() {
	addPlat(0, 500, 1000, 500);
	addPlat(250,500-35,100,10);
	addPlat(120, 500-70, 100, 10);
	addPlat(250,500-35*3,100,10);
	}

	public ArrayList<Rectangle> getPlat(){
		platSetup();
		return this.platlist;
	}
}


