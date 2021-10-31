package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> listUsers();
    void updateUser(long id);
    void deleteUser(long id);
    User getUser(long id);
}
