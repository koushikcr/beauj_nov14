package beauj.day03.web;

import java.util.Date;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SEAWeatherTask implements Runnable {

	private static final String[] CITIES = {"singapore", "kuala lumpur", "bangkok" };

	@Override
	public void run() {
		System.out.println(">>> time: " + new Date());
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://api.openweathermap.org/data/2.5/weather");

		for (String c: CITIES) {
			target = target.queryParam("q", c);
			target = target.queryParam("appid", "_YOUR_KEY_HERE_");

			//Sets Accept: application/json
			Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

			Response response = inv.get(Response.class);
			System.out.println(">>> code: " + response.getStatus());
			String data = response.readEntity(String.class);

			System.out.println(">>> data: " + data);
		}
	}


	
}
