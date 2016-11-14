package beauj.day01.web;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Item {

	private String name;
	private Integer quantity;

	//Default constructor
	public Item() { }

	public Item(String i, Integer q) {
		name = i;
		quantity = q;
	}

	@PostConstruct
	private void start() {
		System.out.println(">>> creating item"); 
	}

	@PreDestroy
	private void end() {
		System.out.println(">>> destroying item");
	}

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
	
}
