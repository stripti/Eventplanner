package event.dbinfo;
import java.sql.*;
public class CrudOperation {
private static Connection cn1=null;
  public static Connection createConnection()
{
	try{
		Class.forName("com.mysql.jdbc.Driver");//type4
		cn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/eventplanner","root","root");
		}
catch(SQLException|ClassNotFoundException cse)
	{
	System.out.println(cse);}
	return cn1;
	}
}
