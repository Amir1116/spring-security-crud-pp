package web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import web.model.User;
import web.services.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String userPrivate(@PathVariable("username") String username, ModelMap model) {
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "userprivate";
    }

    @GetMapping("/content")
    public String userShow() {
        return "usercontent";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, ModelMap model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "useredit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @ModelAttribute("role") String role,
                         @PathVariable("id") int id, ModelMap model) {
        userService.updateUser(user, role, id);
        User userOut = userService.getUser(id);
        model.addAttribute("user", userOut);
        if (userOut.isAdmin()) {
            return "redirect:/admin/" + userOut.getUsername();
        } else {
            return "redirect:/user/" + userOut.getUsername();
        }
    }

}
