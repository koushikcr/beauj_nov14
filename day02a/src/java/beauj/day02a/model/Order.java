package beauj.day02a.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "oid")
	private String orderId;

	private String item;

	@ManyToOne
	@JoinColumn(name = "cid", referencedColumnName = "cid")
	private Client customer;

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public Client getCustomer() {
		return customer;
	}
	public void setCustomer(Client customer) {
		this.customer = customer;
	}
	
}
