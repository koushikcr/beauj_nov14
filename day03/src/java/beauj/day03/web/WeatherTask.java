package beauj.day03.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class WeatherTask implements Runnable, AsyncListener  {

	private String city;
	private AsyncContext ctx;

	public WeatherTask(String city, AsyncContext ctx) {
		this.city = city;
		this.ctx = ctx;
	}

	@Override
	public void run() {

		System.out.println(">>> running asynchronusly: " + city);

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather");
		target = target.queryParam("q", city);
		target = target.queryParam("appid", "_YOUR_KEY_HERE_");

		//Sets Accept: application/json
		Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

		Response response = inv.get(Response.class);
		System.out.println(">>> code: " + response.getStatus());
		String data = response.readEntity(String.class);

		/*
		JsonObject theWeather = inv.get(JsonObject.class);
		JsonArray arr = theWeather.getJsonArray("weather");
		StringBuilder sb = new StringBuilder();
		for (Object o: arr) {
			JsonObject obj = (JsonObject)o;
			sb.append(obj.getString("main"))
					.append("-- ")
					.append(obj.getString("description"));

		} */

		//Get back the resp from async context
		HttpServletRequest req = (HttpServletRequest)ctx.getRequest();
		HttpServletResponse resp = (HttpServletResponse)ctx.getResponse();

		resp.setStatus(200);
		resp.setContentType("application/json");
		try (PrintWriter pw = resp.getWriter()) {
			//pw.println("City: " + city);
			pw.println(data.toString());
		} catch (IOException ex) { 
			ex.printStackTrace();
		}

		//Resume suspended request
		ctx.complete();
		System.out.println(">>> resuming request");
	}

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println(">>> completed !!!!");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
	}

	
	
}
