
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class RequestToMarch extends DomainEntity {

	//Atributos de clase
	private String				status;
	private String				reason;

	//Atributos de asociación
	private Procession			procession;
	private ProcessionPosition	processionPosition;


	@NotBlank
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@NotNull
	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	@Valid
	@ManyToOne(optional = false)
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

	@Valid
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public ProcessionPosition getProcessionPosition() {
		return this.processionPosition;
	}

	public void setProcessionPosition(final ProcessionPosition processionPosition) {
		this.processionPosition = processionPosition;
	}

}
