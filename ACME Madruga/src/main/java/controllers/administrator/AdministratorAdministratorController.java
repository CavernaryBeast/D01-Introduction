
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.AdministratorService;
import services.ConfigurationParametersService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView res;
		Administrator administrator;

		administrator = this.administratorService.create();
		res = this.createEditModelAndView(administrator);
		return res;
	}

	// Edition --------------------------------------------------------

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update() {

		ModelAndView res;
		Administrator administrator = this.administratorService.findByPrincipal();

		administrator = this.administratorService.findOne(administrator.getId());
		Assert.notNull(administrator);
		res = this.createEditModelAndView(administrator);

		return res;
	}

	// Save -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actor") @Valid final Administrator administrator, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(administrator);
		else
			try {
				final Administrator saved = this.administratorService.save(administrator);
				if (administrator.getId() == 0) {
					res = new ModelAndView("redirect:/actor/createAdmin.do?actorId=" + saved.getId());
					final String banner = this.configurationParametersService.find().getBanner();
					res.addObject("banner", banner);
				} else {
					res = new ModelAndView("redirect:/");
					final String banner = this.configurationParametersService.find().getBanner();
					res.addObject("banner", banner);
				}
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(administrator, "actor.commit.error");
			}
		return res;

	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Administrator administrator) {
		ModelAndView res;

		res = this.createEditModelAndView(administrator, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Administrator administrator, final String messageCode) {
		ModelAndView res;
		final UserAccount userAccount = administrator.getUserAccount();

		res = new ModelAndView("actor/edit");
		res.addObject("actor", administrator);
		res.addObject("role", "administrator");
		res.addObject("roleurl", "administrator/administrator");
		res.addObject("requestURI", "actor/edit.do");
		res.addObject("userAccount", userAccount);
		res.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.find().getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
