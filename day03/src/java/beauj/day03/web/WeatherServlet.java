package beauj.day03.web;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/weather", asyncSupported = true)
public class WeatherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String city = req.getParameter("city");

		AsyncContext ctx = req.startAsync(req, resp);

		WeatherTask task = new WeatherTask(city, ctx);
		ctx.addListener(task);

		System.out.println(">>> making task async");
		ctx.start(task);

		System.out.println(">>> exiting doGet method");
	}

	
	
}
