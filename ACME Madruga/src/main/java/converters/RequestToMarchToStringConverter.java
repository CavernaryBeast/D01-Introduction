
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.RequestToMarch;

@Component
@Transactional
public class RequestToMarchToStringConverter implements Converter<RequestToMarch, String> {

	@Override
	public String convert(final RequestToMarch req) {

		String res;
		if (req == null)
			res = null;
		else
			res = String.valueOf(req.getId());
		return res;
	}

}
