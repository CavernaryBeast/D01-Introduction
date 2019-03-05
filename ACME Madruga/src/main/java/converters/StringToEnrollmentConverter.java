
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.EnrollmentRepository;
import domain.Enrollment;

@Component
@Transactional
public class StringToEnrollmentConverter implements Converter<String, Enrollment> {

	@Autowired
	private EnrollmentRepository	enrollmentRepository;


	@Override
	public Enrollment convert(final String text) {

		Enrollment res;
		final int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.enrollmentRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}
