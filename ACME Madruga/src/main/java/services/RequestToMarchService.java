
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestToMarchRepository;
import domain.Brotherhood;
import domain.Member;
import domain.Procession;
import domain.ProcessionPosition;
import domain.RequestToMarch;

@Service
@Transactional
public class RequestToMarchService {

	@Autowired
	private RequestToMarchRepository	requestToMarchRepository;

	@Autowired
	private ProcessionService			processionService;

	@Autowired
	private MemberService				memberService;

	@Autowired
	private BrotherhoodService			brotherhoodService;

	@Autowired
	private ProcessionPositionService	processionPositionService;


	public RequestToMarch create(final int processionId) {

		Assert.isTrue(processionId != 0);
		final Procession pro = this.processionService.findOne(processionId);

		final RequestToMarch request = new RequestToMarch();
		request.setProcession(pro);

		final String status = "PENDING";
		request.setStatus(status);

		final String reason = "";
		request.setReason(reason);

		request.setProcessionPosition(null);

		return request;
	}

	public Collection<RequestToMarch> findAll() {

		final Collection<RequestToMarch> res = this.requestToMarchRepository.findAll();
		Assert.notEmpty(res);

		return res;
	}

	public RequestToMarch findOne(final int id) {

		Assert.isTrue(id != 0);
		final RequestToMarch request = this.requestToMarchRepository.findOne(id);
		Assert.notNull(request);

		return request;
	}

	public RequestToMarch save(final RequestToMarch request) {

		Assert.notNull(request);
		RequestToMarch saved;

		final Member principal = this.memberService.findByPrincipal();
		Assert.isTrue(request.getStatus() == "PENDING");

		saved = this.requestToMarchRepository.save(request);
		principal.getRequests().add(saved);
		this.memberService.updateAssociates(principal);

		return saved;
	}

	public RequestToMarch approveAndAssign(final int requestId, final int row, final int column) {

		final Brotherhood principal = this.brotherhoodService.findByPrincipal();

		final RequestToMarch request = this.findOne(requestId);
		Assert.isTrue(request.getProcession().getBrotherhood().equals(principal));
		RequestToMarch saved;

		request.setStatus("APPROVED");

		final ProcessionPosition proPos = this.processionPositionService.create(row, column);

		final Collection<ProcessionPosition> allProPos = this.processionPositionService.findByProcessionId(request.getProcession().getId());
		for (final ProcessionPosition aux : allProPos)
			Assert.isTrue(!proPos.equals(aux), "The position selected is occupied");

		final ProcessionPosition savedProPos = this.processionPositionService.save(proPos);
		request.setProcessionPosition(savedProPos);

		saved = this.requestToMarchRepository.save(request);

		return saved;
	}

	public RequestToMarch reject(final int requestId, final String reason) {

		final Brotherhood principal = this.brotherhoodService.findByPrincipal();

		Assert.isTrue(reason.trim().length() > 0);

		final RequestToMarch request = this.findOne(requestId);
		Assert.isTrue(request.getProcession().getBrotherhood().equals(principal));
		Assert.isTrue(request.getProcessionPosition().equals(null));
		final RequestToMarch saved;

		request.setStatus("REJECTED");
		request.setReason(reason);

		saved = this.requestToMarchRepository.save(request);
		return saved;
	}

	public void delete(final RequestToMarch request) {

		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);
		Assert.isTrue(request.getStatus() == "PENDING");

		final Member principal = this.memberService.findByPrincipal();
		Assert.isTrue(principal.getRequests().contains(request));

		this.requestToMarchRepository.delete(request);
	}

	public Collection<RequestToMarch> findByProcessionId(final int processionId) {

		Assert.isTrue(processionId != 0);
		final Collection<RequestToMarch> requests = this.requestToMarchRepository.findByProcessionId(processionId);
		Assert.notEmpty(requests);

		return requests;
	}

}
