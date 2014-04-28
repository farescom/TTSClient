import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLiteHelper {
	
	private String databaseFile;
	private Connection c;
	
	public SQLiteHelper(String databaseFile)
	{
		this.databaseFile = databaseFile;
		System.out.println(exists());
	}
	
	private Connection getConnection()
	{
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
	      c.setAutoCommit(false);
	      
	    } catch ( Exception e ) {
	    	//e.printStackTrace();
	    }
	    return c;
	}
	
	private boolean exists() {
		Connection c = getConnection();
		if (c != null) {
			Statement stmt = null;
			ResultSet rs;
			try {
				stmt = c.createStatement();
				rs = stmt.executeQuery( "SELECT * FROM CONFIG;" );
				while ( rs.next() ) {
					int id = rs.getInt("id");
			        String  name = rs.getString("name");
			        int age  = rs.getInt("age");
			        String  address = rs.getString("address");
			        float salary = rs.getFloat("salary");
			        System.out.println( "ID = " + id );
			        System.out.println( "NAME = " + name );
			        System.out.println( "AGE = " + age );
			        System.out.println( "ADDRESS = " + address );
			        System.out.println( "SALARY = " + salary );
			        System.out.println();
			    }
				rs.close();
				stmt.close();
				c.close();
				return true;
			} catch (SQLException e) {
				//e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	private boolean createTables() {
		Connection c = getConnection();
		if (c != null) {
			Statement stmt = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:test.db");
		      System.out.println("Opened database successfully");

		      stmt = c.createStatement();
		      String sql = "CREATE TABLE COMPANY " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " AGE            INT     NOT NULL, " + 
		                   " ADDRESS        CHAR(50), " + 
		                   " SALARY         REAL)"; 
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Table created successfully");
		    return true;
		} else {
			return false;
		}
	}
}
