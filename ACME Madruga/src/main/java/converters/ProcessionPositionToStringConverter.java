
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ProcessionPosition;

@Component
@Transactional
public class ProcessionPositionToStringConverter implements Converter<ProcessionPosition, String> {

	@Override
	public String convert(final ProcessionPosition proPos) {

		String res;
		if (proPos == null)
			res = null;
		else
			res = String.valueOf(proPos.getId());
		return res;
	}

}
