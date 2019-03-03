
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationParametersRepository;
import domain.Administrator;
import domain.ConfigurationParameters;

@Service
@Transactional
public class ConfigurationParametersService {

	@Autowired
	private ConfigurationParametersRepository	configurationParametersRepository;

	@Autowired
	private AdministratorService				administratorService;


	public ConfigurationParameters create() {
		return new ConfigurationParameters();
	}

	public ConfigurationParameters find() {
		final ConfigurationParameters res = (ConfigurationParameters) this.configurationParametersRepository.findAll().toArray()[0];
		Assert.notNull(res);
		return res;
	}

	public ConfigurationParameters save(final ConfigurationParameters c) {
		Assert.notNull(c);

		final Administrator principal = this.administratorService.findByPrincipal();
		System.out.println(principal.getUserAccount().getAuthorities());

		if (c.getId() == 0) {
			c.setBanner("https://tinyurl.com/acme-madruga-logo");
			//c.setCountryCode("+34");
			//c.setDefaultCountry("Spain");

			c.setMessage("Welcome to Acme Handy Worker! Price, quality, and trust in a single place");
			c.setMessageEs("Bienvenidos a Acme Handy Worker! Precio, calidad y confianza en el mismo sitio");
			c.setSysName("Acme Handy Worker");

		}
		final ConfigurationParameters result = this.configurationParametersRepository.save(c);
		Assert.notNull(result);
		System.out.println(result.getId());
		return result;

	}

	public String createTicker() {
		final DateFormat df = new SimpleDateFormat("yymmdd");
		final Calendar cal = Calendar.getInstance();

		return df.format(cal.getTime()) + "-" + RandomStringUtils.randomAlphanumeric(5).toUpperCase();
	}

}
