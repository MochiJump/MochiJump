package com.MochiJump;

class LSOFactory {

  public LevelSelectorObject makeLSO (string levelName, int importOrder){
  return new Imported (levelName, importOrder);
  }

}
