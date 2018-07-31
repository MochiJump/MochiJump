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
	
	private ArrayList<Integer> hairClipStartX;
	
	private ArrayList<Integer> hairClipStartY;
	
	private int mochiStartX;
	
	private int mochiStartY;
	
	
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
	
	
	public ArrayList<Integer> getHairClipStartX(){
		return hairClipStartX;
	}
	
	public void setHairClipStartX(ArrayList<Integer> hairClipStartX) {
		this.hairClipStartX = hairClipStartX;
	}
	
	public ArrayList<Integer> getHairClipStartY(){
		return hairClipStartY;
	}
	
	public void setHairClipStartY(ArrayList<Integer> hairClipStartY) {
		this.hairClipStartY = hairClipStartY;
	}
	
	public int getMochiStartX() {
		return mochiStartX;
	}
	
	public void setMochiStartX (int mochiStartX) {
		this.mochiStartX = mochiStartX;
	}
	
	public int getMochiStartY() {
		return mochiStartY;
	}
	public void setMochiStartY(int mochiStartY) {
		this.mochiStartY = mochiStartY;
	}
	
	//this will define the ArrayList for the rectangles
	
	public void testGenerateLevel (){
		
		for (int i=0; i<getStartX().size(); i++) {
			platforms.add(new Rectangle ((int)(getStartX().get(i)), (int)(getStartY().get(i)), (int)(getWidth().get(i)), (int)(getHeight().get(i))));
		}
		System.out.println(platforms);
	}
	
	public ArrayList<Rectangle> generateLevel (){
		
		for (int i=0; i<startX.size(); i++) {
			platforms.add(new Rectangle ((int)(startX.get(i)), (int)(startY.get(i)), (int)(width.get(i)), (int)(height.get(i))));
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