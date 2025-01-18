package auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class loginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loginUser() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
//		To establish the connection from the jdbc
		DatabaseConnection.getConnection();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		To fetch the queries from the client
		String uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		try {
			
//			To run the sql query
			PreparedStatement ps = 
					DatabaseConnection.getConnection().prepareStatement("select * from formTable where uname=?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			
//			To print the output 
			PrintWriter pw = response.getWriter();
			pw.println("<html><body><center>");
			if(rs.next()) 
			{
				pw.println("Welcome : "+uname);
			}
			else {
				pw.println("User Not Valid");
			}
			pw.println("</center></body><html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
