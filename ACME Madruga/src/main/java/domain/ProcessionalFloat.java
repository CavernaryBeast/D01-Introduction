
package domain;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ProcessionalFloat extends DomainEntity {

	//Atributos de clase
	private String			title;
	private String			description;
	private List<String>	pictures;

	//Atributos de asociación
	private Brotherhood		brotherhood;
	private Procession		procession;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	public List<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final List<String> pictures) {
		this.pictures = pictures;
	}

	@Valid
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	@Valid
	public Procession getProcession() {
		return this.procession;
	}

	public void setProcession(final Procession procession) {
		this.procession = procession;
	}

}
