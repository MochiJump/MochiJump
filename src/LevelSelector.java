public class LevelSelector {
  
  private dbName; // names database
  private dbPassword; // password for Database
  private dbUserName; // username for Database
  private dbLocation; // 

  // this class needs to house a GUI for selecting levels based off of what is available on the SQL database
  // will probably require JPanel extention for the graphical component of level selection
  
  //https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
  
  //https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
  
  MysqlDataSource dataSource = new MysqlDataSource();
  dataSource.setUser(dbUserName);
  dataSource.setPassword(dbPassword);
  dataSource.setDatabaseName(dbName);
  
  //https://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html
  
  /** need to use SQL command:
  * USE dbName
  * GO
  * SELECT *
  * From sys.Tables
  * GO
  */
  
}
