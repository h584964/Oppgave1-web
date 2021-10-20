package oppg1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Innlogging
 */
@WebServlet(name = "Min magiske Servlet", urlPatterns = "/innlogging" )
public class Innlogging extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private Cookie cookie;

   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passord = request.getParameter("passord");
		response.setContentType("text/html; charset=ISO-8859-1");
		
		String feilMelding ="";
		
		String feilmelding = "";
    	String feilkode = request.getParameter("feilkode");
    	if (feilkode != null && feilkode.equals("invalidPassword")) {
    		feilmelding = "Ugyldig Passord. Prøv igjen.";
    	} 
        
        response.setContentType("text/html; charset=ISO-8859-1");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"ISO-8859-1\">");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        
       
        out.println(feilmelding); 

        out.println("<form action=\"" + "innlogging" + "\" method=\"post\">");
		out.println("    <p>Passord: <input type=\"password\" name=\"passord\" /></p>");
		out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
    }
		
		
//		if(!InnlogginUtil.isInnlogget(request)) {
//			
////			out.println("Yoooooooo NIsse");
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<meta charset=\"ISO-8859-1\">");
//			out.println("</head>");
//			out.println("<body>");
//			
//			out.println("<h1>Min handleliste</h1>");
//			out.println("<form action=\"" + "innlogging" + "\" method=\"post\">");
//			out.println("<input type=\"text\" name=\"vare\" />");
//			out.println("<input type=\"submit\" value=\"Legg til\" />");;
//			out.println("</form>");
//			out.println("</body>");
//			out.println("</html>");
//		
//			
//			
//		
//			
//		
//		}else {
//			out.println("<!DOCTYPE html>");
//			out.println("<html>");
//			out.println("<head>");
//			out.println("<meta charset=\"ISO-8859-1\">");
//			out.println("</head>");
//			out.println("<body>");
//			out.println("<form action=\"" + "innlogging" + "\" method=\"post\">");
//			out.println("    <p>Passord: <input type=\"password\" name=\"passord\" /></p>");
//			out.println("    <p><input type=\"submit\" value=\"Logg inn\" /></p>");
//			out.println("</form>");
//			out.println("</body>");
//			out.println("</html>");
//			
//		}
//			
	


	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passord = request.getParameter("passord");
		String riktigPassord = getServletConfig().getInitParameter("passord");
	  
		
		if(!InnlogginUtil.isGyldigPassord(passord, riktigPassord)) {
			
			response.sendRedirect("innlogging" + "?feilkode=invalidPassword");
		}else {
			InnlogginUtil.isGyldigPassord(passord, riktigPassord);
			InnlogginUtil.loggInnMedTimeout(request, 10);
			response.sendRedirect("handleliste");
		}
//		
		
		
		;
//
//		
//		
//		
		}
	
	
	}	


