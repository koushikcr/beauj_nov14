package beauj.day03.web;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	ScheduledFuture future = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>> application starting !!!!");

		SEAWeatherTask task = new SEAWeatherTask();

		future = service.scheduleWithFixedDelay(task, 3, 5, TimeUnit.SECONDS);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(">>> application stopping !!!!");
		future.cancel(true);
	}

	
}
