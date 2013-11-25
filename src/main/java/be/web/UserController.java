package be.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.domain.User;
import be.service.UserService;
import be.validator.VersionableValidator;
import be.web.framework.annotation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private VersionableValidator versionableValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.setValidator(this.versionableValidator);

    }

    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {
	LOGGER.info("Listing");
	model.addAttribute("users", this.userService.findAll());
	return "user/list";
    }

    @RequestMapping(value = "/edit.html")
    public String edit(@ModelAttribute User user, ModelMap model) {
	model.put("user", user);
	return "user/edit";
    }

    @RequestMapping(value = "/delete.html")
    public String delete(@ModelAttribute User user, ModelMap model) {
	this.userService.delete(user);
	return "user/list";
    }

    @RequestMapping(value = "/add.html")
    public String add(ModelMap model) {
	model.put("user", new User());
	return "user/edit";
    }

    @RequestMapping(value = "/save.html", method = RequestMethod.POST)
    public String save(
	    @Valid @ModelAttribute User user,
	    @Valid @RequestParam(value = "fakeUserParam", required = false) User fakeUser,
	    ModelMap model) {
	LOGGER.info("Saving user {}", user);
	this.userService.save(user);
	return "redirect:/user/list.html";
    }

    @RequestMapping(value = "/shit.html", method = RequestMethod.GET)
    public String shit(
	    @RequestParam(value = "fakeUserParam", required = false) User fakeUser) {

	return "redirect:/user/list.html";

    }
}
