package beauj.day03.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String city = req.getParameter("city");

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather");
		target = target.queryParam("q", city);
		target = target.queryParam("appid", "b77e4c649af6ef5c60b57574bc00c3aa");

		//Sets Accept: application/json
		Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

		JsonObject theWeather = inv.get(JsonObject.class);
		JsonArray arr = theWeather.getJsonArray("weather");
		StringBuilder sb = new StringBuilder();
		for (Object o: arr) {
			JsonObject obj = (JsonObject)o;
			sb.append(obj.getString("main"))
					.append("-- ")
					.append(obj.getString("description"));

		}

		resp.setStatus(200);
		resp.setContentType("text/plain");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("City: " + city);
			pw.println(sb.toString());
		}
	}

	
	
}
