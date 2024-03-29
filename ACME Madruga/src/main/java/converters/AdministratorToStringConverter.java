
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;

@Component
@Transactional
public class AdministratorToStringConverter implements Converter<Administrator, String> {

	@Override
	public String convert(final Administrator administrator) {
		String res;
		if (administrator == null)
			res = null;
		else
			res = String.valueOf(administrator.getId());
		return res;
	}

}
