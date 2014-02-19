package be.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.domain.Address;
import be.domain.User;
import be.domain.UserCommand;
import be.service.UserService;
import be.web.framework.annotation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //    @Autowired
    //    private VersionableValidator versionableValidator;
    //    @Autowired
    //    private VersionableCommandValidator versionableCommandValidator;
    //    @InitBinder
    //    public void initBinder(WebDataBinder binder) {
    //        ComposedValidator composedValidator = new ComposedValidator();
    //        composedValidator.addValidator(this.versionableValidator);
    //        composedValidator.addValidator(this.versionableCommandValidator);
    //        binder.setValidator(composedValidator);
    //    }
    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {
        LOGGER.info("Listing");
        model.addAttribute("users", this.userService.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/edit.html")
    public String edit(@ModelAttribute
    User user, ModelMap model) {
        model.put("user", user);
        model.put("address", new Address());
        return "user/edit";
    }

    @RequestMapping(value = "/editCommand.html")
    public String editCommand(@RequestParam
    Long userId, ModelMap model) {
        User user = this.userService.findById(userId);
        UserCommand command = new UserCommand();
        command.setUser(user);
        command.setVersion(user.getVersion());
        command.setEntityId(user.getId());
        model.put("command", command);
        return "user/editCommand";
    }

    @RequestMapping(value = "/delete.html")
    public String delete(@ModelAttribute
    User user, ModelMap model) {
        this.userService.delete(user);
        return "user/list";
    }

    @RequestMapping(value = "/add.html")
    public String add(ModelMap model) {
        model.put("user", new User());
        model.put("address", new Address());
        return "user/edit";
    }

    @RequestMapping(value = "/save.html", method = RequestMethod.POST)
    public String save(@Valid
    @ModelAttribute
    User user, ModelMap model) {
        LOGGER.info("Saving user {}", user);
        // do some useless shit
        if (user.getId() != null) {
            User strange = this.userService.findById(27L);
            if (user.getNickname() == null) {
                user.setNickname("a");
            }
            user.setNickname("" + (user.getNickname().charAt(0) + 1));
        }
        if (user.getAge() > 15) {
            this.userService.saveMe(user);
        }
        return "redirect:/user/list.html";
    }

    @RequestMapping(value = "/saveCommand.html", method = RequestMethod.POST)
    public String saveCommand(@Valid
    @ModelAttribute
    UserCommand userCommand, ModelMap model) {
        LOGGER.info("Saving user {}", userCommand);
        User user = this.userService.findById(userCommand.getEntityId());
        user.setFirstname(userCommand.getUser().getFirstname());
        user.setLastname(userCommand.getUser().getLastname());
        user.setAge(userCommand.getUser().getAge());
        this.userService.saveMe(user);
        return "redirect:/user/list.html";
    }

    @RequestMapping(value = "/shit.html", method = RequestMethod.GET)
    public String shit(@RequestParam(value = "fakeUserParam", required = false)
    User fakeUser) {
        return "redirect:/user/list.html";
    }
}
