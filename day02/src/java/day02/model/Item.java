package day02.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Item {

	@Inject private Cart cart;

	private String name;
	private Integer quantity;

	// Alt-Ins getter/setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item createCopy() {
		Item i = new Item();
		i.name = name;
		i.quantity = quantity;
		return (i);
	}

	public void addToCart() {

		System.out.println(">> name: " + name);
		System.out.println(">> quantity: " + quantity);

		cart.add(this.createCopy());

		quantity = null;
		name = null;
	}
}
