
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import domain.Brotherhood;
import domain.Procession;

@Service
@Transactional
public class FloatService {

	@Autowired
	private FloatRepository		floatRepository;

	@Autowired
	private BrotherhoodService	brotherhoodService;

	@Autowired
	private ProcessionService	processionService;


	public domain.Float create() {

		final domain.Float res = new domain.Float();

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		res.setBrotherhood(bro);
		final Collection<String> pictures = new ArrayList<>();
		res.setPictures(pictures);

		return res;
	}

	public Collection<domain.Float> findAll() {

		final Collection<domain.Float> res = this.floatRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public domain.Float findOne(final int id) {

		Assert.isTrue(id != 0);
		final domain.Float res = this.floatRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public domain.Float save(final domain.Float flo) {

		Assert.notNull(flo);
		domain.Float saved;

		saved = this.floatRepository.save(flo);

		return saved;
	}

	public void delete(final domain.Float flo) {

		Assert.notNull(flo);
		Assert.isTrue(flo.getId() != 0);

		final Collection<Procession> processions = this.processionService.findByFloatId(flo.getId());
		for (final Procession pro : processions) {
			this.processionService.removeFloat(pro, flo);
		}
		this.floatRepository.delete(flo);
	}

	public Collection<domain.Float> findByBrotherhoodId(final int brotherhoodId) {

		Assert.isTrue(brotherhoodId != 0);

		final Collection<domain.Float> res = this.floatRepository.findByBrotherhoodId(brotherhoodId);
		Assert.notEmpty(res);

		return res;
	}

}
