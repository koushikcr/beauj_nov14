package beauj.day03.rest;

import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloResource {

	@GET
	public Response greetings() {

		String hello = "Hello. The current time is " + new Date();

		return (Response.ok()
				.header("Content-Type", "text/plain")
				.entity(hello)
				.build());

	}
	
}
