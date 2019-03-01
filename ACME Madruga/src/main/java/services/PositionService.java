
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import domain.Administrator;
import domain.Enrollment;
import domain.Position;

@Service
@Transactional
public class PositionService {

	@Autowired
	private PositionRepository		positionRepository;

	@Autowired
	private AdministratorService	administratorService;
	
	@Autowired
	private EnrollmentService enrollmentService;


	public Position create() {

		final String positionEs = "";
		final String positionEn = "";

		final Position res = new Position();
		res.setPositionEs(positionEs);
		res.setPositionEn(positionEn);

		return res;
	}

	public Position findOne(final int id) {

		Assert.isTrue(id != 0);
		final Position res = this.positionRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Position> findAll() {

		final Collection<Position> res = this.positionRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Position save(final Position pos) {

		Assert.notNull(pos);
		final Administrator principal = this.administratorService.findByPrincipal();

		return this.positionRepository.save(pos);
	}
	
	public void delete(Position pos){
		
		Assert.notNull(pos);
		Assert.isTrue(pos.getId()!=0);
		Administrator principal = this.administratorService.findByPrincipal();
		
		Collection<Enrollment> enrollments = this.enrollmentService.findAll();
		for(Enrollment enroll : enrollments){
			Assert.isTrue(!enroll.getPosition().equals(pos));
		}
		this.positionRepository.delete(pos);
	}

}
