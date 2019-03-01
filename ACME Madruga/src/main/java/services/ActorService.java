
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository		actorRepository;

	@Autowired
	private UserAccountService	userAccountService;


	public Actor create() {
		return new Actor();
	}

	public Collection<Actor> findAll() {
		final Collection<Actor> res = this.actorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Actor findOne(final int id) {
		Assert.isTrue(id != 0);
		final Actor res = this.actorRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Actor update(final Actor a) {
		Assert.notNull(a);
		Assert.isTrue(a.getId() != 0);

		final Actor principal = this.findByPrincipal();

		Assert.isTrue(principal.getId() == a.getId());

		return this.actorRepository.save(a);
	}

	public Actor findByUserAccountId(final int id) {
		Assert.isTrue(id != 0);
		final Actor res = this.actorRepository.findByUserAccountId(id);
		Assert.notNull(res);
		return res;
	}

	public Actor findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		final Actor res = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);

		return res;
	}

	/**
	 * Check if a user account of an actor contains a specific authority
	 * 
	 * @param actor
	 *            Actor whose authorities will be checked
	 * @param auth
	 *            Authority you want to check
	 * 
	 * @return True if the actor authorities contains the authority passed as a parameter
	 * */
	public boolean checkAuthority(final Actor a, final String auth) {
		Assert.notNull(auth);

		final UserAccount userAccount = a.getUserAccount();
		//final Actor retrieved = this.findByUserId(userAccount.getId());
		//Assert.notNull(retrieved);

		final Collection<Authority> auths = userAccount.getAuthorities();
		Assert.notEmpty(auths);

		final Authority newAuth = new Authority();
		newAuth.setAuthority(auth);

		return auths.contains(newAuth);
	}

	/**
	 * Initialises the attributes common to every actor and assign a determined Authority
	 * 
	 * @param authority
	 *            Authority chosen
	 * @param actor
	 *            The objective actor
	 * 
	 * @return The new actor with attributes instantiated
	 * 
	 * */
	public Actor setNewActor(final String authority, final Actor actor) {

		final UserAccount userAccount = this.userAccountService.create();

		final Collection<Authority> authorities = new ArrayList<>();

		final Authority auth = new Authority();
		auth.setAuthority(authority);

		if (!authorities.contains(auth))
			authorities.add(auth);

		userAccount.setAuthorities(authorities);
		final UserAccount saved = this.userAccountService.save(userAccount);
		actor.setUserAccount(saved);

		return actor;
	}

}
