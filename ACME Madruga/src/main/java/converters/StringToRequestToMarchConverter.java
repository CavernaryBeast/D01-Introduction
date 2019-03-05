
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RequestToMarchRepository;
import domain.RequestToMarch;

@Component
@Transactional
public class StringToRequestToMarchConverter implements Converter<String, RequestToMarch> {

	@Autowired
	private RequestToMarchRepository	requestToMarchRepository;


	@Override
	public RequestToMarch convert(final String text) {

		RequestToMarch res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.requestToMarchRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
