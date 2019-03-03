
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	administratorRepository;

	@Autowired
	private ActorService			actorService;


	public Administrator create() {
		final Administrator res = new Administrator();

		this.actorService.setNewActor(Authority.ADMINISTRATOR, res);

		return res;
	}

	public Administrator findOne(final int id) {
		Assert.isTrue(id != 0);
		final Administrator res = this.administratorRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(final Administrator a) {
		Assert.notNull(a);
		Administrator res;
		if (a.getId() == 0)
			res = this.administratorRepository.save(a);
		else
			res = (Administrator) this.actorService.update(a);

		return res;
	}

	public Administrator findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Administrator res = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		final boolean bool = this.actorService.checkAuthority(res, Authority.ADMINISTRATOR);
		Assert.isTrue(bool);

		return res;
	}

	public Administrator findByUserAccountId(final int id) {
		Assert.isTrue(id != 0);
		final Administrator res = this.administratorRepository.findByUserAccountId(id);
		Assert.notNull(res);
		return res;
	}

}
