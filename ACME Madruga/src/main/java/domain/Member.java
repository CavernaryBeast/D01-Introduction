
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	//Atributos de asociación
	private Collection<RequestToMarch>	requests;


	@Valid
	@OneToMany
	public Collection<RequestToMarch> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<RequestToMarch> requests) {
		this.requests = requests;
	}

}
