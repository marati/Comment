import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewComments extends Forwarder {
    
    private Connection con;
    
    public ViewComments()
    {
        con = Connect.getConnection();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        if (request.getParameter("append") != null)
        {
            java.sql.Date startDate = null;
            java.sql.Date endDate = null;
            try {
                SimpleDateFormat shortFormat = new SimpleDateFormat("dd.MM.yyyy");
                
                Date startDateParam = shortFormat.parse(request.getParameter("start_date"));
                Date endDateParam = shortFormat.parse(request.getParameter("end_date"));
                
                startDate = new java.sql.Date(startDateParam.getTime());
                endDate = new java.sql.Date(endDateParam.getTime());
            } catch (ParseException ex) {
            }
            
            try {
                String sql = "SELECT * FROM Comments WHERE date BETWEEN (?) AND (?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                
                List comments = new ArrayList();
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    Comment comment = new Comment();
                    comment.setId(rs.getInt("id"));
                    comment.setDate(rs.getDate("date"));
                    comment.setText(rs.getString("text"));
                    comments.add(comment);
                }
                
                request.setAttribute("comments", comments);
            } catch (SQLException sqlExc) {
                sqlExc.printStackTrace();
            }
            
            this.forward("/viewComments.jsp", request, response);
        }
    }
}