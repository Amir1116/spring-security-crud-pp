package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);

    List<User> getUsersList();

    void deleteUser(int id);

    User getUser(int id);

    void updateUser(User user);

    User getUser(String name);
}
