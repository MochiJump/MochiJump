import java.util.*;

// I'm going to try to fit this Jpanel and MySQL connection into one class

public class LevelSelector extends JPanel{
  
  private dbName; // names database
  private dbPassword; // password for Database
  private dbUserName; // username for Database
  private dbLocation; // 

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
    
    return SLabel;
  }
  // currently thinking of a 2x2 layout for selecting layout, same concept as the level selector screen, just icons instead of button
  // icons will be created off of the underlying db they pull from, need to do the math to determine how to reduce size to fit 2x2
  // sizing.
}
