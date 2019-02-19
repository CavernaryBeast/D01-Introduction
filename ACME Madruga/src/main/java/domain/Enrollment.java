
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
	private Member		member;
	private Brotherhood	brotherhood;


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

	@Valid
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@Valid
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

}
