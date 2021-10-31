package web.controller;

import web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;

import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserDao userDao;

    @GetMapping()
    public String printWelcome(ModelMap model) {
//        List<User> users = userDao.listUsers();
//        model.addAttribute("users",users);
        return "index";
    }
}
