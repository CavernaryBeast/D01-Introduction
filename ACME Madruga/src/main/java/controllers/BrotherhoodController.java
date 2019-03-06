
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Brotherhood;
import forms.RegistrationForm;
import security.Authority;
import services.BrotherhoodService;
import services.ConfigurationParametersService;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	@Autowired
	private BrotherhoodService				brotherhoodService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;

		final Collection<Authority> authorities = new ArrayList<>();

		final Authority auth = new Authority();
		auth.setAuthority(Authority.BROTHERHOOD);

		if (!authorities.contains(auth))
			authorities.add(auth);

		final RegistrationForm form = new RegistrationForm();
		form.setAuthorities(authorities);

		res = this.createEditModelAndView(form);

		return res;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {

		ModelAndView res;
		Brotherhood bro = this.brotherhoodService.findByPrincipal();

		bro = this.brotherhoodService.findOne(bro.getId());
		Assert.notNull(bro);
		res = this.createEditModelAndView(bro);

		return res;
	}

	// Registration -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView register(final RegistrationForm form, final BindingResult binding) {

		ModelAndView res;
		final Brotherhood bro;

		bro = this.brotherhoodService.reconstruct(form, binding);

		if (binding.hasErrors())
			res = this.createEditModelAndView(form);
		else
			try {

				this.brotherhoodService.save(bro);
				res = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.find().getBanner();
				res.addObject("banner", banner);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(form, "actor.commit.error");
			}
		return res;

	}

	// Update -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView update(@ModelAttribute("actor") @Valid final Brotherhood bro, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(bro);
		else
			try {
				this.brotherhoodService.save(bro);
				res = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.find().getBanner();
				res.addObject("banner", banner);

			} catch (final Throwable oops) {
				res = this.createEditModelAndView(bro, "actor.commit.error");
			}
		return res;

	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Brotherhood bro) {

		ModelAndView res;

		res = this.createEditModelAndView(bro, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Brotherhood bro, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("actor/edit");
		res.addObject("actor", bro);
		res.addObject("role", "brotherhood");
		res.addObject("roleurl", "brotherhood");
		res.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.find().getBanner();
		res.addObject("banner", banner);

		return res;
	}

	//ModelAndView of the RegistrationForm

	protected ModelAndView createEditModelAndView(final RegistrationForm form) {

		ModelAndView res;

		res = this.createEditModelAndView(form, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final RegistrationForm form, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("actor/edit");
		res.addObject("form", form);
		res.addObject("role", "brotherhood");
		res.addObject("roleurl", "brotherhood");
		res.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.find().getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
