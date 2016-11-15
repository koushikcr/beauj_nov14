package beauj.day02a.model;

import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class Register {

	public static final String[] TOPICS = { "Football", "Gadgets", "Cars", "Fashion" };

	private String name;
	private String email;
	private Boolean newsletter;
	private List<String> interest = new LinkedList<>();

	public String[] getTopics() {
		return TOPICS;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interes) {
		this.interest = interes;
	}
	public void setTopics(String[] t) { };

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String registerMe() {
		System.out.println(">>> name: " + name);
		System.out.println(">>> email: " + email);
		System.out.println(">>> newsletter: " + newsletter);
		System.out.println(">>> interest: " + interest);

		if ("fred".equals(name)) {
			FacesMessage msg = new FacesMessage("You have previously registered");
			FacesContext.getCurrentInstance()
					.addMessage(null, msg);
			return (null);
		}

		return ("thankyou");
	}

	
}
