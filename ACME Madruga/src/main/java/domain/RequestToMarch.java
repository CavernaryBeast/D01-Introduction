
package domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class RequestToMarch extends DomainEntity {

	//Atributos de clase
	private String		status;
	private Integer		rowPosition;
	private Integer		columnPosition;
	private String		reason;

	//Atributos asociativos
	private Member		member;
	private Procession	procession;


	@NotBlank
	@Pattern(regexp = "^(PENDING|APPROVED|REJECTED)$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Integer getRowPosition() {
		return this.rowPosition;
	}

	public void setRowPosition(final Integer rowPosition) {
		this.rowPosition = rowPosition;
	}

	public Integer getColumnPosition() {
		return this.columnPosition;
	}

	public void setColumnPosition(final Integer columnPosition) {
		this.columnPosition = columnPosition;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	@Valid
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
	}

	@Valid
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

}
