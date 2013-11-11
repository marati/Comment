import java.sql.*;

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
                String url = "jdbc:mysql://localhost:3306/comments";
                String user = "root";
                String pass = "root";
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException nfexc) {
                System.out.println(nfexc);
            } catch (SQLException sqlexc) {
                System.out.println(sqlexc);
            }
            return con;
        }
    }
    
}
