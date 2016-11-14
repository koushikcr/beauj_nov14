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
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class ShoppingCartServlet extends HttpServlet {

	@Inject private Item itemContainer;
	@Inject private Cart cart;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println(">>> checkout");

		HttpSession sess = req.getSession();
		sess.invalidate();

		req.getRequestDispatcher("index.html")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(">>> class name: " + itemContainer.getClass().getName());

		System.out.println(">>> in shopping cart");
		System.out.println(">>> item: " + itemContainer.getName());
		System.out.println(">>> quantity: " + itemContainer.getQuantity());

		cart.add(itemContainer.createCopy());

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.setContentType("text/html");

		try (PrintWriter pw = resp.getWriter()) {
			pw.print("<h1>The contents of your cart</h1>");
			pw.println("<ol>");
			for (Item i: cart.getItems())
				pw.println(String.format("<li>name = %s, quantity = %d</li>", 
						i.getName(), i.getQuantity()));
			pw.println("</ol>");
			pw.println("Click <a href='index.html'>here</a> to continue shopping<br>");
			pw.println("Click <a href='cart'>here</a> to checkout");
		}
	}
}
