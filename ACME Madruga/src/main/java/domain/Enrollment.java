
package domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Past;

public class Enrollment extends DomainEntity {

	//Atributos de clase
	private Date		moment;
	private boolean		valid;

	//Atributos asociativos
	private Position	position;


	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public boolean isValid() {
		return this.valid;
	}

	public void setValid(final boolean valid) {
		this.valid = valid;
	}

	@Valid
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

}
