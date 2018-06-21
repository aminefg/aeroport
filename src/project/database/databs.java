
package project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class databs {
    
    private static final String url = "jdbc:mysql://localhost:3306/databasename?useSSL=false";    
    private static final String driverName = "com.mysql.jdbc.Driver";   
    private static final String username = "root";   
    private static final String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);

            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return con;
    }
    public ResultSet ExecuteAct(String sql)throws SQLException{  
        databs con = new databs();
                Statement stmt=con.getConnection().createStatement();
                 try {
                      ResultSet rs=stmt.executeQuery(sql);
                      System.out.println("Query Executed");
                      return rs;

            } catch (SQLException ex) {   
                System.out.println("Failed to execute"); 
             return null;
            }
}
    public int UpdateAct(String sql) throws SQLException
    {
                databs con = new databs();
                Statement stmt=con.getConnection().createStatement();
                int i=stmt.executeUpdate(sql);
                return i;
    }
    
}
