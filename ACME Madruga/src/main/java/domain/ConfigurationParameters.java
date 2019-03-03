
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class ConfigurationParameters extends DomainEntity {

	private String	sysName;
	private String	banner;
	private String	message;
	private String	messageEs;
	private String	countryCode;
	private String	defaultCountry;


	@NotBlank
	public String getSysName() {
		return this.sysName;
	}

	public void setSysName(final String sysName) {
		this.sysName = sysName;
	}

	@NotBlank
	@URL
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@NotBlank
	public String getMessageEs() {
		return this.messageEs;
	}

	public void setMessageEs(final String messageEs) {
		this.messageEs = messageEs;
	}

	@NotBlank
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	@NotBlank
	public String getDefaultCountry() {
		return this.defaultCountry;
	}

	public void setDefaultCountry(final String defaultCountry) {
		this.defaultCountry = defaultCountry;
	}

}
