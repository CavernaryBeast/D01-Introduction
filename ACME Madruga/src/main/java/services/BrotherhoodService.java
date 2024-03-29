
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Administrator;
import domain.Brotherhood;
import domain.Member;
import forms.RegistrationForm;
import repositories.BrotherhoodRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class BrotherhoodService {

	@Autowired
	private BrotherhoodRepository	brotherhoodRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private Validator				validator;


	public Brotherhood create() {

		final Brotherhood bro = new Brotherhood();
		//this.actorService.setNewActor(Authority.BROTHERHOOD, bro);

		final Collection<String> defaultPictures = new ArrayList<>();
		bro.setPictures(defaultPictures);

		final Collection<Member> defaultMembers = new ArrayList<>();
		bro.setMembers(defaultMembers);

		//Default moment to pass the binding
		final Date establishmentDate = new Date(System.currentTimeMillis() - 100);
		bro.setEstablishmentDate(establishmentDate);

		return bro;
	}

	public Collection<Brotherhood> findAll() {
		final Collection<Brotherhood> res = this.brotherhoodRepository.findAll();
		Assert.notEmpty(res);
		return res;
	}

	public Brotherhood findOne(final int id) {
		Assert.isTrue(id != 0);
		final Brotherhood res = this.brotherhoodRepository.findOne(id);
		Assert.notNull(res);

		return res;
	}

	public Brotherhood findByUserAccountId(final int id) {
		Assert.isTrue(id != 0);
		final Brotherhood res = this.brotherhoodRepository.findByUserAccountId(id);
		Assert.notNull(res);

		return res;
	}

	public Brotherhood findByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Brotherhood res = this.findByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		final boolean bool = this.actorService.checkAuthority(res, Authority.BROTHERHOOD);
		Assert.isTrue(bool);

		return res;
	}

	public boolean exists(final int id) {
		return this.brotherhoodRepository.exists(id);
	}

	public Brotherhood save(final Brotherhood bro) {

		Assert.notNull(bro);
		Brotherhood saved;

		if (bro.getId() == 0) {

			//Default moment to pass the binding
			final Date establishmentDate = new Date(System.currentTimeMillis() - 100);
			bro.setEstablishmentDate(establishmentDate);

			saved = this.brotherhoodRepository.save(bro);
		} else
			saved = (Brotherhood) this.actorService.update(bro);
		return saved;
	}

	public Brotherhood updateAssociates(final Brotherhood bro) {

		Assert.isTrue(bro.getId() != 0);
		final Brotherhood principal = this.findByPrincipal();

		return this.brotherhoodRepository.save(bro);
	}

	//Brotherhoods a las que pertenece un Member
	public Collection<Brotherhood> findBelonging(final int id) {

		Assert.isTrue(id != 0);
		final Collection<Brotherhood> res = this.brotherhoodRepository.findBelonging(id);
		Assert.notEmpty(res);

		return res;
	}

	//Brotherhoods a las que ha pertenecido un Member
	public Collection<Brotherhood> findHasBelonged(final int id) {

		Assert.isTrue(id != 0);
		final Collection<Brotherhood> res = this.brotherhoodRepository.findHasBelonged(id);
		Assert.notEmpty(res);

		return res;
	}

	public Collection<Double> findAvgMinMaxStdDev() {

		final Administrator principal = this.administratorService.findByPrincipal();
		final Collection<Double> res = this.brotherhoodRepository.findAvgMinMaxStdDev();
		Assert.notNull(res);

		return res;
	}

	public Brotherhood findLargestBrotherhood() {

		final Integer aux = this.brotherhoodRepository.findAuxLargestBrotherhood();
		Assert.notNull(aux);
		final Brotherhood bro = this.brotherhoodRepository.findLargestBrotherhood(aux);
		Assert.notNull(bro);

		return bro;
	}

	public Brotherhood findSmallestBrotherhood() {

		final Integer aux = this.brotherhoodRepository.findAuxSmallestBrotherhood();
		Assert.notNull(aux);
		final Brotherhood bro = this.brotherhoodRepository.findSmallestBrotherhood(aux);
		Assert.notNull(bro);

		return bro;
	}

	public Brotherhood reconstruct(final RegistrationForm form, final BindingResult binding) {

		final Brotherhood bro = this.create();
		final UserAccount userAccount = new UserAccount();

		//Setteos de los distintos atributos y comprobacion de que se ha chequeado la doble pass y los Terms&&Conditions
		Assert.isTrue(form.getPassword() == form.getRepeatPassword(), "The passwords entered are not the same");
		Assert.isTrue(form.isTermAndConditions(), "The Terms&&Conditions hasn't been accepted");

		bro.setName(form.getName());
		bro.setMiddleName(form.getMiddleName());
		bro.setSurname(form.getSurname());
		bro.setPhoto(form.getPhoto());
		bro.setEmail(form.getEmail());
		bro.setPhoneNumber(form.getPhoneNumber());
		bro.setAddress(form.getAddress());

		userAccount.setUsername(form.getUsername());
		userAccount.setPassword(form.getPassword());
		userAccount.setAuthorities(form.getAuthorities());

		bro.setUserAccount(userAccount);

		bro.setTitle(form.getTitle());
		bro.setPictures(form.getPictures());

		this.validator.validate(bro, binding);

		return bro;
	}

}
