
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionPositionRepository;
import domain.Brotherhood;
import domain.ProcessionPosition;

@Service
@Transactional
public class ProcessionPositionService {

	@Autowired
	private ProcessionPositionRepository	processionPositionRepository;

	@Autowired
	private BrotherhoodService				brotherhoodService;


	public ProcessionPosition create(final int row, final int column) {

		final ProcessionPosition res = new ProcessionPosition();
		res.setRowPosition(row);
		res.setColumnPosition(column);

		return res;
	}

	public Collection<ProcessionPosition> findAll() {

		final Collection<ProcessionPosition> res = this.processionPositionRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public ProcessionPosition findOne(final int id) {

		Assert.isTrue(id != 0);
		final ProcessionPosition res = this.processionPositionRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public ProcessionPosition save(final ProcessionPosition proPos) {

		Assert.notNull(proPos);
		ProcessionPosition saved;
		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		saved = this.processionPositionRepository.save(proPos);

		return saved;
	}

	public void delete(final ProcessionPosition proPos) {

		Assert.notNull(proPos);
		Assert.isTrue(proPos.getId() != 0);

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		this.processionPositionRepository.delete(proPos);
	}

	public Collection<ProcessionPosition> findByProcessionId(final int processionId) {

		Assert.isTrue(processionId != 0);
		final Collection<ProcessionPosition> allProPos = this.processionPositionRepository.findByProcessionId(processionId);
		Assert.notEmpty(allProPos);

		return allProPos;
	}

}
