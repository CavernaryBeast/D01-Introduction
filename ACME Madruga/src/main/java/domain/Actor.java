
package domain;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

public class Actor extends DomainEntity {

	//Atributos de clase
	private String		name;
	private String		middleName;
	private String		surname;
	private String		photo;
	private String		email;
	private String		phoneNumber;
	private String		addres;

	//Atributos asociativos
	private UserAccount	userAccount;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@Pattern(regexp = "^\\w+([\\ \\<]+\\w+)*@+([\\w\\.\\>]*)+$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	//Pendiente de revision, ya que el phoneNumber sigue un patron concreto
	//Podria hacerse en servicios o controladores
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddres() {
		return this.addres;
	}

	public void setAddres(final String addres) {
		this.addres = addres;
	}

	@Valid
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
