package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public String printUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    @GetMapping("/{username}")
    public String userPrivate(@PathVariable("username") String username, ModelMap model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "adminprivate";
    }

    @GetMapping("/content")
    public String adminContent(){
        return "admincontent";
    }
}
