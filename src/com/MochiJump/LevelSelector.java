/*
* Define and use factories for this since there's no way of knowing how many levels are going to import
*/

package com.MochiJump;


public class LevelSelector extends JPanel{
  JPanel lSPanel = new JPanel();
  
// will need to create and import images for ui
// http://www.java-gaming.org/index.php/topic,13599.0
// https://stackoverflow.com/questions/43205942/how-to-display-zoom-cubes-in-3d-in-javas-frames-panel

 public LevelSelector(){
   // to add keybinding lSPanel.add(componentwithkeybindingassociated);
   setupSelection();
   add(lSPanel);
   
 }
  public void setupSelection(){
    
  }
  
}
