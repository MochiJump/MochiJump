import java.util.*;

// I'm going to try to fit this Jpanel and MySQL connection into one class

public class LevelSelector extends JPanel{
  
  private dbName; // names database
  private dbPassword; // password for Database
  private dbUserName; // username for Database
  private dbLocation; //
  private Action SelectorRight;
  private Action SelectorLeft;
  private Action SelectorUp;
  private Action SelectorDown;

  // this class needs to house a GUI for selecting levels based off of what is available on the SQL database
  // will probably require JPanel extention for the graphical component of level selection
  
  //https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
  
  //https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
  public void dbConnection{
    
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(dbUserName);
    dataSource.setPassword(dbPassword);
    dataSource.setServerName();// i.e. localhost
    dataSource.setDatabaseName(dbName);
    Connection conn = dataSource.getConnection();
  
  //https://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html
  
  /** need to use SQL command:
  * USE dbName
  * GO
  * SELECT *
  * From sys.Tables
  * GO
  */
  
  // okay the result from the above needs to go into an arraylist. then using length of the arraylist & a random number generator
  // we can pick four levels to randomly display in four imageicons displayed in a 2x2 grid.
    
  }
// will include jlabel based keybindings here:
  public JLabel kInputs{
    JLabel SLabel = new JLabel ("MAKE A SELECTION");
    // key bindings go here:
    SelectorRight SelectorRight = new SelectorRight();
    SelectorLeft SelectorLeft = new SelectorLeft();
    SelectorUp SelectorUp = new SelectorUp();
    SelectorDown SelectorDown = new SelectorDown();
    
    InputMap im = SLabel.getInputMap(Jcomponent.WHEN_IN_FOCUSED_WINDOW);
    ActionMap am = SLabel.getActionMap();
    
    im.put(KeyStroke.getKeyStroke("Right"), "SelectorRight");
    am.put("SelectorRight"; SelectorRight);
    im.put (KeyStroke.getKeyStroke("Left"), "SelectorLeft");
    am.put("SelectorLeft"; SelectorLeft);
    im.put(KeyStroke.getKeyStroke("Up"), "SelectorUp");
    am.put("SelectorUp", SelectorUp);
    im.put(KeyStroke.getKeyStroke("Down"), "SelectorDown");
    am.put("SelectorDown". SelectorDown);
    
    
    return SLabel;
  }
  
  class SelectorRight extends AbstractAction{
    public void actionPerformed (ActionEvent sr){
      //keybinding action go here: 2x2 grid so simple if statement will suffice, but need to make Icons first.
    }
  }
    class SelectorLeft extends AbstractAction{
    public void actionPerformed (ActionEvent sl){
      //keybinding action go here
    }
  }
    class SelectorUp extends AbstractAction{
    public void actionPerformed (ActionEvent su){
      //keybinding action go here
    }
  }
    class SelectorDown extends AbstractAction{
    public void actionPerformed (ActionEvent sd){
      //keybinding action go here
    }
  }
  
  // okay what I actually need here is a 2x2 grid of boxes, once the size of those boxes is determined then I need to import Rect from
  // DB and then scale the rect sizes to the sizes of the boxes. Layout can be continued to be built w/o DB connection and rects for
  // now.
  
  /* Y Layout 0-15% Header
  * 15.125% -56.125% Top Grid
  * 58.375% - 98.375% Bottom Grid
  */
  
  /* X Layout 5%-45% Left Grid
  * 55%-95% Right Grid
  */
}
