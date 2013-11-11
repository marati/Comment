import java.io.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddComment extends Forwarder {
    
    private Connection con;
    
    public AddComment()
    {
        con = Connect.getConnection();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("CP1251"); 

        if (request.getParameter("append") != null)
        {
            java.sql.Date sqlDate = null;
            try {
                String dateStr = request.getParameter("date");
                SimpleDateFormat shortFormat = new SimpleDateFormat("dd.MM.yyyy");
                Date date = shortFormat.parse(dateStr);
                sqlDate = new java.sql.Date(date.getTime());
                
            } catch (ParseException ex) {
            }
            
            String text = request.getParameter("text");
            
            try {
                String sql = "INSERT INTO Comments (date, text)"+
                        "VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, sqlDate);
                ps.setString(2, text);
                ps.executeUpdate();
            } catch (SQLException sqlExc) {
                sqlExc.printStackTrace();
            }
            
            this.forward("/add_more.html", request, response);
                    
        }
    }
    
}
