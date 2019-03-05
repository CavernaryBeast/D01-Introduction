
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Procession;

@Component
@Transactional
public class ProcessionToStringConverter implements Converter<Procession, String> {

	@Override
	public String convert(final Procession pro) {

		String res;
		if (pro == null)
			res = null;
		else
			res = String.valueOf(pro.getId());
		return res;
	}

}
