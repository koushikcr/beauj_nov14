package beauj.day01.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items = new LinkedList<>();

	@PostConstruct
	private void start() { System.out.println(">>> creating cart"); }

	@PreDestroy
	private void end() { System.out.println(">>> destroying cart"); }

	public void add(Item i) {
		items.add(i);
	}

	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	
}
