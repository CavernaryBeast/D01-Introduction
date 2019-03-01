
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Member;
import domain.RequestToMarch;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository	memberRepository;

	@Autowired
	private ActorService		actorService;


	public Member create() {

		final Member res = new Member();

		this.actorService.setNewActor(Authority.MEMBER, res);

		final Collection<RequestToMarch> defaultRequests = new ArrayList<>();
		res.setRequests(defaultRequests);

		return res;
	}

	public Collection<Member> findAll() {

		final Collection<Member> res = this.memberRepository.findAll();
		Assert.notEmpty(res);
		return res;
	}

	public Member findOne(final int id) {

		Assert.isTrue(id != 0);
		final Member res = this.memberRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Member findByUserAccountId(final int id) {

		Assert.isTrue(id != 0);
		final Member res = this.memberRepository.findByUserAccountId(id);
		Assert.notNull(res);
		return res;
	}

	public Member findByPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Member res = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		final boolean bool = this.actorService.checkAuthority(res, Authority.MEMBER);
		Assert.isTrue(bool);

		return res;
	}

	public Member save(final Member member) {

		Assert.notNull(member);
		Member saved;

		if (member.getId() == 0)
			saved = this.memberRepository.save(member);
		else
			saved = (Member) this.actorService.update(member);

		return saved;
	}

	public Member updateAssociates(final Member member) {

		Assert.isTrue(member.getId() != 0);

		return this.memberRepository.save(member);
	}

	public Member findByRequest(final int id) {

		Assert.isTrue(id != 0);
		final Member res = this.memberRepository.findByRequestId(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<Member> findByBrotherhoodId(final int id) {

		Assert.isTrue(id != 0);
		final Collection<Member> res = this.memberRepository.findByBrotherhoodId(id);
		Assert.notEmpty(res);

		return res;
	}

}
