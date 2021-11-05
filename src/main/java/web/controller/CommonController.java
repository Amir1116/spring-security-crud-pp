package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.services.RoleService;
import web.services.UserService;

import javax.enterprise.inject.Model;

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

    @PostMapping("/register/user/")
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

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id")int id, ModelMap model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        User userOut = userService.getUser(id);
        userOut.setEmail(user.getEmail());
        userOut.setName(user.getName());
        userOut.setLastName(user.getLastName());
        userService.updateUser(user);
        return "redirect:/";
    }
}
