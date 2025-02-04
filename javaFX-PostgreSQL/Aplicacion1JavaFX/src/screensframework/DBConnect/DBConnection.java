package screensframework.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;
    private static String url = "jdbc:postgresql://localhost:5432/Inspeccion";
    private static String user = "postgres";
    private static String pass = "pgadmin";
    /*


    private static String url = "jdbc:mysql://localhost/sysventas";
    private static String user = "root";
    private static String pass = "";

     */

    public static Connection connect() throws SQLException{
	try {
            Class.forName("org.postgresql.Driver").newInstance();
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch(ClassNotFoundException cnfe) {
            System.err.println("Error: "+cnfe.getMessage());
	} catch(InstantiationException ie) {
            System.err.println("Error: "+ie.getMessage());
	} catch(IllegalAccessException iausere) {
            System.err.println("Error: "+iausere.getMessage());
	}
            conn = DriverManager.getConnection(url,user,pass);
            return conn;
	}
	
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
            connect();
            return conn;
    }
}
