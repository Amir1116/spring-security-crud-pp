package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.services.RoleService;
import web.services.UserService;

import javax.enterprise.inject.Model;
import java.net.Authenticator;

@Controller
public class CommonController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public CommonController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping()
    public String mainPage(){
        return "index";
    }

    @GetMapping("/register")
    public String newUserPage(ModelMap model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/register/user")
    public String newUser(@ModelAttribute("user") User user){
        Role role = roleService.getRole("USER");
        role.addUserToRolen(user);
        user.addRole(role);
        user.setEnabled(1);
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String perfomLogout(){
        return "redirect:/";
    }

    @GetMapping("/private/page")
    public String privatePageLink(Authentication authentication,ModelMap model){
        User user = userService.getUser(authentication.getName());
        System.out.println("User id admin"+ user.isAdmin());
        System.out.println(authentication.getName());
        model.addAttribute("user",user);
        if(user.isAdmin()){
            return "adminprivate";
        }else{
            return "userprivate";
        }
    }
}
