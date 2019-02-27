
package security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import domain.Actor;

@Service
@Transactional
public class UserAccountService {

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private ActorService			actorService;


	public UserAccount create() {
		return new UserAccount();
	}

	public Collection<UserAccount> findAll() {
		final Collection<UserAccount> res = this.userAccountRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public UserAccount findOne(final int id) {
		Assert.isTrue(id != 0);
		final UserAccount res = this.userAccountRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public UserAccount save(final UserAccount ua) {
		Assert.notNull(ua);

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(principal.getUserAccount().equals(ua));

		return this.userAccountRepository.save(ua);
	}

	public UserAccount findByUserName(final String userName) {
		Assert.notNull(userName);

		final UserAccount res = this.userAccountRepository.findByUsername(userName);
		Assert.notNull(res);

		return res;
	}

}
