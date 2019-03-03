
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Brotherhood;
import domain.Procession;
import domain.RequestToMarch;

@Service
@Transactional
public class ProcessionService {

	@Autowired
	private ProcessionRepository			processionRepository;

	@Autowired
	private BrotherhoodService				brotherhoodService;

	@Autowired
	private RequestToMarchService			requestToMarchService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	public Procession create() {

		final Procession pro = new Procession();

		final Brotherhood principal = this.brotherhoodService.findByPrincipal();
		pro.setBrotherhood(principal);

		final String ticker = this.configurationParametersService.createTicker();
		pro.setTicker(ticker);

		//Default moment to pass the binding
		final Date moment = new Date(System.currentTimeMillis() - 100);
		pro.setMoment(moment);

		final String mode = "DRAFT";
		pro.setMode(mode);

		final Collection<domain.Float> floats = new ArrayList<>();
		pro.setFloats(floats);

		return pro;
	}

	public Collection<Procession> findAll() {

		final Collection<Procession> res = this.processionRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Procession findOne(final int id) {

		Assert.isTrue(id != 0);
		final Procession res = this.processionRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Procession save(final Procession pro) {

		Assert.notNull(pro);
		Procession saved;

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		Assert.isTrue(pro.getBrotherhood().equals(bro));

		final String ticker = this.configurationParametersService.createTicker();
		pro.setTicker(ticker);

		saved = this.processionRepository.save(pro);

		return saved;
	}

	public void delete(final Procession pro) {

		Assert.notNull(pro);
		Assert.isTrue(pro.getId() != 0);

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();
		Assert.isTrue(pro.getBrotherhood().equals(bro));

		final Collection<RequestToMarch> requests = this.requestToMarchService.findByProcessionId(pro.getId());
		for (final RequestToMarch request : requests)
			this.requestToMarchService.delete(request);
		this.processionRepository.delete(pro);
	}

	public Collection<Procession> findByFloatId(final int floatId) {

		Assert.isTrue(floatId != 0);

		final Collection<Procession> res = this.processionRepository.findByFloatId(floatId);
		Assert.notEmpty(res);

		return res;
	}

	public void removeFloat(final Procession pro, final domain.Float flo) {

		Assert.notNull(pro);
		Assert.isTrue(pro.getId() != 0);

		Assert.notNull(flo);
		Assert.isTrue(flo.getId() != 0);

		pro.getFloats().remove(flo);
		this.processionRepository.save(pro);
	}

}
