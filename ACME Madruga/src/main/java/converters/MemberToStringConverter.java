
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Member;

@Component
@Transactional
public class MemberToStringConverter implements Converter<Member, String> {

	@Override
	public String convert(final Member mem) {

		String res;
		if (mem == null)
			res = null;
		else
			res = String.valueOf(mem.getId());
		return res;
	}

}
