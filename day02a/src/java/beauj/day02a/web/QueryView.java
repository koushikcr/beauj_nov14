package beauj.day02a.web;

import beauj.day02a.model.Client;
import beauj.day02a.model.Order;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
@Named
public class QueryView implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext private EntityManager em;

	private Integer queryId;
	private Client client;

	List<Order> orders = null;

	public Integer getQueryId() {
		return queryId;
	}
	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String searchOrders() {
		client = em.find(Client.class, queryId);
		if (null == client) {
			FacesMessage msg = new FacesMessage("Cannot find client " + queryId);
			FacesContext.getCurrentInstance()
					.addMessage(null, msg);
			return (null);
		}

		orders = client.getOrders();

		return ("orders");

	}

	public void search2() {

		client = em.find(Client.class, queryId);

		if (null == client) {
			FacesMessage msg = new FacesMessage("Cannot find client " + queryId);
			FacesContext.getCurrentInstance()
					.addMessage(null, msg);
			return;
		}

		queryId = null;
		orders = client.getOrders();
	}

	public String search() {

		if (queryId == null)
			return ("");

		client = em.find(Client.class, queryId);
		if (null == client) {
			FacesMessage msg = new FacesMessage("Cannot find client " + queryId);
			FacesContext.getCurrentInstance()
					.addMessage(null, msg);
			return (null);
		}

		System.out.println(">>> client: " + client);
		queryId = null;

		return ("customer");
	}

	
}
