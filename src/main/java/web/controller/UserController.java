package web.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import web.model.User;
import web.services.UserService;

import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String mainPage(){
        return "index";
    }

    @GetMapping("/admin/allusers")
    public String printUsers(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id")int id,ModelMap model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user,@PathVariable("id") int id){
        System.out.println(user);
        userService.updateUser(id,user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String showUser(@PathVariable("id") int id,ModelMap model){
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUserPage(ModelMap model){
        User user = new User();
        model.addAttribute("user",user);
        return "new";
    }

    @PostMapping("/new/user")
    public String newUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/";
    }

}
