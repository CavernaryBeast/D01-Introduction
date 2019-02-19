
package domain;

import org.hibernate.validator.constraints.NotBlank;

public class Position extends DomainEntity {

	//Atributos de clase
	private String	positionEs;
	private String	positionEn;


	@NotBlank
	public String getPositionEs() {
		return this.positionEs;
	}

	public void setPositionEs(final String positionEs) {
		this.positionEs = positionEs;
	}

	@NotBlank
	public String getPositionEn() {
		return this.positionEn;
	}

	public void setPositionEn(final String positionEn) {
		this.positionEn = positionEn;
	}

}
