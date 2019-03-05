
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ProcessionPositionRepository;
import domain.ProcessionPosition;

@Component
@Transactional
public class StringToProcessionPositionConverter implements Converter<String, ProcessionPosition> {

	@Autowired
	private ProcessionPositionRepository	processionPositionRepository;


	@Override
	public ProcessionPosition convert(final String text) {

		ProcessionPosition res;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.processionPositionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
