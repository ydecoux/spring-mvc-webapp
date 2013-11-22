package be.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.domain.User;
import be.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {
	LOGGER.info("Listing");
	model.addAttribute("users", this.userService.findAll());
	return "user/list";
    }

    @RequestMapping(value = "/{id}/edit.html")
    public String edit(@PathVariable Long id, ModelMap model) {
	User user = this.userService.findById(id);
	model.put("user", user);
	return "user/edit";
    }

    @RequestMapping(value = "/save.html", method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
	LOGGER.info("Saving user {}", user);
	this.userService.save(user);
	return "redirect:/user/list.html";
    }
}
