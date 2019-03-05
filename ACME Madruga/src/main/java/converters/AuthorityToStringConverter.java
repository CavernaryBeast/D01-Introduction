
package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class AuthorityToStringConverter implements Converter<Authority, String> {

	@Override
	public String convert(final Authority auth) {

		String res;
		StringBuilder builder;

		if (auth == null)
			res = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(auth.getAuthority(), "UTF-8"));
				res = builder.toString();
				System.out.println(res);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		return res;
	}

}
