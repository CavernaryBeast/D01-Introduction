
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

	@Override
	public Authority convert(final String text) {
		Authority result;
		String parts[];

		if (text == null)
			result = null;
		else
			try {
				parts = text.split("\\|");
				result = new Authority();
				if (URLDecoder.decode(parts[0], "UTF-8").equals("ADMINISTRATOR"))
					result.setAuthority(Authority.ADMINISTRATOR);
				else if (URLDecoder.decode(parts[0], "UTF-8").equals("BROTHERHOOD"))
					result.setAuthority(Authority.BROTHERHOOD);
				else if (URLDecoder.decode(parts[0], "UTF-8").equals("MEMBER"))
					result.setAuthority(Authority.MEMBER);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return result;
	}

}
