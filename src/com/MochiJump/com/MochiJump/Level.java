package com.MochiJump;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Level {
	
	private Integer id;
	
	private String 	levelName;
	
	private ArrayList<Integer> startX;
	
	private ArrayList<Integer> startY;
	
	private ArrayList<Integer> width;
	
	private ArrayList<Integer> height;
	
	private ArrayList<Rectangle> platforms;

	public Integer getId () {
		return id;
	}
	

	public void setId (Integer id) {
		this.id = id;
	}
	
	
	public String getlevelName() {
		return levelName;
	}
	

	public void setLevelName(String levelName) {
		this.levelName=levelName;
		System.out.println(levelName);
	}
	

	public ArrayList<Integer> getStartX() {
		return startX;
	}

	public void setStartX(ArrayList<Integer> startX) {
		this.startX = startX;
	}

	public ArrayList<Integer> getStartY() {
		return startY;
	}

	public void setStartY (ArrayList<Integer> startY) {
		this.startY = startY;
	}

	public ArrayList<Integer> getWidth () {
		return width;

	}

	public void setWidth (ArrayList<Integer> width) {
		this.width = width;
	}

	public ArrayList<Integer> getHeight() {
		return height;
	}

	public void setHeight (ArrayList<Integer> height) {
		this.height = height;
	}
	
	//this will define the ArrayList for the rectangles
	
	public ArrayList<Rectangle> generateLevel (){
		
		for (int i=0; i<startX.size(); i++) {
			platforms.add(new Rectangle (startX.get(i), startY.get(i), width.get(i), height.get(i)));
		}
		return platforms;
		
	}
	
	public ArrayList<Rectangle> getPlatforms(){
		return platforms;
	}
	
	public void setPlatforms (ArrayList<Rectangle> rects) {
		this.platforms = rects;
	}

}
