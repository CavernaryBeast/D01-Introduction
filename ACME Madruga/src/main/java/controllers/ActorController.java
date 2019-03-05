
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import security.UserAccountService;
import services.AdministratorService;
import services.BrotherhoodService;
import services.ConfigurationParametersService;
import services.MemberService;
import domain.Administrator;
import domain.Brotherhood;
import domain.Member;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private AdministratorService			administratorService;

	@Autowired
	private BrotherhoodService				brotherhoodService;

	@Autowired
	private MemberService					memberService;

	@Autowired
	private UserAccountService				userAccountService;

	@Autowired
	private ConfigurationParametersService	configurationParametersService;


	// Sign up --------------------------------------------------------

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView signUp() {

		ModelAndView res;

		res = new ModelAndView("actor/signUp");
		res.addObject("requestURI", "actor/signUp.do");
		final String banner = this.configurationParametersService.find().getBanner();
		res.addObject("banner", banner);

		return res;
	}

	@RequestMapping(value = "/createAdmin", method = RequestMethod.GET)
	public ModelAndView createAdmin(@RequestParam final int actorId) {

		ModelAndView res;
		final Administrator admin = this.administratorService.findOne(actorId);
		final UserAccount ua = this.userAccountService.findOne(admin.getUserAccount().getId());

		res = this.createEditModelAndView(ua);

		return res;
	}

	@RequestMapping(value = "/createBrotherhood", method = RequestMethod.GET)
	public ModelAndView createBrotherhood(@RequestParam final int actorId) {

		ModelAndView res;
		final Brotherhood bro = this.brotherhoodService.findOne(actorId);
		final UserAccount ua = this.userAccountService.findOne(bro.getUserAccount().getId());

		res = this.createEditModelAndView(ua);

		return res;
	}

	@RequestMapping(value = "/createMember", method = RequestMethod.GET)
	public ModelAndView createMember(@RequestParam final int actorId) {

		ModelAndView res;
		final Member mem = this.memberService.findOne(actorId);
		final UserAccount ua = this.userAccountService.findOne(mem.getUserAccount().getId());

		res = this.createEditModelAndView(ua);

		return res;
	}

	//============================================================================================================

	@RequestMapping(value = "/signup", method = RequestMethod.POST, params = "save")
	public ModelAndView signup(@Valid final UserAccount userAccount, final BindingResult binding) {

		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(userAccount);
		else
			try {
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				final String encoded = encoder.encodePassword(userAccount.getPassword(), null);
				userAccount.setPassword(encoded);
				this.userAccountService.save(userAccount);
				res = new ModelAndView("redirect:/");
				final String banner = this.configurationParametersService.find().getBanner();
				res.addObject("banner", banner);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(userAccount, "actor.commit.error");
			}
		return res;

	}

	// Ancillary methods --------------------------------------------------------

	protected ModelAndView createEditModelAndView(final UserAccount userAccount) {

		ModelAndView res;

		res = this.createEditModelAndView(userAccount, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final UserAccount userAccount, final String messageCode) {

		ModelAndView res;

		res = new ModelAndView("userAccount/edit");
		res.addObject("userAccount", userAccount);
		res.addObject("message", messageCode);
		// the message code references an error message or null
		final String banner = this.configurationParametersService.find().getBanner();
		res.addObject("banner", banner);

		return res;
	}

}
