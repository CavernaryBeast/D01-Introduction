
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EnrollmentRepository;
import domain.Brotherhood;
import domain.Enrollment;
import domain.Member;
import domain.Position;

@Service
@Transactional
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository	enrollmentRepository;

	@Autowired
	private MemberService			memberService;

	@Autowired
	private BrotherhoodService		brotherhoodService;

	@Autowired
	private PositionService			positionService;


	public Enrollment create(final int brotherhoodId) {

		final Enrollment res = new Enrollment();
		final Date moment = new Date(System.currentTimeMillis() - 100);
		res.setMoment(moment);

		final Member principal = this.memberService.findByPrincipal();
		res.setMember(principal);

		final Brotherhood bro = this.brotherhoodService.findOne(brotherhoodId);
		res.setBrotherhood(bro);

		return res;
	}

	public Collection<Enrollment> findAll() {

		final Collection<Enrollment> res = this.enrollmentRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public Enrollment findOne(final int id) {

		Assert.isTrue(id != 0);
		final Enrollment res = this.enrollmentRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Collection<Enrollment> findOwn() {

		final Member principal = this.memberService.findByPrincipal();
		final Collection<Enrollment> res = this.enrollmentRepository.findOwn(principal.getUserAccount().getId());
		Assert.notEmpty(res);

		return res;
	}

	public Enrollment save(final Enrollment enroll) {

		Assert.notNull(enroll);
		Enrollment saved;
		final Member principal = this.memberService.findByPrincipal();

		final Date moment = new Date(System.currentTimeMillis() - 100);
		enroll.setMoment(moment);
		saved = this.enrollmentRepository.save(enroll);

		return saved;
	}

	public Enrollment enroll(final int enrollmentId, final int positionId) {

		Assert.isTrue(enrollmentId != 0);
		Assert.isTrue(positionId != 0);

		final Brotherhood bro = this.brotherhoodService.findByPrincipal();

		final Enrollment enroll = this.findOne(enrollmentId);

		Assert.isTrue(enroll.getBrotherhood().equals(bro));

		final Position position = this.positionService.findOne(positionId);

		enroll.setPosition(position);
		enroll.setDropOutMoment(null);

		bro.getMembers().add(enroll.getMember());
		this.brotherhoodService.updateAssociates(bro);

		return this.enrollmentRepository.save(enroll);
	}

	//Member hace el dropOut
	public Enrollment dropOut(final int enrollmentId) {

		Assert.isTrue(enrollmentId != 0);

		final Member principal = this.memberService.findByPrincipal();

		final Enrollment enroll = this.findOne(enrollmentId);
		Assert.isTrue(enroll.getMember().equals(principal));

		enroll.setPosition(null);
		final Date dropOutMoment = new Date(System.currentTimeMillis() - 100);
		enroll.setDropOutMoment(dropOutMoment);

		final Brotherhood bro = enroll.getBrotherhood();
		bro.getMembers().remove(enroll.getMember());
		this.brotherhoodService.updateAssociates(bro);

		return this.enrollmentRepository.save(enroll);
	}

	//Brotherhood removes a member
	public Enrollment remove(final int enrollmentId) {

		Assert.isTrue(enrollmentId != 0);

		final Brotherhood principal = this.brotherhoodService.findByPrincipal();

		final Enrollment enroll = this.findOne(enrollmentId);
		Assert.isTrue(enroll.getBrotherhood().equals(principal));

		enroll.setPosition(null);
		final Date dropOutMoment = new Date(System.currentTimeMillis() - 100);
		enroll.setDropOutMoment(dropOutMoment);

		principal.getMembers().remove(enroll.getMember());
		this.brotherhoodService.updateAssociates(principal);

		return this.enrollmentRepository.save(enroll);
	}

}
