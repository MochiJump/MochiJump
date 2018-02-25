// I'm going to try to use a compound JPanel to hold the other two JPanels that we will switch between in this program.

// interested to see what happens with the repaint() & @Override Paint methods doing this.

// read:https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html

public class Switcher {
  DogLogic dogLogic = new DogLogic();
  StartPause startPause = new StartPause();
  
  public Switcher (){
    JPanel switcherPanel = new JPanel();
    add (switcherPanel);
    switchControl();
  }
  
  private void switchControl(){
    Thread switcherThread = new Thread(){
      public void run (){
        while (true){
          updateFlags();
          //need to be sure that a starting condition actually happens
          if (startpause.isStart == true :: startpause.isStart == false){
            startPauseRun();
          }
          else{
            mainGameRun();
          }
          
          try {
            Thread.sleep(1000/refreshRate);
          }catch(InterruptedException s) {
            System.out.println("An error has occured in the Switcher class"):
          }
        }
      }
    }; 
    switcherThread.start();
  }
  private void startPauseRun(){
    //would it be better to set this to a boolean or try running something like below:
    if (dogLogic.testPanel.isVisible()){
    // call method for removing dogPanel JPanel
      switcherPanel.getContentPane().add(startPause, BorderLayout.Center);
    }
    if (!dogLogic.testPanel.isVisible()){ // need to include && startPause Panel ! visible here
     // nothing happens 
    }
  }
  private void mainGameRun(){
    //add DogLogic Jpanel and remove startPause Jpanel
  }
  private void updateFlags(){
    // get booleans (including if panel is visibile) for thread conditions 
  }
}
