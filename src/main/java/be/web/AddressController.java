package be.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import be.domain.Address;
import be.domain.User;
import be.service.UserService;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add.html")
    public String add(@ModelAttribute
    Address address, @RequestParam("user")
    User user) {
        user.addaddress(address);
        this.userService.saveMe(user);
        return "redirect:/user/edit.html?user=" + user.getId();
    }

    @RequestMapping(value = "/edit.html")
    public String edit(@ModelAttribute
    Address address, @RequestParam("user")
    User user, ModelMap model) {
        model.put("address", address);
        model.put("userId", user.getId());
        return "/address/edit";
    }

    @RequestMapping(value = "save.html")
    public String save(@ModelAttribute
    Address address, @RequestParam("user")
    User user) {
        this.userService.saveMe(user);
        return "redirect:/user/edit.html?user=" + user.getId();
    }

    @RequestMapping(value = "delete.html")
    public String delete(@ModelAttribute
    Address address, @RequestParam("user")
    User user) {
        user.getAddresses().remove(address);
        this.userService.delete(address);
        return "redirect:/user/edit.html?user=" + user.getId();
    }
}
