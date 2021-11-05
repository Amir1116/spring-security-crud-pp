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

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String userPrivate(@PathVariable("username") String username, ModelMap model){
        User user = userService.getUser(username);
        model.addAttribute("user", user);
        return "userprivate";
    }

    @GetMapping("/content")
    public String userShow(){
        return "usercontent";
    }

}
