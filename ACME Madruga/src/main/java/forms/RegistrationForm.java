
package forms;

import java.util.Collection;

import security.Authority;

public class RegistrationForm {

	//Atributos de Actor
	private String					name;
	private String					middleName;
	private String					surname;
	private String					photo;
	private String					email;
	private String					phoneNumber;
	private String					address;

	//Atributos de UserAccount
	private String					username;
	private String					password;
	private Collection<Authority>	authorities;

	//Atributos de Brotherhood
	private String					title;
	private Collection<String>		pictures;
	//No es necesario, lo setearemos en el reconstruct como default
	//private Collection<Member>		members;

	//Double check
	private String					repeatPassword;

	//Terms and Conditions
	private boolean					termAndConditions;


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

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public Collection<String> getPictures() {
		return this.pictures;
	}

	public void setPictures(final Collection<String> pictures) {
		this.pictures = pictures;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Collection<Authority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(final Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getRepeatPassword() {
		return this.repeatPassword;
	}

	public void setRepeatPassword(final String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public boolean isTermAndConditions() {
		return this.termAndConditions;
	}

	public void setTermAndConditions(final boolean termAndConditions) {
		this.termAndConditions = termAndConditions;
	}

}
