package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> listUsers();
    void deleteUser(int id);
    User getUser(int id);
    void updateUser(int id, User user);
    User getUser(String name);
}
