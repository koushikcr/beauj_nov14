package beauj.day01.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*", 
		dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST })
public class LoggingFilter implements Filter {

	@Inject private Cart cart;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		long start = System.currentTimeMillis();
		HttpServletRequest httpReq = (HttpServletRequest)request;

		System.out.println(">>> url: " + httpReq.getRequestURI());
		System.out.println("\tbefore items in cart: " + cart.getItems().size());

		//Incoming request
		chain.doFilter(request, response);

		//Outgoing response
		System.out.println("\tafter items in cart: " + cart.getItems().size());
		System.out.println(">>> process time: " + (System.currentTimeMillis() - start));
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override
	public void destroy() { }
	
}
