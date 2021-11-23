package web.services;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;


public interface UserService {
    void save(User user);

    List<User> getUsersList();

    void deleteUser(int id);

    User getUser(int id);

    void updateUser(User user, String role, int id);

    User getUser(String name);
}
