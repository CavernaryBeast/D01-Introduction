
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Enrollment;

@Component
@Transactional
public class EnrollmentToStringConverter implements Converter<Enrollment, String> {

	@Override
	public String convert(final Enrollment enroll) {

		String res;
		if (enroll == null)
			res = null;
		else
			res = String.valueOf(enroll.getId());
		return res;
	}

}
