import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import JavaPackages.DBUtilities;
import JavaPackages.Pagination;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class Picture_servlet extends HttpServlet {

	private static final long serialVersionUID = -7137276633333215190L;
    private Object st;
/*
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
                    {

		//response.setContentType("text/html;charset=utf-8");
                        
		doPost(request,response);
	}
*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
                    {
   


                        
final String dbURL = "jdbc:mysql://localhost:3306/toywebproject";
    final String dbUser = "Sowmiyaa";
    final String dbPass = "Soso777!!!";

    Connection conn = null;
    Statement stmt = null;
   byte[] imgData=null;
    try {
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        System.out.println("db connected");
        stmt = (Statement) conn.createStatement();

        ResultSet rs1;
                String pr_id = request.getParameter("pr_id");
                System.out.println("SELECT `PD_ProductImage` FROM `product_details` where `PD_ProductID`= '"+pr_id+"';");

                rs1 = stmt.executeQuery("SELECT `PD_ProductImage` FROM `product_details` where `PD_ProductID`= '"+pr_id+"';");
                 
        
        if (rs1.next()){
            imgData = rs1.getBytes("PD_ProductImage");//Here r1.getBytes() extract byte data from resultSet 
            System.out.println(imgData);
            response.setHeader("expires", "0");
            response.setContentType("image/jpg");
            //request.setAttribute("img", imgData);
            OutputStream os = response.getOutputStream(); // output with the help of outputStream 
            os.write(imgData);
            os.flush();
            os.close();
        }
    }catch (SQLException ex) {
        // String message = "ERROR: " + ex.getMessage();
        ex.printStackTrace();
    } finally {
        if (conn != null) {
            // closes the database connection
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    request.setAttribute("img", imgData);
    request.getRequestDispatcher("Image.jsp").forward(request, response);
}    

}

            
         