
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	//Atributos de asociación
	private List<RequestToMarch>	requests;


	@Valid
	@OneToMany
	public List<RequestToMarch> getRequests() {
		return this.requests;
	}

	public void setRequests(final List<RequestToMarch> requests) {
		this.requests = requests;
	}

}
