package beauj.day01.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

	//Injection point
	@Inject private Item item;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(">>> in validate");
		System.out.println(">>> item.getName: " + item.getName());
		System.out.println(">>> item.getQuantity: " + item.getQuantity());

		String i = req.getParameter("item");
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));

		if (quantity <= 0) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.setContentType("text/plain");
			try (PrintWriter pw = resp.getWriter()) {
				pw.println("Cannot have -ve quantity");
			}
			return;
		}

		item.setName(i);
		item.setQuantity(quantity);

		RequestDispatcher rd = req.getRequestDispatcher("cart");
		rd.forward(req, resp);

	}

	
	
}
