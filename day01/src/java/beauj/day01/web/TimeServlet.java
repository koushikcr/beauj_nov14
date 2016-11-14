package beauj.day01.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/time-of-day")
public class TimeServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println(">>> servlet is initializing");
	}

	@Override
	public void destroy() {
		System.out.println(">>> servlet is destroyed");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {

			pw.println("<h1>");
			pw.println("The current time is " + new Date());
			pw.println("</h1>");
		}
	}
	
}
