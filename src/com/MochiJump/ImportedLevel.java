package com.MochiJump

public class ImportedLevel implements LevelSelectorObject{
 protected string levelName;
 protected int importOrder;
 
 public ImportedLevel (string levelName, int importOrder){
 this.levelName = levelName;
 this.importOrder = importOrder;
 }
 
 public string getLevelName(){
 return levelName;
 }
 
 public int getImportOrder(){
 return importOrder;
 }
}
