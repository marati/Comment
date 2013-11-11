import java.sql.*;
import java.util.Properties;

public class Connect {
    
    private static Connection con = null;
    
    public static Connection getConnection()
    {
        if (con != null)
            return con;
        else
        {
            try {
                String driver = "com.mysql.jdbc.Driver";
                Class.forName(driver);
                Properties properties=new Properties();
                properties.setProperty("user","root");
                properties.setProperty("password","root");
                properties.setProperty("useUnicode","true");
                properties.setProperty("characterEncoding","UTF-8");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/comments",
                        properties);
            } catch (ClassNotFoundException nfexc) {
                System.out.println(nfexc);
            } catch (SQLException sqlexc) {
                System.out.println(sqlexc);
            }
            return con;
        }
    }
    
}
