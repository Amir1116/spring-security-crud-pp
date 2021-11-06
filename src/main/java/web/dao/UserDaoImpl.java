package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDaoImpl(){}

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public List<User> listUsers() {
       return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser( User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        entityManager.merge(user);
    }

    @Override
    public User getUser(String name) {
        String username = name;
        Query query = entityManager.createQuery("FROM User u where u.username = :username",User.class);
        User user = (User) query.setParameter("username", name).getSingleResult();
        return user;
    }
}

