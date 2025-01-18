package auth;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reg")
public class registerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER="com.mysql.cj.jdbc.Driver";
	 public static String  URL="jdbc:mysql://localhost:3306/servletFormDB";
	 public static String  USERNAME="root";
	 public static String  PASSWORD="dbPassCode";
	 Connection connection;

    public registerUser() {
        super();
       
    }

	public void init(ServletConfig config) throws ServletException {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String uname = req.getParameter("uname");
		String pword = req.getParameter("pword");
		
		try {
			PreparedStatement ps = connection.prepareStatement("insert into formTable values (?, ?, ?, ?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uname);
			ps.setString(4, pword);
			ps.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			pw.println("<html><body bgcolor=black text=white><center>");
			pw.println("<h2>registration Successfully...</h2>");
			pw.println("<a href=login.html>Login</a>");
			pw.println("</center></body><html>");
			
;		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}







